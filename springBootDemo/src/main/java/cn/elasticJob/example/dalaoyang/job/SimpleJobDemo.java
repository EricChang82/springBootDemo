package cn.elasticJob.example.dalaoyang.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import cn.Util;
//@ElasticSimpleJob(cron = "* * * * * ?", jobName = "test123", shardingTotalCount = 2, jobParameter = "测试参数", shardingItemParameters = "0=A,1=B")
@Component
public class SimpleJobDemo implements SimpleJob {
    Logger logger = LoggerFactory.getLogger(SimpleJobDemo.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        Util.print(String.format("Thread ID: %s, 作业分片总数: %s, " +
                        "当前分片项: %s.当前参数: %s," +
                        "作业名称: %s.作业自定义参数: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()
        ));

    }
}