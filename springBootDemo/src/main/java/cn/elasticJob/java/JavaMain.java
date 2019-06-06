/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package cn.elasticJob.java;

import java.io.IOException;

import org.quartz.Job;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import cn.elasticJob.jobs.SpringSimpleJob;

public final class JavaMain {
    
    private static final int EMBED_ZOOKEEPER_PORT = 2181;
    
    private static final String ZOOKEEPER_CONNECTION_STRING = "localhost:" + EMBED_ZOOKEEPER_PORT;
    
    private static final String JOB_NAMESPACE = "elastic-job-example-lite-java";
    
    // switch to MySQL by yourself
//    private static final String EVENT_RDB_STORAGE_DRIVER = "com.mysql.jdbc.Driver";
//    private static final String EVENT_RDB_STORAGE_URL = "jdbc:mysql://localhost:3306/elastic_job_log";
    

    
    public static void main(final String[] args) throws IOException {
        
        //#1S-相关配置定义-S
        Class<? extends Job> jobClass = (Class<? extends Job>) SpringSimpleJob.class;
        String jobName = "SpringSimpleJob";
        int shardingTotalCount = 2;
        String cronStr = "0/2 * * * * ?";
        String shardingItemParameters = "0=Beijing,1=Shanghai,2=Guangzhou";
        String zookeeperConnectionString = ZOOKEEPER_CONNECTION_STRING;
        String jobNamespace = JOB_NAMESPACE;
        //#2E-相关配置定义-E 

        CoordinatorRegistryCenter regCenter =initRegistryCenter(zookeeperConnectionString, jobNamespace);
        startElasticJob(jobClass,jobName, cronStr, regCenter, shardingItemParameters, shardingTotalCount);
    }


    /**Purpose: 开始job
     * @author changle
     * Create Time: 2019年6月5日 
     * Version: 1.0
     * @param jobClass TODO
     * @param jobName TODO
     * @param cronStr TODO
     * @param shardingItemParameters TODO
     * @param shardingTotalCount TODO
     * @param zookeeperConnInfo TODO
     * @param jobNameSpace TODO
     */
    private static void startElasticJob(Class<? extends Job> jobClass,String jobName, String cronStr, CoordinatorRegistryCenter regCenter, String shardingItemParameters, int shardingTotalCount) {
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(jobName, cronStr, shardingTotalCount).shardingItemParameters(shardingItemParameters).build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, jobClass.getCanonicalName());
        new JobScheduler(regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build()).init();
    }
    /**
     * Purpose:获得zookeeper注册中心
     * @author changle
     * Create Time: 2019年6月5日 
     * @param zookeeperConnInfo
     * @param jobNameSpace
     * @return
     * Version: 1.0
     */
    private static CoordinatorRegistryCenter initRegistryCenter(String zookeeperConnInfo, String jobNameSpace) {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(zookeeperConnInfo, jobNameSpace);
        CoordinatorRegistryCenter result = new ZookeeperRegistryCenter(zkConfig);
        result.init();
        return result;
    }

    
}
