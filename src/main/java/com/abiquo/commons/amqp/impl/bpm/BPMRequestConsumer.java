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
    public BPMRequestConsumer(String datacenterId)
    {
        super(datacenterId, RequestType.BPM);
    }

    @Override
    protected DatacenterRequest deserializeRequest(Envelope envelope, byte[] body)
    {
        return BPMRequest.fromByteArray(body);
    }

    @Override
    protected void consume(DatacenterRequest request, Set<RequestBasedCallback> callbacks)
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

    protected void consume(ImageConverterRequest request, Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            ImageConverterRequestCallback realCallback = (ImageConverterRequestCallback) callback;
            realCallback.convertDisk(request);
        }
    }

    protected void consume(StatefulDiskRequest request, Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            StatefulDiskRequestCallback realCallback = (StatefulDiskRequestCallback) callback;

            switch (request.getSender())
            {
                case STATEFUL:
                    realCallback.dumpVolumeToDisk(request);
                    break;

                case STATEFUL_BUNDLE:
                    realCallback.dumpDiskToVolume(request);
                    break;
            }
        }
    }

    protected void consume(InitiatorRequest request, Set<RequestBasedCallback> callbacks)
    {
        for (RequestBasedCallback callback : callbacks)
        {
            InitiatorRequestCallback realCallback = (InitiatorRequestCallback) callback;
            realCallback.getInitiatorIQN(request);
        }
    }
}
