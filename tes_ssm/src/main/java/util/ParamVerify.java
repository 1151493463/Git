package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import exception.MissingTypMatch;

public class ParamVerify {
	
	public static boolean isNULL(String... params) {
		
		for(String param:params) {
			if("undefined".equals(param)) {
				return true;
			}
		}
		return false;
		
	}
	
	public static String getLoginType(String loginName) throws MissingTypMatch {
		Pattern telReg = Pattern.compile("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");
		Matcher tel = telReg.matcher(loginName);
		Pattern emailReg = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		Matcher email = emailReg.matcher(loginName);
		if(tel.matches()) {
			return "手机";
		}else if(email.matches()) {
			return "邮箱";
		}else {
			throw new MissingTypMatch("用户登陆名不符合要求");
		}
	}
	
	public static void verifyUploadImage(CommonsMultipartFile image) {
		if(!CommonValue.CONTENTTYPE.contains(image.getContentType())) {
			throw new RuntimeException("文件类型不符合要求，文件类型应该为bmp,jpeg,jpg,png,gif,icon");
		}
		if(image.getSize()>4194304) {
			throw new RuntimeException("上传文件过大，应小于4M");
		}
	}

}
