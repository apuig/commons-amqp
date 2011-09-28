package com.abiquo.commons.amqp.impl.tarantino;

import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestProducer;

public class TarantinoRequestProducer extends DatacenterRequestProducer
{
    public TarantinoRequestProducer(String datacenterId)
    {
        super(datacenterId, RequestType.TARANTINO);
    }
}
