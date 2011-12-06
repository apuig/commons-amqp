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

package com.abiquo.commons.amqp.impl.am;

import static com.abiquo.commons.amqp.impl.am.AMConfiguration.AM_QUEUE;
import static com.abiquo.commons.amqp.util.ConsumerUtils.ackMessage;
import static com.abiquo.commons.amqp.util.ConsumerUtils.rejectMessage;

import java.io.IOException;

import com.abiquo.commons.amqp.consumer.BasicConsumer;
import com.abiquo.commons.amqp.impl.am.domain.TemplateStatusEvent;
import com.rabbitmq.client.Envelope;

public class AMConsumer extends BasicConsumer<AMCallback>
{
    public AMConsumer()
    {
        super(new AMConfiguration(), AM_QUEUE);
    }

    @Override
    public void consume(Envelope envelope, byte[] body) throws IOException
    {
        TemplateStatusEvent event = TemplateStatusEvent.fromByteArray(body);

        if (event != null)
        {
            for (AMCallback callback : callbacks)
            {

                if (event.getStatus().equalsIgnoreCase("DOWNLOADING"))
                {
                    callback.onDownloading(event);
                }
                else if (event.getStatus().equalsIgnoreCase("DOWNLOAD"))
                {
                    callback.onDownload(event);
                }
                else if (event.getStatus().equalsIgnoreCase("ERROR"))
                {
                    callback.onError(event);
                }
                else if (event.getStatus().equalsIgnoreCase("NOT_DOWNLOAD"))
                {
                    callback.onNotDownload(event);
                }
                else
                {
                    throw new IOException("Unexpected OVF Package Instance status : "
                        + event.getStatus());
                }
            }

            ackMessage(getChannel(), envelope.getDeliveryTag());
        }
        else
        {
            rejectMessage(getChannel(), envelope.getDeliveryTag());
        }
    }
}
