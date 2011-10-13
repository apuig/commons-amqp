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

import static com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob.getParent;
import static com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob.getParentAt;
import static com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob.isRoot;
import static com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob.level;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class BaseJobTest
{

    @Test
    public void isRoot_test()
    {
        assertTrue(isRoot("a"));
        assertFalse(isRoot("a.a"));
    }

    @Test
    public void getParent_test()
    {
        assertEquals(getParent("1.1.1"), "1.1");
        assertEquals(getParent("1.1"), "1");
        assertEquals(getParent("1"), "1");
    }

    @Test
    public void level_test()
    {
        assertEquals(level("1.1.1"), 3);
        assertEquals(level("1.1"), 2);
        assertEquals(level("1"), 1);
    }

    @Test
    public void getParentAtLevel_test()
    {
        assertEquals(getParentAt("1.1.1", 3), null);
        assertEquals(getParentAt("1.1.1", 2), "1.1");
        assertEquals(getParentAt("1.1.1", 1), "1");
    }
}
