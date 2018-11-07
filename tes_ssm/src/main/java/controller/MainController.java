package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Execution;
import enums.StateEnum;
import service.MainService;
import vo.UserCartogram;
import vo.VideoCartogram;
@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	private MainService mainService;
	
	@RequestMapping(value="/userCartogram",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> userCartogram(){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		Execution<List<UserCartogram>> me = mainService.userCartogram();
		if(me.getState()==StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("userCartogram", me.getData());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", me.getStateInfo());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/videoCartogram",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> videoCartogram(){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		Execution<List<VideoCartogram>> me = mainService.videoCartogram();
		if(me.getState()==StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("videoCartogram", me.getData());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", me.getStateInfo());
		}
		return modelMap;
	}

}
