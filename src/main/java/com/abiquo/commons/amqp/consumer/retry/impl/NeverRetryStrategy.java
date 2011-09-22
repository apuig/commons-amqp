package com.abiquo.commons.amqp.consumer.retry.impl;

import com.abiquo.commons.amqp.consumer.retry.RetryStrategy;

public class NeverRetryStrategy implements RetryStrategy
{
    @Override
    public boolean shouldRetry()
    {
        return false;
    }
}
