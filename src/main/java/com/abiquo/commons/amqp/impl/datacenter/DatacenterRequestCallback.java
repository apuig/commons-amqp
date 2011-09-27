package com.abiquo.commons.amqp.impl.datacenter;

import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;

public abstract class DatacenterRequestCallback
{
    public abstract Class< ? extends DatacenterRequest> getRequestClass();
}
