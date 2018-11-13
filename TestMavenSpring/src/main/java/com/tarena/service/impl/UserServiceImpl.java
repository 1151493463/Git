package com.tarena.service.impl;

import com.tarena.dao.UserDao;
import com.tarena.entiry.User;
import com.tarena.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl.addUser()");
		userDao.addUser(user);
		return false;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}
