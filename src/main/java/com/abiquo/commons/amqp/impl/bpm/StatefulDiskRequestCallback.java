package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;

public abstract class StatefulDiskRequestCallback implements RequestBasedCallback
{
    public abstract void dumpDiskToVolume(StatefulDiskRequest request);

    public abstract void dumpVolumeToDisk(StatefulDiskRequest request);

    @Override
    public Class<StatefulDiskRequest> getRequestClass()
    {
        return StatefulDiskRequest.class;
    }
}
