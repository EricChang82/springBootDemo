/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月18日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.priority;

import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.Util;
import lombok.Getter;
import lombok.Setter;

/**
 * @author changle 
 * Create Time: 2019年4月18日 
 * Purpose:
 */
//@Setter@Getter 这种方式在之类不行
public class PriorityDemoJob implements Job {
    private String triggerMessage;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Util.print(Util.getTimeMillis()+triggerMessage+"触发的job."+ context.getTrigger().getKey());
    }

    /**
     * @return Returns the triggerMessage.
     */
    public String getTriggerMessage() {
        return triggerMessage;
    }

    /**
     * @param triggerMessage The triggerMessage to set.
     */
    public void setTriggerMessage(String triggerMessage) {
        this.triggerMessage = triggerMessage;
    }


}
