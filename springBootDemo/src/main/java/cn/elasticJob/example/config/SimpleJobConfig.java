
package cn.elasticJob.example.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import cn.elasticJob.example.job.SpringSimpleJob;
import cn.elasticJob.example.job.SpringSimpleJob2;
import freemarker.core.ReturnInstruction.Return;

@Configuration
public class SimpleJobConfig {

    @Resource  //或@Autowired
    private ZookeeperRegistryCenter regCenter;

    //    @Resource
    //    private JobEventConfiguration jobEventConfiguration;

    @Bean
    public SimpleJob simpleJob() {
        return new SpringSimpleJob();
    }

    @Bean
    public SimpleJob simpleJob2() {
        return new SpringSimpleJob2();
    }

    //    @Bean(initMethod = "init")  //入口方法，相当于elasticJob的main方法
    //    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob, @Value("${simpleJob.cron}") final String cron, @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount,
    //                                           @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
    ////        return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters), jobEventConfiguration);
    //        return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters));
    //    }
    @Bean
    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob, @Value("${simpleJob.cron}") final String cron, @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount, @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
        SpringSimpleJob job1=  new SpringSimpleJob();
        SpringSimpleJob2 job2=  new SpringSimpleJob2();
        SpringJobScheduler springJobScheduler2 = getSpringJobScheduler("job2", job2, 10, shardingItemParameters, cron);
        springJobScheduler2.init();
        SpringJobScheduler springJobScheduler = getSpringJobScheduler("job22", job1, 10, shardingItemParameters, cron); 
        springJobScheduler.init();
        return springJobScheduler;
    }

    /**
     *@author changle
     *Create Time: 2019年5月15日 
     *Purpose:
     * @param jobInfo TODO
     */
    private SpringJobScheduler getSpringJobScheduler(String jobInfo, SimpleJob job2, final int shardingTotalCount, final String shardingItemParameters, final String cron) {
        return new SpringJobScheduler(job2, regCenter, getLiteJobConfiguration(jobInfo,job2.getClass(), cron, shardingTotalCount, shardingItemParameters));
    }
//    @Bean
    public JobScheduler simpleJobScheduler3(final SimpleJob simpleJob, @Value("${simpleJob.cron}") final String cron, @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount, @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
       
        SpringSimpleJob2 job=  new SpringSimpleJob2();
        SpringJobScheduler springJobScheduler = new SpringJobScheduler(job, regCenter, getLiteJobConfiguration("job3",job.getClass(), cron, shardingTotalCount, shardingItemParameters));
        springJobScheduler.init();
        return springJobScheduler;
    }

//    @Bean
    public JobScheduler simpleJobScheduler2(final SimpleJob simpleJob2, @Value("${simpleJob.cron}") final String cron, @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount, @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
       
        //获得litejobconfiguration
        LiteJobConfiguration litejobconfiguration = getLiteJobConfiguration("job2",simpleJob2.getClass(), cron, shardingTotalCount, shardingItemParameters);

        //获得springJobScheduler
        SpringJobScheduler springJobScheduler2 = new SpringJobScheduler(simpleJob2, regCenter, litejobconfiguration);
        //执行初始化
        springJobScheduler2.init();

        //返回 springJobScheduler2
        return springJobScheduler2;
    }

    /**
     *@author changle
     *Create Time: 2019年5月8日 
     *Purpose:获得了LiteJobConfiguration 
     */
    private LiteJobConfiguration getLiteJobConfiguration(String jobInfo,final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String shardingItemParameters) {

        //获得JobCoreConfiguration
        JobCoreConfiguration jobcoreconfiguration = JobCoreConfiguration
                    .newBuilder(jobClass.getName(), cron, shardingTotalCount)
                    .shardingItemParameters(shardingItemParameters)
                    .failover(true)  //是否开启任务执行失效转移，开启表示如果作业在一次任务执行中途宕机，允许将该次未完成的任务在另一作业节点上补偿执行
                    .description("作业描述")
                    .jobParameter(jobInfo)
                    .build();
        //获得SimpleJobConfiguration 
        SimpleJobConfiguration simplejobconfiguration = new SimpleJobConfiguration(jobcoreconfiguration, jobClass.getCanonicalName());
        //返回LiteJobConfiguration
        LiteJobConfiguration litejobconfiguration= LiteJobConfiguration.newBuilder(simplejobconfiguration).overwrite(true).build();
        return  litejobconfiguration;
    }
}
