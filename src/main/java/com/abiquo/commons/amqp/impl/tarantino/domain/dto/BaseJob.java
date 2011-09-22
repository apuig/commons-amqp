package com.abiquo.commons.amqp.impl.tarantino.domain.dto;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "@class")
public class BaseJob
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
