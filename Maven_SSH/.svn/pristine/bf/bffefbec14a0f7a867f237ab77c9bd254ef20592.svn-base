package cn.itcast.utils;

import java.io.File;
import java.net.URL;

public class Test {
	public static void main(String[] args) {
		//类加载根路径
		//String classPath = this.getClass().getResource("/").getPath();

		//类加载根路径
		//URL xmlPath = this.getClass().getClassLoader().getResource("");

		//类所在工程根路径
		//String proClassPath = this.getClass().getResource("").getPath();

		//项目服务器脚本文件路径
		File directory = new File("");// 参数为空
		//String proRootPath = directory.getCanonicalPath();

		//项目服务器脚本文件路径
		String proPath = System.getProperty("user.dir");

		// 获取所有的类路径 包括jar包的路径
		String allClassPath = System.getProperty("java.class.path");
		
		//项目部署的路径
		//String path = request.getSession().getServletContext().getRealPath("/");

		//System.out.println("类加载根路径:" + classPath);
		//System.out.println("类加载根路径:" + xmlPath);
		//System.out.println("类所在工程路径:" + proClassPath);
		//System.out.println("项目服务器脚本文件路径:" + proRootPath);
		System.out.println("项目服务器脚本文件路径:" + proPath);
		System.out.println("项目部署的路径:" + allClassPath );
		//System.out.println("获取所有的类路径:" + path );
	}
}
