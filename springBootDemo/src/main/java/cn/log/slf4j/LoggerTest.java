package cn.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    public static void main(String[] args) {

//        BasicConfigurator.configure(); //自动快速地使用缺省Log4j环境。
//        org.apache.log4j.LogManager.resetConfiguration();
//        org.apache.log4j.PropertyConfigurator.configure("E:/git/repository/springBootDemo/src/main/resources/log4j.properties");

        logger.info("Current Time: {}", System.currentTimeMillis());
        logger.info("Current Time: " + System.currentTimeMillis());
        logger.info("Current Time: {}", System.currentTimeMillis());
        logger.trace("trace log");
        logger.warn("warn log");
        logger.debug("debug log");
        logger.info("info log");
        logger.error("error log");
    }
}
