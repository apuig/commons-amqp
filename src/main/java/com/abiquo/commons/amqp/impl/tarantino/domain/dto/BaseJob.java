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

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterRequest;

@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "@class")
public class BaseJob extends DatacenterRequest
{
    public final static int MAX_JOB_LEVEL = 1000;

    /** point separated */
    protected String id;

    private Boolean isRollback = Boolean.FALSE;

    public Boolean getIsRollback()
    {
        return isRollback;
    }

    public void setIsRollback(Boolean isRollback)
    {
        this.isRollback = isRollback;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public static String getParent(String id)
    {
        if (isRoot(id))
        {
            return id;
        }
        else
        {

            return id.substring(0, id.lastIndexOf('.'));
        }
    }

    public static boolean isRoot(String id)
    {
        return !id.contains(".");
    }

    public static int level(final String id)
    {
        return id.split("\\.").length;
    }

    public static String getRoot(final String id)
    {
        return getParentAt(id, 1);
    }

    public static String getParentAt(final String id, final int level)
    {
        if (level(id) <= level)
        {
            return null;
        }

        String[] parts = id.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++)
        {
            sb.append(parts[i]);
            if (i != level - 1)
            {
                sb.append(".");
            }
        }

        return sb.toString();
    }

}
