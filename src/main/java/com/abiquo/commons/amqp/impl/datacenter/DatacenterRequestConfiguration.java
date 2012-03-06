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

package com.abiquo.commons.amqp.impl.datacenter;

import java.io.IOException;

import com.abiquo.commons.amqp.config.DefaultConfiguration;
import com.rabbitmq.client.Channel;

public class DatacenterRequestConfiguration extends DefaultConfiguration
{
    private String datacenterId;

    private RequestType type;

    private static final String DATACENTER_EXCHANGE = "abiquo.datacenter.requests";

    private static final String JOBS_ROUTING_KEY = "abiquo.datacenter.requests";

    private static final String JOBS_QUEUE = "abiquo.datacenter.requests";

    public enum RequestType
    {
        VIRTUAL_FACTORY("virtualfactory"), BPM("bpm");

        public String internalName;

        private RequestType(final String internalName)
        {
            this.internalName = internalName;
        }
    };

    public static String getDatacenterExchange()
    {
        return DATACENTER_EXCHANGE;
    }

    public static String buildJobsRoutingKey(final String datacenterId, final RequestType type)
    {
        return JOBS_ROUTING_KEY.concat(".").concat(datacenterId).concat(".")
            .concat(type.internalName);
    }

    public static String buildJobsQueue(final String datacenterId, final RequestType type)
    {
        return JOBS_QUEUE.concat(".").concat(datacenterId).concat(".").concat(type.internalName);
    }

    public DatacenterRequestConfiguration(final String datacenterId, final RequestType type)
    {
        this.datacenterId = datacenterId;
        this.type = type;
    }

    @Override
    public void declareExchanges(Channel channel) throws IOException
    {
        channel.exchangeDeclare(getDatacenterExchange(), TopicExchange, Durable);
    }

    @Override
    public void declareQueues(Channel channel) throws IOException
    {
        channel.queueDeclare(buildJobsQueue(datacenterId, type), Durable, NonExclusive,
            NonAutodelete, null);
        channel.queueBind(buildJobsQueue(datacenterId, type), getDatacenterExchange(),
            buildJobsRoutingKey(datacenterId, type));
    }
}
