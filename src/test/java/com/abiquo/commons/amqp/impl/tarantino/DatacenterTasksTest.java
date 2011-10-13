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
package com.abiquo.commons.amqp.impl.tarantino;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import com.abiquo.commons.amqp.impl.tarantino.domain.dto.DatacenterTasks;

public class DatacenterTasksTest
{

    @Test
    public void getJob_test()
    {
        DatacenterTasks t = create3x3Task();

        assertNullGetJob(t, "2");
        assertNullGetJob(t, "1.4");
        assertNullGetJob(t, "1.1.1.1");

        assertGetJob(t, "1.2");
        assertGetJob(t, "1.3.2");

    }

    public static void assertGetJob(DatacenterTasks t, final String id)
    {
        assertEquals(t.getJob(id).getId(), id);
    }

    public static void assertNullGetJob(DatacenterTasks t, final String id)
    {
        assertNull(t.getJob(id));
    }

    public DatacenterTasks create3x3Task()
    {
        DatacenterTasks t1 = task("1");
        DatacenterTasks t11 = task("1.1");
        DatacenterTasks t12 = task("1.2");
        DatacenterTasks t13 = task("1.3");

        t11.getJobs().add(task("1.1.1"));
        t11.getJobs().add(task("1.1.2"));
        t11.getJobs().add(task("1.1.3"));

        t12.getJobs().add(task("1.2.1"));
        t12.getJobs().add(task("1.2.2"));
        t12.getJobs().add(task("1.2.3"));

        t13.getJobs().add(task("1.3.1"));
        t13.getJobs().add(task("1.3.2"));
        t13.getJobs().add(task("1.3.3"));

        t1.getJobs().add(t11);
        t1.getJobs().add(t12);
        t1.getJobs().add(t13);

        return t1;
    }

    public static DatacenterTasks task(String id)
    {
        DatacenterTasks t1 = new DatacenterTasks();
        t1.setId(id);
        return t1;
    }
}
