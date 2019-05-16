
package cn.elasticJob.example.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import cn.Util;

public class SpringSimpleJob2 implements SimpleJob {
    @Override
    public void execute(final ShardingContext shardingContext) {
        String shardingparameter = shardingContext.getShardingParameter();
        String info = shardingContext.getJobParameter() + ",分片ID:" + shardingContext.getShardingItem() + ",shardingparameter:" + shardingparameter;
        Util.exeOneJob(info);
    }
}
