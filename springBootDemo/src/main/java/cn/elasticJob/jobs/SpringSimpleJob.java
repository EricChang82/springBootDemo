
package cn.elasticJob.jobs;

import org.quartz.DisallowConcurrentExecution;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import cn.Util;
@DisallowConcurrentExecution 
public class SpringSimpleJob implements SimpleJob {
    @Override
    public void execute(final ShardingContext shardingContext) {
        String shardingparameter = shardingContext.getShardingParameter();
        String info = shardingContext.getJobParameter() + ",分片ID:" + shardingContext.getShardingItem() + ",shardingparameter:" + shardingparameter;
        Util.exeOneJob(info);
    }
}
