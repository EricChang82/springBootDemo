/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月18日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.concurrency;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 */
//@DisallowConcurrentExecution
@PersistJobDataAfterExecution //多次执行job时，会对job进行持久化，保护一个job信息.job对象还是会创建多次,jobDataMap会被持久化
public class HelloJobForConcurrency implements Job {
    private String message;

    @Override
    public void execute(JobExecutionContext context) {
        long id =Util.getTimeMillis();
        Util.print(Util.getCurrentTimeStr()+"||before"+message+",Id="+id);
        try {
            Thread.sleep(5000);
            Util.print(Util.getCurrentTimeStr()+"||after"+message+",Id="+id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  
    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
