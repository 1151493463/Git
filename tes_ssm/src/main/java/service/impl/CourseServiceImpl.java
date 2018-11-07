package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.CourseDao;
import dto.Execution;
import dto.Page;
import entity.Course;
import enums.ImageCategory;
import enums.StateEnum;
import service.CourseService;
import util.CommonValue;
import util.FileUtil;
import util.ImageUtil;
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CommonValue commonValue;
	
	@Override
	public Execution<Page<Course>> getCourseByPage(int currentPage, Map<String,Object> keyWords) {
		// TODO Auto-generated method stub
		Execution<Page<Course>> ce = null;
		try {
			Page<Course> page = new Page<Course>(currentPage,keyWords,commonValue.getPageSize());
			page.setTotalCount(courseDao.getTotalCount(keyWords));
			page.setData(courseDao.queryCourseList(page.getOffset(), page.getPageSize(), keyWords));
			ce = new Execution<Page<Course>>(StateEnum.SUCCESS,page);
		}catch (Exception e) {
			// TODO: handle exception
			ce = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		return ce;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<Course> courseOperation(CommonsMultipartFile courseImg, Course course) {
		// TODO Auto-generated method stub
		Execution<Course> ce = null;
		boolean flag = course.getId()==null;
		try {
			String image = null;
			if(flag) {
				//操作为添加时
				int insertCourse = courseDao.insertCourse(course);
				if(insertCourse<1) {
					return new Execution<Course>(StateEnum.EXECUTE_FAIL,"添加课程失败");
				}
				image = ImageUtil.generateImage(courseImg, course.getId(), ImageCategory.COURSE);
			}else {
				//操作为更新时
				if(courseImg!=null) {
					FileUtil.markerFile(FileUtil.getImagePath(course.getId(), ImageCategory.COURSE));
					image = ImageUtil.generateImage(courseImg, course.getId(), ImageCategory.COURSE);
				}
			}
			course.setPicture(image);
			int updateCourse = courseDao.updateCourse(course);
			if(updateCourse<1) {
				return new Execution<Course>(StateEnum.EXECUTE_FAIL,"更新课程失败");
			}
			//是跟新操作并且上传了图片
			if(!flag && courseImg!=null) {
				FileUtil.deleteFile(FileUtil.getImagePath(course.getId(), ImageCategory.COURSE), ".bak");
			}
			ce = new Execution<>(StateEnum.SUCCESS,course);
		}catch (Exception e) {
			// TODO: handle exception
			if(flag && course.getId()!=null) {
				FileUtil.deleteFile(FileUtil.getImageBasePath()+FileUtil.getImagePath(course.getId(), ImageCategory.COURSE), null);
			}else {
				FileUtil.recoverFile(FileUtil.getImagePath(course.getId(), ImageCategory.COURSE));
			}
			ce = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return ce;
	}
	@Override
	public Execution<Course> getCourseById(String courseId) {
		// TODO Auto-generated method stub
		Execution<Course> ce = null;
		Course course = courseDao.queryCourseById(courseId);
		if(course==null) {
			ce = new Execution<>(StateEnum.EXECUTE_FAIL,"没有查询到该用户");
		}else {
			ce = new Execution<>(StateEnum.SUCCESS,course);
		}
		return ce;
	}
	@Override
	public Execution<List<Course>> getAllCourse() {
		// TODO Auto-generated method stub
		Execution<List<Course>> ce = null;
		try {
			Map<String, Object> keyWords = new HashMap<String,Object>();
			keyWords.put("status", "上线");
			List<Course> courseList = courseDao.queryCourseList(0, 1000, keyWords);
			ce = new Execution<>(StateEnum.SUCCESS,courseList);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ce = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		
		return ce;
	}
	
	
	
	
}
