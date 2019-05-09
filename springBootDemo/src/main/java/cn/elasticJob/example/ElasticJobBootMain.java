
package cn.elasticJob.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author changle 
 * Create Time: 2019年5月7日 
 * Purpose:demo,对应配置文件为application-ElasticJobServer1.yml
 */
@SpringBootApplication
public class ElasticJobBootMain {
    
    // CHECKSTYLE:OFF
    public static void main(final String[] args) {
    // CHECKSTYLE:ON
//        EmbedZookeeperServer.start(6181);
        SpringApplication.run(ElasticJobBootMain.class, args);
    }
}
