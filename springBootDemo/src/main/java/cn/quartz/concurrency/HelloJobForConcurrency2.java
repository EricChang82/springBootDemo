/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月18日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.concurrency;

import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

import cn.Util;
import lombok.Getter;
import lombok.Setter;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 */
@Setter@Getter
//@DisallowConcurrentExecution  //加上该注解则该jobDetail的job运行完成后，才会执行下一次(即使下一次的运行时间已到)
@PersistJobDataAfterExecution //多次执行job时，会对job进行持久化，保护一个job信息.job对象还是会创建多次,jobDataMap会被持久化
public class HelloJobForConcurrency2 extends  HelloJobForConcurrency{
    private String message;

    @Override
    public void execute(JobExecutionContext context) {
        Util.exeOneJob(message);
    }

}
