package com.abiquo.commons.amqp.impl.tarantino.domain;

import com.abiquo.commons.amqp.impl.datacenter.domain.DatacenterNotification;
import com.abiquo.commons.amqp.util.JSONUtils;

public class TarantinoResponse extends DatacenterNotification
{
    protected String jobId;

    protected String result;

    public String getJobId()
    {
        return jobId;
    }

    public void setJobId(String jobId)
    {
        this.jobId = jobId;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public static TarantinoResponse fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, TarantinoResponse.class);
    }
}
