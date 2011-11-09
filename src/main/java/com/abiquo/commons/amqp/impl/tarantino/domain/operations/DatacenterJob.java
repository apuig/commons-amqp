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

package com.abiquo.commons.amqp.impl.tarantino.domain.operations;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

import com.abiquo.commons.amqp.impl.tarantino.domain.HypervisorConnection;
import com.abiquo.commons.amqp.impl.tarantino.domain.VirtualMachineDefinition;
import com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob;

/**
 * Carry common attributes to all the virtual machine operations.
 */
@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "@class")
public abstract class DatacenterJob extends BaseJob
{
    /** Hypervisor connection attributes. */
    protected HypervisorConnection hypervisorConnection;

    /** Operation target virtual machine */
    protected VirtualMachineDefinition virtualMachine;

    /** Retries performed by the supervisor (connection exception). */
    protected Integer retry = 0;

    public VirtualMachineDefinition getVirtualMachine()
    {
        return virtualMachine;
    }

    public void setVirtualMachine(VirtualMachineDefinition virtualMachine)
    {
        this.virtualMachine = virtualMachine;
    }

    public HypervisorConnection getHypervisorConnection()
    {
        return hypervisorConnection;
    }

    public void setHypervisorConnection(HypervisorConnection hypervisorConnection)
    {
        this.hypervisorConnection = hypervisorConnection;
    }

    public void incrementRetry()
    {
        retry++;
    }

    public Integer getRetry()
    {
        return retry;
    }

    public void setRetry(Integer retry)
    {
        this.retry = retry;
    }
}
