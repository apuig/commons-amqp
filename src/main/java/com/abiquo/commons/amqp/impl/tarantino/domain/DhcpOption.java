package com.abiquo.commons.amqp.impl.tarantino.domain;

public class DhcpOption
{

    protected Integer option;

    protected String gateway;

    protected String address;

    protected String networkAddress;

    protected Integer mask;

    protected Integer netmask;

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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(final String address)
    {
        this.address = address;
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

    public Integer getNetmask()
    {
        return netmask;
    }

    public void setNetmask(final Integer netmask)
    {
        this.netmask = netmask;
    }

}
