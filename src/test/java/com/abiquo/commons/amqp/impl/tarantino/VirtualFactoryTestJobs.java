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

import com.abiquo.commons.amqp.impl.tarantino.domain.DiskDescription.DiskFormatType;
import com.abiquo.commons.amqp.impl.tarantino.domain.HypervisorConnection.HypervisorType;
import com.abiquo.commons.amqp.impl.tarantino.domain.StateTransition;
import com.abiquo.commons.amqp.impl.tarantino.domain.builder.ApplyVirtualMachineStateJobBuilder;
import com.abiquo.commons.amqp.impl.tarantino.domain.builder.ReconfigureVirtualMachineJobBuilder;
import com.abiquo.commons.amqp.impl.tarantino.domain.builder.SnapshotVirtualMachineJobBuilder;
import com.abiquo.commons.amqp.impl.tarantino.domain.builder.VirtualMachineDescriptionBuilder;
import com.abiquo.commons.amqp.impl.tarantino.domain.operations.ApplyVirtualMachineStateOp;
import com.abiquo.commons.amqp.impl.tarantino.domain.operations.ReconfigureVirtualMachineOp;
import com.abiquo.commons.amqp.impl.tarantino.domain.operations.SnapshotVirtualMachineOp;

public class VirtualFactoryTestJobs
{

    public static VirtualMachineDescriptionBuilder testVirtualMachine()
    {
        return new VirtualMachineDescriptionBuilder() //
            .hardware(1, 256) //
            .addNetwork("mac:mac:mac", "127.0.0.1", "vSwitchName", "networkName", 1, "leaseName",
                "forwardMode", "netAddress", "gateway", "mask", "primaryDNS", "secondaryDNS",
                "sufixDNS", 0) //
            // .primaryDisk("RAW", "1024", "iqn.bla.bla-lun-0")
            .primaryDisk(DiskFormatType.RAW, 1024l, "nfs-devel:/opt/vm_repo",
                "1/rs.bcn/m0n0/m0n0.iso", "datastore1", "http://localhost/am") //
            .addSecondaryScsiDisk(DiskFormatType.RAW, 1024l, "iqn....", "sdasd", 1);
    }

    public static ApplyVirtualMachineStateOp testConfigureVirtualMachine(
        final VirtualMachineDescriptionBuilder vmbuilder)
    {
        return new ApplyVirtualMachineStateJobBuilder() //
            .connection(HypervisorType.TEST, "localhost", "root", "root") //
            .setVirtualMachineDefinition(vmbuilder, "virtualMachineID") //
            .state(StateTransition.CONFIGURE) //
            .buildApplyVirtualMachineStateDto();
    }

    public static ApplyVirtualMachineStateOp testApplyVirtualMachineState(
        final VirtualMachineDescriptionBuilder vmbuilder)
    {
        return new ApplyVirtualMachineStateJobBuilder() //
            .connection(HypervisorType.TEST, "10.60.1.15", "root", "root") //
            .setVirtualMachineDefinition(vmbuilder, "virtualMachineID") //
            .state(StateTransition.PAUSE)//
            .buildApplyVirtualMachineStateDto();
    }

    public static ApplyVirtualMachineStateOp testApplyVirtualMachineState(
        final VirtualMachineDescriptionBuilder vmbuilder, StateTransition state)
    {
        return new ApplyVirtualMachineStateJobBuilder() //
            .connection(HypervisorType.TEST, "10.60.1.15", "root", "root") //
            .setVirtualMachineDefinition(vmbuilder, "virtualMachineID") //
            .state(state)//
            .buildApplyVirtualMachineStateDto();
    }

    public static SnapshotVirtualMachineOp testSnapshotVirtualMachine(
        final VirtualMachineDescriptionBuilder vmbuilder)
    {
        return new SnapshotVirtualMachineJobBuilder()
            .connection(HypervisorType.TEST, "10.60.1.15", "root", "root") //
            .setVirtualMachineDefinition(vmbuilder, "virtualMachineID") //
            .destinationDisk(DiskFormatType.RAW, 1024l, "nfs-devel:/opt/vm_repository",
                "1/some/bundle/m0n0.iso", "test-snapshot")//
            .buildSnapshotVirtualMachineDto();
    }

    public static ReconfigureVirtualMachineOp testReconfigureVirtualMachine(
        final VirtualMachineDescriptionBuilder vmbuilder)
    {
        return new ReconfigureVirtualMachineJobBuilder()//
            .connection(HypervisorType.TEST, "10.60.1.15", "root", "root") //
            .setVirtualMachineDefinition(vmbuilder, "virtualMachineID") //
            .setNewVirtualMachineDefinition(vmbuilder.hardware(4, 512), "virtualMachineID") //
            .buildReconfigureVirtualMachineDto();

    }

}
