package dao;


import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import entity.Role;

public class RoleDaoTest extends BaseTest{
	@Autowired
	private RoleDao roleDao;
	@Test
	public void test() {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		//hashMap.put("roleName", "管");
		int totalCount = roleDao.getTotalCount(hashMap);
		System.out.println(totalCount);
	}
	@Test
	public void test1() {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		//hashMap.put("roleName", "管");
		List<Role> queryRoleList = roleDao.queryRoleList(0, 6, hashMap);
		System.out.println(queryRoleList);
	}
	
	@Test
	public void test2() {
		Role role = new Role();
		role.setName("ggg");
		int addRole = roleDao.addRole(role);
		System.out.println(addRole);
	}
	
	@Test
	public void test3() {
		int deleteRole = roleDao.deleteRole("5a19dc11-d992-11e8-8aba-10c37bc0aec1");
		System.out.println(deleteRole);
	}
	
	@Test
	public void test4() {
		Role role = new Role();
		role.setId("6b8c5a1b-d995-11e8-8aba-10c37bc0aec1");
		role.setName("就哈哈哈");
		int updateRole = roleDao.updateRole(role);
		System.out.println(updateRole);
	}

}
