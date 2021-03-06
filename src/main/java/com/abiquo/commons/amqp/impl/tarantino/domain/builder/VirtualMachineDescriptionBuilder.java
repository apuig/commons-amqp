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

import java.util.List;

import com.abiquo.commons.amqp.impl.tarantino.domain.DhcpOptionCom;
import com.abiquo.commons.amqp.impl.tarantino.domain.DiskDescription.DiskControllerType;
import com.abiquo.commons.amqp.impl.tarantino.domain.DiskDescription.DiskFormatType;
import com.abiquo.commons.amqp.impl.tarantino.domain.DiskStandard;
import com.abiquo.commons.amqp.impl.tarantino.domain.DiskStateful;
import com.abiquo.commons.amqp.impl.tarantino.domain.SecondaryDiskStandard;
import com.abiquo.commons.amqp.impl.tarantino.domain.SecondaryDiskStateful;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.BootstrapConfiguration;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.EthernetDriver;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.HardwareConfiguration;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.NetworkConfiguration;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.PrimaryDisk;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition.SecondaryDisks;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualNIC;

public class VirtualMachineDescriptionBuilder
{
    private String uuid;

    private String name;

    private HardwareConfiguration hardConf;

    private NetworkConfiguration netConf;

    private PrimaryDisk primaryDisk;

    private SecondaryDisks secondaryDisks;

    private BootstrapConfiguration bootstrapConf;

    private boolean isHA;

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

    public VirtualMachineDescriptionBuilder setBasics(final String uuid, final String name)
    {
        this.uuid = uuid;
        this.name = name;

        return this;
    }

    public VirtualMachineDescriptionBuilder setHA(final boolean isHA)
    {
        this.isHA = isHA;

        return this;
    }

    public VirtualMachineDescriptionBuilder addNetwork(final String macAddress, final String ip,
        final String vSwitchName, final String networkName, final int vlanTag,
        final String leaseName, final String forwardMode, final String netAddress,
        final String gateway, final String mask, final String primaryDNS,
        final String secondaryDNS, final String sufixDNS, final int sequence,
        final List<DhcpOptionCom> list, final boolean configureGateway, final boolean isUnmanaged,
        final EthernetDriver driver)
    {
        if (netConf == null)
        {
            netConf = new NetworkConfiguration();
        }

        final VirtualNIC nic = new VirtualNIC();
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
        nic.setDhcpOptions(list);
        nic.setConfigureGateway(configureGateway);
        nic.setUnmanaged(isUnmanaged);

        if (driver != null)
        {
            nic.setEthernetDriver(driver);
        }

        netConf.getVirtualNICList().add(nic);

        return this;
    }

    public VirtualMachineDescriptionBuilder addNetwork(final String macAddress, final String ip,
        final String vSwitchName, final String networkName, final int vlanTag,
        final String leaseName, final String forwardMode, final String netAddress,
        final String gateway, final String mask, final String primaryDNS,
        final String secondaryDNS, final String sufixDNS, final int sequence,
        final List<DhcpOptionCom> list, final boolean configureGateway, final boolean isUnmanaged)
    {
        return addNetwork(macAddress, ip, vSwitchName, networkName, vlanTag, leaseName,
            forwardMode, netAddress, gateway, mask, primaryDNS, secondaryDNS, sufixDNS, sequence,
            list, configureGateway, isUnmanaged, null);
    }

    public VirtualMachineDescriptionBuilder dhcp(final String dhcpAddress, final Integer dhcpPort)
    {
        if (netConf == null)
        {
            netConf = new NetworkConfiguration();
        }

        netConf.setDhcpAddress(dhcpAddress);
        netConf.setDhcpPort(dhcpPort);
        return this;
    }

    public VirtualMachineDescriptionBuilder setRdPassword(final String rdpassword)
    {
        if (netConf == null)
        {
            netConf = new NetworkConfiguration();
        }
        netConf.setRdPassword(rdpassword);

        return this;
    }

