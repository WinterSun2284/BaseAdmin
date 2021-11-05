package cn.wintersun.basetask.jobservice;

import cn.wintersun.basetask.dto.QuartzJobModule;
import org.quartz.JobDataMap;

import java.util.List;
import java.util.Map;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basetask.jobservice.QuartzJobService
 * @create 2021-11-04-10:22
 * @Description TODO
 */
public interface QuartzJobService {


    /**
     * : 添加一个定时任务
     */
    void addJob(QuartzJobModule quartzModel);

    /**
     * :修改任务，（可以修改任务名，任务类，触发时间）
     * 原理：移除原来的任务，添加新的任务
     *
     * @param oldJobName ：原任务名
     */
    void modifyJob(
            String oldJobName,
            String jobName,
            Class jobclass,
            String cron,
            String triggerGroupName,
            String jobGroupName,
            JobDataMap jobDataMap);

    /**
     * : 移除一个任务
     */
    void removeJob(String jobName,
                   String jobGroupName,
                   String triggerName,
                   String triggerGroupName);

    /**
     * :暂停一个任务
     */
    void pauseJob(String jobName, String jobGroupName);

    /**
     * :恢复一个任务
     */
    void resumeJob(String jobName, String jobGroupName);

    /**
     * :获取最近5次执行时间
     */
    List<String> getRecentTriggerTime(String cron);

    List<Map<String, String>> getAll();

}
