package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMResponse;

public abstract class BPMResponseCallback implements RequestBasedCallback
{
    public abstract void processResponse(BPMResponse request);

    @Override
    public Class<BPMResponse> getRequestClass()
    {
        return BPMResponse.class;
    }
}
