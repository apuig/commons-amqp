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

import static com.abiquo.commons.amqp.impl.datacenter.domain.State.CONFIGURED;
import static com.abiquo.commons.amqp.impl.datacenter.domain.State.OFF;
import static com.abiquo.commons.amqp.impl.datacenter.domain.State.ON;
import static com.abiquo.commons.amqp.impl.datacenter.domain.State.PAUSED;
import static com.abiquo.commons.amqp.impl.datacenter.domain.State.UNDEPLOYED;
import static java.util.Collections.singleton;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Valid transactions between {@link State}.
 */
public enum StateTransition
{
    // Configure transition
    CONFIGURE(singleton(UNDEPLOYED), CONFIGURED),

    // Reconfigure transition
    RECONFIGURE(singleton(OFF), CONFIGURED),

    // Deconfigure transition
    DECONFIGURE(singleton(CONFIGURED), UNDEPLOYED),

    // PowerOn transition
    POWERON(new HashSet<State>(Arrays.asList(CONFIGURED, OFF)), ON),

    // PowerOff transition
    POWEROFF(singleton(ON), OFF),

    // Reset transition
    RESET(singleton(ON), ON),

    // Pause transition
    PAUSE(singleton(ON), PAUSED),

    // Resume transition
    RESUME(singleton(PAUSED), ON),

    // Snapshot transition
    SNAPSHOT(singleton(OFF), OFF);

    private Set<State> origins;

    private State end;

    private StateTransition(Set<State> origins, State end)
    {
        this.origins = origins;
        this.end = end;
    }

    public State getEndState()
    {
        return this.end;
    }

    public boolean isValidOrigin(State origin)
    {
        return this.origins.contains(origin);
    }

    public static StateTransition fromValue(String value)
    {
        return StateTransition.valueOf(value.toUpperCase());
    }

    public static StateTransition rollback(StateTransition s)
    {
        switch (s)
        {
            case CONFIGURE:
                return DECONFIGURE;

            case DECONFIGURE:
                return CONFIGURE;

            case POWEROFF:
                return POWERON;
            case POWERON:
                return POWEROFF;

            case PAUSE:
                return RESUME;

            case RESUME:
                return PAUSE;

            default: // reset reconfigure snapshot
                return s;
        }
    }
}
