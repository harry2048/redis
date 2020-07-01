///*
//package com.baidu.redis.jedis;
//
//import org.junit.Test;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisSentinelPool;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class JedisDemo {
//
//    @Test
//    public void testJedis() {
//        Jedis jedis = new Jedis("192.168.190.128");
//        Set<String> keys = jedis.keys("*");
//        System.out.println(keys);
//    }
//
//    public Jedis getJedisFromPool() {
//        String mastname = "mymaster";
//        String password = "Redis@123";
//
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(10);
//
//        // 哨兵的地址
//        Set<String> sentinels = new HashSet<>();
//        sentinels.add("10.200.169.181:26389");
//        sentinels.add("10.200.169.182:26389");
//        sentinels.add("10.200.169.183:26389");
//
//        JedisSentinelPool sentinelPool = new JedisSentinelPool(mastname, sentinels, poolConfig, 10000, password);
//        Jedis jedis = sentinelPool.getResource();
//
//        return jedis;
//    }
//}
//*/
