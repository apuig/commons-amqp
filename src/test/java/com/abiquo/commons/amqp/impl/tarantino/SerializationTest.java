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

package com.abiquo.commons.amqp.impl.tarantino;

import static com.abiquo.commons.amqp.impl.tarantino.VirtualFactoryTestJobs.testApplyVirtualMachineState;
import static com.abiquo.commons.amqp.impl.tarantino.VirtualFactoryTestJobs.testReconfigureVirtualMachine;
import static com.abiquo.commons.amqp.impl.tarantino.VirtualFactoryTestJobs.testSnapshotVirtualMachine;
import static com.abiquo.commons.amqp.impl.tarantino.VirtualFactoryTestJobs.testVirtualMachine;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;
import com.abiquo.commons.amqp.impl.tarantino.domain.dto.DatacenterTasks;
import com.abiquo.commons.amqp.impl.tarantino.domain.operations.ApplyVirtualMachineStateOp;
import com.abiquo.commons.amqp.impl.tarantino.domain.operations.ReconfigureVirtualMachineOp;
import com.abiquo.commons.amqp.impl.tarantino.domain.operations.SnapshotVirtualMachineOp;

public class SerializationTest
{

    @Test
    public void test_ApplyVirtualMachineStateOpSerialization()
    {
        ApplyVirtualMachineStateOp operation = buildApplyVirtualMachineStateOp();
        operation.setId("some tasks.job");
        DatacenterTasks tlist = new DatacenterTasks();
        tlist.getJobs().add(operation);
        tlist.setDependent(false);
        tlist.setId("some tasks");

        String serialization = new String(tlist.toByteArray());
        DatacenterRequest deserialization = DatacenterTasks.fromByteArray(serialization.getBytes());
        assertNotNull(deserialization);
    }

    @Test
    public void test_ReconfigureVirtualMachineOpSerialization()
    {
        ReconfigureVirtualMachineOp operation = buildReconfigureVirtualMachineOp();
        operation.setId("some tasks.job");
        DatacenterTasks tlist = new DatacenterTasks();
        tlist.getJobs().add(operation);
        tlist.setDependent(false);
        tlist.setId("some tasks");

        String serialization = new String(tlist.toByteArray());
        DatacenterRequest deserialization = DatacenterTasks.fromByteArray(serialization.getBytes());
        assertNotNull(deserialization);
    }

    @Test
    public void test_SnapshotVirtualMachineOpSerialization()
    {
        SnapshotVirtualMachineOp operation = buildSnapshotVirtualMachineOp();
        operation.setId("some tasks.job");
        DatacenterTasks tlist = new DatacenterTasks();
        tlist.getJobs().add(operation);
        tlist.setDependent(false);
        tlist.setId("some tasks");

        String serialization = new String(tlist.toByteArray());
        DatacenterRequest deserialization = DatacenterTasks.fromByteArray(serialization.getBytes());
        assertNotNull(deserialization);
    }

    private ApplyVirtualMachineStateOp buildApplyVirtualMachineStateOp()
    {
        return testApplyVirtualMachineState(testVirtualMachine());
    }

    private ReconfigureVirtualMachineOp buildReconfigureVirtualMachineOp()
    {
        return testReconfigureVirtualMachine(testVirtualMachine());
    }

    private SnapshotVirtualMachineOp buildSnapshotVirtualMachineOp()
    {
        return testSnapshotVirtualMachine(testVirtualMachine());
    }
}
