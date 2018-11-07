package service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dto.Execution;
import dto.Page;
import entity.Course;

public interface CourseService {
	/**
	 * 根据条件分页获取课程
	 * @param currentPage
	 * @param keyWord
	 * @return
	 */
	Execution<Page<Course>> getCourseByPage(int currentPage, Map<String,Object> keyWords);
	/**
	 * 添加课程
	 * @param courseImg
	 * @param course
	 * @return
	 */
	Execution<Course> courseOperation(CommonsMultipartFile courseImg, Course course);
	/**
	 * 根据课程ID查询课程信息
	 * @param courseId
	 * @return
	 */
	Execution<Course> getCourseById(String courseId);
	/**
	 * 获取所有课程
	 * @return
	 */
	Execution<List<Course>> getAllCourse();
	
	
}
