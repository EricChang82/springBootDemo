/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月23日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.listener.jobListener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月23日 
 * Purpose:
 */

public class JobListenerForHelloJob implements JobListener {
  public static String  listenerName="hello监听器名称";
    @Override
    public String getName() {
        return listenerName;
    }
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        Util.print("jobToBeExecuted...");
    }

    /**
     * 执行被否决
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        Util.print("jobExecutionVetoed...");

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        Util.print("jobWasExecuted...");

    }

}
