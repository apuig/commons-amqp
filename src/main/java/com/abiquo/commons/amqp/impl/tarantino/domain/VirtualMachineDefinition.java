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

package com.abiquo.commons.amqp.impl.tarantino.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.abiquo.commons.amqp.impl.tarantino.domain.DiskDescription.DiskControllerType;
import com.abiquo.commons.amqp.impl.tarantino.domain.DiskDescription.DiskFormatType;

public class VirtualMachineDefinition
{
    protected String machineUUID;

    protected String machineName;

    protected HardwareConfiguration hardwareConfiguration;

    protected NetworkConfiguration networkConfiguration;

    protected PrimaryDisk primaryDisk;

    protected SecondaryDisks secondaryDisks;

    protected BootstrapConfiguration bootstrap;

    public String getMachineUUID()
    {
        return machineUUID;
    }

    public void setMachineUUID(final String machineUUID)
    {
        this.machineUUID = machineUUID;
    }

    public String getMachineName()
    {
        return machineName;
    }

    public void setMachineName(final String machineName)
    {
        this.machineName = machineName;
    }

    public HardwareConfiguration getHardwareConfiguration()
    {
        return hardwareConfiguration;
    }

    public void setHardwareConfiguration(final HardwareConfiguration hardwareConfiguration)
    {
        this.hardwareConfiguration = hardwareConfiguration;
    }

    public NetworkConfiguration getNetworkConfiguration()
    {
        return networkConfiguration;
    }

    public void setNetworkConfiguration(final NetworkConfiguration networkConfiguration)
    {
        this.networkConfiguration = networkConfiguration;
    }

    public PrimaryDisk getPrimaryDisk()
    {
        return primaryDisk;
    }

    public void setPrimaryDisk(final PrimaryDisk primaryDisk)
    {
        this.primaryDisk = primaryDisk;
    }

    public SecondaryDisks getSecondaryDisks()
    {
        if (secondaryDisks == null)
        {
            secondaryDisks = new SecondaryDisks();
        }

        return secondaryDisks;
    }

    public void setSecondaryDisks(final SecondaryDisks secondaryDisks)
    {
        this.secondaryDisks = secondaryDisks;
    }

    public BootstrapConfiguration getBootstrap()
    {
        return bootstrap;
    }

    public void setBootstrap(final BootstrapConfiguration bootstrap)
    {
        this.bootstrap = bootstrap;
    }

    public static class HardwareConfiguration
    {
        protected int numVirtualCpus;

        protected int ramInMb;

        public int getNumVirtualCpus()
        {
            return numVirtualCpus;
        }

        public void setNumVirtualCpus(final int numVirtualCpus)
        {
            this.numVirtualCpus = numVirtualCpus;
        }

        public int getRamInMb()
        {
            return ramInMb;
        }

        public void setRamInMb(final int value)
        {
            this.ramInMb = value;
        }

    }

    public enum EthernetDriver
    {
        PCNet32, VMXNET3, E1000
    }

    public static class NetworkConfiguration
    {
        /** OMAPI address */
        protected String dhcpAddress;

        /** OMAPI port */
        protected int dhcpPort;

        protected int rdport;

        protected String rdPassword;

        protected List<VirtualNIC> virtualNICs;

        public void setRdPort(final int rdport)
        {
            this.rdport = rdport;
        }

        public int getRdPort()
        {
            return rdport;
        }

        public String getRdPassword()
        {
            return rdPassword;
        }

        public void setRdPassword(final String rdPassword)
        {
            this.rdPassword = rdPassword;
        }

        public String getDhcpAddress()
        {
            return dhcpAddress;
        }

        public void setDhcpAddress(final String dhcpAddress)
        {
            this.dhcpAddress = dhcpAddress;
        }

        public int getDhcpPort()
        {
            return dhcpPort;
        }

        public void setDhcpPort(final int dhcpPort)
        {
            this.dhcpPort = dhcpPort;
        }

