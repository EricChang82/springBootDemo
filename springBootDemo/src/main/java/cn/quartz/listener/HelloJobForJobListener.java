/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月18日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.listener;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 */
@PersistJobDataAfterExecution  //多次执行job时，会对job进行持久化，保护一个job信息.job对象还是会创建多次,jobDataMap会被持久化
public class HelloJobForJobListener implements Job{
    private Integer count;
    public HelloJobForJobListener() {
//        Util.print("HelloJob 构造函数被调用，创建了新的实例");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        count++;
        context.getJobDetail().getJobDataMap().put("count", count);  
        Util.print("第"+count+"次执行job,"+"时间:"+Util.getCurrentTimeStr());
    }
    /**
     * @return Returns the count.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count to set.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}
