package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMResponse;

public abstract class BPMResponseCallback implements RequestBasedCallback
{
    public abstract void processResponse(BPMResponse response);

    @Override
    public Class< ? > getRequestClass()
    {
        return BPMResponse.class;
    }
}
