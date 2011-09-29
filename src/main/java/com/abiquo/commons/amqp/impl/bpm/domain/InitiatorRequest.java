package com.abiquo.commons.amqp.impl.bpm.domain;

public class InitiatorRequest extends BPMRequest
{

    private Integer enterpriseId;

    /** The id of the conversion of the Stateful process being processed. */
    private Integer conversionId;

    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }

    public void setEnterpriseId(final Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }

    public Integer getConversionId()
    {
        return conversionId;
    }

    public void setConversionId(final Integer conversionId)
    {
        this.conversionId = conversionId;
    }

}
