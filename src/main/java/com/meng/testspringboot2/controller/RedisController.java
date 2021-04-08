package com.meng.testspringboot2.controller;

import com.meng.testspringboot2.ApiException;
import com.meng.testspringboot2.pojo.RedisInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/redis")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private ReactiveRedisOperations<String, RedisInfo> redisOperations;

    @PostMapping
    public Mono<Boolean> insert(@RequestBody RedisInfo redisInfo) {
        logger.info("insert : {}", redisInfo);

        return redisOperations.opsForValue()
                   .set(redisInfo.getKey(), redisInfo);
    }

    @GetMapping("{key}")
    public Mono<RedisInfo> findOne(@PathVariable("key") String key) {

        return redisOperations.opsForValue()
                   .get(key);
    }

    @PostMapping("/delete/{key}")
    public Mono<Long> deleteUser(@PathVariable("key") String key) {

        return redisOperations.delete(key);
    }

    @GetMapping("/test")
    public Mono<RedisInfo> test() {

        RedisInfo info = new RedisInfo();
        info.setMsg("这是测试");

        return redisOperations.opsForValue().set("key2", info)
                   .flatMap(aBoolean -> redisOperations.opsForValue().get("key2"))
                   .map(redisInfo -> {

                       logger.info("key2: {}", redisInfo);

                       return redisInfo;
                   });
    }

    @GetMapping("/test2")
    public Mono<String> test2() {

        String data = "xx";

        throw new ApiException("31", "测试");
        //        return Mono.error();
        //                return Mono.just(data);
    }
}
