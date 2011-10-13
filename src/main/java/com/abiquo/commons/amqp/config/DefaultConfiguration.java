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

package com.abiquo.commons.amqp.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;

/**
 * Generic broker configuration, each module configuration must extend this class and fill the
 * abstract methods.
 * 
 * @author eruiz@abiquo.com
 */
public abstract class DefaultConfiguration
{
    /** Logger **/
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultConfiguration.class);

    /** Constants **/
    protected final String FanoutExchange = "fanout";

    protected final String DirectExchange = "direct";

    protected final String TopicExchange = "topic";

    protected final boolean Durable = true;

    protected final boolean NonDurable = false;

    protected final boolean Exclusive = true;

    protected final boolean NonExclusive = false;

    protected final boolean Autodelete = true;

    protected final boolean NonAutodelete = false;

    public abstract void declareExchanges(Channel channel) throws IOException;

    public abstract void declareQueues(Channel channel) throws IOException;

    public static String getHost()
    {
        return System.getProperty("abiquo.rabbitmq.host", "localhost");
    }

    public static int getPort()
    {
        return Integer.parseInt(System.getProperty("abiquo.rabbitmq.port", "5672"));
    }

    public static String getUserName()
    {
        return System.getProperty("abiquo.rabbitmq.username", "guest");
    }

    public static String getPassword()
    {
        return System.getProperty("abiquo.rabbitmq.password", "guest");
    }

    public static String getVirtualHost()
    {
        return System.getProperty("abiquo.rabbitmq.virtualHost", "/");
    }

    protected DefaultConfiguration()
    {
        LOGGER.debug(String.format("RabbitMQ configuration. Host: %s, port: %d, username: %s",
            getHost(), getPort(), getUserName()));
    }
}
