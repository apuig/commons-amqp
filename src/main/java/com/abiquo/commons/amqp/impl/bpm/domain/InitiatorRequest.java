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

public class InitiatorRequest extends BPMRequest
{
    public InitiatorRequest()
    {
    }

    public InitiatorRequest(final Integer userId, final Integer enterpriseId,
        final Integer conversionId)
    {
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.conversionId = conversionId;
        this.sender = Sender.INITIATOR;
    }

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
