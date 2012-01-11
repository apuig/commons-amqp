package com.abiquo.commons.amqp.impl.tarantino.domain;

public class DhcpOptionCom
{

    protected Integer option;

    protected String gateway;

    protected String networkAddress;

    protected Integer mask;

    protected String netmask;

    // public DhcpOptionCom(final Integer mask, final Integer option, final String gateway,
    // final String networkAddress, final String netmask)
    // {
    // this.option = option;
    // this.gateway = gateway;
    // this.networkAddress = networkAddress;
    // this.mask = mask;
    // this.netmask = netmask;
    // }

    public Integer getOption()
    {
        return option;
    }

    public void setOption(final Integer option)
    {
        this.option = option;
    }

    public String getGateway()
    {
        return gateway;
    }

    public void setGateway(final String gateway)
    {
        this.gateway = gateway;
    }

    public String getNetworkAddress()
    {
        return networkAddress;
    }

    public void setNetworkAddress(final String networkAddress)
    {
        this.networkAddress = networkAddress;
    }

    public Integer getMask()
    {
        return mask;
    }

    public void setMask(final Integer mask)
    {
        this.mask = mask;
    }

    public String getNetmask()
    {
        return netmask;
    }

    public void setNetmask(final String netmask)
    {
        this.netmask = netmask;
    }

}
