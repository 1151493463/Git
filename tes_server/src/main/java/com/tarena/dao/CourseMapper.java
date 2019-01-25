package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Course;

public interface CourseMapper {
	//查询所有的课程信息
	public List<Course> findAllCourse();

}
