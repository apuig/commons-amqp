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
package com.abiquo.commons.amqp.impl.bpm.domain;

import com.abiquo.commons.amqp.util.JSONUtils;

public class StatefulDiskRequest extends BPMRequest
{
    private String storagePoolTarget;

    private String diskPath;

    private Integer nodeId;

    private Long volumeSize;

    private Integer enterpriseId;

    /**
     * Fields used on bundle and stateful
     */
    protected Integer idVirtualMachine;

    protected String creationUser;

    protected String templateName;

    protected boolean mustPowerOnWhenFinish;

    public StatefulDiskRequest()
    {
    }

    public StatefulDiskRequest(final Integer userId, final String storagePoolTarget,
        final String diskPath, final int nodeId, final Long volumeSize, final int enterpriseId,
        final Sender sender)
    {
        this.userId = userId;
        this.storagePoolTarget = storagePoolTarget;
        this.diskPath = diskPath;
        this.nodeId = nodeId;
        this.volumeSize = volumeSize;
        this.enterpriseId = enterpriseId;
        this.sender = sender;
    }

    public String getStoragePoolTarget()
    {
        return storagePoolTarget;
    }

    public void setStoragePoolTarget(final String storagePoolTarget)
    {
        this.storagePoolTarget = storagePoolTarget;
    }

    public String getDiskPath()
    {
        return diskPath;
    }

    public void setDiskPath(final String diskPath)
    {
        this.diskPath = diskPath;
    }

    public Integer getNodeId()
    {
        return nodeId;
    }

    public void setNodeId(final Integer nodeId)
    {
        this.nodeId = nodeId;
    }

    public Long getVolumeSize()
    {
        return volumeSize;
    }

    public void setVolumeSize(final Long volumeSize)
    {
        this.volumeSize = volumeSize;
    }

    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }

    public void setEnterpriseId(final Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }

    public Integer getIdVirtualMachine()
    {
        return idVirtualMachine;
    }

    public void setIdVirtualMachine(final Integer idVirtualMachine)
    {
        this.idVirtualMachine = idVirtualMachine;
    }

    public String getCreationUser()
    {
        return creationUser;
    }

    public void setCreationUser(final String creationUser)
    {
        this.creationUser = creationUser;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(final String templateName)
    {
        this.templateName = templateName;
    }

    public boolean isMustPowerOnWhenFinish()
    {
        return mustPowerOnWhenFinish;
    }

    public void setMustPowerOnWhenFinish(final boolean mustPowerOnWhenFinish)
    {
        this.mustPowerOnWhenFinish = mustPowerOnWhenFinish;
    }

    public static StatefulDiskRequest fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, StatefulDiskRequest.class);
    }
}
