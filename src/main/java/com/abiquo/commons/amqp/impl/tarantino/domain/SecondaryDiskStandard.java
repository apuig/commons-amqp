/**
 * 
 */
package com.abiquo.commons.amqp.impl.tarantino.domain;

/**
 * @author jdevesa
 */
public class SecondaryDiskStandard extends DiskStandard
{
    protected int sequence;

    public int getSequence()
    {
        return sequence;
    }

    public void setSequence(final int value)
    {
        this.sequence = value;
    }
}
