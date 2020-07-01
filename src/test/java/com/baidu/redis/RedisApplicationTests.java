package com.baidu.redis;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {

	/*@Autowired
	private RedisTemplate redisTemplate;

	String key = "xindaixiaolock";
	String value = UUID.randomUUID().toString();

	@Test
	void contextLoads() {
		try {
			getLock();


		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(value.equals(redisTemplate.opsForValue().get(key)))
				redisTemplate.delete(key);
		}
	}

	public void getLock() {
		for (; ; ) {
			Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, 30, TimeUnit.SECONDS);
			if (result) {
				testDemon();
				break;
			}
		}
	}


	// 开启守护线程为key续命
	public void testDemon() {
		AddFlood addFlood = new AddFlood();
		addFlood.setDaemon(true);
		addFlood.start();
	}

	class AddFlood extends Thread {
		@Override
		public void run() {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					//
				}
			}, 30 * 1000);
		}
	}*/

	@Autowired
	private Redisson redisson;

	@Test
	public void testRedisson() {
		String key = "hello";
		RLock lock = redisson.getLock(key);
		try {
//			lock.lock(10, TimeUnit.SECONDS);
			lock.tryLock(10, TimeUnit.SECONDS);

//			Thread.sleep(20000);
			Thread.currentThread().sleep(40000);
			System.out.println("锁了30秒");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
