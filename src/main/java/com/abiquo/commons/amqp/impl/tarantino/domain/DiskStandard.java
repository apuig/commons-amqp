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

public class DiskStandard extends DiskDescription
{
    /**
     * Datastore repository location (abiquo repository if is managed and local datastore if it is
     * imported). Only used for VBOX. Other hypervisors have a property in abiquo.properties and
     * aim.ini. Ex: nfs-devel:/opt/vm_repository
     */
    protected String repository;

    /** Relative path inside the datastore. Ex: 1/rs.bcn.abiquo.com/nostalgia/nostalgia.vdi */
    protected String path;

    /**
     * The address of the repository manager (usually the Appliance Library itself). This property
     * is used by the XenServer plugin to perform a copy of the disk file between two directories of
     * the repository.
     */
    protected String repositoryManagerAddress;

    protected long diskFileSizeInBytes;

    public String getRepository()
    {
        return repository;
    }

    public void setRepository(final String value)
    {
        this.repository = value;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(final String value)
    {
        this.path = value;
    }

    public String getRepositoryManagerAddress()
    {
        return repositoryManagerAddress;
    }

    public void setRepositoryManagerAddress(final String repositoryManagerAddress)
    {
        this.repositoryManagerAddress = repositoryManagerAddress;
    }

    public long getDiskFileSizeInBytes()
    {
        return diskFileSizeInBytes;
    }

    public void setDiskFileSizeInBytes(final long diskFileSizeInBytes)
    {
        this.diskFileSizeInBytes = diskFileSizeInBytes;
    }
}
