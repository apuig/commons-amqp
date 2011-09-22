package com.abiquo.commons.amqp.consumer.retry.impl;

import com.abiquo.commons.amqp.consumer.retry.RetryStrategy;

public class AlwaysRetryStrategy implements RetryStrategy
{
    @Override
    public boolean shouldRetry()
    {
        return true;
    }
}
