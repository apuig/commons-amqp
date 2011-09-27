package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterRequestCallback;

public abstract class ImageConverterRequestCallback extends DatacenterRequestCallback
{
    public abstract void convertDisk(ImageConverterRequest request);

    @Override
    public Class< ? extends BPMRequest> getRequestClass()
    {
        return ImageConverterRequest.class;
    }
}
