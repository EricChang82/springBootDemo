
package cn.elasticJob.example2;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

//Item: 0 | Time: 17:17:30 | Thread: 57 | SIMPLE
//Item: 1 | Time: 17:17:30 | Thread: 59 | SIMPLE
//Item: 2 | Time: 17:17:30 | Thread: 39 | SIMPLE


public class SpringSimpleJob implements SimpleJob {
    @Override
    public void execute(final ShardingContext shardingContext) {
        System.out.println(String.format("Item: %s | Time: %s | Thread: %s | %s",
                shardingContext.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread.currentThread().getId(), "SIMPLE"));
    }
}
