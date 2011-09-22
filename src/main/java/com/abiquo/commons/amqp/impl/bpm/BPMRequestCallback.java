package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestCallback;

public interface BPMRequestCallback extends DatacenterRequestCallback
{
    public void convertDisk(ImageConverterRequest request);

    public void dumpDiskToVolume(StatefulDiskRequest request);

    public void dumpVolumeToDisk(StatefulDiskRequest request);
}
