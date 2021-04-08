package com.meng.testspringboot2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meng.testspringboot2.pojo.User;
import com.meng.testspringboot2.pojo.User2;
import java.util.List;
import reactor.core.publisher.Mono;

public interface UserService {

	Mono<List<User>> findAll();

	Mono<User> findOne(int id);

	Mono<Integer> deleteUsers(List<Integer> delList);

	Mono<IPage<User>> findPage(Integer pageNum);

	Mono<Void> insert(User user);

	Mono<Integer> update(User user);

	Mono<List<User2>> account();
}
