/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月4日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.springBootDemo.annotation.exceptionProcess;

/**
 * Project Name:springBootDemo
 * @author changle
 * Purpose:
 * Create Time: 2019年4月4日 
 * Create Specification:
 * Modified Time:
 * Modified by:
 * Modified Specification:
 * Version: 1.0
 */

public class UdfException extends RuntimeException {
    public String exeMessage;

    /**
     * @param exeMessage
     */
    public UdfException(String exeMessage) {
        super();
        this.exeMessage = exeMessage;
    }

    /**
     * @return Returns the exeMessage.
     */
    public String getExeMessage() {
        return exeMessage;
    }

    /**
     * @param exeMessage The exeMessage to set.
     */
    public void setExeMessage(String exeMessage) {
        this.exeMessage = exeMessage;
    }
}
