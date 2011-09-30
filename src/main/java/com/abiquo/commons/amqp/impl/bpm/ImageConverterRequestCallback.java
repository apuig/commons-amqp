package com.abiquo.commons.amqp.impl.bpm;

import java.io.IOException;

import com.abiquo.commons.amqp.consumer.RequestBasedCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterNotificationProducer;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterNotification;

public abstract class ImageConverterRequestCallback implements RequestBasedCallback
{
    protected DatacenterNotificationProducer producer;

    public ImageConverterRequestCallback()
    {
        producer = new DatacenterNotificationProducer();
    }

    public abstract void convertDisk(ImageConverterRequest request);

    @Override
    public Class<ImageConverterRequest> getRequestClass()
    {
        return ImageConverterRequest.class;
    }

    protected void sendNotification(final DatacenterNotification message) throws IOException
    {
        this.producer.openChannel();
        this.producer.publish(message);
        this.producer.closeChannel();
    }
}
