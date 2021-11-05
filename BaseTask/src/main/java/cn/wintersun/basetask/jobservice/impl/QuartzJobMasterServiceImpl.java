package cn.wintersun.basetask.jobservice.impl;

import cn.wintersun.basetask.constant.GlobalConstant;
import cn.wintersun.basetask.dto.QuartzJobModule;
import cn.wintersun.basetask.jobservice.QuartzJobService;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TODO
 *
 * @author: WinterSun
 */
public class QuartzJobMasterServiceImpl implements QuartzJobService {

    public static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobMasterServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    /**
     * : 添加一个定时任务
     */
    @Override
    public void addJob(QuartzJobModule quartzModel) {
        if (quartzModel.verify()) {
            try {
                JobDetail job = JobBuilder.newJob(quartzModel.getJobClass())
                        .withIdentity(quartzModel.getJobName(), quartzModel.getJobGroupName())
                        .setJobData(quartzModel.getJobDataMap()).build();
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzModel.getCron());
                // 按新的cronExpression表达式构建一个新的trigger
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(quartzModel.getTriggerName(), quartzModel.getTriggerGroupName())
                        //.startAt(quartzModel.getStartTime()).endAt(quartzModel.getEndTime())
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler.scheduleJob(job, trigger);
                // 启动
                if (!scheduler.isShutdown()) {
                    scheduler.start();
                }
            } catch (SchedulerException e) {
                LOGGER.error("Add quartz job error, jobName = {}", quartzModel.getJobName());
            }

        } else {
            LOGGER.error("QuartzModel is invalid!");
        }
    }

    /**
     * : 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     */
    public void modifyJobTime(String jobName, String cron, Date startDate, Date endDate) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, GlobalConstant.QZ_TRIGGER_GROUP_NAME);

        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .startAt(startDate)
                        .endAt(endDate).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * :修改任务，（可以修改任务名，任务类，触发时间）
     * 原理：移除原来的任务，添加新的任务
     *
     * @param oldJobName ：原任务名
     */
    public void modifyJob(String oldJobName, String jobName, Class jobclass, String cron) {
        TriggerKey triggerKey = TriggerKey.triggerKey(oldJobName, GlobalConstant.QZ_TRIGGER_GROUP_NAME);
        JobKey jobKey = JobKey.jobKey(oldJobName, GlobalConstant.QZ_JOB_GROUP_NAME);
        try {
            Trigger trigger = (Trigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
            System.err.println("移除任务:" + oldJobName);

            JobDetail job = JobBuilder.newJob(jobclass).withIdentity(jobName,
                    GlobalConstant.QZ_JOB_GROUP_NAME)
                    .build();
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            // 按新的cronExpression表达式构建一个新的trigger
            Trigger newTrigger = TriggerBuilder.newTrigger().withIdentity(jobName,
                    GlobalConstant.QZ_TRIGGER_GROUP_NAME)
                    .withSchedule(scheduleBuilder).build();

            // 交给scheduler去调度
            scheduler.scheduleJob(job, newTrigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
                System.err.println("添加新任务:" + jobName);
            }
            System.err.println("修改任务【" + oldJobName + "】为:" + jobName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * :修改任务，（可以修改任务名，任务类，触发时间）
     * 原理：移除原来的任务，添加新的任务
     *
     * @param oldJobName ：原任务名
     */
    @Override
    public void modifyJob(
            String oldJobName,
            String jobName,
            Class jobclass,
            String cron,
            String triggerGroupName,
            String jobGroupName,
            JobDataMap jobDataMap) {
        TriggerKey triggerKey = TriggerKey.triggerKey(oldJobName, triggerGroupName);
        JobKey jobKey = JobKey.jobKey(oldJobName, jobGroupName);
        try {
            /*Trigger trigger = (Trigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }*/
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
            JobDetail job = JobBuilder.newJob(jobclass).withIdentity(jobName,
                    jobGroupName).setJobData(jobDataMap)
                    .build();
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            // 按新的cronExpression表达式构建一个新的trigger
            Trigger newTrigger = TriggerBuilder.newTrigger().withIdentity(jobName,
                    triggerGroupName)
                    .withSchedule(scheduleBuilder).build();

            // 交给scheduler去调度
            scheduler.scheduleJob(job, newTrigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * : 修改一个任务的触发时间
     */
    public void modifyJobTime(String triggerName, String triggerGroupName, String cron) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                // trigger已存在，则更新相应的定时设置
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行
                scheduler.resumeTrigger(triggerKey);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     */
    public void removeJob(String jobName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, GlobalConstant.QZ_TRIGGER_GROUP_NAME);
        JobKey jobKey = JobKey.jobKey(jobName, GlobalConstant.QZ_JOB_GROUP_NAME);
        try {
            Trigger trigger = (Trigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(jobKey);
            System.err.println("移除任务:" + jobName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * : 移除一个任务
     */
    @Override
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, triggerGroupName);
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * :暂停一个任务(使用默认组名)
     */
    public void pauseJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, GlobalConstant.QZ_JOB_GROUP_NAME);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * :暂停一个任务
     */
    @Override
    public void pauseJob(String jobName, String jobGroupName) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * :恢复一个任务(使用默认组名)
     */
    public void resumeJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, GlobalConstant.QZ_JOB_GROUP_NAME);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * :恢复一个任务
     */
    @Override
    public void resumeJob(String jobName, String jobGroupName) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * :启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * : 立即运行任务，这里的立即运行，只会运行一次，方便测试时用。
     */
    public void triggerJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, GlobalConstant.QZ_JOB_GROUP_NAME);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * : 立即运行任务，这里的立即运行，只会运行一次，方便测试时用。
     */
    public void triggerJob(String jobName, String jobGroupName) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * : 获取任务状态
     *
     * @param jobName 触发器名
     */
    public String getTriggerState(String jobName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, GlobalConstant.QZ_TRIGGER_GROUP_NAME);
        String name = null;
        try {
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            name = triggerState.name();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * :获取最近5次执行时间
     */
    @Override
    public List<String> getRecentTriggerTime(String cron) {
        List<String> list = new ArrayList<String>();
        try {
            CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
            cronTriggerImpl.setCronExpression(cron);
            List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, 5);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            for (Date date : dates) {
                list.add(dateFormat.format(date));
            }
        } catch (ParseException e) {
            LOGGER.error("GetRecentTriggerTime error, cron = {}", cron, e);
        }
        return list;
    }

    @Override
    public List<Map<String, String>> getAll() {
        List<Map<String, String>> list = new ArrayList<>();
        //再获取Scheduler下的所有group
        List<String> triggerGroupNames = null;
        try {
            triggerGroupNames = scheduler.getTriggerGroupNames();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        for (String groupName : triggerGroupNames) {
            //组装group的匹配，为了模糊获取所有的triggerKey或者jobKey
            GroupMatcher groupMatcher = GroupMatcher.groupEquals(groupName);
            //获取所有的triggerKey
            Set<TriggerKey> triggerKeySet = null;
            try {
                triggerKeySet = scheduler.getTriggerKeys(groupMatcher);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            for (TriggerKey triggerKey : triggerKeySet) {
                //通过triggerKey在scheduler中获取trigger对象
                CronTrigger trigger = null;
                try {
                    trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
                //获取trigger拥有的Job
                JobKey jobKey = trigger.getJobKey();
                JobDetailImpl jobDetail = null;
                try {
                    jobDetail = (JobDetailImpl) scheduler.getJobDetail(jobKey);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
                //组装页面需要显示的数据
                Map<String, String> map = new HashMap<>();
                map.put("groupName", groupName);
                map.put("jobDetailName", jobDetail.getName());
                map.put("jobCronExpression", trigger.getCronExpression());
                map.put("timeZone", trigger.getTimeZone().getID());
                list.add(map);
            }
        }
        return list;
    }

}
