package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import entity.Course;

public interface CourseDao {
	/**
	 * 根据条件查询课程列表
	 * @param offset
	 * @param pageSize
	 * @param keyWords
	 * @return
	 */
	List<Course> queryCourseList(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("keyWords")Map<String,Object> keyWords);
	/**
	 * 获取总记录数
	 * @param keyWords
	 * @return
	 */
	int getTotalCount(@Param("keyWords")Map<String,Object> keyWords);
	/**
	 * 插入课程
	 * @param course
	 * @return
	 */
	int insertCourse(Course course);
	/**
	 * 更新课程
	 * @param newCourse
	 */
	int updateCourse(Course newCourse);
	/**
	 * 根据ID查询课程
	 * @param courseId
	 */
	Course queryCourseById(String courseId);
}
