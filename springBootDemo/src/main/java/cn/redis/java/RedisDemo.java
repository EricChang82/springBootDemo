
package cn.redis.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class RedisDemo {

    /**
     * 初始化Redis连接池
     */
    public static void main(String[] args) {

        //#1S-定义连接信息-S
        String ADDR = "47.102.143.3"; //服务器IP地址  test9
        int PORT = 63801; //端口
        String password = "mwmQQn7TG13DdEqVReids";//密码
        int MAX_ACTIVE = 1024; //连接实例的最大连接数
        int MAX_IDLE = 200; //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
        int MAX_WAIT = 10000;//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
        int TIMEOUT = 10000; //连接超时的时间　　
        boolean TEST_ON_BORROW = true; // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        int DEFAULT_DATABASE = 0; //数据库模式是16个数据库 0~15 
        //#2E-定义连接信息-E 

        //#1S-获得JedisPoolConfig-S
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        //#2E-获得JedisPoolConfig-E 
        
        //#1S-获得jedis连接-S
        //普通模式
        JedisPool jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, password, DEFAULT_DATABASE);
         //哨兵模式  test9端口不通，待测试
        Set<String> sentinels = new HashSet<String>(Arrays.asList("192.168.11.128:6379", "192.168.11.129:6379", "192.168.11.130:6379"));
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, config, password);
        //#2E-获得jedis连接-E 

        Jedis jedisResource = jedisPool.getResource();

        System.out.println(jedisResource.get("testname"));
    }
}
