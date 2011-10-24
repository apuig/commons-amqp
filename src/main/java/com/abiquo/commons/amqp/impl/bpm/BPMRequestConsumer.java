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
package com.abiquo.commons.amqp.impl.bpm;

import java.util.Set;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.InitiatorRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConsumer;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;

public class BPMRequestConsumer extends DatacenterRequestConsumer
{
    public BPMRequestConsumer(final String datacenterId)
    {
        super(datacenterId, RequestType.BPM);
    }

    @Override
    protected void consume(final DatacenterRequest request,
        final Set<RequestBasedCallback> callbacks)
    {
        if (request instanceof ImageConverterRequest)
        {
            consume((ImageConverterRequest) request, callbacks);
        }
        else if (request instanceof StatefulDiskRequest)
        {
            consume((StatefulDiskRequest) request, callbacks);
        }
        else if (request instanceof InitiatorRequest)
        {
            consume((InitiatorRequest) request, callbacks);
        }
    }

    protected void consume(final ImageConverterRequest request,
        final Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            ImageConverterRequestCallback realCallback = (ImageConverterRequestCallback) callback;
            realCallback.convertDisk(request);
        }
    }

    protected void consume(final StatefulDiskRequest request,
        final Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            StatefulDiskRequestCallback realCallback = (StatefulDiskRequestCallback) callback;

            switch (request.getSender())
            {
                case STATEFUL:
                    realCallback.dumpDiskToVolume(request);
                    break;

                case STATEFUL_BUNDLE:
                    realCallback.dumpVolumeToDisk(request);
                    break;
            }
        }
    }

    protected void consume(final InitiatorRequest request, final Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            InitiatorRequestCallback realCallback = (InitiatorRequestCallback) callback;
            realCallback.getInitiatorIQN(request);
        }
    }
}
