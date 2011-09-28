package com.abiquo.commons.amqp.consumer;

import static com.abiquo.commons.amqp.util.ConsumerUtils.ackMessage;
import static com.abiquo.commons.amqp.util.ConsumerUtils.rejectMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.abiquo.commons.amqp.config.DefaultConfiguration;
import com.rabbitmq.client.Envelope;

public abstract class RequestBasedConsumer<R> extends BasicConsumer<RequestBasedCallback>
{
    protected Map<Class<R>, Set<RequestBasedCallback>> callbacksMap;

    public RequestBasedConsumer(DefaultConfiguration configuration, String queue)
    {
        super(configuration, queue);
        callbacksMap = new HashMap<Class<R>, Set<RequestBasedCallback>>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addCallback(RequestBasedCallback callback)
    {
        addCallback((Class<R>) callback.getRequestClass(), callback);
    }

    protected void addCallback(Class<R> type, RequestBasedCallback callback)
    {
        if (!callbacksMap.containsKey(type))
        {
            callbacksMap.put(type, new HashSet<RequestBasedCallback>());
        }

        callbacksMap.get(type).add(callback);
    }

    @Override
    public void consume(Envelope envelope, byte[] body) throws IOException
    {
        R request = deserializeRequest(envelope, body);

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

    protected abstract R deserializeRequest(Envelope envelope, byte[] body);

    protected abstract void consume(R request, Set<RequestBasedCallback> callbacks);
}
