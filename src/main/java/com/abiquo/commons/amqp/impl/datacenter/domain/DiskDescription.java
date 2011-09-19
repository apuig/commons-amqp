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

public class DiskDescription
{
    protected DiskFormatType format;

    protected long capacityInBytes;

    /**
     * Datastore (rootPath + directory) where the virtualmachine is booted. VirtualMachine UUID is
     * added to build the complete destination path. Ex: /var/lib/virt
     */
    protected String destinationDatastore;

    public DiskFormatType getFormat()
    {
        return format;
    }

    public void setFormat(DiskFormatType format)
    {
        this.format = format;
    }

    public long getCapacityInBytes()
    {
        return capacityInBytes;
    }

    public void setCapacityInBytes(long capacityInBytes)
    {
        this.capacityInBytes = capacityInBytes;
    }

    public String getDestinationDatastore()
    {
        return destinationDatastore;
        // .endsWith("/") ? destinationDatastore
        // : destinationDatastore + '/';
    }

    public void setDestinationDatastore(String destinationDatastore)
    {
        this.destinationDatastore = destinationDatastore;
    }

    // TODO duplicated
    public enum DiskFormatType
    {
        UNKNOWN("raw"),

        RAW("raw"),

        INCOMPATIBLE("raw"),

        VMDK_STREAM_OPTIMIZED("raw"),

        VMDK_FLAT("raw"),

        VMDK_SPARSE("vmdk"),

        VHD_FLAT("raw"),

        VHD_SPARSE("raw"),

        VDI_FLAT("raw"),

        VDI_SPARSE("raw"),

        QCOW2_FLAT("qcow2"),

        QCOW2_SPARSE("qcow2");

        private String libvirtFormat;

        private DiskFormatType(String libvirtFormat)
        {
            this.libvirtFormat = libvirtFormat;
        }

        public String getLibvirtFormat()
        {
            return libvirtFormat;
        }
    }
}
