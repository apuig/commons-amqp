package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.InitiatorRequest;

public abstract class InitiatorRequestCallback implements RequestBasedCallback
{
    public abstract void getInitiatorIQN(InitiatorRequest request);

    @Override
    public Class<InitiatorRequest> getRequestClass()
    {
        return InitiatorRequest.class;
    }
}
