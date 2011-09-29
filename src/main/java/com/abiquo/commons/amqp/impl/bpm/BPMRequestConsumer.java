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
    public BPMRequestConsumer(final String datacenterId)
    {
        super(datacenterId, RequestType.BPM);
    }

    @Override
    protected BPMRequest deserializeRequest(final Envelope envelope, final byte[] body)
    {
        return BPMRequest.fromByteArray(body);
    }

    @Override
    protected void consume(final BPMRequest request, final Set<DatacenterRequestCallback> callbacks)
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
        final Set<DatacenterRequestCallback> callbacks)
    {
        for (DatacenterRequestCallback callback : callbacks)
        {
            ImageConverterRequestCallback realCallback = (ImageConverterRequestCallback) callback;
            realCallback.convertDisk(request);
        }
    }

    protected void consume(final StatefulDiskRequest request,
        final Set<DatacenterRequestCallback> callbacks)
    {
        for (DatacenterRequestCallback callback : callbacks)
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

    protected void consume(final InitiatorRequest request,
        final Set<DatacenterRequestCallback> callbacks)
    {
        for (DatacenterRequestCallback callback : callbacks)
        {
            InitiatorRequestCallback realCallback = (InitiatorRequestCallback) callback;
            realCallback.getInitiatorIQN(request);
        }
    }
}
