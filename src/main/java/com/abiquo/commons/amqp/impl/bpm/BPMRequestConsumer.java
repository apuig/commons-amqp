package com.abiquo.commons.amqp.impl.bpm;

import static com.abiquo.commons.amqp.util.ConsumerUtils.ackMessage;
import static com.abiquo.commons.amqp.util.ConsumerUtils.rejectMessage;

import java.io.IOException;

import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConfiguration.RequestType;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestConsumer;
import com.rabbitmq.client.Envelope;

public class BPMRequestConsumer extends DatacenterRequestConsumer
{
    public BPMRequestConsumer(String id)
    {
        super(id, RequestType.BPM);
    }

    @Override
    public void consume(Envelope envelope, byte[] body) throws IOException
    {
        BPMRequest request = BPMRequest.fromByteArray(body);

        if (request != null)
        {
            for (BPMRequestCallback callback : callbacksMap.get(request.getClass()))
            {
                if (request instanceof ImageConverterRequest)
                {
                }
                else if (request instanceof StatefulDiskRequest)
                {
                }
            }

            ackMessage(channel, envelope.getDeliveryTag());
        }
        else
        {
            rejectMessage(channel, envelope.getDeliveryTag());
        }
    }
}
