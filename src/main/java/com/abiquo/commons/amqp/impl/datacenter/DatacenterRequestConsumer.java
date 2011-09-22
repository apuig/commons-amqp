package com.abiquo.commons.amqp.impl.datacenter;

import static com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.buildJobsQueue;

import com.abiquo.commons.amqp.consumer.BasicConsumer;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;

public abstract class DatacenterRequestConsumer extends BasicConsumer<DatacenterRequestCallback>
{
    public DatacenterRequestConsumer(String id, RequestType type)
    {
        super(new DatacenterRequestConfiguration(id, type), buildJobsQueue(id, type));
    }
}
