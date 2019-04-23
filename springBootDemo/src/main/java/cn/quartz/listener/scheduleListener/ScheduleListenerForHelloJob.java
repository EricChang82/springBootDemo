/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月23日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.listener.scheduleListener;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月23日 
 * Purpose:
 */

public class ScheduleListenerForHelloJob implements SchedulerListener {
    public static String listenerName = "ScheduleListenerForHelloJob监听器名称";

    @Override
    public void jobScheduled(Trigger trigger) {
        Util.print(trigger.getKey()+",jobScheduled...");
        
    }
    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#triggerPaused(org.quartz.TriggerKey)
     */
    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#triggersPaused(java.lang.String)
     */
    @Override
    public void triggersPaused(String triggerGroup) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#triggerResumed(org.quartz.TriggerKey)
     */
    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#triggersResumed(java.lang.String)
     */
    @Override
    public void triggersResumed(String triggerGroup) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#jobAdded(org.quartz.JobDetail)
     */
    @Override
    public void jobAdded(JobDetail jobDetail) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#jobDeleted(org.quartz.JobKey)
     */
    @Override
    public void jobDeleted(JobKey jobKey) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#jobPaused(org.quartz.JobKey)
     */
    @Override
    public void jobPaused(JobKey jobKey) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#jobsPaused(java.lang.String)
     */
    @Override
    public void jobsPaused(String jobGroup) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#jobResumed(org.quartz.JobKey)
     */
    @Override
    public void jobResumed(JobKey jobKey) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#jobsResumed(java.lang.String)
     */
    @Override
    public void jobsResumed(String jobGroup) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#schedulerError(java.lang.String, org.quartz.SchedulerException)
     */
    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#schedulerInStandbyMode()
     */
    @Override
    public void schedulerInStandbyMode() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#schedulerStarted()
     */
    @Override
    public void schedulerStarted() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#schedulerStarting()
     */
    @Override
    public void schedulerStarting() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#schedulerShutdown()
     */
    @Override
    public void schedulerShutdown() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#schedulerShuttingdown()
     */
    @Override
    public void schedulerShuttingdown() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.quartz.SchedulerListener#schedulingDataCleared()
     */
    @Override
    public void schedulingDataCleared() {
        // TODO Auto-generated method stub
        
    }


}
