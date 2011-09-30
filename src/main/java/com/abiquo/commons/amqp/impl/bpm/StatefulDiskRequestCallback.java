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
