package com.abiquo.commons.amqp.consumer;

public abstract class RetryStrategy
{
    public abstract boolean shouldRetry();
}
