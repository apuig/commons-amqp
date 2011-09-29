package com.abiquo.commons.amqp.impl.datacenter;

import static com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.buildJobsQueue;

import com.abiquo.commons.amqp.consumer.RequestBasedConsumer;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;

public abstract class DatacenterRequestConsumer extends RequestBasedConsumer<DatacenterRequest>
{
    public DatacenterRequestConsumer(String datacenterId, RequestType type)
    {
        super(new DatacenterRequestConfiguration(datacenterId, type), buildJobsQueue(datacenterId,
            type));
    }
}
