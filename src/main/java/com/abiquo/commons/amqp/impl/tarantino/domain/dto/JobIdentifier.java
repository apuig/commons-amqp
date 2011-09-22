package com.abiquo.commons.amqp.impl.tarantino.domain.dto;

public interface JobIdentifier
{

    public abstract String getId();

    public abstract void setId(String id);

    public abstract String getParent();

    public abstract boolean isRoot();

    public abstract int level();

    public abstract String getParentAt(final int level);

}
