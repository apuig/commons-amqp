/**
 * Abiquo community edition
 * cloud management application for hybrid clouds
 * Copyright (C) 2008-2010 - Abiquo Holdings S.L.
 * 
 * This application is free software; you can redistribute it and/or
 * modify it under the terms of the GNU LESSER GENERAL PUBLIC
 * LICENSE as published by the Free Software Foundation under
 * version 3 of the License
 * 
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * LESSER GENERAL PUBLIC LICENSE v.3 for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */
package com.abiquo.commons.amqp.consumer.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abiquo.commons.amqp.consumer.RetryStrategy;

public class DelayedRetryStrategy extends RetryStrategy
{
    private final static Logger LOGGER = LoggerFactory.getLogger(DelayedRetryStrategy.class);

    protected int retriesLeft;

    protected long msToSleep;

    protected boolean infiteRetries;

    public DelayedRetryStrategy()
    {
        this.retriesLeft = getNumberOfRetries();
        this.msToSleep = getMsToSleep();
        this.infiteRetries = (this.retriesLeft == 0);
    }

    /**
     * Number of retries to perform. 0 for infinite retries.
     * 
     * @return The number of retries to perform.
     */
    protected int getNumberOfRetries()
    {
        return Integer.parseInt(System.getProperty("abiquo.retry.retries", "0"));
    }

    /**
     * Milliseconds to sleep between each retry.
     * 
     * @return The milliseconds to sleep between each retry.
     */
    protected long getMsToSleep()
    {
        return Integer.parseInt(System.getProperty("abiquo.retry.mstosleep", "10000"));
    }

    @Override
    public boolean shouldRetry()
    {
        boolean retry = infiteRetries ? true : (retriesLeft-- > 0);

        if (retry)
        {
            try
            {
                LOGGER.debug(String.format(
                    "Sleeping for %d ms, %d retries left, infinite retries %b", msToSleep,
                    retriesLeft, infiteRetries));
                Thread.sleep(msToSleep);
            }
            catch (InterruptedException e)
            {
                LOGGER.debug("Interrupted on thread sleep");
            }
        }

        return retry;
    }
}
