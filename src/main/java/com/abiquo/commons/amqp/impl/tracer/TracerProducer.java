package com.abiquo.commons.amqp.impl.tracer;

import com.abiquo.commons.amqp.impl.tracer.domain.Trace;
import com.abiquo.commons.amqp.producer.BasicProducer;
import com.abiquo.commons.amqp.serialization.JSONSerializer;

public class TracerProducer extends BasicProducer<Trace>
{
    public TracerProducer()
    {
        super(new TracerConfiguration(), new JSONSerializer<Trace>(Trace.class));
    }
}
