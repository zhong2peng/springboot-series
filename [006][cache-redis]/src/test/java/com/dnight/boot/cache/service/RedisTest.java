package com.dnight.boot.cache.service;

import com.dnight.boot.cache.UTApplication;
import com.dnight.boot.cache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
@Slf4j
public class RedisTest extends UTApplication {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Test
    public void get(){
        // 测试线程安全，程序结束查看redis中count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(
                () -> stringRedisTemplate.opsForValue().increment("count", 1)
        ));
        redisCacheTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.debug("[k1] = {}", k1);

        // 以下演示整合，具体Redis命令可以参考官方文档
        String key = "user:1";
        redisCacheTemplate.opsForValue().set(key, new User(1L, "DDD"));
        // 对应 String（字符串）
        User user = (User) redisCacheTemplate.opsForValue().get(key);
        log.debug("[user] = {}", user);
    }
}
