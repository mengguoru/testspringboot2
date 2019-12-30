package com.meng.testspringboot2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meng.testspringboot2.pojo.User2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface User2Mapper {
    List<User2> findAll();
}
