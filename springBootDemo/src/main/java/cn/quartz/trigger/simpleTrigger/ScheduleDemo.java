/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月18日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.trigger.simpleTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 * 1.定时器创建
 * 2.相关属性输出
 * 3.相关参数传递
 */

public class ScheduleDemo {
    public static void main(String[] args) throws Exception {

        //调度器(Schedule)
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //任务实例(JobDetail)
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class) //加载任务类(与hellJob进行绑定)
                .withIdentity("job1", "group1")//参数1：任务的名称(唯一实例) 参数2:任务组的名称
                .usingJobData("message", "参数from JobDetail")
                .usingJobData("count", 0)
                .build();//返回JobDetail(存放job相关变量以及可以设置相关属性)
        //#1S-相关输出-S
//        Util.print("Job名称:" + jobDetail.getKey().getName());
//        Util.print("Job组的名称:" + jobDetail.getKey().getGroup());//若没有指定组名,默认值为DEFAULT
//        Util.print("任务类" + jobDetail.getJobClass().getSimpleName());
        //#2E-相关输出-E 

        //触发器(Trigger)
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") //参数1：触发器的名称(唯一实例) 参数2:触发器组的名称
                .startNow()//马上启动
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(2))//每2秒重复执行一次
                .usingJobData("triggerMessage", "参数triggerMessage")
                .usingJobData("message", "参数from trigger").build();

        // 让调度器关联任务和触发器，保证按调度器定义的条件执行
        Date date =  //返回的开始时间
                scheduler.scheduleJob(jobDetail, trigger); //每次执行execute会创建新的实例，调用完成后关联的job对象会被释放。GC回收
//        Util.printDateTime(date);
        //启动
        scheduler.start();
        
//        //挂起
//        scheduler.standby();
        
//        Thread.sleep(5000);
//        
//        //重启
//        scheduler.start();
        
        //#1S-关闭-S
//        scheduler.shutdown();//默认false
        //scheduler.shutdown(false);//直接关闭
        //scheduler.shutdown(true);//等待执行完成后再关闭
        //#2E-关闭-E 
        
        
    }


}