        /**
         * Null or empty password
         */
        @JsonIgnore
        public boolean isRdPasswordSet()
        {
            return rdPassword != null && !rdPassword.isEmpty();
        }

        public List<VirtualNIC> getVirtualNICList()
        {
            if (virtualNICs == null)
            {
                virtualNICs = new ArrayList<VirtualNIC>();
            }

            return this.virtualNICs;
        }

    }

    public static class BootstrapConfiguration
    {
        protected String uri;

        protected String auth;

        public String getUri()
        {
            return uri;
        }

        public void setUri(final String uri)
        {
            this.uri = uri;
        }

        public String getAuth()
        {
            return auth;
        }

        public void setAuth(final String auth)
        {
            this.auth = auth;
        }
    }

    public static class PrimaryDisk
    {
        protected DiskStandard diskStandard;

        protected DiskStateful diskStateful;

        /** If not ''requiresMoveToDatastore'' then is a HA deploy. */
        protected Boolean requiresMoveToDatastore;

        public DiskStandard getDiskStandard()
        {
            return diskStandard;
        }

        public void setDiskStandard(final DiskStandard diskStandard)
        {
            this.diskStandard = diskStandard;
        }

        public DiskStateful getDiskStateful()
        {
            return diskStateful;
        }

        public void setDiskStateful(final DiskStateful value)
        {
            this.diskStateful = value;
        }

        /**
         * Only DiskStandard
         */
        public Boolean getRequiresMoveToDatastore()
        {
            return requiresMoveToDatastore == null ? true : requiresMoveToDatastore;
        }

        // TODO compatibility with old code FIXME
        public boolean requiresMoveToDatastore()
        {
            return requiresMoveToDatastore;
        }

        public void setRequiresMoveToDatastore(final Boolean requiresMoveToDatastore)
        {
            this.requiresMoveToDatastore = requiresMoveToDatastore;
        }

        @JsonIgnore
        public boolean isStateful()
        {
            return getDiskStateful() != null;
        }

        @JsonIgnore
        public boolean isStandard()
        {
            return getDiskStandard() != null;
        }

        /**
         * Checks if the {@link DiskControllerType} or {@link DiskStateful} have the
         * {@link DiskFormatType} set.
         */
        @JsonIgnore
        public boolean isDiskControllerTypeSet()
        {
            return isStateful() ? getDiskStateful().isDiskControllerTypeSet() : getDiskStandard()
                .isDiskControllerTypeSet();
        }

        /**
         * Gets the {@link DiskControllerType} from the {@link DiskStandard} or {@link DiskStateful}
         */
        @JsonIgnore
        public DiskControllerType getDiskControllerType()
        {
            return isStateful() ? getDiskStateful().getDiskControllerType() : getDiskStandard()
                .getDiskControllerType();
        }

        /**
         * @return the destination datastore (even if standard or stateful disk)
         */
        @JsonIgnore
        public String getDestinationDatastore()
        {
            return isStateful() ? getDiskStateful().getDestinationDatastore() : getDiskStandard()
                .getDestinationDatastore();
        }
    }

    public static class SecondaryDisks
    {
        protected List<SecondaryDiskStateful> statefulDisks;

        protected List<SecondaryDiskStandard> standardDisks;

        public List<SecondaryDiskStateful> getStatefulDisks()
        {
            if (statefulDisks == null)
            {
                statefulDisks = new ArrayList<SecondaryDiskStateful>();
            }

            return this.statefulDisks;
        }

        public List<SecondaryDiskStandard> getStandardDisks()
        {
            if (standardDisks == null)
            {
                standardDisks = new ArrayList<SecondaryDiskStandard>();
            }

            return this.standardDisks;
        }

        @JsonIgnore
        public boolean isEmpty()
        {
            return getStatefulDisks().isEmpty();
        }

    }
}
