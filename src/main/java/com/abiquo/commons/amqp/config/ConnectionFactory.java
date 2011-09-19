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

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ConnectionFactory
{
    private static ConnectionFactory singleton = null;

    private com.rabbitmq.client.ConnectionFactory factory;

    private Connection connection;

    public static ConnectionFactory getInstance()
    {
        if (singleton == null)
        {
            singleton = new ConnectionFactory();
        }

        return singleton;
    }

    private ConnectionFactory()
    {
        factory = new com.rabbitmq.client.ConnectionFactory();

        factory.setHost(DefaultConfiguration.getHost());
        factory.setPort(DefaultConfiguration.getPort());
        factory.setUsername(DefaultConfiguration.getUserName());
        factory.setPassword(DefaultConfiguration.getPassword());
        factory.setVirtualHost(DefaultConfiguration.getVirtualHost());

        connection = null;
    }

    public Channel createChannel() throws IOException
    {
        if (connection == null)
        {
            connection = factory.newConnection();
        }

        return connection.createChannel();
    }
}
