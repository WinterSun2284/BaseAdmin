package cn.wintersun.basetask.jobservice.impl;

import cn.wintersun.basetask.dto.QuartzJobModule;
import cn.wintersun.basetask.jobservice.QuartzJobService;
import org.quartz.JobDataMap;

import java.util.List;
import java.util.Map;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basetask.jobservice.impl.QuartzJobSlaveServiceImpl
 * @create 2021-11-04-10:26
 * @Description TODO
 */
public class QuartzJobSlaveServiceImpl implements QuartzJobService {
    @Override
    public void addJob(QuartzJobModule quartzModel) {

    }

    @Override
    public void modifyJob(String oldJobName, String jobName, Class jobclass, String cron, String triggerGroupName, String jobGroupName, JobDataMap jobDataMap) {

    }

    @Override
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {

    }

    @Override
    public void pauseJob(String jobName, String jobGroupName) {

    }

    @Override
    public void resumeJob(String jobName, String jobGroupName) {

    }

    @Override
    public List<String> getRecentTriggerTime(String cron) {
        return null;
    }

    @Override
    public List<Map<String, String>> getAll() {
        return null;
    }
}
