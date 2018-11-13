package com.tarena.spring;

import org.junit.Test;

import com.tarena.entiry.User;
import com.tarena.service.UserService;

public class SpringContextTest {

	@Test
	public void test() {
		SpringContext context = new SpringContext("applicationContext.xml");
		Object bean = context.getBean("userService");
		UserService userService = (UserService)bean;
		userService.addUser(new User());
	}

}
 