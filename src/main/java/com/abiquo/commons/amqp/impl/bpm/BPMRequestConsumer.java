package com.abiquo.commons.amqp.impl.bpm;

import java.util.Set;

import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.InitiatorRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestCallback;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConsumer;
import com.rabbitmq.client.Envelope;

public class BPMRequestConsumer extends DatacenterRequestConsumer<BPMRequest>
{
    public BPMRequestConsumer(String datacenterId)
    {
        super(datacenterId, RequestType.BPM);
    }

    @Override
    protected BPMRequest deserializeRequest(Envelope envelope, byte[] body)
    {
        return BPMRequest.fromByteArray(body);
    }

    @Override
    protected void consume(BPMRequest request, Set<DatacenterRequestCallback> callbacks)
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

    protected void consume(ImageConverterRequest request, Set<DatacenterRequestCallback> callbacks)
    {
        for (DatacenterRequestCallback callback : callbacks)
        {
            ImageConverterRequestCallback realCallback = (ImageConverterRequestCallback) callback;
            realCallback.convertDisk(request);
        }
    }

    protected void consume(StatefulDiskRequest request, Set<DatacenterRequestCallback> callbacks)
    {
        for (DatacenterRequestCallback callback : callbacks)
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

    protected void consume(InitiatorRequest request, Set<DatacenterRequestCallback> callbacks)
    {
        for (DatacenterRequestCallback callback : callbacks)
        {
            InitiatorRequestCallback realCallback = (InitiatorRequestCallback) callback;
            realCallback.getInitiatorIQN(request);
        }
    }
}
