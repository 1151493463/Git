package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserDao {
	/**
	 * 根据条件查询用户
	 * @param userCondition
	 * @return
	 */
	User queryUserByCondition(User userCondition);
	/**
	 * 根据条件分页查询用户
	 * @param start
	 * @param pageSize
	 * @param keyWords
	 * @return
	 */
	List<User> queryUserList(@Param("offset")int offset, @Param("pageSize")int pageSize, @Param("keyWords")Map<String, Object> keyWords);
	/**
	 * 根据条件查询总记录数
	 * @param keyWord
	 * @return
	 */
	int getTotalCount(Map<String, Object> keyWords);
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int insertUser(User user);
	/**
	 * 跟新用户
	 * @param newUser
	 * @return
	 */
	int updateUser(User newUser);
	/**
	 * 添加用户角色
	 * @param userId
	 * @param roles
	 * @return
	 */
	int addUserRole(@Param("userId")String userId, @Param("roles")String[] roles);
	/**
	 * 删除用户角色
	 * @param id
	 * @param roles
	 * @return
	 */
	int deleteUserRole(String id);
	/**
	 * 批量插入用户
	 * @param userList
	 * @return
	 */
	int batchInsertUser(List<User> userList);
	/**
	 * 根据用户名获取此用户拥有的模块名称
	 * @param username
	 * @return
	 */
	List<String> queryUserModulesByLoginName(String username);
	
	
	
}
