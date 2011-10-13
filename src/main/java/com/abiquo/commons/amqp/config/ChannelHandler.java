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
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;

public abstract class ChannelHandler implements ShutdownListener
{
    private ConnectionFactory connectionFactory;

    private Connection connection;

    private Channel channel;

    public ChannelHandler()
    {
        connectionFactory = new com.rabbitmq.client.ConnectionFactory();

        connectionFactory.setHost(DefaultConfiguration.getHost());
        connectionFactory.setPort(DefaultConfiguration.getPort());
        connectionFactory.setUsername(DefaultConfiguration.getUserName());
        connectionFactory.setPassword(DefaultConfiguration.getPassword());
        connectionFactory.setVirtualHost(DefaultConfiguration.getVirtualHost());

        connection = null;
        channel = null;
    }

    public Channel getChannel()
    {
        return channel;
    }

    protected void openChannelAndConnection() throws IOException
    {
        if (connection == null)
        {
            connection = connectionFactory.newConnection();
            connection.addShutdownListener(this);
        }
        else if (!connection.isOpen())
        {
            connection.removeShutdownListener(this);

            connection = connectionFactory.newConnection();
            connection.addShutdownListener(this);
        }

        if (channel == null)
        {
            channel = connection.createChannel();
            channel.addShutdownListener(this);
        }
        else if (!channel.isOpen())
        {
            channel.removeShutdownListener(this);

            channel = connection.createChannel();
            channel.addShutdownListener(this);
        }
    }

    protected void closeChannelAndConnection() throws IOException
    {
        if (channel != null && channel.isOpen())
        {
            channel.removeShutdownListener(this);
            channel.close();
        }

        if (connection != null && connection.isOpen())
        {
            connection.removeShutdownListener(this);
            connection.close();
        }
    }
}
