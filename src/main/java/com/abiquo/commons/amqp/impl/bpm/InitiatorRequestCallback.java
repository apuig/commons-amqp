package com.abiquo.commons.amqp.impl.bpm;

import java.io.IOException;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.InitiatorRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterNotificationProducer;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterNotification;

public abstract class InitiatorRequestCallback implements RequestBasedCallback
{
    private DatacenterNotificationProducer producer;

    public InitiatorRequestCallback()
    {
        producer = new DatacenterNotificationProducer();
    }

    public abstract void getInitiatorIQN(InitiatorRequest request);

    @Override
    public Class<InitiatorRequest> getRequestClass()
    {
        return InitiatorRequest.class;
    }

    protected void sendNotification(final DatacenterNotification message) throws IOException
    {
        this.producer.openChannel();
        this.producer.publish(message);
        this.producer.closeChannel();
    }
}
