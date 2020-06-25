package com.baidu.redis.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;

public class RedissonDemo {


    public Redisson testRedisson() {
        // 集群，哨兵模式 都有，此处为哨兵模式
        String masterName = "mymaster";
        String password = "password";
        String sentinels = new StringBuilder("127.0.0.1:26379")
                .append("127.0.0.1:26379")
                .append("127.0.0.1:26379").toString();


        Config config = new Config();
        config.useSentinelServers().setMasterName(masterName)
                .setPassword(password)
                .addSentinelAddress(sentinels);

        return (Redisson) Redisson.create(config);
    }
}
