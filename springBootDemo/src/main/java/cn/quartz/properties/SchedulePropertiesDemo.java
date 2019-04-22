
package cn.quartz.properties;

import java.util.Properties;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * @author changle 
 * Create Time: 2019年4月22日 
 * Purpose:JAVA 代码中设置属性
 */
public class SchedulePropertiesDemo {
    
    
    public static void main(String[] args) throws Exception {

        //调度器(Schedule)
        //        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        StdSchedulerFactory fact = new StdSchedulerFactory();

        //#1S-设置属性-S
        Properties properties = new Properties();
        properties.put(StdSchedulerFactory.PROP_SCHED_NAME, "Udf_schedName");
        properties.put(StdSchedulerFactory.PROP_SCHED_THREAD_NAME, "Udf_threadName");
        properties.put("org.quartz.threadPool.threadCount", "6");  //必须大于0
        //#2E-设置属性-E 

        fact.initialize(properties);  //加载属性配置

        Scheduler scheduler = fact.getScheduler();

        scheduler.start();
    }

}
