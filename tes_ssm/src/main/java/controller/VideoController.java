package controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Execution;
import dto.Page;
import entity.Video;
import enums.StateEnum;
import service.VideoService;
import util.ParamVerify;
@Controller
@RequestMapping("/video")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping("/{course}/{keyWord}/{currentPage}")
	@ResponseBody
	public Map<String,Object> getVideoByPage(@PathVariable("course")String courseName,@PathVariable("keyWord")String keyWord,@PathVariable("currentPage")String currentPageStr){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			Map<String, Object> keyWords = new HashMap<String,Object>();
			if(!"undefined".equals(keyWord)) {
				keyWords.put("keyWord",keyWord);
			}
			if(!"undefined".equals(courseName)) {
				keyWords.put("courseName", courseName);
			}
			int currentPage = "undefined".equals(currentPageStr) || Integer.parseInt(currentPageStr)<1?1:Integer.parseInt(currentPageStr);
			Execution<Page<Video>> ve = videoService.getVideoByPage(currentPage,keyWords);
			if(ve.getState()==StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("page", ve.getData());
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ve.getStateInfo());
			}
		}catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getVideoById(@PathVariable("id")String videoId){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		if(!ParamVerify.isNULL(videoId)) {
			Execution<Video> ve = videoService.getVideoById(videoId);
			if(ve.getState()==StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("video", ve.getData());
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ve.getStateInfo());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "视频ID不能为空");
		}
		return modelMap;
	}
	@RequestMapping(value="/{id}/{status}",method=RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> verifyVideo(@PathVariable("id")String videoId,@PathVariable("status")String status){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		if(ParamVerify.isNULL(videoId,status)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "视频ID为空");
			return modelMap;
		}
		Execution<Video> ve = videoService.passVideo(videoId,status);
		if(ve.getState()==StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ve.getStateInfo());
		}
		return modelMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
