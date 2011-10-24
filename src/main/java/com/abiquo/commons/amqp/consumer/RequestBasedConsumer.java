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

import static com.abiquo.commons.amqp.util.ConsumerUtils.ackMessage;
import static com.abiquo.commons.amqp.util.ConsumerUtils.rejectMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.abiquo.commons.amqp.config.DefaultConfiguration;
import com.rabbitmq.client.Envelope;

public abstract class RequestBasedConsumer<R> extends BasicConsumer<R, RequestBasedCallback>
{
    protected Map<Class<R>, Set<RequestBasedCallback>> callbacksMap;

    public RequestBasedConsumer(DefaultConfiguration<R> configuration)
    {
        super(configuration);
        callbacksMap = new HashMap<Class<R>, Set<RequestBasedCallback>>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addCallback(RequestBasedCallback callback)
    {
        addCallback((Class<R>) callback.getRequestClass(), callback);
    }

    protected void addCallback(Class<R> type, RequestBasedCallback callback)
    {
        if (!callbacksMap.containsKey(type))
        {
            callbacksMap.put(type, new HashSet<RequestBasedCallback>());
        }

        callbacksMap.get(type).add(callback);
    }

    @Override
    public void consume(Envelope envelope, R message) throws IOException
    {
        if (message != null)
        {
            consume(message, callbacksMap.get(message.getClass()));
            ackMessage(getChannel(), envelope.getDeliveryTag());
        }
        else
        {
            rejectMessage(getChannel(), envelope.getDeliveryTag());
        }
    }

    protected abstract void consume(R request, Set<RequestBasedCallback> callbacks);
}
