package com.abiquo.commons.amqp.manual;

import java.io.IOException;

import com.abiquo.commons.amqp.impl.bpm.BPMResponseCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMResponse;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterResponseConsumer;
import com.abiquo.commons.amqp.impl.datacenter.DatacenterResponseProducer;

public class DatacenterResponseManualTest
{
    public static void main(String[] args) throws IOException
    {
        DatacenterResponseConsumer r = new DatacenterResponseConsumer();

        r.addCallback(new BPMResponseCallback()
        {
            @Override
            public void processResponse(BPMResponse request)
            {
                System.out.println(request.toString());
            }
        });

        r.start();

        DatacenterResponseProducer p = new DatacenterResponseProducer();

        p.openChannel();

        for (int i = 0; i < 10; i++)
        {
            p.publish(new BPMResponse());
        }

        p.closeChannel();
    }
}
