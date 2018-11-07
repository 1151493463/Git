package service.impl;


import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.UserDao;
import dto.Execution;
import dto.Page;
import entity.User;
import enums.ImageCategory;
import enums.StateEnum;
import service.UserService;
import util.ActiveCodeUtil;
import util.CommonValue;
import util.ExcelUtill;
import util.FileUtil;
import util.ImageUtil;
import util.MailUtil;
import util.ParamVerify;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private CommonValue commonValue;
	@Autowired
	private MailUtil mail;
	@Override
	public Execution<User> login(String userName, String userPassword, HttpSession session) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		try {
			User userCondition = new User();
			userCondition.setLoginName(userName);
			userCondition.setPassword(userPassword);
			User user = userDao.queryUserByCondition(userCondition);
			if (user != null) {
				session.setAttribute("user", user.getLoginName());
				ue = new Execution<User>(StateEnum.SUCCESS);
			} else {
				ue = new Execution<User>(StateEnum.NULL_PARAMS, "用户名或密码为空");
			}

		} catch (Exception e) {
			// TODO: handle exception
			ue = new Execution<User>(StateEnum.INNER_ERROR, e.getMessage());
		}

		return ue;
	}
	@Override
	public Execution<Page<User>> getUserByPage(int currentPage, Map<String, Object> keyWords, String roleCategory) {
		// TODO Auto-generated method stub
		Execution<Page<User>> ue = null;
		try {
			keyWords.put("roleCategory", roleCategory);
			Page<User> page = new Page<User>(currentPage,keyWords,commonValue.getPageSize());
			int offset = (currentPage-1)*page.getPageSize();
			int totalCount = userDao.getTotalCount(keyWords);
			List<User> userList = userDao.queryUserList(offset,page.getPageSize(),keyWords);
			page.setData(userList);
			page.setTotalCount(totalCount);
			
			ue = new Execution<>(StateEnum.SUCCESS,page);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ue = new Execution<Page<User>>(StateEnum.INNER_ERROR,e.getMessage());
		}
		return ue;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<User> addUser(User user,CommonsMultipartFile userImg, String[] role) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		try {
			int addUser = userDao.insertUser(user);
			if(addUser<1) {
				throw new RuntimeException("添加用户失败");
			}
			User newUser = new User();
			newUser.setId(user.getId());
			if(userImg!=null) {
				newUser.setHead(ImageUtil.generateImage(userImg, user.getId(),ImageCategory.USER));
			}
			int updateUser = userDao.updateUser(newUser);
			if(updateUser<1) {
				throw new RuntimeException("添加用户失败");
			}
			addUserRole(user.getId(),role);
			ue = new Execution<>(StateEnum.SUCCESS);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ue = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			if(user.getId()!=null) {
				FileUtil.deleteFile(FileUtil.getImageBasePath()+FileUtil.getImagePath(user.getId(),ImageCategory.USER), null);
			}
			throw new RuntimeException(e.getMessage());
		}
		
		return ue;
	}
	
	public void addUserRole(String userId,String ...roles) throws Exception{
		int addUserRole = userDao.addUserRole(userId,roles);
		if(addUserRole<1) {
			throw new RuntimeException("添加用户角色失败");
		}
	}
	
	@Override
	public Execution<User> getUserById(String userId) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		try {
			User userCondition = new User();
			userCondition.setId(userId);
			User user = userDao.queryUserByCondition(userCondition);
			if(user!=null) {
				ue = new Execution<>(StateEnum.SUCCESS,user);
			}else {
				ue = new Execution<User>(StateEnum.EXECUTE_FAIL,"没有查询到该用户");
			}
		}catch (Exception e) {
			// TODO: handle exception
			ue = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		
		return ue;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<User> updateImage(String userId, CommonsMultipartFile userImg) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		String path = null;
		try {
			path = FileUtil.getImageBasePath()+FileUtil.getImagePath(userId,ImageCategory.USER);
			FileUtil.markerFile(path);
			User user = new User();
			String head = ImageUtil.generateImage(userImg, userId,ImageCategory.USER);
			user.setHead(head);
			user.setId(userId);
			int updateUser = userDao.updateUser(user);
			if(updateUser==1) {
				ue = new Execution<>(StateEnum.SUCCESS,user);
			}else {
				ue = new Execution<User>(StateEnum.EXECUTE_FAIL,"更新用户头像失败");
			}
			FileUtil.deleteFile(path, ".bak");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ue = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			FileUtil.recoverFile(path);
			throw new RuntimeException(e.getMessage());
		}
		
		return ue;
	}
	
	/**
	 * 如果要删除则
	 * 删除顺序
		t_user_module
		t_user_role
		t_particpation
		t_history_cache_collection_purchased
		t_friendlist
		t_comment
		t_activity
		t_video
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<User> updateUser(User user,String[] roles) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		try {
			if(user.getLoginName()!=null) {
				user.setLoginType(ParamVerify.getLoginType(user.getLoginName()));
			}
			int updateUser = userDao.updateUser(user);
			if(updateUser!=1) {
				throw new RuntimeException("更新用户失败");
			}
			if(roles!=null) {
				deleteUserRole(user.getId(),roles);
				addUserRole(user.getId(), roles);
			}
			ue = new Execution<>(StateEnum.SUCCESS);
		}catch (Exception e) {
			// TODO: handle exception
			ue = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return ue;
	}
	private void deleteUserRole(String id, String[] roles) {
		// TODO Auto-generated method stub
		int deleteUserRole = userDao.deleteUserRole(id);
		if(deleteUserRole<1) {
			throw new RuntimeException("删除用户角色失败");
		}
	}
	@Override
	public HSSFWorkbook exporUserTotExcel() {
		// TODO Auto-generated method stub
		HSSFWorkbook hw = null;
		try {
			List<User> users = userDao.queryUserList(0, 100000, null);
			hw = ExcelUtill.generatetUserToExcel(users);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return hw;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<User> importUserExcel(CommonsMultipartFile userExcel) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		try {
			List<User> userList = ExcelUtill.parseUserExcel(userExcel.getInputStream());
			//userDao.batchInsertUser(userList);
			for(User user:userList) {
				int insertUser = userDao.insertUser(user);
				if(insertUser!=1) {
					throw new RuntimeException("导入用户失败");
				}
				addUserRole(user.getId(), "f4bb6184-6aa9-40eb-a37d-ff82a8769cce");
			}
			ue = new Execution<>(StateEnum.SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ue = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return ue;
	}
	/**
	 * shiro登陆
	 */
	@Override
	public Execution<User> shiroLogin(String name, String password, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		
		//用shiro的api启动shiro
		//创建一个subject对象，存储用户数据
		Subject subject = SecurityUtils.getSubject();
		//将用户数据封装给令牌
		UsernamePasswordToken token = new UsernamePasswordToken(name,password);
		//获取shiro的session
		subject.getSession().setAttribute("loginName", name);
		try {
			//启动shiro，把令牌的数据带到安全管理中心
			subject.login(token);
			ue = new Execution<>(StateEnum.SUCCESS);
		}catch(AuthenticationException e) {
			ue = new Execution<>(StateEnum.EXECUTE_FAIL,"用户名或密码错误");
		}
		catch (Exception e) {
			// TODO: handle exception
			ue = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		
		return ue;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<User> getBackPasswordByEmail(String loginName) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		try {
			User user = new User();
			user.setLoginName(loginName);
			User userByCondition = userDao.queryUserByCondition(user);
			if(userByCondition==null) {
				throw new RuntimeException("该用户不存在！");
			}
			String activeCode = ActiveCodeUtil.generateActiveCode();
			mail.sendMail(loginName, "您本次验证码:"+activeCode, "找回密码");
			user.setActiveCode(activeCode);
			int updateUser = userDao.updateUser(user);
			if(updateUser!=1) {
				throw new RuntimeException("发送验证码失败！");
			}
			ue = new Execution<>(StateEnum.SUCCESS);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ue = new Execution<>(StateEnum.INNER_ERROR,"无效的邮箱");
			throw new RuntimeException(e.getMessage());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ue = new Execution<>(StateEnum.INNER_ERROR,"验证码失发送败！");
			throw new RuntimeException(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ue = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return ue;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<User> updatePasswordByEmail(User user) {
		// TODO Auto-generated method stub
		Execution<User> ue = null;
		try {
			User tmp = new User();
			tmp.setLoginName(user.getLoginName());
			User userByCondition = userDao.queryUserByCondition(tmp);
			if (!(user.getActiveCode()).equals(userByCondition.getActiveCode())) {
				throw new RuntimeException("验证码错误");
			}
			int updateUser = userDao.updateUser(user);
			if (updateUser != 1) {
				throw new RuntimeException("修改密码失败");
			} 
			ue = new Execution<>(StateEnum.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ue = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return ue;
	}
	
	
	
	

	
}
