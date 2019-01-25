package com.tarena.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.CourseMapper;
import com.tarena.entity.Course;
import com.tarena.service.CourseService;
import com.tarena.vo.Result;
@Service("courseService")
public class CourseServiceImpl implements CourseService {
	@Resource(name="courseMapper")
	private CourseMapper courseMapper;
	@Override
	public Result findAllCourse() {
		Result result=new Result();
		List<Course> courses=this.courseMapper.findAllCourse();
		if(courses!=null){
			result.setStatus(1);
			result.setData(courses);
		}else{
			result.setStatus(0);
			result.setMessage("没有课程信息");
		}
		return result;
	}

}
