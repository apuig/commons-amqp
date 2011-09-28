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


import org.codehaus.jackson.annotate.JsonIgnore;

import com.abiquo.commons.amqp.util.AddressingUtils;

/**
 * @see AddressingUtils
 */
public class DiskStateful extends DiskDescription
{
    /** The name of the disk in the storage device. */
    protected String name;

    /**
     * The location of the disk in the form:
     * 
     * <pre>
     * ip-<ip:port>-iscsi-<iqn>-lun-<lun>-part-<partition>
     * </pre>
     * 
     * You should use the helper methods provided in this class to access concrete information about
     * the location. See {@link AddressingUtils} for details about the format.
     * 
     * @see AddressingUtils
     */
    protected String location;

    @JsonIgnore
    public String getStorageDeviceIp()
    {
        return AddressingUtils.getIP(location);
    }

    @JsonIgnore
    public String getStorageDevicePort()
    {
        return AddressingUtils.getPort(location);
    }

    @JsonIgnore
    public String getIQN()
    {
        return AddressingUtils.getIQN(location);
    }

    @JsonIgnore
    public String getLUN()
    {
        return AddressingUtils.getLUN(location);
    }

    @JsonIgnore
    public String getPartition()
    {
        return AddressingUtils.getPartition(location);
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(final String value)
    {
        this.location = value;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }
}
