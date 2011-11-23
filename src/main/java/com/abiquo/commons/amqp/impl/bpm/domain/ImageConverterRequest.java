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

public class ImageConverterRequest extends BPMRequest
{
    private String imagePathSource;

    private String imagePathDest;

    private String sourceFormat;

    private String destFormat;

    private Integer conversionId;

    private Integer enterpriseId;

    public ImageConverterRequest()
    {
        this.enterpriseId = 0;
    }

    public ImageConverterRequest(final String sourcePath, final String destPath,
        final String source, final String dest, final Integer enterpriseId, final int conversionId,
        final Sender sender)
    {
        this.imagePathSource = sourcePath;
        this.imagePathDest = destPath;
        this.sourceFormat = source;
        this.destFormat = dest;
        this.conversionId = conversionId;
        this.enterpriseId = enterpriseId;
        this.sender = sender;
    }

    public String getImagePathSource()
    {
        return imagePathSource;
    }

    public void setImagePathSource(final String imagePathSource)
    {
        this.imagePathSource = imagePathSource;
    }

    public String getImagePathDest()
    {
        return imagePathDest;
    }

    public void setImagePathDest(final String imagePathDest)
    {
        this.imagePathDest = imagePathDest;
    }

    public String getSourceFormat()
    {
        return sourceFormat;
    }

    public void setSourceFormat(final String sourceFormat)
    {
        this.sourceFormat = sourceFormat;
    }

    public String getDestFormat()
    {
        return destFormat;
    }

    public void setDestFormat(final String destFormat)
    {
        this.destFormat = destFormat;
    }

    public Integer getConversionId()
    {
        return conversionId;
    }

    public void setConversionId(final Integer conversionId)
    {
        this.conversionId = conversionId;
    }

    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }

    public void setEnterpriseId(final Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }

    public static ImageConverterRequest fromByteArray(final byte[] bytes)
    {
        return JSONUtils.deserialize(bytes, ImageConverterRequest.class);
    }
}
