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

/**
 * The states that a virtual machine can have.
 */
public enum State
{
    ON, OFF, PAUSED, ALLOCATED, CONFIGURED, UNKNOWN, NOT_ALLOCATED, LOCKED;

    public static State fromValue(final String value)
    {
        return State.valueOf(value.toUpperCase());
    }

    public State travel(final StateTransition transaction)
    {
        if (!transaction.isValidOrigin(this))
        {
            throw new RuntimeException("Invalid origin " + this + " for transaction " + transaction);
        }

        return transaction.getEndState();
    }
}
