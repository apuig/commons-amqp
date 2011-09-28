package com.abiquo.commons.amqp.impl.tarantino;

import org.testng.annotations.Test;

import static com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob.getParent;
import static com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob.getParentAt;
import static com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob.isRoot;
import static com.abiquo.commons.amqp.impl.tarantino.domain.dto.BaseJob.level;
import static org.testng.Assert.*;

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
