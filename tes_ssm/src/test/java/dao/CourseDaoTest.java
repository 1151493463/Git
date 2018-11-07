package dao;


import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import entity.Course;

public class CourseDaoTest extends BaseTest{
	@Autowired
	private CourseDao courseDao;
	
	@Test
	public void test() {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("status", "上线");
		//hashMap.put("keyWord","web");
		List<Course> queryCourseList = courseDao.queryCourseList(0, 110, hashMap);
		System.err.println(queryCourseList);
	}
	@Test
	public void test2() {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("keyWord", "java");
		int totalCount = courseDao.getTotalCount(hashMap);
		System.out.println(totalCount);
	}
	
	@Test
	public void test3() {
		Course course = new Course();
		course.setName("jjj");
		course.setOrder(21);
		course.setDesc("good");
		int insertCourse = courseDao.insertCourse(course);
		System.out.println(course.getId());
	}
	
	@Test
	public void test4() {
		Course course = new Course();
		course.setId("c605d4a3-dc0f-11e8-81f8-10c37bc0aec1");
		course.setPicture("hahah");
		int updateCourse = courseDao.updateCourse(course);
		System.out.println(updateCourse);
	}
	@Test
	public void test5() {
		Course queryCourseById = courseDao.queryCourseById("60d188f6-d351-4c7f-8600-8a97ee3ca371");
		System.out.println(queryCourseById);
	}

}
