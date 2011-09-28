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

package com.abiquo.commons.amqp.impl.datacenter;

import static com.abiquo.commons.amqp.impl.datacenter.DatacenterResponseConfiguration.NOTIFICATIONS_QUEUE;

import java.util.Set;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.consumer.RequestBasedConsumer;
import com.abiquo.commons.amqp.impl.bpm.BPMResponseCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMResponse;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterResponse;
import com.rabbitmq.client.Envelope;

public class DatacenterResponseConsumer extends RequestBasedConsumer<DatacenterResponse>
{
    public DatacenterResponseConsumer()
    {
        super(new DatacenterResponseConfiguration(), NOTIFICATIONS_QUEUE);
    }

    @Override
    protected DatacenterResponse deserializeRequest(Envelope envelope, byte[] body)
    {
        return DatacenterResponse.fromByteArray(body);
    }

    @Override
    protected void consume(DatacenterResponse request, Set<RequestBasedCallback> callbacks)
    {
        if (request instanceof BPMResponse)
        {
            consume((BPMResponse) request, callbacks);
        }
    }

    protected void consume(BPMResponse request, Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            BPMResponseCallback realCallback = (BPMResponseCallback) callback;
            realCallback.processResponse(request);
        }
    }
}
