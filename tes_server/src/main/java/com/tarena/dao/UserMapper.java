package com.tarena.dao;

import com.tarena.entity.User;

public interface UserMapper {
    //登录
	public User login(User user);
	//查询登录名是否存在
	public String findUserByLoginName(String loginName);
	//注册用户
	public void addUser(User user);

}
