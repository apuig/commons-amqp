package com.abiquo.commons.amqp.mains;

import java.io.IOException;

import com.abiquo.commons.amqp.impl.bpm.BPMRequestCallback;
import com.abiquo.commons.amqp.impl.bpm.BPMRequestConsumer;
import com.abiquo.commons.amqp.impl.bpm.BPMRequestProducer;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.Sender;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;

public class BPMTest
{
    public static void main(String[] args) throws IOException
    {
        BPMRequestConsumer bpm0 = new BPMRequestConsumer("0");

        bpm0.addCallback(new BPMRequestCallback()
        {
            @Override
            public void convertDisk(ImageConverterRequest request)
            {

            }

            @Override
            public void dumpDiskToVolume(StatefulDiskRequest request)
            {

            }

            @Override
            public void dumpVolumeToDisk(StatefulDiskRequest request)
            {

            }
        });

        bpm0.start();

        BPMRequestProducer p = new BPMRequestProducer("0");
        p.openChannel();

        for (int i = 0; i < 10; i++)
        {
            ImageConverterRequest r = new ImageConverterRequest("klj", "lk", "asd", "asd", 2, 0);

            BPMRequest request = new BPMRequest();
            request.setConversionRequest(r);
            request.setSender(Sender.AM_DOWNLOAD);

            p.publish(request);
        }

        p.closeChannel();
    }
}
