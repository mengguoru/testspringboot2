package com.meng.testspringboot2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meng.testspringboot2.mapper.User2Mapper;
import com.meng.testspringboot2.mapper.UserMapper;
import com.meng.testspringboot2.pojo.User;
import com.meng.testspringboot2.pojo.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {
    @Autowired
    private UserMapper userMapper;
    private int pageSize = 2;
    @Autowired
    private User2Mapper user2Mapper;

    public List<User> findAll(){
        return userMapper.selectList(null);
    }

    public User findOne(int id){
        return userMapper.selectById(id);
    }

    public void deleteUser(int id){
        userMapper.deleteById(id);
    }

    public int deleteUsers(List<Integer> delList){
        return userMapper.deleteBatchIds(delList);
    }

    public IPage<User> findPage(Integer pageNum){
        Page<User> page = new Page<>(pageNum, pageSize);
        return userMapper.selectPage(page, null);
    }

    public void insert(User user){
        userMapper.insert(user);
    }

    public int update(User user){
        int update = userMapper.updateById(user);
        return update;
    }

    public List<User2> account(){
        //return IUser2Dao.findAll();
        return user2Mapper.findAll();
    }
}
