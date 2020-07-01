package com.baidu.redis.springredis;

public class SpringRedisDemo {
    /**
     * 1、配置jedisPoolConfig连接池的基本信息
     * 2、设置RedisSentinalConfig的属性：master的name，哨兵的ip:port
     * 3、设置jedisPoolConnectionFactory，填充jedisPool，密码，第一步的连接池的config,第二步的sentinalConfig
     * 4、将jedisPoolConnectionFactory设置到redisTemplate中
     */

    public void testSpringRedis() {

    }
}
