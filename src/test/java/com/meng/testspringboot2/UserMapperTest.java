package com.meng.testspringboot2;

import com.meng.testspringboot2.mapper.User2Mapper;
import com.meng.testspringboot2.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testSelect(){
        var l = userMapper.selectList(null);
        for(var i:l)
            System.out.println(i);
    }

    @Test
    public void testCustom(){
        var l = user2Mapper.findAll();
        for(var i:l){
            System.out.println("---分割线---");
            System.out.println(i);
            var accounts = i.getAccounts();
            if(accounts.size() > 0) {
                for (var a : accounts)
                    System.out.println(a);
            }
        }

    }
}
