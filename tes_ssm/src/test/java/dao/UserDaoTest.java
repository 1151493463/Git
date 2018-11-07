package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import entity.User;

public class UserDaoTest extends BaseTest{
	@Autowired
	private UserDao userDao;
	
	@Test
	public void test() {
		User userCondition = new User();
		userCondition.setId("1b529183-8e97-4e59-b2c9-60770bdba5a9");
		User queryUserByCondition = userDao.queryUserByCondition(userCondition);
		System.out.println(queryUserByCondition);
	}
	@Test
	public void test1() {
		Map<String, Object> map = new HashMap<String,Object>();
		//map.put("userName", "ff");
		map.put("roleCategory", "讲师");
		List<User> queryUserList = userDao.queryUserList(0, 111, map);
		System.out.println(queryUserList);
	}
	
	@Test
	public void test2() {
		Map<String, Object> map = new HashMap<String,Object>();
		//map.put("userName", "ff");
		//map.put("roleCategory", "讲师");
		int totalCount = userDao.getTotalCount(map);
		System.out.println(totalCount);
	}
	@Test
	public void test3() {
		User user = new User();
		user.setLoginName("hahhah@.com");
		user.setLoginType("邮箱");
		user.setNickName("jjj");
		user.setAge(32);
		user.setPassword("1123");
		user.setSex("女");
		userDao.insertUser(user);
		System.err.println(user.getId());
	}
	
	@Test
	public void test4() {
		User user = new User();
		user.setId("2f4d4388-da87-11e8-894d-10c37bc0aec1");
		user.setHead("ffff");
		int updateUser = userDao.updateUser(user);
		System.out.println(updateUser);
	}
	
	@Test
	public void test5() {
		String[] arr = new String[] {"7cc9a30c-e7d7-41c8-a1d4-00465f88bb92","8fa1e074-6719-48df-97e9-981741dc354e","d2cd243c-c670-4f69-98fd-292b41e37625"};
		int addUserRole = userDao.addUserRole("2f4d4388-da87-11e8-894d-10c37bc0aec1", arr);
		System.out.println(addUserRole);
	}
	@Test
	public void test6() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setLoginName("test");
		user.setLoginType("test");
		user.setNickName("test");
		user.setPassword("test");
		user.setAge(23);
		user.setType(0);
		user.setSex("男");
		user.setIsLock("是");
		user.setScore(88);
		user.setIntroduction("test");
		
		User user1 = new User();
		user1.setId(UUID.randomUUID().toString());
		user1.setLoginName("test1");
		user1.setLoginType("test");
		user1.setNickName("test");
		user1.setPassword("test");
		user1.setAge(23);
		user1.setType(0);
		user1.setSex("男");
		user1.setIsLock("是");
		user1.setScore(88);
		user1.setIntroduction("test");
		
		ArrayList<User> arrayList = new ArrayList<>();
		arrayList.add(user1);
		arrayList.add(user);
		int batchInsertUser = userDao.batchInsertUser(arrayList);
		System.out.println(batchInsertUser);
	}
	
	@Test
	public void test7() {
		List<String> queryUserModulesByLoginName = userDao.queryUserModulesByLoginName("admin@tedu.cn");
		System.out.println(queryUserModulesByLoginName);
	}

}