    public VirtualMachineDescriptionBuilder bootstrap(final String uri, final String auth)
    {
        bootstrapConf = new BootstrapConfiguration();
        bootstrapConf.setUri(uri);
        bootstrapConf.setAuth(auth);

        return this;
    }

    public VirtualMachineDescriptionBuilder primaryDisk(final DiskFormatType format,
        final long capacityInBytes, final String repository, final String sourcePath,
        final String destinationDatastore, final String repositoryManagerAddress,
        final DiskControllerType controllerType)
    {

        final DiskStandard disk = new DiskStandard();
        disk.setFormat(format);
        disk.setCapacityInBytes(capacityInBytes);
        disk.setRepository(repository);
        disk.setPath(sourcePath);
        disk.setDestinationDatastore(destinationDatastore);
        disk.setRepositoryManagerAddress(repositoryManagerAddress);
        disk.setDiskControllerType(controllerType);

        primaryDisk = new PrimaryDisk();
        primaryDisk.setDiskStandard(disk);

        return this;
    }

    public VirtualMachineDescriptionBuilder primaryDisk(final DiskFormatType format,
        final long capacityInBytes, final String iqn, final String destinationDatastore,
        final DiskControllerType controllerType, final String name)
    {
        final DiskStateful disk = new DiskStateful();
        disk.setFormat(format);
        disk.setCapacityInBytes(capacityInBytes);
        disk.setLocation(iqn);
        disk.setDestinationDatastore(destinationDatastore);
        disk.setDiskControllerType(controllerType);
        disk.setName(name); // Used in XenServer

        primaryDisk = new PrimaryDisk();
        primaryDisk.setDiskStateful(disk);
        return this;
    }

    public VirtualMachineDescriptionBuilder addSecondaryScsiDisk(final DiskFormatType format,
        final long capacityInBytes, final String iqn, final String destinationDatastore,
        final int sequence, final DiskControllerType controllerType, final String name)
    {
        if (secondaryDisks == null)
        {
            secondaryDisks = new SecondaryDisks();
        }

        final SecondaryDiskStateful auxDisk = new SecondaryDiskStateful();
        auxDisk.setFormat(format);
        auxDisk.setCapacityInBytes(capacityInBytes);
        auxDisk.setLocation(iqn);
        auxDisk.setDestinationDatastore(destinationDatastore);
        auxDisk.setSequence(sequence);
        auxDisk.setDiskControllerType(controllerType);
        auxDisk.setName(name); // Used in XenServer

        secondaryDisks.getStatefulDisks().add(auxDisk);

        return this;
    }

    public VirtualMachineDescriptionBuilder addSecondaryHardDisk(final long diskFileSizeInBytes,
        final int sequence, final String datastorePath, final DiskControllerType controllerType)
    {
        if (secondaryDisks == null)
        {
            secondaryDisks = new SecondaryDisks();
        }

        final SecondaryDiskStandard hdDisk = new SecondaryDiskStandard();
        hdDisk.setCapacityInBytes(0l);
        hdDisk.setDestinationDatastore(datastorePath);
        hdDisk.setDiskFileSizeInBytes(diskFileSizeInBytes);
        hdDisk.setFormat(null);
        hdDisk.setPath(null);
        hdDisk.setRepository(null);
        hdDisk.setRepositoryManagerAddress(null);
        hdDisk.setSequence(sequence);
        hdDisk.setDiskControllerType(controllerType);

        secondaryDisks.getStandardDisks().add(hdDisk);

        return this;
    }

    public VirtualMachineDefinition build()
    {
        final VirtualMachineDefinition virtualMachine = new VirtualMachineDefinition();
        // TODO check not null
        virtualMachine.setMachineUUID(uuid);
        virtualMachine.setMachineName(name);
        virtualMachine.setHardwareConfiguration(hardConf);
        virtualMachine.setNetworkConfiguration(netConf);
        virtualMachine.setBootstrap(bootstrapConf);

        virtualMachine.setPrimaryDisk(primaryDisk);
        virtualMachine.setSecondaryDisks(secondaryDisks);
        virtualMachine.setHA(isHA);

        return virtualMachine;
    }

}// create builder
