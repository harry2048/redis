package com.baidu.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedissonController {
    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/testRedisson")
    public int testRedisson() {
        String key = "hello";
        int val = 0;
        RLock lock = redisson.getLock(key);
        try {
			lock.lock();// 如果设置了超时时间，则watch失效


            Thread.currentThread().sleep(60000);

            String key2 = "test2";
//            String test2 = (String) stringRedisTemplate.opsForHash().get(key2, key2);
//            val = Integer.parseInt(test2);
//
//            if (val > 1) {
//                val = val-1;
//                stringRedisTemplate.opsForHash().put(key2,key2,Integer.toString(val));
//
//            }

            Double increment = stringRedisTemplate.opsForHash().increment(key2, key2, -1.0);
            val = increment.intValue();

            System.out.println(increment);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return val;
    }

    @GetMapping("/testRedisTemplate")
    public Object testRedisTemplate() {
        String key = "floatKey";
        Object o = stringRedisTemplate.opsForValue().get(key);
//        redisTemplate.opsForValue().set("aaa","aaa");
        if (null == o) {
            return "空指针";
        }
        return o;
    }



    @GetMapping("/testRedis")
    public int testRedis() {
        String key = "redislockkey";
        String value = UUID.randomUUID().toString();

        String key2 = "test2";
        int val=0;
        try {
            getLock(key, value);

            // 获取值
            Object o = stringRedisTemplate.opsForHash().get(key2, key2);
            Double d = Double.valueOf(o.toString());
            // 进行计算
            d = d - 1;
            val = d.intValue();
            // 赋值
            stringRedisTemplate.opsForHash().put(key2,key2,d.toString());
            System.out.println(val);
//            Double increment = stringRedisTemplate.opsForHash().increment(key2, key2, -1.0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if(value.equals(stringRedisTemplate.opsForValue().get(key)))
                stringRedisTemplate.delete(key);
        }
        return val;
    }

    public void getLock(String key, String value) {
        for (; ; ) {
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(key, value, 1, TimeUnit.MINUTES);
            if (result) {
                // 服务器端开启守护线程后，守护线程无法结束
//                testDemon();
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

    /**
     * delay: 多少秒后执行
     * period：每次间隔多长时间
     */
    static class AddFlood extends Thread {
        @Override
        public void run() {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //stringRedisTemplate.expire(key,30,TimeUnit.SECONDS);
                }
            }, 1000,1000);
        }
    }

// 主线程使用守护线程，守护线程是可以自动结束的。但是服务器的线程是不能自动结束的
//    public static void main(String[] args) throws Exception{
//        AddFlood addFlood = new AddFlood();
//        addFlood.setDaemon(true);
//        addFlood.start();
//
//        Thread.currentThread().sleep(10000);
//    }

    public static void main(String[] args) {
        Double d = new Double("1");
        int i = d.compareTo(new Double("0"));
        System.out.println(i);
    }
}
