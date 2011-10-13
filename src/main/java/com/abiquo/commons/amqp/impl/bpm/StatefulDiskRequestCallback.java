/*******************************************************************************
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
 ******************************************************************************/
package com.abiquo.commons.amqp.impl.bpm;

import java.io.IOException;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterNotificationProducer;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterNotification;

public abstract class StatefulDiskRequestCallback implements RequestBasedCallback
{
    private DatacenterNotificationProducer producer;

    public StatefulDiskRequestCallback()
    {
        this.producer = new DatacenterNotificationProducer();
    }

    public abstract void dumpDiskToVolume(StatefulDiskRequest request);

    public abstract void dumpVolumeToDisk(StatefulDiskRequest request);

    @Override
    public Class<StatefulDiskRequest> getRequestClass()
    {
        return StatefulDiskRequest.class;
    }

    protected void sendNotification(final DatacenterNotification message) throws IOException
    {
        this.producer.openChannel();
        this.producer.publish(message);
        this.producer.closeChannel();
    }
}
