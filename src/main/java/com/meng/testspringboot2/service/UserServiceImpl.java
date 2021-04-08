package com.meng.testspringboot2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meng.testspringboot2.mapper.User2Mapper;
import com.meng.testspringboot2.mapper.UserMapper;
import com.meng.testspringboot2.pojo.User;
import com.meng.testspringboot2.pojo.User2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private static final Scheduler SCHEDULER = Schedulers.newBoundedElastic(10, 10000,
        "user-service", 60);

    private static final int PAGE_SIZE = 2;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Override
    public Mono<List<User>> findAll() {

        return Mono.fromCallable(() -> userMapper.selectList(null))
                   .subscribeOn(SCHEDULER);
    }

    @Override
    public Mono<User> findOne(int id) {
        return Mono.fromCallable(() -> userMapper.selectById(id))
                   .subscribeOn(SCHEDULER);
    }

    @Override
    public Mono<Integer> deleteUsers(List<Integer> delList) {
        return Mono.fromCallable(() -> userMapper.deleteBatchIds(delList))
                   .subscribeOn(SCHEDULER);
    }

    @Override
    public Mono<IPage<User>> findPage(Integer pageNum) {
        Page<User> page = new Page<>(pageNum, PAGE_SIZE);

        return Mono.fromCallable(() -> (IPage<User>) userMapper.selectPage(page, null))
                   .subscribeOn(SCHEDULER);
    }

    @Override
    public Mono<Void> insert(User user) {
        return Mono.fromCallable(() -> userMapper.insert(user))
                   .then()
                   .subscribeOn(SCHEDULER);
    }

    @Override
    public Mono<Integer> update(User user) {

        return Mono.fromCallable(() -> userMapper.updateById(user))
                   .subscribeOn(SCHEDULER);
    }

    @Override
    public Mono<List<User2>> account() {
        return Mono.fromCallable(() -> user2Mapper.findAll())
                   .subscribeOn(SCHEDULER);
    }
}
