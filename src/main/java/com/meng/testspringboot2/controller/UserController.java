package com.meng.testspringboot2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meng.testspringboot2.mapper.UserMapper;
import com.meng.testspringboot2.pojo.User;
import com.meng.testspringboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public HttpEntity<?> findAll(){
//        var users = userService.findAll();
//        if(users.isEmpty())
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public HttpEntity<?> index(){
        var page = userService.findPage(1);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public HttpEntity<?> findPage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum){
        IPage<User> page = userService.findPage(pageNum);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public HttpEntity<?> findOne(@PathVariable("id")int id){
        var user = userService.findOne(id);
        if(null != user)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public HttpEntity<?> deleteUser(@PathVariable("id")int id){
        var user = userService.findOne(id);
        if(null == user)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
