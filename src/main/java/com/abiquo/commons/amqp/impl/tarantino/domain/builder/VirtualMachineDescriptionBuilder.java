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

package com.abiquo.commons.amqp.impl.tarantino.domain.builder;

import com.abiquo.commons.amqp.impl.tarantino.domain.AuxiliaryDisk;
import com.abiquo.commons.amqp.impl.tarantino.domain.DiskStandard;
import com.abiquo.commons.amqp.impl.tarantino.domain.DiskStateful;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualNIC;
import com.abiquo.commons.amqp.impl.tarantino.domain.DiskDescription.DiskFormatType;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.HardwareConfiguration;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.NetworkConfiguration;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.PrimaryDisk;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.SecondaryDisks;

public class VirtualMachineDescriptionBuilder
{
    private HardwareConfiguration hardConf;

    private NetworkConfiguration netConf;

    private PrimaryDisk primaryDisk;

    private SecondaryDisks secondaryDisks;

    public VirtualMachineDescriptionBuilder hardware(final int virtualCpu, final int ramInMb)
    {
        hardConf = new HardwareConfiguration();
        hardConf.setNumVirtualCpus(virtualCpu);
        hardConf.setRamInMb(ramInMb);

        return this;
    }

    public VirtualMachineDescriptionBuilder setRdPort(final int rdport)
    {
        if (netConf == null)
        {
            netConf = new NetworkConfiguration();
        }
        netConf.setRdPort(rdport);

        return this;
    }

    public VirtualMachineDescriptionBuilder addNetwork(final String macAddress, final String ip,
        final String vSwitchName, final String networkName, final int vlanTag,
        final String leaseName, final String forwardMode, final String netAddress,
        final String gateway, final String mask, final String primaryDNS,
        final String secondaryDNS, final String sufixDNS, final int sequence)
    {
        if (netConf == null)
        {
            netConf = new NetworkConfiguration();
        }

        VirtualNIC nic = new VirtualNIC();
        nic.setMacAddress(macAddress);
        nic.setIp(ip);

        nic.setVSwitchName(vSwitchName);
        nic.setNetworkName(networkName);
        nic.setVlanTag(vlanTag);
        nic.setSequence(sequence);

        nic.setLeaseName(leaseName);
        nic.setForwardMode(forwardMode);
        nic.setNetAddress(netAddress);
        nic.setGateway(gateway);
        nic.setMask(mask);
        nic.setPrimaryDNS(primaryDNS);
        nic.setSecondaryDNS(secondaryDNS);
        nic.setSufixDNS(sufixDNS);

        netConf.getVirtualNICList().add(nic);

        return this;
    }

    public VirtualMachineDescriptionBuilder primaryDisk(final DiskFormatType format,
        final long capacityInBytes, final String repository, final String sourcePath,
        final String destinationDatastore, final String repositoryManagerAddress)
    {

        DiskStandard disk = new DiskStandard();
        disk.setFormat(format);
        disk.setCapacityInBytes(capacityInBytes);
        disk.setRepository(repository);
        disk.setPath(sourcePath);
        disk.setDestinationDatastore(destinationDatastore);
        disk.setRepositoryManagerAddress(repositoryManagerAddress);

        primaryDisk = new PrimaryDisk();
        primaryDisk.setDiskStandard(disk);
        primaryDisk.setRequiresMoveToDatastore(true);

        return this;
    }

    public VirtualMachineDescriptionBuilder primaryDisk(final DiskFormatType format,
        final long capacityInBytes, final String iqn, final String destinationDatastore)
    {
        DiskStateful disk = new DiskStateful();
        disk.setFormat(format);
        disk.setCapacityInBytes(capacityInBytes);
        disk.setLocation(iqn);
        disk.setDestinationDatastore(destinationDatastore);

        primaryDisk = new PrimaryDisk();
        primaryDisk.setDiskStateful(disk);
        return this;
    }

    public VirtualMachineDescriptionBuilder addAuxDisk(final DiskFormatType format,
        final long capacityInBytes, final String iqn, final String destinationDatastore,
        final int sequence)
    {
        if (secondaryDisks == null)
        {
            secondaryDisks = new SecondaryDisks();
        }

        AuxiliaryDisk auxDisk = new AuxiliaryDisk();
        auxDisk.setFormat(format);
        auxDisk.setCapacityInBytes(capacityInBytes);
        auxDisk.setLocation(iqn);
        auxDisk.setDestinationDatastore(destinationDatastore);

        secondaryDisks.getAuxiliaryDisks().add(auxDisk);

        return this;
    }

    public VirtualMachineDefinition build(final String uuid)
    {
        VirtualMachineDefinition virtualMachine = new VirtualMachineDefinition();
        // TODO check not null
        virtualMachine.setMachineUUID(uuid);
        virtualMachine.setMachineName("ABQ_" + uuid);
        virtualMachine.setHardwareConfiguration(hardConf);
        virtualMachine.setNetworkConfiguration(netConf);

        virtualMachine.setPrimaryDisk(primaryDisk);
        virtualMachine.setSecondaryDisks(secondaryDisks);

        return virtualMachine;
    }

}// create builder
