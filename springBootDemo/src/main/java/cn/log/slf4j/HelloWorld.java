package cn.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World --info");
    logger.debug("Hello World--debug");
    logger.error("Hello World --error");
  }
}