package com.abiquo.commons.amqp.impl.bpm;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;

public abstract class ImageConverterRequestCallback implements RequestBasedCallback
{
    public abstract void convertDisk(ImageConverterRequest request);

    @Override
    public Class<ImageConverterRequest> getRequestClass()
    {
        return ImageConverterRequest.class;
    }
}
