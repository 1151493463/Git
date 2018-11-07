package controller;

import java.io.BufferedReader;
import java.io.FileReader;
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
import entity.Comment;
import enums.StateEnum;
import service.CommentService;
import util.ParamVerify;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/{courses}/{currentPage}/{keyWord}/{isVerify}/{order}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getCommentByPage(@PathVariable("courses")String courses,@PathVariable("currentPage")String currentPageStr,@PathVariable("keyWord")String keyWord,
			@PathVariable("isVerify")String isVerify,@PathVariable("order")String order){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		Map<String, Object> keyWords = new HashMap<String,Object>();
		if(!"undefined".equals(courses)) {
			String[] courseArray = courses.split(",");
			keyWords.put("courseArray", courseArray);
		}
		int currentPage = "undefined".equals(currentPageStr) || Integer.parseInt(currentPageStr)<1?1:Integer.parseInt(currentPageStr);
		if(!ParamVerify.isNULL(keyWord)) {
			keyWords.put("keyWord", keyWord);
		}
		if(!ParamVerify.isNULL(isVerify)) {
			keyWords.put("isVerify", isVerify);
		}
		if(!ParamVerify.isNULL(order) &&("ASC".equals(order) || "DESC".equals(order))) {
			keyWords.put("order", order);
		}
		Execution<Page<Comment>> ce = commentService.getCommentByPage(currentPage,keyWords);
		if(ce.getState()==StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
			modelMap.put("page", ce.getData());
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
		}
		return modelMap;
	}
	@RequestMapping(value="/{ids}/{advice}",method=RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> verifyComment(@PathVariable("ids")String ids,@PathVariable("advice")String advice){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		if(ParamVerify.isNULL(ids,advice)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "参数不能为空！");
			return modelMap;
		}
		String[] idArray = ids.split(",");
		Execution<Comment> ce = commentService.verifyComment(idArray,advice);
		if(ce.getState()==StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg", ce.getStateInfo());
		}
		
		return modelMap;
	}
	@RequestMapping(value="/{word}",method=RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> saveSensitiveWord(@PathVariable("word")String sensitiveWord){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		Execution<Comment> ce = commentService.saveSensitiveWord(sensitiveWord);
		if(ce.getState()==StateEnum.SUCCESS.getState()) {
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ce.getStateInfo());
		}
		return modelMap;
	}
	@RequestMapping(value="/word",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getSensitiveWord(){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		try {
			String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(basePath + "/sensitiveWord.txt"));
			String readLine = br.readLine();
			StringBuilder sb = new StringBuilder();
			if(readLine!=null) {
				sb.append(readLine);
				readLine = br.readLine();
			}
			modelMap.put("success", true);
			modelMap.put("word", sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

}
