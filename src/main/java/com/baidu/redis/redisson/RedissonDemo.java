package com.baidu.redis.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;

public class RedissonDemo {


    public Redisson getRedisson() {
        // 集群，哨兵模式 都有，此处为哨兵模式
        String masterName = "mymaster";
        String password = "password";
        String sentinels = new StringBuilder("127.0.0.1:26379")
                .append("127.0.0.1:26379")
                .append("127.0.0.1:26379").toString();


        Config config = new Config();
        config.useSentinelServers()
                .setMasterName(masterName)
                .setPassword(password)
                .addSentinelAddress(sentinels)
                .setMasterConnectionPoolSize(2)
                .setSlaveConnectionPoolSize(2);


        //指定编码，默认编码为org.redisson.codec.JsonJacksonCodec
        //之前使用的spring-data-redis，用的客户端jedis，编码为org.springframework.data.redis.serializer.StringRedisSerializer
        //改用redisson后为了之间数据能兼容，这里修改编码为org.redisson.client.codec.StringCodec
        config.setCodec(new org.redisson.client.codec.StringCodec());



        return (Redisson) Redisson.create(config);

        // redisson.shutDown();
    }
}
