/**
 * Abiquo community edition
 * cloud management application for hybrid clouds
 * Copyright (C) 2008-2010 - Abiquo Holdings S.L.
 *
 * This application is free software; you can redistribute it and/or
 * modify it under the terms of the GNU LESSER GENERAL PUBLIC
 * LICENSE as published by the Free Software Foundation under
 * version 3 of the License
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * LESSER GENERAL PUBLIC LICENSE v.3 for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package com.abiquo.commons.amqp.consumer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abiquo.commons.amqp.config.ChannelHandler;
import com.abiquo.commons.amqp.config.DefaultConfiguration;
import com.abiquo.commons.amqp.consumer.retry.DelayedRetryStrategy;
import com.abiquo.commons.amqp.serialization.Serializer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public abstract class BasicConsumer<T, C> extends ChannelHandler
{
    private final static Logger LOGGER = LoggerFactory.getLogger(BasicConsumer.class);

    protected QueueSubscriber<BasicConsumer<T, C>> consumer;

    protected Set<C> callbacks;

    protected DefaultConfiguration configuration;

    protected Class< ? extends RetryStrategy> strategyClass;

    protected Serializer<T> serializer;

    public BasicConsumer(DefaultConfiguration configuration, Serializer<T> serializer)
    {
        this.callbacks = new HashSet<C>();
        this.configuration = configuration;
        this.strategyClass = DelayedRetryStrategy.class;
        this.serializer = serializer;
    }

    public BasicConsumer(DefaultConfiguration configuration, Serializer<T> serializer,
        Class< ? extends RetryStrategy> retryStrategy)
    {
        this.callbacks = new HashSet<C>();
        this.configuration = configuration;
        this.serializer = serializer;
        this.strategyClass = retryStrategy;
    }

    public void start() throws IOException
    {
        openChannelAndConnection();
        getChannel().basicQos(getPrefetchCount());

        configuration.declareExchanges(getChannel());
        configuration.declareQueues(getChannel());

        consumer = new QueueSubscriber<BasicConsumer<T, C>>(getChannel(), this);
        getChannel().basicConsume(configuration.getQueue(), false, consumer);
    }

    public void stop() throws IOException
    {
        getChannel().basicCancel(consumer.getConsumerTag());
        closeChannelAndConnection();
    }

    public void addCallback(C callback)
    {
        callbacks.add(callback);
    }

    protected int getPrefetchCount()
    {
        return 1;
    }

    @Override
    public void shutdownCompleted(ShutdownSignalException cause)
    {
        String rabbitmqHost = DefaultConfiguration.getHost();

        LOGGER.debug(String.format("Connection lost to %s", rabbitmqHost));

        try
        {
            RetryStrategy strategy = strategyClass.newInstance();

            while (strategy.shouldRetry())
            {
                LOGGER.debug(String.format("Try to reconnect to %s", rabbitmqHost));

                try
                {
                    openChannelAndConnection();
                    start();

                    LOGGER.debug("And we are back!");
                    return;
                }
                catch (Exception e)
                {
                    continue;
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.debug("Unable to instance new retry strategy");
        }

        LOGGER.debug(String.format("Unable to reconnect to %s", rabbitmqHost));
    }

    public void consume(Envelope envelope, byte[] body) throws IOException
    {
        T message = serializer.fromByteArray(body);
        consume(envelope, message);
    }

    public abstract void consume(Envelope envelope, T message) throws IOException;
}
