package com.baidu.redis;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@SpringBootApplication
public class RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}


	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(connectionFactory);
		stringRedisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		return stringRedisTemplate;
	}

	@Bean
	public Redisson redisson() {
		// 集群，哨兵模式 都有，此处为单机模式
		String addr = "redis://192.168.190.128:6379";
		Config config = new Config();
		config.useSingleServer().setAddress(addr);
//		config.setLockWatchdogTimeout(10000);
		return (Redisson) Redisson.create(config);
	}
}
