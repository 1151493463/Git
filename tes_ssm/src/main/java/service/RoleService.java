package service;


import java.util.List;
import java.util.Map;

import dto.Execution;
import dto.Page;
import entity.Role;


public interface RoleService {
	/**
	 * 按条件分页查询角色
	 * @param page
	 * @return
	 */
	Execution<Page<Role>> getRoleByPage(Map<String, Object> keyWords,int currentPageS);
	/**
	 * 添加角色
	 * @param roleName
	 * @return
	 */
	Execution<Role> addRole(String roleName);
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	Execution<Role> deleteRole(String roleId);
	/**
	 * 更新角色
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	Execution<Role> updateRole(String roleId, String roleName);
	/**
	 * 获取所有角色
	 * @return
	 */
	Execution<List<Role>> getAllUser();

}
