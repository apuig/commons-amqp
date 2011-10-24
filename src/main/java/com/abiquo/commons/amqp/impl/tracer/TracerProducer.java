package com.abiquo.commons.amqp.impl.tracer;

import com.abiquo.commons.amqp.impl.tracer.domain.Trace;
import com.abiquo.commons.amqp.producer.BasicProducer;

public class TracerProducer extends BasicProducer<Trace>
{
    public TracerProducer()
    {
        super(new TracerConfiguration());
    }
}
