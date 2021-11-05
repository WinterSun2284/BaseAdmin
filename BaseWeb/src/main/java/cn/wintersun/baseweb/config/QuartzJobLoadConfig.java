package cn.wintersun.baseweb.config;

import cn.wintersun.basetask.jobservice.QuartzJobService;
import cn.wintersun.basetask.jobservice.impl.QuartzJobMasterServiceImpl;
import cn.wintersun.basetask.jobservice.impl.QuartzJobSlaveServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.baseweb.config.QuartzJobLoadConfig
 * @create 2021-11-04-10:28
 * @Description TODO
 */
@Configuration
public class QuartzJobLoadConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobLoadConfig.class);

    /**
     * 如果master.slave.node为主节点 加载此类
     * @return com.deepdt.dcgstar.dcgstarweb.service.impl.quartJob.QuartzJobMasterServiceImpl
     **/
    @Bean
    @ConditionalOnMissingBean(QuartzJobService.class)
    @ConditionalOnProperty(
            name = {"node"},
            havingValue = "master",
            matchIfMissing = true)
    QuartzJobMasterServiceImpl loadQuartzMaster() {
        LOGGER.info("=================当前系统为master，加载master");
        // 实现类
        return new QuartzJobMasterServiceImpl();
    }

    /**
     * 如果master.slave.node为从节点 加载此类
     * @return com.deepdt.dcgstar.dcgstarweb.component.QuartzJobSalveServiceImpl
     **/
    @Bean
    @ConditionalOnMissingBean(QuartzJobService.class)
    @ConditionalOnProperty(
            name = {"node"},
            havingValue = "slave")
    QuartzJobSlaveServiceImpl loadQuartzSlave() {
        LOGGER.info("=================当前系统为slave，加载slave");
        // 实现类
        return new QuartzJobSlaveServiceImpl();
    }
}
