/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月23日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.quartz.listener.triggerListener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

import cn.Util;

/**
 * @author changle 
 * Create Time: 2019年4月23日 
 * Purpose:
 */

public class TriggerListenerForHelloJob implements TriggerListener {
    public static String listenerName = "helloTriggerListener监听器名称";

    @Override
    public String getName() {
        return listenerName;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        Util.print(trigger.getKey()+"triggerFired...");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        Util.print(trigger.getKey()+"vetoJobExecution...");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        Util.print(trigger.getKey()+",triggerMisfired...");

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {
        Util.print(trigger.getKey()+",triggerComplete...");

    }

}
