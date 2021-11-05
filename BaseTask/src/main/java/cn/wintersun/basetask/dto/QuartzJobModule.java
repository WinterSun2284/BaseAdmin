package cn.wintersun.basetask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basetask.dto.QuartzJobModule
 * @create 2021-11-04-10:04
 * @Description TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartzJobModule {

    /**
     * 触发器开始时间
     */
    private Date startTime;
    /**
     * 触发器结束时间
     */
    private Date endTime;
    /**
     * job名称
     */
    private String jobName;
    /**
     * job组名
     */
    private String jobGroupName;
    /**
     * 定时器名称
     */
    private String triggerName;
    /**
     * 定时器组名
     */
    private String triggerGroupName;
    /**
     * 执行定时任务的具体操作
     */
    private Class jobClass;
    /**
     * cron表达式
     */
    private String cron;
    /**
     * job的附加信息
     */
    private JobDataMap jobDataMap;

    private String oldJobName;


    /**
     * 校验
     *
     */
    public boolean verify() {
        return !(StringUtils.isEmpty(jobName)
                || StringUtils.isEmpty(jobGroupName)
                || StringUtils.isEmpty(triggerName)
                || StringUtils.isEmpty(triggerGroupName)
                || StringUtils.isEmpty(cron)
                || !ClassUtils.hasMethod(Job.class, "execute", JobExecutionContext.class)
        );
    }

}
