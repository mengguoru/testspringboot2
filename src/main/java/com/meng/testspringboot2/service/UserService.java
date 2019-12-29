package com.meng.testspringboot2.service;

import com.meng.testspringboot2.mapper.UserMapper;
import com.meng.testspringboot2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
        return userMapper.selectList(null);
    }

    public User findOne(int id){
        return userMapper.selectById(id);
    }
}
