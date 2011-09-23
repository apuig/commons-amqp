package com.abiquo.commons.amqp.impl.bpm.domain;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;
import com.abiquo.commons.amqp.util.JSONUtils;

@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "@class")
public class BPMRequest extends DatacenterRequest
{
    protected Sender sender = Sender.UNKNOWN;

    public BPMRequest()
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

    public static BPMRequest fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, BPMRequest.class);
    }
}
