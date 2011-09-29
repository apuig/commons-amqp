package com.abiquo.commons.amqp.impl.bpm;

import java.util.Set;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.InitiatorRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConsumer;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;
import com.rabbitmq.client.Envelope;

public class BPMRequestConsumer extends DatacenterRequestConsumer
{
    public BPMRequestConsumer(final String datacenterId)
    {
        super(datacenterId, RequestType.BPM);
    }

    @Override
    protected DatacenterRequest deserializeRequest(final Envelope envelope, final byte[] body)
    {
        return BPMRequest.fromByteArray(body);
    }

    @Override
    protected void consume(final DatacenterRequest request,
        final Set<RequestBasedCallback> callbacks)
    {
        if (request instanceof ImageConverterRequest)
        {
            consume((ImageConverterRequest) request, callbacks);
        }
        else if (request instanceof StatefulDiskRequest)
        {
            consume((StatefulDiskRequest) request, callbacks);
        }
        else if (request instanceof InitiatorRequest)
        {
            consume((InitiatorRequest) request, callbacks);
        }
    }

    protected void consume(final ImageConverterRequest request,
        final Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            ImageConverterRequestCallback realCallback = (ImageConverterRequestCallback) callback;
            realCallback.convertDisk(request);
        }
    }

    protected void consume(final StatefulDiskRequest request,
        final Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            StatefulDiskRequestCallback realCallback = (StatefulDiskRequestCallback) callback;

            switch (request.getSender())
            {
                case STATEFUL:
                    realCallback.dumpDiskToVolume(request);
                    break;

                case STATEFUL_BUNDLE:
                    realCallback.dumpVolumeToDisk(request);
                    break;
            }
        }
    }

    protected void consume(final InitiatorRequest request, final Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            InitiatorRequestCallback realCallback = (InitiatorRequestCallback) callback;
            realCallback.getInitiatorIQN(request);
        }
    }
}
