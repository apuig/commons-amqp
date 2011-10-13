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
package com.abiquo.commons.amqp.manual;

import java.io.IOException;

import com.abiquo.commons.amqp.impl.bpm.BPMRequestConsumer;
import com.abiquo.commons.amqp.impl.bpm.BPMRequestProducer;
import com.abiquo.commons.amqp.impl.bpm.ImageConverterRequestCallback;
import com.abiquo.commons.amqp.impl.bpm.InitiatorRequestCallback;
import com.abiquo.commons.amqp.impl.bpm.StatefulDiskRequestCallback;
import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.InitiatorRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.Sender;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;

public class BPMManualTest
{
    public static void main(String[] args) throws IOException
    {
        BPMRequestConsumer bpmZero = new BPMRequestConsumer("0");

        bpmZero.addCallback(new ImageConverterRequestCallback()
        {
            @Override
            public void convertDisk(ImageConverterRequest request)
            {
                System.out.println(request.getClass());
            }
        });

        bpmZero.addCallback(new StatefulDiskRequestCallback()
        {
            @Override
            public void dumpVolumeToDisk(StatefulDiskRequest request)
            {
                System.out.println("volume -> disk " + request.getClass());
            }

            @Override
            public void dumpDiskToVolume(StatefulDiskRequest request)
            {
                System.out.println("disk -> volume " + request.getClass());
            }
        });

        bpmZero.addCallback(new InitiatorRequestCallback()
        {
            @Override
            public void getInitiatorIQN(InitiatorRequest request)
            {
                System.out.println(request.getClass());
            }
        });

        bpmZero.start();

        BPMRequestProducer p = new BPMRequestProducer("0");
        p.openChannel();

        for (int i = 0; i < 10; i++)
        {
            BPMRequest request = new ImageConverterRequest("klj", "lk", "asd", "asd", 2, 0);
            request.setSender(Sender.AM_DOWNLOAD);

            p.publish(request);
        }

        for (int i = 0; i < 10; i++)
        {
            BPMRequest request = new StatefulDiskRequest();
            request.setSender(Sender.STATEFUL);

            p.publish(request);
        }

        for (int i = 0; i < 10; i++)
        {
            BPMRequest request = new InitiatorRequest();
            request.setSender(Sender.INITIATOR);

            p.publish(request);
        }

        p.closeChannel();
    }
}
