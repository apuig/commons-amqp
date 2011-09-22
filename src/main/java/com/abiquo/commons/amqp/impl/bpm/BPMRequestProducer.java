package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestProducer;

public class BPMRequestProducer extends DatacenterRequestProducer
{
    public BPMRequestProducer(String datacenterId)
    {
        super(datacenterId, RequestType.BPM);
    }
}
