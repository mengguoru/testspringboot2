package com.meng.testspringboot2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meng.testspringboot2.pojo.User;
import com.meng.testspringboot2.pojo.User2;
import com.meng.testspringboot2.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    public Mono<IPage<User>> findPage(
        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {

        return userService.findPage(pageNum);
    }

    @GetMapping(value = "/{id}")
    public Mono<User> findOne(@PathVariable("id") int id) {
        return userService.findOne(id);
    }

    @PostMapping("/delete")
    public Mono<Integer> deleteUsers(@RequestParam("delList") List<Integer> delList) {

        return userService.deleteUsers(delList);
    }

    @PostMapping("/insert")
    public Mono<Void> insert(@RequestBody User user) {
        logger.info("insert user: {}", user);

        return userService.insert(user);
    }

    @PostMapping("/update")
    public Mono<Integer> update(@RequestBody User user) {
        logger.info("update user: {}", user);

        return userService.update(user);
    }

    @GetMapping("/account")
    public Mono<List<User2>> account() {
        return userService.account();
    }
}
