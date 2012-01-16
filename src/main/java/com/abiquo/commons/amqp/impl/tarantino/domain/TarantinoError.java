/**
 * Abiquo premium edition
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

package com.abiquo.commons.amqp.impl.tarantino.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum TarantinoError
{
    THURMAN0("TH-0", "Hypervisor type not supported"),

    THURMAN1("TH-1", "Datacenter operation not supported"),

    THURMAN2("TH-2", "Invalid request state"),

    CONFIG("VM-01", "Error configuring virtual machine"),

    RECONFIG("VM-02", "Error reconfiguring virtual machine"),

    DEPLOY("VM-03", "Error deploying virtual machine"),

    CREATE_VM("VM-04", "Error creating virtual machine"),

    DESTROY_VM("VM-05", "Error destroying virtual machine"),

    DECONFIGURE("VM-06", "The virtual machine couldn't be deconfigured"),

    SNAPSHOT("VM-07", "Error snapshoting the virtual machine"),

    DISK_NOT_FOUND("VM-08", "Primary disk not found"),

    CLONING_DISK("VM-09", "Could not copy primary disk to the hypervisor"),

    ATTACHING_STATEFUL_DISK("VM-10",
        "Could not attach primary stateful disk to the virtual machine"),

    REMOVING_DISK("VM-11", "Could not delete the primary disk"),

    DETACHING_STATEFUL_DISK("VM-12",
        "Could not detach primary stateful disk from the virtual machine"),

    CHANGING_STATE("VM-13", "Error changing a state on VM"),

    EXECUTING_ACTION("VM-14", "Generic error performing an action on VM"),

    UNABLE_RECONFIGURE("VM-15",
        "Unable to reconfigure. The definitions refer to different virtual machines"),

    ATTACHING_DISK("VM-16", "Could not attach auxiliary disk to the virtual machine"),

    DETACHING_DISK("VM-17", "Could not detach auxiliary disk from the virtual machine"),

    // VIRTUAL MACHINE
    VIRTUAL_MACHINE_NOT_FOUND("VM-18", "Virtual Machine not found on the hypervisor"),

    VIRTUAL_MACHINE_RETRIEVE_ERROR("VM-19", "Could not retrieve the virtual machine"),

    VIRTUAL_MACHINE_ALREADY_EXIST("VM-20",
        "A virtual machine with the same UUID already exists in the hypervisor"),

    // NETWORK
    NETWORK_DECONFIGURE("NET-0",
        "Could not deconfigure the network resources of the virtual machine"),

    NETWORK_VSWITCH_PORT("NET-1",
        "The port group attached to the virtual switch doesn't match the expected virtual switch"),

    NETWORK_VSWITCH_NOT_FOUND(
        "NET-2",
        "The Virtual Switch couln't be found in the hypervisor. The virtual machine networking resources can't be configured"),

    NETWORK_CONFIGURATION("NET-3", "Could not configure the virtual nic"),

    NETWORK_NOT_FOUND("NET-4", "Could not obtain the target network"),

    NO_NIC_DEFINED("NET-5", "No NIC defined"),

    // DATASTORE
    DATASTORE_NOT_FOUND("DS-0", "Target datastore doesn't exist in the hypervisor"),

    DATASTORE_NOT_ACCESSIBLE("DS-1", "The given datastore is not accessible"),

    CANT_CREATE_DATASTORE("DS-2", "Could not create the target datastore"),

    // AIM
    AIM_COMMUNICATION("AIM-0", "Error communicating with abiquo AIM"),

    // REPOSITORY
    REPOSITORY_CONFIGURATION("REP-0", "Can not configure the repository datastore"),

    // HYPERVISOR
    HYPERVISOR_LICENSE("HYP-00", "The hypervisor license version is not compatible with Abiquo"),

    HYPERVISOR_CONNECTION("HYP-01", "Unable to connect; invalid hypervisor (ip or port) location"),

    HYPERVISOR_DISCONNECTION("HYP-02",
        "Unable to disconnect; invalid hypervisor (ip or port) location"),

    HYPERVISOR_CONFIGURATION("HYP-03", "Hypervisor is not properly configured to eb used in Abiquo"),

    HYPERVISOR_UNREADABLE("HYP-04", "Could not read hypervisor configuration"),

    // ISCSI

    ISCSI_CONTROLLER_NOT_PRESENT("SCS-0", "iSCSI controller not found"),

    ISCSI_CONTROLLER_NOT_ENABLED("SCS-1", "iSCSI controller not enabled"),

    CANT_UPDATE_ISCSI_LUNS("SCS-2", "Could not update the iSCSI target paths"),

    VOLUME_NOT_FOUND("SCS-3", "iSCSI target IQN not found"),

    CANT_CONFIGURE_AUXILARY_DISKS("SCS-4", "Could not configure auxiliary disks"),

    // External Disks
    ED_CAN_NOT_FIND_IDE_CONTROLLER("ED-0", "Can not find IDE controller in Virtual Machine"),

    // vCENTER

    VCENTER_CONNECT("VCENTER-0", "Could not connect to vCenter"),

    VCENTER_NOT_CONFIGURED("VCENTER-1", "Required vCenter is not configured"),

    VCENTER_UNREGISTER_VM("VCENTER-2", "Could not unregiter the virtual machine from vCenter"),

    // DISK CONTROLLERS
    ESXI_INCOMPATIBLE_DISK_CONTROLER("CNTRL-0",
        "ESX can configure an SPARSE disk image in a SCSI controler"),

    // DISTRIBUTED VIRTUAL SWITCH
    ESXI_DVS_CREATE_PORT_GROUP("DVS-0", "Could not create the port group"),

    ESXI_DVS_GET_PORT_GROUP("DVS-1", "Could not retrieve the port group"),

    ESXI_DVS_DELETE_PORT_GROUP("DVS-2", "Can't delete port group"),

    ESXI_DVS_ATTACH_NIC_TO_PORT_GROUP("DVS-3", "Could not associate the NIC to the port group"),

    ESXI_DVS_CREATE_NIC("DVS-4", "Coult not create the NIC in the DVS"),

    ESXI_DVS_INCONSISTENT_STATE("DVS-5",
        "Inconsistent state attaching NIC to VirtualMachine in vCenter"),

    ESXI_DVS_HOST_NOT_CONNECTED(
        "DVS-6",
        "Host that stores the virtual machine is not connected. It may happens due the incompatibility between Abiquo's HA and vCenter."),

    // deleting duplicated virtual machines
    ESXI_DUPLICATED_TASK("ESXI_DUP_1", "Action in duplicated machine failed"),

    // DHCP omapi
    DHCP_SERVER_COMM("DHCP-0", "Could not connect to DHCP server"),

    DHCP_RULE_NOT_FOUND("DHCP-1", "OMAPI rule not found"), //

    DHCP_RULE_DUPLICATED("DHCP-2",
        "An OMAPI rule with the same name already exists in the DHCP server"),

    DHCP_RULE_INVALID("DHCP-3", "Invalid DHCP rule"),

    // VALIDATION ERRORS IN HYPERVISOR SANDWICH
    EXIST_VALIDATION("VAL-0", "The requested virtual machine does not exist"),

    NON_EXIST_VALIDATION("VAL-1", "The requested virtual machine already exists"),

    INVALID_TRANSITION("VAL-2", "Invalid transition from current virtual machine state"),

    NON_UNIQUE_SECONDARIES_SEQUENCE("VAL-3",
        "Non unique secondaries sequences in virtual machine definition"),

    NON_UNIQUE_SECONDARIES("VAL-4", "Non unique secondaries in virtual machine definition"),

    // UNHANDLED
    UNHANDLED("MF-0", "Unhandled DatacenterJob exception"),

    // Task related
    INVALID_TASKID("TK-0", "Invalid DatacenterTask ID (or child Jobs doesn't respect parent ID)");

    /** Internal error code */
    private String code;

    /** Description message */
    private String message;

    public String getCode()
    {
        return String.valueOf(this.code);
    }

    public String getMessage()
    {
        return this.message;
    }

    TarantinoError(final String code, final String message)
    {
        this.code = code;
        this.message = message;
    }

    public static void main(final String[] args)
    {
        TarantinoError[] errors = TarantinoError.values();
        Arrays.sort(errors, new Comparator<TarantinoError>()
        {
            @Override
            public int compare(final TarantinoError err1, final TarantinoError err2)
            {
                return String.CASE_INSENSITIVE_ORDER.compare(err1.code, err2.code);
            }

        });

        // Outputs all errors in wiki table format
        for (TarantinoError error : errors)
        {
            System.out.println(String.format("| %s | %s | %s |", error.code, error.message,
                error.name()));
        }
    }
}
