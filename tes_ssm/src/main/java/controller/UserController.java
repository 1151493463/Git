package controller;

import java.io.FileInputStream;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;

import dto.Execution;
import dto.Page;
import entity.User;
import enums.StateEnum;
import exception.MissingTypMatch;
import service.UserService;
import util.ParamVerify;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login/{userName}/{userPassword}/{verifyCode}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> login(@PathVariable("userName") String userName,
			@PathVariable("userPassword") String userPassword, @PathVariable("verifyCode") String verifyCode,
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		if ((request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString())
				.equalsIgnoreCase(verifyCode)) {
			Execution<User> ue = null;
			if (!ParamVerify.isNULL(userName, userPassword)) {
				//login_shoiro()
				//ue = userService.login(userName, userPassword, request.getSession());
				ue = userService.shiroLogin(userName, userPassword, request);
				if (ue.getState() == StateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ue.getStateInfo());
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码为空");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误！");
		}

		return modelMap;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> shiroLogout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(value = "/users/{currentPage}/{keyWord}/{roleCategory}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserByPage(@PathVariable("currentPage") String currentPageStr,
			@PathVariable("keyWord") String keyWord, @PathVariable("roleCategory") String roleCategory) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			int currentPage = "undefined".equals(currentPageStr) || Integer.parseInt(currentPageStr) < 1 ? 0
					: Integer.parseInt(currentPageStr);
			keyWord = "undefined".equals(keyWord) ? null : keyWord;
			roleCategory = "undefined".equals(roleCategory) ? null : roleCategory;
			Map<String, Object> keyWords = new HashMap<String,Object>();
			keyWords.put("userName", keyWord);
			Execution<Page<User>> ue = userService.getUserByPage(currentPage, keyWords, roleCategory);
			if (ue.getState() == StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("page", ue.getData());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());
			}
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(@RequestParam(required=false)CommonsMultipartFile userImg, String userStr, String userRole) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Execution<User> ue = null;
		try {
			if(ParamVerify.isNULL(userStr)) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户信息不能为空");
				return modelMap;
			}
			User user = new ObjectMapper().readValue(userStr, User.class);
			user.setLoginType(ParamVerify.getLoginType(user.getLoginName()));
			if(userImg!=null) {
				ParamVerify.verifyUploadImage(userImg);
			}
			String[] role = null;
			if(!ParamVerify.isNULL(userRole)) {
				role = userRole.split(",");
			}
			ue = userService.addUser(user,userImg,role);
			if(ue.getState()==StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/{userId}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserById(@PathVariable("userId") String userId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		if (!ParamVerify.isNULL(userId)) {
			Execution<User> ue = userService.getUserById(userId);
			if (ue.getState() == StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("user", ue.getData());
			} else {
				modelMap.put("success", true);
				modelMap.put("errMsg", ue.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "要查询的用户ID不能为空");
		}

		return modelMap;
	}
	@RequestMapping(value="/head",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateHeadImage(@RequestParam(required=false)CommonsMultipartFile userImg,String userId){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		if(userImg!=null) {
			ParamVerify.verifyUploadImage(userImg);
			Execution<User> ue = userService.updateImage(userId,userImg);
			if(ue.getState()==StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
				modelMap.put("imgAddr", ue.getData().getHead());
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传头像不能为空！");
		}
		return modelMap;
	}
	@RequestMapping(value= "/{userStr}/{userRole}",method=RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> updateUser(@PathVariable("userStr")String userStr,@PathVariable("userRole")String userRole){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		try {
			if(userStr!=null) {
				User user = new ObjectMapper().readValue(userStr, User.class);
				String[] roles = null;
				if(!ParamVerify.isNULL(userRole)) {
					if(!"changeState".equals(userRole)) {
						roles = userRole.split(",");
					}
				}
				Execution<User> ue = userService.updateUser(user,roles);
				if(ue.getState()==StateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ue.getStateInfo());
				}
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户信息不能为空");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	}
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public void exporUserTotExcel(HttpServletResponse response) {
		HSSFWorkbook hw = userService.exporUserTotExcel();
		// 清除首部空白行
		try {
			response.reset();
			response.setContentType("application/msexcel;charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String(("用户信息表.xls").getBytes("GBK"), "ISO8859-1"));
			OutputStream out = response.getOutputStream();
			hw.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/template",method=RequestMethod.GET)
	public void exporUserTemplate(HttpServletResponse response) {
		try {
			response.reset();
			response.setContentType("application/msexcel;charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String(("用户信息表-模板.xls").getBytes("GBK"), "ISO8859-1"));
			OutputStream out = response.getOutputStream();
			String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String filePath = basePath+"/用户信息表-模板.xls";
			
			byte[] data = new byte[1024];
			@SuppressWarnings("resource")
			FileInputStream stream = new FileInputStream(filePath);
			int len=0;
			while((len=stream.read(data))!=-1) {
				out.write(data,0,len);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/import",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> importUserExcel(@RequestParam(required=false)CommonsMultipartFile file){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		try {
			if(file==null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传文件不能为空");
				return modelMap;
			}
			if(!"application/vnd.ms-excel".equals(file.getContentType())) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传文件类型错误");
				return modelMap;
			}
			Execution<User> ue = userService.importUserExcel(file);
			if(ue.getState()==StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());
			}
		}catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	@RequestMapping(value="/active",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getBackPasswordByEmail(String loginName){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		try {
			Execution<User> ue = null;
			String loginType = ParamVerify.getLoginType(loginName);
			if("邮箱".equals(loginType)) {
				ue = userService.getBackPasswordByEmail(loginName);
				if(ue.getState()==StateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ue.getStateInfo());
				}
			}else if("手机".equals(loginType)) {
				//发送手机验证码
			}
		} catch (MissingTypMatch e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg",e.getMessage());
		}
		return modelMap;
	} 
	@RequestMapping(value="/password",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updatePasswordByCode(User user){
		Map<String, Object> modelMap = new HashMap<String,Object>();
		try {
			Execution<User> ue = null;
			if (ParamVerify.isNULL(user.getLoginName(), user.getPassword(), user.getActiveCode())) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "信息不能为空");
				return modelMap;
			}
			if ("邮箱".equals(ParamVerify.getLoginType(user.getLoginName()))) {
				ue = userService.updatePasswordByEmail(user);
			}else if("手机".equals(ParamVerify.getLoginType(user.getLoginName()))) {
				
			}
			if(ue.getState()==StateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ue.getStateInfo());
			}
		} catch (Exception e) {
			// TODO: handle exception
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
