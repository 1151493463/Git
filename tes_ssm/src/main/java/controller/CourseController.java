package controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.Execution;
import dto.Page;
import entity.Course;
import enums.StateEnum;
import service.CourseService;
import util.ParamVerify;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/{currentPage}/{keyWord}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCourseByPage(@PathVariable("currentPage")String currentPageStr,@PathVariable("keyWord")String keyWord){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		int currentPage = ParamVerify.isNULL(currentPageStr) | Integer.parseInt(currentPageStr)<1?1:Integer.parseInt(currentPageStr);
		keyWord = ParamVerify.isNULL(keyWord)?"":keyWord;
		Map<String, Object> keyWords = new HashMap<String,Object>();
		keyWords.put("keyWord", keyWord);
		Execution<Page<Course>> ce = courseService.getCourseByPage(currentPage,keyWords);
		if(ce.getState()==StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("page", ce.getData());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
		}
		return modelMap;
	}
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> courseOperation(@RequestParam(required=false)CommonsMultipartFile courseImg,String courseStr){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		try {
			if(courseStr==null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "课程信息不能为空");
				return modelMap;
			}
			Course course = new ObjectMapper().readValue(courseStr, Course.class);
			if(course.getId()==null) {
				if(courseImg==null) {
					modelMap.put("success", false);
					modelMap.put("errMsg", "课程图片不能为空");
					return modelMap;
				}
			}
			Execution<Course> ce = courseService.courseOperation(courseImg,course);
			if(ce.getState()==StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("imgAddr", ce.getData().getPicture());
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ce.getStateInfo());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	@RequestMapping(value="/{courseId}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCourseById(@PathVariable("courseId")String courseId){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		if(!ParamVerify.isNULL(courseId)) {
			Execution<Course> ce = courseService.getCourseById(courseId);
			if(ce.getState()==StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("course", ce.getData());
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ce.getState());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "课程ID不能为空");
			return modelMap;
		}
		return modelMap;
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAllCourse(){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		Execution<List<Course>> ce = courseService.getAllCourse();
		if(ce.getState()==StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("courseList", ce.getData());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
		}
		return modelMap;
	}
}
