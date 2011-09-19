package com.abiquo.commons.amqp.impl.datacenter.domain.dto;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

import com.abiquo.commons.amqp.impl.datacenter.domain.StateTransaction;
import com.abiquo.commons.amqp.impl.datacenter.domain.operations.ApplyVirtualMachineStateOp;
import com.abiquo.commons.amqp.impl.datacenter.domain.operations.ConfigureVirtualMachineOp;
import com.abiquo.commons.amqp.impl.datacenter.domain.operations.DatacenterJob;

;

/**
 * Dependent or independent BaseJob collection
 */
@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "@class")
public class DatacenterTasks extends BaseJob
{
    // TODO will be better have a map <jobid, BaseJob>
    private List<BaseJob> jobs;

    private Boolean dependent;

    public boolean isDependent()
    {
        return dependent;
    }

    public void setDependent(boolean dependent)
    {
        this.dependent = dependent;
    }

    public List<BaseJob> getJobs()
    {
        if (jobs == null)
        {
            jobs = new LinkedList<BaseJob>();
        }

        return jobs;
    }

    public List<String> getJobsId()
    {
        List<String> ids = new LinkedList<String>();
        for (BaseJob bj : jobs)
        {
            ids.add(bj.id);
        }
        return ids;
    }

    /**
     * Utility to get a job (child or recursive)
     * <p>
     * TODO use a BaseJob map
     * 
     * @return null if not found
     */
    public BaseJob getJob(final String jobId)
    {
        int dif = level(jobId) - level(id);

        if (dif <= 0)
        {
            return null; // invalid
        }

        if (dif == 1)
        {
            return getJobInCurrentLevel(jobId);
        }
        else
        {
            final String thisparent = getParentAt(jobId, level(id) + 1);

            return ((DatacenterTasks) getJobInCurrentLevel(thisparent)).getJob(jobId);
        }
    }

    private BaseJob getJobInCurrentLevel(final String jobId)
    {
        for (BaseJob bj : getJobs())
        {
            if (bj.getId().equalsIgnoreCase(jobId))
            {
                return bj;
            }
        }

        return null;
    }

    public static DatacenterTasks rollback(DatacenterTasks t)
    {
        DatacenterTasks tt = new DatacenterTasks();

        tt.setDependent(t.dependent);
        tt.setId(t.id);
        tt.setIsRollback(true);

        List<BaseJob> rjobs = new LinkedList<BaseJob>();

        for (BaseJob bj : t.getJobs())
        {
            if (bj instanceof DatacenterTasks)
            {
                rjobs.add(rollback((DatacenterTasks) bj));
            }
            else
            // DatacenterJob
            {
                rjobs.add(rollback((DatacenterJob) bj));
            }
        }

        Collections.reverse(rjobs);

        tt.getJobs().addAll(rjobs);

        return tt;
    }

    private static DatacenterJob rollback(DatacenterJob j)
    {

        if (j instanceof ConfigureVirtualMachineOp)
        {
            ApplyVirtualMachineStateOp jj = new ApplyVirtualMachineStateOp();

            jj.setTransaction(StateTransaction.DECONFIGURE);
            jj.setHypervisorConnection(j.getHypervisorConnection());
            jj.setVirtualMachine(j.getVirtualMachine());
            jj.setIsRollback(true);
            jj.setId(j.id);

            return jj;
        }
        else if (j instanceof ApplyVirtualMachineStateOp)
        {
            ApplyVirtualMachineStateOp jj = new ApplyVirtualMachineStateOp();

            jj.setTransaction(StateTransaction.rollback(((ApplyVirtualMachineStateOp) j)
                .getTransaction()));
            jj.setHypervisorConnection(j.getHypervisorConnection());
            jj.setVirtualMachine(j.getVirtualMachine());
            jj.setIsRollback(true);
            jj.setId(j.id);

            return jj;
        }
        else
        {
            // TODO reconfigure, snapshot
            throw new RuntimeException("Rollback not implemented for " + j.getClass().getName());
        }
    }
}
