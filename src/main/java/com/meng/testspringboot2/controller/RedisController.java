package com.meng.testspringboot2.controller;

import com.meng.testspringboot2.pojo.RedisInfo;
import com.meng.testspringboot2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public HttpEntity<?> insert(@RequestBody RedisInfo redisInfo){
        System.out.println(redisInfo);
        redisTemplate.opsForValue().set(redisInfo.getKey(), redisInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{key}", method = RequestMethod.GET)
    public HttpEntity<?> findOne(@PathVariable("key")String key){
        var redisInfo = redisTemplate.opsForValue().get(key);;
        if(null != redisInfo)
            return new ResponseEntity<>(redisInfo, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{key}", method = RequestMethod.DELETE)
    public HttpEntity<?> deleteUser(@PathVariable("key")String key){
        Boolean delete = redisTemplate.delete(key);
        if(true == delete)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        redisTemplate.opsForValue().set("key2", "这是测试");
        System.out.println((String)redisTemplate.opsForValue().get("key2"));
        String tmp = (String)redisTemplate.opsForValue().get("key1");
        System.out.println(tmp);
        return tmp;
    }
}
