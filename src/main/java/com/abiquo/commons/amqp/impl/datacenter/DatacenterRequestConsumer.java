package com.abiquo.commons.amqp.impl.datacenter;

import static com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.buildJobsQueue;
import static com.abiquo.commons.amqp.util.ConsumerUtils.ackMessage;
import static com.abiquo.commons.amqp.util.ConsumerUtils.rejectMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.abiquo.commons.amqp.consumer.BasicConsumer;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;
import com.rabbitmq.client.Envelope;

public abstract class DatacenterRequestConsumer<T extends DatacenterRequest> extends
    BasicConsumer<DatacenterRequestCallback>
{
    // TODO Generic callBack for tarantino Jobs
    protected Map<Class< ? extends DatacenterRequest>, Set<DatacenterRequestCallback>> callbacksMap;

    public DatacenterRequestConsumer(String id, RequestType type)
    {
        super(new DatacenterRequestConfiguration(id, type), buildJobsQueue(id, type));
        callbacksMap =
            new HashMap<Class< ? extends DatacenterRequest>, Set<DatacenterRequestCallback>>();
    }

    @Override
    public void addCallback(DatacenterRequestCallback callback)
    {
        addCallback(callback.getRequestClass(), callback);
    }

    protected void addCallback(Class< ? extends DatacenterRequest> type,
        DatacenterRequestCallback callback)
    {
        if (!callbacksMap.containsKey(type))
        {
            callbacksMap.put(type, new HashSet<DatacenterRequestCallback>());
        }

        callbacksMap.get(type).add(callback);
    }

    @Override
    public void consume(Envelope envelope, byte[] body) throws IOException
    {
        T request = deserializeRequest(envelope, body);

        if (request != null)
        {
            consume(request, callbacksMap.get(request.getClass()));
            ackMessage(channel, envelope.getDeliveryTag());
        }
        else
        {
            rejectMessage(channel, envelope.getDeliveryTag());
        }
    }

    protected abstract T deserializeRequest(Envelope envelope, byte[] body);

    protected abstract void consume(T request, Set<DatacenterRequestCallback> callbacks);
}
