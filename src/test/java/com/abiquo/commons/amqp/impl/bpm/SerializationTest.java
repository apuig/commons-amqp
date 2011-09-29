package com.abiquo.commons.amqp.impl.bpm;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMResponse;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;
import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterResponse;

public class SerializationTest
{
    @Test
    public void test_BPMRequestInheritance()
    {
        ImageConverterRequest imageRequest = new ImageConverterRequest();
        String serialization = new String(imageRequest.toByteArray());

        BPMRequest deserialization = BPMRequest.fromByteArray(serialization.getBytes());
        Assert.assertNotNull(deserialization);
        Assert.assertTrue(deserialization instanceof ImageConverterRequest);
        Assert.assertFalse(deserialization instanceof StatefulDiskRequest);

        StatefulDiskRequest statefulRequest = new StatefulDiskRequest("", "", 1, 22L, 22);
        serialization = new String(statefulRequest.toByteArray());

        deserialization = BPMRequest.fromByteArray(serialization.getBytes());
        Assert.assertNotNull(deserialization);
        Assert.assertTrue(deserialization instanceof StatefulDiskRequest);
        Assert.assertFalse(deserialization instanceof ImageConverterRequest);
    }

    @Test
    public void test_BPMResponse()
    {
        BPMResponse response = new BPMResponse();
        String serialization = new String(response.toByteArray());

        DatacenterResponse deserialization =
            DatacenterResponse.fromByteArray(serialization.getBytes());
        Assert.assertNotNull(deserialization);
        Assert.assertTrue(deserialization instanceof BPMResponse);
    }
}
