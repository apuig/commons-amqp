package com.abiquo.commons.amqp.impl.bpm.domain;

import com.abiquo.commons.amqp.util.JSONUtils;

public class StatefulDiskRequest extends BPMRequest
{
    private String storagePoolTarget;

    private String diskPath;

    private Integer nodeId;

    private Long volumeSize;

    private Integer enterpriseId;

    public StatefulDiskRequest()
    {
    }

    public StatefulDiskRequest(final String storagePoolTarget, final String diskPath,
        final int nodeId, final Long volumeSize, final int enterpriseId)
    {
        this.storagePoolTarget = storagePoolTarget;
        this.diskPath = diskPath;
        this.setNodeId(nodeId);
        this.volumeSize = volumeSize;
        this.enterpriseId = enterpriseId;
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

    public static StatefulDiskRequest fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, StatefulDiskRequest.class);
    }
}
