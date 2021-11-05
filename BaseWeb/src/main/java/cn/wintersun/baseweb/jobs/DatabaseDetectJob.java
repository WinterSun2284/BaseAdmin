package cn.wintersun.baseweb.jobs;

import cn.wintersun.baseweb.service.intrer.databse.DbConnService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.jobs.DatabaseDetectJob
 * @create 2021-11-04-9:23
 * @Description TODO
 */
public class DatabaseDetectJob extends QuartzJobBean {

    @Autowired private DbConnService dbConnService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        dbConnService.autoDetectDb();
    }
}
