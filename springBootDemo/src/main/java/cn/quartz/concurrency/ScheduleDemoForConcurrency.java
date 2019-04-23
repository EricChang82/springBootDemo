/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月18日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.concurrency;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 * 1.定时器创建
 * 2.相关属性输出
 * 3.相关参数传递
 */

public class ScheduleDemoForConcurrency {
    public static void main(String[] args) throws Exception {

        startJob("job1", "trigger1", "job--1--参数", 2);
//        startJob("job2", "trigger2", "job--2--参数", 1);
        
        
    }

    /**
     *@author changle
     *Create Time: 2019年4月22日 
     *Purpose:
     * @param jobId TODO
     * @param triggerId TODO
     * @param jobMessage TODO
     * @param jobInterval TODO
     */
    private static void startJob(String jobId, String triggerId, String jobMessage, int jobInterval) throws SchedulerException {
        //调度器(Schedule)
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //任务实例(JobDetail)
        JobDetail jobDetail = JobBuilder.newJob(HelloJobForConcurrency.class) //加载任务类(与hellJob进行绑定)
                .withIdentity(jobId, "group1")//参数1：任务的名称(唯一实例) 参数2:任务组的名称
                .usingJobData("message", jobMessage)
                .build();//返回JobDetail(存放job相关变量以及可以设置相关属性)

        //触发器(Trigger)
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerId, "group1") //参数1：触发器的名称(唯一实例) 参数2:触发器组的名称
                .startNow()//马上启动
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(jobInterval))//每2秒重复执行一次
                .build();
        //关联
        scheduler.scheduleJob(jobDetail, trigger);
        
        //启动
        scheduler.start();
    }


}
