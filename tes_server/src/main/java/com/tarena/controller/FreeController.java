package com.tarena.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.User;
import com.tarena.service.CourseService;
import com.tarena.service.UserService;
import com.tarena.service.VideoService;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Controller
@RequestMapping("/free/")
public class FreeController {
	@Resource(name="videoService")
	private VideoService videoService;
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="courseService")
	private CourseService courseService;
	/**
	 * 首页推荐4个免费的视频
	 * @return
	 */
	@RequestMapping(value="findRecommendVideos",method=RequestMethod.GET)
	@ResponseBody
	public Result findRecommendVideos(){
		Result result=null;
		result=this.videoService.findRecommendVideos();		
		return result;
	}
	/**
	 * 登录
	 */
	@RequestMapping(value="/login/loginName/{loginName}/loginPassword/{password}",method=RequestMethod.GET)
	@ResponseBody
	public Result login(User user,HttpSession session){
		Result result=null;
		result=this.userService.login(user,session);
		return result;
	}
	/**
	 * 注册
	 */
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	@ResponseBody
	public Result addUser(User user){
		Result result=null;
		result=this.userService.addUser(user);
		return result;
	}
	/**
	 * 分页查询所有的视频信息
	 * 
	 */
	@RequestMapping(value="/findAllVideos/currentPage/{currentPage}",method=RequestMethod.GET)
	@ResponseBody
	public Result findAllVideos(Page page){
		Result result=null;
		result=this.videoService.findAllVideosByPage(page);
		return result;
	}
	/**
	 * 查询所有的课程信息
	 * 
	 */
	@RequestMapping(value="/findAllCourse",method=RequestMethod.GET)
	@ResponseBody
	public Result findAllCourse(){
		Result result=null;
		result=this.courseService.findAllCourse();
		return result;
	}
}
