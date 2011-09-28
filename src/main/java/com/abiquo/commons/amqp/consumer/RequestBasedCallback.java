package com.abiquo.commons.amqp.consumer;

public interface RequestBasedCallback
{
    public Class< ? > getRequestClass();
}
