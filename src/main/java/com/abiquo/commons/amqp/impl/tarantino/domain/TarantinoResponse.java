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

import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterNotification;
import com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob;
import com.abiquo.commons.amqp.util.JSONUtils;

public class TarantinoResponse extends DatacenterNotification
{
    public enum JobStateType
    {
        START, DONE, ERROR, ROLLBACK_START, ROLLBACK_DONE, ROLLBACK_ERROR, ROLLBACK_ABORTED
    };

    protected String jobId;

    protected JobStateType state;

    /** for ERROR and ROLLBACK_ERROR adds the cause. TODO use VirtualFactoryException. */
    protected String error;

    public String getJobId()
    {
        return jobId;
    }

    public void setJobId(final String jobId)
    {
        this.jobId = jobId;
    }

    public JobStateType getState()
    {
        return state;
    }

    public void setState(final JobStateType state)
    {
        this.state = state;
    }

    public String getError()
    {
        return error;
    }

    public void setError(final String error)
    {
        this.error = error;
    }

    public boolean isTask()
    {
        return BaseJob.isRoot(this.jobId);
    }

    public static TarantinoResponse fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, TarantinoResponse.class);
    }
}
