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
