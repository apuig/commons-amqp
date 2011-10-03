package com.abiquo.commons.amqp.consumer.retry;

import com.abiquo.commons.amqp.consumer.RetryStrategy;


public class NeverRetryStrategy extends RetryStrategy
{
    @Override
    public boolean shouldRetry()
    {
        return false;
    }
}
