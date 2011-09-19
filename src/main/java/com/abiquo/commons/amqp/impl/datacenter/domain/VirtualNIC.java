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

package com.abiquo.commons.amqp.impl.datacenter.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

public class VirtualNIC extends DHCPRule
{
    protected String vSwitchName;

    protected String networkName;

    protected int vlanTag;

    protected String forwardMode;

    @Deprecated
    // use DCHPRule.ip
    protected String netAddress;


    
    protected int sequence;


    public String getVSwitchName()
    {
        return vSwitchName;
    }

    public void setVSwitchName(String vSwitchName)
    {
        this.vSwitchName = vSwitchName;
    }

   

    @JsonIgnore
    public String getRuleName()
    {
        return "host_" + getMacAddress().replaceAll(":", "");
    }

    public String getNetworkName()
    {
        return networkName;
    }

    public void setNetworkName(String value)
    {
        this.networkName = value;
    }

    public int getVlanTag()
    {
        return vlanTag;
    }

    public void setVlanTag(int value)
    {
        this.vlanTag = value;
    }

    public String getForwardMode()
    {
        return forwardMode;
    }

    public void setForwardMode(String value)
    {
        this.forwardMode = value;
    }

    @Deprecated
    // use DCHPRule.ip
    public String getNetAddress()
    {
        return netAddress;
    }

    @Deprecated
    // use DCHPRule.ip
    public void setNetAddress(String value)
    {
        this.netAddress = value;
    }

    public int getSequence()
    {
        return sequence;
    }

    public void setSequence(int value)
    {
        this.sequence = value;
    }
}
