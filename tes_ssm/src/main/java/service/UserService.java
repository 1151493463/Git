package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dto.Execution;
import dto.Page;
import entity.User;

public interface UserService {
	/**
	 * 用户登陆
	 * @param user
	 * @return
	 */
	Execution<User> login(String userName,String userPassword,HttpSession session);
	/**
	 * 根据条件分页获取用户
	 * @param currentPageStr
	 * @param keyWord
	 * @param roleCategory
	 * @return
	 */
	Execution<Page<User>> getUserByPage(int currentPage, Map<String, Object> keyWords, String roleCategory);
	/**
	 * 添加用户
	 * @param user
	 * @param userImg
	 * @param role
	 * @return
	 */
	Execution<User> addUser(User user, CommonsMultipartFile userImg, String[] role);
	/**
	 * 按ID获取用户
	 * @param userId
	 * @return
	 */
	Execution<User> getUserById(String userId);
	/**
	 * 更新用户头像
	 * @param userId
	 * @param userImg
	 * @return
	 */
	Execution<User> updateImage(String userId, CommonsMultipartFile userImg);
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	Execution<User> updateUser(User user,String[] roles);
	/**
	 * 导出用户信息到excel
	 * @return
	 */
	HSSFWorkbook exporUserTotExcel();
	/**
	 * 导入用户
	 * @param userExcel
	 * @return
	 */
	Execution<User> importUserExcel(CommonsMultipartFile userExcel);
	/**
	 * shiro方式登陆
	 */
	Execution<User> shiroLogin(String name,String password,HttpServletRequest request);
	/**
	 * 邮箱找回密码
	 * @param loginName
	 * @return
	 */
	Execution<User> getBackPasswordByEmail(String loginName);
	/**
	 * 
	 * @param user
	 * @return
	 */
	Execution<User> updatePasswordByEmail(User user);

	
}
