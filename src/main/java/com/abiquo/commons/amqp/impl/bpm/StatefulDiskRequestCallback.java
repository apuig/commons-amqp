package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestCallback;

public abstract class StatefulDiskRequestCallback extends DatacenterRequestCallback
{
    public abstract void dumpDiskToVolume(StatefulDiskRequest request);

    public abstract void dumpVolumeToDisk(StatefulDiskRequest request);

    @Override
    public Class< ? extends BPMRequest> getRequestClass()
    {
        return StatefulDiskRequest.class;
    }
}
