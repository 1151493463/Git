package com.tarena.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tarena.annotation.MyController;
import com.tarena.annotation.MyRequestMapping;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Properties properties = new Properties();
	private List<String> classNames = new ArrayList<String>();
	private Map<String,Object> controllerMapping = new HashMap<String, Object>();
	private Map<String,Method> handlerMapping = new HashMap<String, Method>();
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		//加载配置文件
		doLoadConfig(config.getInitParameter("contextConfigLocation"));
		//要扫描的包
		doScanner(properties.getProperty("scanPackage"));
		//3.拿到扫描到的类,通过反射机制,实例化,并且放到ioc容器中(k-v  beanName-bean) beanName默认是首字母小写
		doInstance();
		//初始化HandlerMapping k-v url-Method
		initHandlerMapping();
	}


	private void initHandlerMapping() {
		// TODO Auto-generated method stub
		if(controllerMapping.isEmpty()){
			return;
		}
		try{
			for(Entry<String, Object> entry:controllerMapping.entrySet()){
				Class<? extends Object> clazz = entry.getValue().getClass();
				if(!clazz.isAnnotationPresent(MyController.class)){
					return;
				}
				String baseUrl = null;
				if(clazz.isAnnotationPresent(MyRequestMapping.class)){
					MyRequestMapping annotation = clazz.getAnnotation(MyRequestMapping.class);
					baseUrl = annotation.value();
				}
				Method[] methods = clazz.getMethods();
				for(Method method:methods){
					if(!method.isAnnotationPresent(MyRequestMapping.class)){
						continue;
					}
					MyRequestMapping annotation = method.getAnnotation(MyRequestMapping.class);
					String url = annotation.value();
					url =(baseUrl+"/"+url).replaceAll("/+", "/");
					handlerMapping.put(url, method);
					controllerMapping.put(url, clazz.newInstance());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private void doInstance() {
		if(classNames.isEmpty()){
			return;
		}
		for(String className:classNames){
			try{
				Class<?> clazz = Class.forName(className);
				if(clazz.isAnnotationPresent(MyController.class)){
					/*controllerMapping.put(toLowerFistWord(clazz.getName()), clazz.newInstance());*/
					controllerMapping.put(clazz.getName(), clazz.newInstance());
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				continue;
			}
		}
	}


	/*private String toLowerFistWord(String name) {
		char[] charArray = name.toCharArray();
		charArray[0] += 32;
		System.out.println(String.valueOf(charArray));
		return String.valueOf(charArray);

	}
*/

	private void doScanner(String packageName) {
		//把所有的.替换为/
		URL url = this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.", "/"));
		File dir = new File(url.getFile());
		for(File file:dir.listFiles()){
			if(file.isDirectory()){
				doScanner(packageName+file.getName());
			}else{
				String className = packageName+"."+file.getName().replace(".class", "");
				classNames.add(className);
			}
		}
	}


	private void doLoadConfig(String fileName) {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(null!=resourceAsStream){
				try {
					resourceAsStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			doDispatcher(request,response);
		}catch(Exception e){
			response.getWriter().write("500 服务器内部错误");
		}
	}

	
	private void doDispatcher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		if(handlerMapping.isEmpty()){
			return;
		}
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.replace(contextPath, "").replaceAll("/+", "/");
		if(!handlerMapping.containsKey(url)){
			response.getWriter().write("404");
			return;
		}
		//指定的Controller方法
		Method method = handlerMapping.get(url);
		//方法参数列表
		Class<?>[] parameterTypes = method.getParameterTypes();
		//请求参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		//保存参数值
		Object[] paramValues = new Object[parameterTypes.length];
		for(int i=0;i<parameterTypes.length;i++){
			String requestParam = parameterTypes[i].getSimpleName();
			if(requestParam.equals("HttpServletRequest")){
				paramValues[i]=request;
				continue;
			}
			if(requestParam.equals("HttpServletResponse")){
				paramValues[i]=response;
				continue;
			}
			if(requestParam.equals("String")){
				for(Entry<String,String[]> param:parameterMap.entrySet()){
					String value =Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
					paramValues[i]=value;
				}
			}
		}
		//反射调用
		try{
			method.invoke(controllerMapping.get(url),paramValues);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
