package com.abiquo.commons.amqp.impl.tarantino;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMResponse;
import com.abiquo.commons.amqp.impl.tarantino.domain.TarantinoResponse;

public abstract class TarantinoResponseCallback implements RequestBasedCallback
{
    public abstract void processResponse(TarantinoResponse response);

    @Override
    public Class<BPMResponse> getRequestClass()
    {
        return BPMResponse.class;
    }
}
