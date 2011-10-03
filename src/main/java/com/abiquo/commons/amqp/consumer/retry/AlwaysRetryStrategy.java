package com.abiquo.commons.amqp.consumer.retry;

import com.abiquo.commons.amqp.consumer.RetryStrategy;


public class AlwaysRetryStrategy extends RetryStrategy
{
    @Override
    public boolean shouldRetry()
    {
        return true;
    }
}
