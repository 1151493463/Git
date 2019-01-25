package com.tarena.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.vo.Result;

@Controller
@RequestMapping(value="/carousel/")
public class CarouselController {
	@RequestMapping(value="carouselImage",method=RequestMethod.GET)
	@ResponseBody
	public Result carsoleImage(HttpServletRequest request,
			                         HttpServletResponse response){
		Result result=new Result();
		List<String> list=new ArrayList<String>();
		//获取服务器中的指定目录中的图片,并检查图片是否存在,构建list对象
		//获取服务的路径
		String realPath=request.getServletContext().getRealPath("/carousel");
		File realFile=new File(realPath);
		if(realFile.exists()){
			//图片的路径是存在的
			File[] files=realFile.listFiles(new FileFilter(){
				@Override
				public boolean accept(File pathname) {
					if(pathname.isFile())
						if(pathname.getName().endsWith(".jpg") ||
						   pathname.getName().endsWith(".jpeg") ||
						   pathname.getName().endsWith(".png") ||
						   pathname.getName().endsWith(".gif") ||
						   pathname.getName().endsWith(".ico")){
							//是文件且也是图片
							list.add(pathname.getName());
							return true;
						}					  
					return false;
				}
			});						
		}else{
			list.add("没有图片信息!");
		}	  
		result.setStatus(1);
		result.setData(list);
		return result;
	}
}
