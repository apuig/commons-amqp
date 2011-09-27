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

import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.CONFIGURE;
import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.DECONFIGURE;
import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.PAUSE;
import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.POWEROFF;
import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.POWERON;
import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.RECONFIGURE;
import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.RESET;
import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.RESUME;
import static com.abiquo.commons.amqp.impl.datacenter.domain.StateTransition.SNAPSHOT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

public class StateTransitionTest
{
    @Test
    public void test_availableTransitions()
    {
        Set<StateTransition> transactions = new HashSet<StateTransition>();

        transactions.add(StateTransition.CONFIGURE);
        transactions.add(StateTransition.DECONFIGURE);
        transactions.add(StateTransition.PAUSE);
        transactions.add(StateTransition.POWEROFF);
        transactions.add(StateTransition.POWERON);
        transactions.add(StateTransition.RECONFIGURE);
        transactions.add(StateTransition.RESET);
        transactions.add(StateTransition.RESUME);
        transactions.add(StateTransition.SNAPSHOT);

        assertEquals(StateTransition.values().length, 9);

        for (StateTransition transaction : StateTransition.values())
        {
            assertTrue(transactions.contains(transaction));
        }
    }

    @Test
    public void test_configure()
    {
        assertEquals(CONFIGURE.getEndState(), State.CONFIGURED);
        assertTrue(CONFIGURE.isValidOrigin(State.UNDEPLOYED));
        assertFalse(CONFIGURE.isValidOrigin(State.ON));
        assertFalse(CONFIGURE.isValidOrigin(State.OFF));
        assertFalse(CONFIGURE.isValidOrigin(State.UNKNOWN));
        assertFalse(CONFIGURE.isValidOrigin(State.PAUSED));
        assertFalse(CONFIGURE.isValidOrigin(State.CONFIGURED));
    }

    @Test
    public void test_reconfigure()
    {
        assertEquals(RECONFIGURE.getEndState(), State.CONFIGURED);
        assertTrue(RECONFIGURE.isValidOrigin(State.OFF));
        assertFalse(RECONFIGURE.isValidOrigin(State.UNDEPLOYED));
        assertFalse(RECONFIGURE.isValidOrigin(State.ON));
        assertFalse(RECONFIGURE.isValidOrigin(State.UNKNOWN));
        assertFalse(RECONFIGURE.isValidOrigin(State.PAUSED));
        assertFalse(RECONFIGURE.isValidOrigin(State.CONFIGURED));
    }

    @Test
    public void test_deconfigure()
    {
        assertEquals(DECONFIGURE.getEndState(), State.UNDEPLOYED);
        assertTrue(DECONFIGURE.isValidOrigin(State.CONFIGURED));
        assertFalse(DECONFIGURE.isValidOrigin(State.ON));
        assertFalse(DECONFIGURE.isValidOrigin(State.OFF));
        assertFalse(DECONFIGURE.isValidOrigin(State.UNDEPLOYED));
        assertFalse(DECONFIGURE.isValidOrigin(State.UNKNOWN));
        assertFalse(DECONFIGURE.isValidOrigin(State.PAUSED));
    }

    @Test
    public void test_powerOn()
    {
        assertEquals(POWERON.getEndState(), State.ON);
        assertTrue(POWERON.isValidOrigin(State.OFF));
        assertTrue(POWERON.isValidOrigin(State.CONFIGURED));
        assertFalse(POWERON.isValidOrigin(State.ON));
        assertFalse(POWERON.isValidOrigin(State.UNDEPLOYED));
        assertFalse(POWERON.isValidOrigin(State.UNKNOWN));
        assertFalse(POWERON.isValidOrigin(State.PAUSED));
    }

    @Test
    public void test_powerOff()
    {
        assertEquals(POWEROFF.getEndState(), State.OFF);
        assertTrue(POWEROFF.isValidOrigin(State.ON));
        assertFalse(POWEROFF.isValidOrigin(State.OFF));
        assertFalse(POWEROFF.isValidOrigin(State.UNDEPLOYED));
        assertFalse(POWEROFF.isValidOrigin(State.UNKNOWN));
        assertFalse(POWEROFF.isValidOrigin(State.PAUSED));
        assertFalse(POWEROFF.isValidOrigin(State.CONFIGURED));
    }

    @Test
    public void test_reset()
    {
        assertEquals(RESET.getEndState(), State.ON);
        assertTrue(RESET.isValidOrigin(State.ON));
        assertFalse(RESET.isValidOrigin(State.OFF));
        assertFalse(RESET.isValidOrigin(State.UNDEPLOYED));
        assertFalse(RESET.isValidOrigin(State.UNKNOWN));
        assertFalse(RESET.isValidOrigin(State.PAUSED));
        assertFalse(RESET.isValidOrigin(State.CONFIGURED));
    }

    @Test
    public void test_pause()
    {
        assertEquals(PAUSE.getEndState(), State.PAUSED);
        assertTrue(PAUSE.isValidOrigin(State.ON));
        assertFalse(PAUSE.isValidOrigin(State.OFF));
        assertFalse(PAUSE.isValidOrigin(State.UNDEPLOYED));
        assertFalse(PAUSE.isValidOrigin(State.UNKNOWN));
        assertFalse(PAUSE.isValidOrigin(State.PAUSED));
        assertFalse(PAUSE.isValidOrigin(State.CONFIGURED));
    }

    @Test
    public void test_resume()
    {
        assertEquals(RESUME.getEndState(), State.ON);
        assertTrue(RESUME.isValidOrigin(State.PAUSED));
        assertFalse(RESUME.isValidOrigin(State.ON));
        assertFalse(RESUME.isValidOrigin(State.OFF));
        assertFalse(RESUME.isValidOrigin(State.UNDEPLOYED));
        assertFalse(RESUME.isValidOrigin(State.UNKNOWN));
        assertFalse(RESUME.isValidOrigin(State.CONFIGURED));
    }

    @Test
    public void test_snapshot()
    {
        assertEquals(SNAPSHOT.getEndState(), State.OFF);
        assertTrue(SNAPSHOT.isValidOrigin(State.OFF));
        assertFalse(SNAPSHOT.isValidOrigin(State.ON));
        assertFalse(SNAPSHOT.isValidOrigin(State.UNDEPLOYED));
        assertFalse(SNAPSHOT.isValidOrigin(State.UNKNOWN));
        assertFalse(SNAPSHOT.isValidOrigin(State.PAUSED));
        assertFalse(SNAPSHOT.isValidOrigin(State.CONFIGURED));
    }
}
