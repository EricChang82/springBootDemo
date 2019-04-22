/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月18日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.basDemo.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.TriggerKey;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 */
@PersistJobDataAfterExecution  //多次执行job时，会对job进行持久化，保护一个job信息.job对象还是会创建多次,jobDataMap会被持久化
public class HelloJob implements Job{
    private String message;
    private Integer count;
    public HelloJob() {
//        Util.print("HelloJob 构造函数被调用，创建了新的实例");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        count++;
        context.getJobDetail().getJobDataMap().put("count", count);  
        Util.print("第"+count+"次执行job,"+"时间:"+Util.getCurrentTimeStr());
        
       
        
        //#1S-context相关输出-S
        //#1S-jobDetail内容-S
        JobKey jobKey = context.getJobDetail().getKey(); //jobkey
//        Util.print("execute:"+"Job名称:"+jobKey.getName());·
//        Util.print("execute:"+"Job组的名称:"+jobKey.getGroup());//若没有指定组名,默认值为DEFAULT
//        Util.print("execute:"+"任务类"+context.getJobDetail().getJobClass().getName());
        //#2E-jobDetail内容-E 
        
        //#1S-获取job传参 -S
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
//        Util.print("execute:"+"Job message:"+dataMap.getString("message"));
        //#2E-获取job传参-E 
        
        //#1S-获取trigger传参-S
        JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();
//        Util.print("execute:"+"trigger message:"+triggerDataMap.getString("message"));
        //#2E-获取trigger传参-E 
        
        //#1S-triggerKey-S
        TriggerKey triggerKey = context.getTrigger().getKey();//triggerKey
//        Util.print("execute:"+"trigger名称:"+triggerKey.getName());
        //#2E-triggerKey-E 
        
        //#2E-context相关输出-E 
        
//        Util.print("job通过setter方法直接获取的message："+message);//同名key的话，trigger会覆盖job的传参
        
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
