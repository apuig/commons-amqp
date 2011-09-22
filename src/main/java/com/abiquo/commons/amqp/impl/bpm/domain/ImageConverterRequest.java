package com.abiquo.commons.amqp.impl.bpm.domain;

import com.abiquo.commons.amqp.util.JSONUtils;

public class ImageConverterRequest
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
        final String source, final String dest, final Integer enterpriseId, final int conversionId)
    {
        this.imagePathSource = sourcePath;
        this.imagePathDest = destPath;
        this.sourceFormat = source;
        this.destFormat = dest;
        this.conversionId = conversionId;
        this.enterpriseId = enterpriseId;
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
