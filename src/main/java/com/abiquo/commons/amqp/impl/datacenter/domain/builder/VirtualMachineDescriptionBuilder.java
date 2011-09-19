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

package com.abiquo.commons.amqp.impl.datacenter.domain.builder;

import com.abiquo.commons.amqp.impl.datacenter.domain.AuxiliaryDisk;
import com.abiquo.commons.amqp.impl.datacenter.domain.DiskDescription.DiskFormatType;
import com.abiquo.commons.amqp.impl.datacenter.domain.DiskStandard;
import com.abiquo.commons.amqp.impl.datacenter.domain.DiskStateful;
import com.abiquo.commons.amqp.impl.datacenter.domain.VirtualMachineDefinition;
import com.abiquo.commons.amqp.impl.datacenter.domain.VirtualMachineDefinition.HardwareConfiguration;
import com.abiquo.commons.amqp.impl.datacenter.domain.VirtualMachineDefinition.NetworkConfiguration;
import com.abiquo.commons.amqp.impl.datacenter.domain.VirtualMachineDefinition.PrimaryDisk;
import com.abiquo.commons.amqp.impl.datacenter.domain.VirtualMachineDefinition.SecondaryDisks;
import com.abiquo.commons.amqp.impl.datacenter.domain.VirtualNIC;

public class VirtualMachineDescriptionBuilder
{
    private HardwareConfiguration hardConf;

    private NetworkConfiguration netConf;

    private PrimaryDisk primaryDisk;

    private SecondaryDisks secondaryDisks;

    public VirtualMachineDescriptionBuilder hardware(int virtualCpu, int ramInMb)
    {
        hardConf = new HardwareConfiguration();
        hardConf.setNumVirtualCpus(virtualCpu);
        hardConf.setRamInMb(ramInMb);

        return this;
    }

    public VirtualMachineDescriptionBuilder setRdPort(int rdport)
    {
        if (netConf == null)
        {
            netConf = new NetworkConfiguration();
        }
        netConf.setRdPort(rdport);

        return this;
    }

    public VirtualMachineDescriptionBuilder addNetwork(String macAddress, String ip,
        String vSwitchName, String networkName, int vlanTag, String leaseName, String forwardMode,
        String netAddress, String gateway, String mask, String primaryDNS, String secondaryDNS,
        String sufixDNS, int sequence)
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

    public VirtualMachineDescriptionBuilder primaryDisk(DiskFormatType format,
        long capacityInBytes, String repository, String sourcePath, String destinationDatastore)
    {

        DiskStandard disk = new DiskStandard();
        disk.setFormat(format);
        disk.setCapacityInBytes(capacityInBytes);
        disk.setRepository(repository);
        disk.setPath(sourcePath);
        disk.setDestinationDatastore(destinationDatastore);

        primaryDisk = new PrimaryDisk();
        primaryDisk.setDiskStandard(disk);
        primaryDisk.setRequiresMoveToDatastore(true);

        return this;
    }

    public VirtualMachineDescriptionBuilder primaryDisk(DiskFormatType format,
        long capacityInBytes, String iqn, String destinationDatastore)
    {
        DiskStateful disk = new DiskStateful();
        disk.setFormat(format);
        disk.setCapacityInBytes(capacityInBytes);
        disk.setIqn(iqn);
        disk.setDestinationDatastore(destinationDatastore);

        primaryDisk = new PrimaryDisk();
        primaryDisk.setDiskStateful(disk);
        return this;
    }

    public VirtualMachineDescriptionBuilder addAuxDisk(DiskFormatType format, long capacityInBytes,
        String iqn, String destinationDatastore, int sequence)
    {
        if (secondaryDisks == null)
        {
            secondaryDisks = new SecondaryDisks();
        }

        AuxiliaryDisk auxDisk = new AuxiliaryDisk();
        auxDisk.setFormat(format);
        auxDisk.setCapacityInBytes(capacityInBytes);
        auxDisk.setIqn(iqn);
        auxDisk.setDestinationDatastore(destinationDatastore);

        secondaryDisks.getAuxiliaryDisks().add(auxDisk);

        return this;
    }

    public VirtualMachineDefinition build(String uuid)
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
