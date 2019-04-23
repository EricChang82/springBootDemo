package cn.quartz.priority;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class PriorityDemo {
    public static void main(String[] args) throws Exception {

        //调度器(Schedule)
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        
        //任务实例(JobDetail)
        JobDetail jobDetail1 = JobBuilder.newJob(PriorityDemoJob.class) //加载任务类(与hellJob进行绑定)
                .withIdentity("job1", "group1")//参数1：任务的名称(唯一实例) 参数2:任务组的名称
                .usingJobData("message", "参数from JobDetail")
                .usingJobData("count", 0)
                .build();//返回JobDetail(存放job相关变量以及可以设置相关属性)
//        JobDetail jobDetail2 = JobBuilder.newJob(PriorityDemoJob.class) //加载任务类(与hellJob进行绑定)
//                .withIdentity("job2", "group1")//参数1：任务的名称(唯一实例) 参数2:任务组的名称
//                .usingJobData("message", "参数from JobDetail")
//                .usingJobData("count", 0)
//                .build();//返回JobDetail(存放job相关变量以及可以设置相关属性)
//    
        Date startTime = DateBuilder.futureDate(3, DateBuilder.IntervalUnit.SECOND);
        //触发器(Trigger)--优先级1
        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1") //参数1：触发器的名称(唯一实例) 参数2:触发器组的名称
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))
                .forJob(jobDetail1)
                .withPriority(10)
                .usingJobData("triggerMessage", "触发器-优先级1")
                .build();
        
        //触发器(Trigger)--优先级2
        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1") //参数1：触发器的名称(唯一实例) 参数2:触发器组的名称
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))
                .forJob(jobDetail1)
                .usingJobData("triggerMessage", "触发器-优先级2")
                .withPriority(2)
                .build();
        //调度
        scheduler.scheduleJob(jobDetail1,trigger1); 
//        scheduler.scheduleJob(trigger1); 
        scheduler.scheduleJob(trigger2); 
        //启动
        scheduler.start();
        
        
    }}
