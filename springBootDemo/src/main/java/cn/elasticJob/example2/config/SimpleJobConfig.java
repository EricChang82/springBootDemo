
package cn.elasticJob.example2.config;

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

import cn.elasticJob.example2.job.SpringSimpleJob;
import cn.elasticJob.example2.job.SpringSimpleJob2;

@Configuration
public class SimpleJobConfig {
    
    @Resource
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
    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob, @Value("${simpleJob.cron}") final String cron, @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount,
            @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
//        return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters), jobEventConfiguration);
        SpringJobScheduler springJobScheduler = new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters));
        springJobScheduler.init();
        return springJobScheduler;
    }
    @Bean
    public JobScheduler simpleJobScheduler2(final SimpleJob simpleJob2, @Value("${simpleJob.cron}") final String cron, @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount,
            @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
//        return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters), jobEventConfiguration);
        SpringJobScheduler springJobScheduler2 = new SpringJobScheduler(simpleJob2, regCenter, getLiteJobConfiguration(simpleJob2.getClass(), cron, shardingTotalCount, shardingItemParameters));
        springJobScheduler2.init();
        return springJobScheduler2;
    }
    
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }
}
