package com.meng.testspringboot2.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.meng.testspringboot2.pojo.Account2;
import com.meng.testspringboot2.pojo.User;
import com.meng.testspringboot2.pojo.User2;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private User2Mapper user2Mapper;

	@Test
	void testSelect(){

		List<User> users = userMapper.selectList(null);

		assertNotNull(users);

		users.forEach(System.out::println);
	}

	@Test
	void testCustom(){
		List<User2> users = user2Mapper.findAll();

		assertNotNull(users);

		for(User2 user: users){
			System.out.println("---分割线---");

			System.out.println(user);

			List<Account2> accounts = user.getAccounts();
			if(CollectionUtils.isNotEmpty(accounts)) {

				accounts.forEach(System.out::println);
			}
		}

	}
}