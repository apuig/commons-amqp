package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.InitiatorRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestCallback;

public abstract class InitiatorRequestCallback extends DatacenterRequestCallback
{
    public abstract void getInitiatorIQN(InitiatorRequest request);

    @Override
    public Class< ? extends BPMRequest> getRequestClass()
    {
        return InitiatorRequest.class;
    }
}
