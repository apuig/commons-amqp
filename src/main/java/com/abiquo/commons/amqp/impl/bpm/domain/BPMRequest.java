package com.abiquo.commons.amqp.impl.bpm.domain;

import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;
import com.abiquo.commons.amqp.util.JSONUtils;

public class BPMRequest extends DatacenterRequest
{
    protected Sender sender = Sender.UNKNOWN;

    protected ImageConverterRequest conversionRequest = null;

    protected StatefulDiskRequest statefulRequest = null;

    public BPMRequest()
    {
        this.sender = Sender.UNKNOWN;
        this.conversionRequest = null;
        this.statefulRequest = null;
    }

    public Sender getSender()
    {
        return sender;
    }

    public void setSender(final Sender sender)
    {
        this.sender = sender;
    }

    public ImageConverterRequest getConversionRequest()
    {
        return conversionRequest;
    }

    public void setConversionRequest(ImageConverterRequest conversionRequest)
    {
        this.conversionRequest = conversionRequest;
    }

    public StatefulDiskRequest getStatefulRequest()
    {
        return statefulRequest;
    }

    public void setStatefulRequest(StatefulDiskRequest statefulRequest)
    {
        this.statefulRequest = statefulRequest;
    }

    public static BPMRequest fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, BPMRequest.class);
    }

    public boolean isConversionRequest()
    {
        return conversionRequest != null;
    }

    public boolean isStatefulRequest()
    {
        return statefulRequest != null;
    }
}
