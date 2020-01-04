package com.meng.testspringboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.RedisTemplate;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test(){
        redisTemplate.opsForValue().set("key2", "这是测试");
        System.out.println((String)redisTemplate.opsForValue().get("key2"));
        String tmp = (String)redisTemplate.opsForValue().get("key1");
        System.out.println(tmp);
        return tmp;
    }
}
