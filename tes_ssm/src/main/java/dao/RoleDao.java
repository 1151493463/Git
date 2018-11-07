package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import entity.Role;

public interface RoleDao {
	/**
	 * 根据条件获取角色分页
	 * @param start
	 * @param pageSize
	 * @param keyWords
	 * @return
	 */
	List<Role> queryRoleList(@Param("offset")int offset, @Param("pageSize")int pageSize, @Param("keyWords")Map<String,Object> keyWords);
	/**
	 * 根据条件获取总记录数
	 * @param keyWords
	 * @return
	 */
	int getTotalCount(Map<String,Object> keyWords);
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	int addRole(Role role);
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	int deleteRole(String roleId);
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	int updateRole(Role role);
}
