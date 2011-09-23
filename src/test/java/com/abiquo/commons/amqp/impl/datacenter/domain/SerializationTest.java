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

package com.abiquo.commons.amqp.impl.datacenter.domain;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abiquo.commons.amqp.impl.bpm.domain.BPMRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.ImageConverterRequest;
import com.abiquo.commons.amqp.impl.bpm.domain.StatefulDiskRequest;

public class SerializationTest
{
    // @Test
    // public void test_ConfigureVirtualMachineOpSerialization()
    // {
    // DatacenterRequest job = new DatacenterRequest();
    // ConfigureVirtualMachineOp operation = buildConfigureOp();
    // operation.setId("some tasks.job");
    // DatacenterTasks tlist = new DatacenterTasks();
    // tlist.getJobs().add(operation);
    // tlist.setDependent(false);
    // tlist.setId("some tasks");
    // job.setDatacenterTasks(tlist);
    //
    // String serialization = new String(job.toByteArray());
    // DatacenterRequest deserialization =
    // DatacenterRequest.fromByteArray(serialization.getBytes());
    //
    // assertNotNull(deserialization);
    // }
    //
    // @Test
    // public void test_ApplyVirtualMachineStateOpSerialization()
    // {
    // DatacenterRequest job = new DatacenterRequest();
    // ApplyVirtualMachineStateOp operation = buildApplyVirtualMachineStateOp();
    // operation.setId("some tasks.job");
    // DatacenterTasks tlist = new DatacenterTasks();
    // tlist.getJobs().add(operation);
    // tlist.setDependent(false);
    // tlist.setId("some tasks");
    // job.setDatacenterTasks(tlist);
    //
    // String serialization = new String(job.toByteArray());
    // DatacenterRequest deserialization =
    // DatacenterRequest.fromByteArray(serialization.getBytes());
    //
    // assertNotNull(deserialization);
    // }
    //
    // @Test
    // public void test_ReconfigureVirtualMachineOpSerialization()
    // {
    // DatacenterRequest job = new DatacenterRequest();
    // ReconfigureVirtualMachineOp operation = buildReconfigureVirtualMachineOp();
    // operation.setId("some tasks.job");
    // DatacenterTasks tlist = new DatacenterTasks();
    // tlist.getJobs().add(operation);
    // tlist.setDependent(false);
    // tlist.setId("some tasks");
    // job.setDatacenterTasks(tlist);
    // String serialization = new String(job.toByteArray());
    // DatacenterRequest deserialization =
    // DatacenterRequest.fromByteArray(serialization.getBytes());
    //
    // assertNotNull(deserialization);
    // }
    //
    // @Test
    // public void test_SnapshotVirtualMachineOpSerialization()
    // {
    // DatacenterRequest job = new DatacenterRequest();
    // SnapshotVirtualMachineOp operation = buildSnapshotVirtualMachineOp();
    // operation.setId("some tasks.job");
    // DatacenterTasks tlist = new DatacenterTasks();
    // tlist.getJobs().add(operation);
    // tlist.setDependent(false);
    // tlist.setId("some tasks");
    // job.setDatacenterTasks(tlist);
    //
    // String serialization = new String(job.toByteArray());
    // DatacenterRequest deserialization =
    // DatacenterRequest.fromByteArray(serialization.getBytes());
    //
    // assertNotNull(deserialization);
    // }
    //
    // private ConfigureVirtualMachineOp buildConfigureOp()
    // {
    // return testConfigureVirtualMachine(testVirtualMachine());
    // }
    //
    // private ApplyVirtualMachineStateOp buildApplyVirtualMachineStateOp()
    // {
    // return testApplyVirtualMachineState(testVirtualMachine());
    // }
    //
    // private ReconfigureVirtualMachineOp buildReconfigureVirtualMachineOp()
    // {
    // return testReconfigureVirtualMachine(testVirtualMachine());
    // }
    //
    // private SnapshotVirtualMachineOp buildSnapshotVirtualMachineOp()
    // {
    // return testSnapshotVirtualMachine(testVirtualMachine());
    // }

    @Test
    public void test_BPMRequestInheritance()
    {
        ImageConverterRequest imageRequest = new ImageConverterRequest();
        String serialization = new String(imageRequest.toByteArray());

        BPMRequest deserialization = BPMRequest.fromByteArray(serialization.getBytes());
        Assert.assertNotNull(deserialization);
        Assert.assertTrue(deserialization instanceof ImageConverterRequest);
        Assert.assertFalse(deserialization instanceof StatefulDiskRequest);

        StatefulDiskRequest statefulRequest = new StatefulDiskRequest("", "", 1, 22L, 22);
        serialization = new String(statefulRequest.toByteArray());

        deserialization = BPMRequest.fromByteArray(serialization.getBytes());
        Assert.assertNotNull(deserialization);
        Assert.assertTrue(deserialization instanceof StatefulDiskRequest);
        Assert.assertFalse(deserialization instanceof ImageConverterRequest);
    }

}
