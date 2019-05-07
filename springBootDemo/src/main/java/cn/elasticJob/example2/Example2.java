
package cn.elasticJob.example2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author changle 
 * Create Time: 2019年5月7日 
 * Purpose:官方demo
 */
@SpringBootApplication
public class Example2 {
    
    // CHECKSTYLE:OFF
    public static void main(final String[] args) {
    // CHECKSTYLE:ON
//        EmbedZookeeperServer.start(6181);
        SpringApplication.run(Example2.class, args);
    }
}
