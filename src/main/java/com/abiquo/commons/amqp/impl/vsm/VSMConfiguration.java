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

package com.abiquo.commons.amqp.impl.vsm;

import java.io.IOException;

import com.abiquo.commons.amqp.config.DefaultConfiguration;
import com.abiquo.commons.amqp.impl.vsm.domain.VirtualSystemEvent;
import com.abiquo.commons.amqp.serialization.JSONSerializer;
import com.rabbitmq.client.Channel;

/**
 * Common RabbitMQ Broker configuration for VSM consumer and producer.
 * 
 * @author eruiz@abiquo.com
 */
public class VSMConfiguration extends DefaultConfiguration<VirtualSystemEvent>
{
    protected static final String VSM_EXCHANGE = "abq.vsm";

    protected static final String EVENT_SYNK_QUEUE = "abq.event_synk";

    protected static final String VSM_ROUTING_KEY = "";

    public VSMConfiguration()
    {
        super(new JSONSerializer<VirtualSystemEvent>(VirtualSystemEvent.class));
    }

    @Override
    public void declareExchanges(Channel channel) throws IOException
    {
        channel.exchangeDeclare(getExchange(), FanoutExchange, Durable);
    }

    @Override
    public void declareQueues(Channel channel) throws IOException
    {
        channel.queueDeclare(getQueue(), Durable, NonExclusive, NonAutodelete, null);
        channel.queueBind(getQueue(), getExchange(), getRoutingKey());
    }

    @Override
    public String getExchange()
    {
        return VSM_EXCHANGE;
    }

    @Override
    public String getRoutingKey()
    {
        return VSM_ROUTING_KEY;
    }

    @Override
    public String getQueue()
    {
        return EVENT_SYNK_QUEUE;
    }
}
