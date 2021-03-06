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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abiquo.commons.amqp.util.ConsumerUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class QueueSubscriber<T extends BasicConsumer< ? >> extends DefaultConsumer
{
    private final static Logger LOGGER = LoggerFactory.getLogger(QueueSubscriber.class);

    private T consumer;

    public QueueSubscriber(Channel channel, T consumer)
    {
        super(channel);

        this.consumer = consumer;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
        byte[] body)
    {
        try
        {
            consumer.consume(envelope, body);
        }
        catch (Throwable t)
        {
            LOGGER.error(
                "Unhandled exception captured, trying to reject message to prevent consumer crash",
                t);

            try
            {
                ConsumerUtils.rejectMessage(getChannel(), envelope.getDeliveryTag());
            }
            catch (IOException io)
            {
                LOGGER.error("Unable to reject message", io);
            }
        }
    }
}
