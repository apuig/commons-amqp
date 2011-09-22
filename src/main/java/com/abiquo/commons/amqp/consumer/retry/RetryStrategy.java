package com.abiquo.commons.amqp.consumer.retry;

public interface RetryStrategy
{
    public boolean shouldRetry();
}
