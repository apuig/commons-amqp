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
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.abiquo.commons.amqp.impl.tarantino.domain.State;
import com.abiquo.commons.amqp.impl.tarantino.domain.StateTransition;

public class StateTest
{
    @Test
    public void test_availableStates()
    {
        Set<State> states = new HashSet<State>();

        states.add(State.CONFIGURED);
        states.add(State.OFF);
        states.add(State.ON);
        states.add(State.PAUSED);
        states.add(State.ALLOCATED);
        states.add(State.UNKNOWN);
        states.add(State.LOCKED);
        states.add(State.NOT_ALLOCATED);
        assertEquals(State.values().length, 8);

        for (State state : State.values())
        {
            assertTrue(states.contains(state));
        }
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void test_invalidTravel()
    {
        State.UNKNOWN.travel(StateTransition.CONFIGURE);
    }

    @Test
    public void test_configureTravel()
    {
        assertEquals(State.ALLOCATED.travel(StateTransition.CONFIGURE), State.CONFIGURED);
    }

    @Test
    public void test_reconfigureTravel()
    {
        assertEquals(State.OFF.travel(StateTransition.RECONFIGURE), State.CONFIGURED);
    }

    @Test
    public void test_deconfigureTravel()
    {
        assertEquals(State.CONFIGURED.travel(StateTransition.DECONFIGURE), State.ALLOCATED);
    }

    @Test
    public void test_powerOnTravel()
    {
        assertEquals(State.OFF.travel(StateTransition.POWERON), State.ON);
        assertEquals(State.CONFIGURED.travel(StateTransition.POWERON), State.ON);
    }

    @Test
    public void test_powerOffTravel()
    {
        assertEquals(State.ON.travel(StateTransition.POWEROFF), State.OFF);
    }

    @Test
    public void test_resetTravel()
    {
        assertEquals(State.ON.travel(StateTransition.RESET), State.ON);
    }

    @Test
    public void test_pauseTravel()
    {
        assertEquals(State.ON.travel(StateTransition.PAUSE), State.PAUSED);
    }

    @Test
    public void test_resumeTravel()
    {
        assertEquals(State.PAUSED.travel(StateTransition.RESUME), State.ON);
    }

    @Test
    public void test_snapshotTravel()
    {
        assertEquals(State.OFF.travel(StateTransition.SNAPSHOT), State.OFF);
    }
}
