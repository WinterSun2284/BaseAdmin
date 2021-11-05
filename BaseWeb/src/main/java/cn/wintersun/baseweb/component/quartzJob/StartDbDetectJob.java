package cn.wintersun.baseweb.component.quartzJob;

import cn.wintersun.basetask.constant.GlobalConstant;
import cn.wintersun.basetask.dto.QuartzJobModule;
import cn.wintersun.basetask.jobservice.QuartzJobService;
import cn.wintersun.baseweb.jobs.DatabaseDetectJob;
import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.component.quartzJob.StartDbDetectJob
 * @create 2021-11-04-9:36
 * @Description TODO
 */
@Component
public class StartDbDetectJob implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(StartDbDetectJob.class);

    @Value("${node}")
    private String node;

    public static final String MASTER = "master";

    @Autowired
    private QuartzJobService quartzJobService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (MASTER.equals(node)){
            QuartzJobModule quartzJobModule = new QuartzJobModule();
            quartzJobModule.setJobName(GlobalConstant.DB_DETECT_JOB_NAME);
            quartzJobModule.setTriggerName(GlobalConstant.DB_DETECT_TRIGGER_NAME);
            quartzJobModule.setJobGroupName(GlobalConstant.DB_DETECT_GROUP_JOB_NAME);
            quartzJobModule.setTriggerGroupName(GlobalConstant.DB_DETECT_GROUP_TRIGGER_GROUP_NAME);
            JobDataMap jobDataMap = new JobDataMap();
            quartzJobModule.setJobDataMap(jobDataMap);
            quartzJobModule.setCron("0 0/10 * * * ? ");
            quartzJobModule.setJobClass(DatabaseDetectJob.class);
            quartzJobService.addJob(quartzJobModule);
            LOGGER.debug("添加quartz任务，自动侦测数据库连接任务");
        }
    }
}
