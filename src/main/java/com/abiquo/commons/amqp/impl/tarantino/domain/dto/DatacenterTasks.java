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
package com.abiquo.commons.amqp.impl.tarantino.domain.dto;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

import com.abiquo.commons.amqp.impl.tarantino.domain.StateTransition;
import com.abiquo.commons.amqp.impl.tarantino.domain.operations.ApplyVirtualMachineStateOp;
import com.abiquo.commons.amqp.impl.tarantino.domain.operations.DatacenterJob;
import com.abiquo.commons.amqp.util.JSONUtils;

/**
 * Dependent or independent BaseJob collection
 */
@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "@class")
public class DatacenterTasks extends BaseJob
{
    // XXX consider using a map <jobid, BaseJob>
    private List<BaseJob> jobs;

    private Boolean dependent;

    public boolean isDependent()
    {
        return dependent;
    }

    public void setDependent(boolean dependent)
    {
        this.dependent = dependent;
    }

    public List<BaseJob> getJobs()
    {
        if (jobs == null)
        {
            jobs = new LinkedList<BaseJob>();
        }

        return jobs;
    }

    public List<String> getJobsId()
    {
        List<String> ids = new LinkedList<String>();
        for (BaseJob bj : jobs)
        {
            ids.add(bj.id);
        }
        return ids;
    }

    /**
     * Utility to get a job (child or recursive)
     * <p>
     * TODO use a BaseJob map
     * 
     * @return null if not found
     */
    public BaseJob getJob(final String jobId)
    {
        int dif = level(jobId) - level(id);

        if (dif <= 0)
        {
            return null; // invalid
        }

        if (dif == 1)
        {
            return getJobInCurrentLevel(jobId);
        }
        else
        {
            final String thisparent = getParentAt(jobId, level(id) + 1);
            return ((DatacenterTasks) getJobInCurrentLevel(thisparent)).getJob(jobId);
        }
    }

    private BaseJob getJobInCurrentLevel(final String jobId)
    {
        for (BaseJob bj : getJobs())
        {
            if (bj.getId().equalsIgnoreCase(jobId))
            {
                return bj;
            }
        }

        return null;
    }

    public static DatacenterTasks fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, DatacenterTasks.class);
    }
}
