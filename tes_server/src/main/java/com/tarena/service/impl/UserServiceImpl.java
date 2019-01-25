package com.tarena.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.dao.UserMapper;
import com.tarena.entity.User;
import com.tarena.service.UserService;
import com.tarena.util.UUIDUtil;
import com.tarena.vo.Result;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	@Override
	public Result login(User user, HttpSession session) {
		Result result=new Result();
		User u=this.userMapper.login(user);
		if(u!=null){
			if("否".equals(u.getIsLock())){
				result.setStatus(1);
				result.setData(u);
			}else if("是".equals(u.getIsLock())){
				session.setAttribute("loginName", user.getLoginName());
				result.setStatus(0);
				result.setMessage("您的账号被锁定,请联系管理员");
			}
		}else{
			result.setStatus(0);
			result.setMessage("用户名和密码错误!");
		}
		
		return result;
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Result addUser(User user) {
		Result result=new Result();
		
		String userId=this.userMapper.findUserByLoginName(user.getLoginName());
		if(userId==null){
			//说明此登录名称可以使用
			user.setId(UUIDUtil.getUUID());
			try {
				this.userMapper.addUser(user);//添加用户
				result.setStatus(1);
				result.setMessage("用户添加成功!");
			} catch (Exception e) {
				result.setStatus(0);
				result.setMessage("用户添加失败!");
				e.printStackTrace();
				throw new RuntimeException(e);
			}			
		}else{
			result.setStatus(0);
			result.setMessage("用户名已经被注册!");			
		}		
		return result;
	}

}
