package com.abiquo.commons.amqp.impl.bpm.domain;

import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterNotification;
import com.abiquo.commons.amqp.util.JSONUtils;

public class BPMResponse extends DatacenterNotification
{
    protected Sender sender = Sender.UNKNOWN;

    public BPMResponse()
    {
        this.sender = Sender.UNKNOWN;
    }

    public Sender getSender()
    {
        return sender;
    }

    public void setSender(final Sender sender)
    {
        this.sender = sender;
    }

    public static BPMResponse fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, BPMResponse.class);
    }
}
