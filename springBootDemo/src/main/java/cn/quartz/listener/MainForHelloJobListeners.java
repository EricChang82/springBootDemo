/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月18日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.listener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import cn.quartz.listener.jobListener.JobListenerForHelloJob;
import cn.quartz.listener.scheduleListener.ScheduleListenerForHelloJob;
import cn.quartz.listener.triggerListener.TriggerListenerForHelloJob;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 */

public class MainForHelloJobListeners {
    public static void main(String[] args) throws Exception {

        //调度器(Schedule)
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //任务实例(JobDetail)
        JobDetail jobDetail = JobBuilder.newJob(HelloJobForJobListener.class) //加载任务类(与hellJob进行绑定)
                .withIdentity("job1", "group1")//参数1：任务的名称(唯一实例) 参数2:任务组的名称
                .usingJobData("message", "参数from JobDetail").usingJobData("count", 0).build();//返回JobDetail(存放job相关变量以及可以设置相关属性)
        
        //触发器(Trigger)
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") //参数1：触发器的名称(唯一实例) 参数2:触发器组的名称
                .startNow()//马上启动
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))//每5秒重复执行一次
                .usingJobData("message", "参数from trigger").build();

         //#1S-增加监听器-S
        scheduler.getListenerManager().addJobListener(new JobListenerForHelloJob());
        scheduler.getListenerManager().addTriggerListener(new TriggerListenerForHelloJob());
        scheduler.getListenerManager().addSchedulerListener(new ScheduleListenerForHelloJob());
        //#2E-增加监听器-E 
        
        
        // 让调度器关联任务和触发器，保证按调度器定义的条件执行
        scheduler.scheduleJob(jobDetail, trigger); //每次执行execute会创建新的实例，调用完成后关联的job对象会被释放。GC回收
        //启动
        scheduler.start();

    }

}
