package com.tarena.service;

import javax.servlet.http.HttpSession;

import com.tarena.entity.User;
import com.tarena.vo.Result;

public interface UserService {
    //登录的业务
	public Result login(User user, HttpSession session);
	//注册用户
	public Result addUser(User user);

}
