package com.abiquo.commons.amqp.impl.datacenter;

import static com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.buildJobsQueue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.abiquo.commons.amqp.consumer.BasicConsumer;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;

public abstract class DatacenterRequestConsumer extends BasicConsumer<DatacenterRequestCallback>
{
    protected Map<Class<DatacenterRequest>, Set<DatacenterRequestCallback>> callbacksMap;

    public DatacenterRequestConsumer(String id, RequestType type)
    {
        super(new DatacenterRequestConfiguration(id, type), buildJobsQueue(id, type));
        callbacksMap = new HashMap<Class<DatacenterRequest>, Set<DatacenterRequestCallback>>();
    }

    @Override
    public void addCallback(DatacenterRequestCallback callback)
    {
        throw new RuntimeException();
    }

    public void addCallback(Class<DatacenterRequest> type, DatacenterRequestCallback callback)
    {
        if (!callbacksMap.containsKey(type))
        {
            callbacksMap.put(type, new HashSet<DatacenterRequestCallback>());
        }

        callbacksMap.get(type).add(callback);
    }
}
