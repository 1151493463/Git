package com.tarena.test;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class TestFreeController {
	private final String host="http://localhost:8080";
	private final String basePath="/tes_server";
	/**
	 * 测试首页推荐4个视频
	 */
	@Test
	public void testFindRecommendVideos(){
		String content=null;
		Map<String,Object> contentMap=null;
		try{
			HttpClient httpClient=HttpClients.createDefault();
			HttpGet httpGet=new HttpGet(host+basePath+"/free/findRecommendVideos");
			HttpResponse response=httpClient.execute(httpGet);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
				httpClient.getConnectionManager().shutdown();
			}
			System.out.println("content-->"+content);
			if(content!=null){
				contentMap=(Map<String,Object>)JSON.parse(content.trim());
				System.out.println("conentMap-->"+contentMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 登录
	 */
	@Test
	public void testLogin(){
		String content=null;
		Map<String,Object> contentMap=null;
		try{
			HttpClient httpClient=HttpClients.createDefault();
			HttpGet httpGet=new HttpGet(host+basePath+"/free/login/wet_zss@126.com/123");
			HttpResponse response=httpClient.execute(httpGet);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
				httpClient.getConnectionManager().shutdown();
			}
			System.out.println("content-->"+content);
			if(content!=null){
				contentMap=(Map<String,Object>)JSON.parse(content.trim());
				System.out.println("conentMap-->"+contentMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 注册
	 */
	@Test
	public void testAddUser(){
		String content=null;
		Map<String,Object> contentMap=null;
		try{
			HttpClient httpClient=HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(host+basePath+"/free/registerUser");
			//给post方式添加提交是的数据
			MultipartEntityBuilder multipartEntityBuilder=MultipartEntityBuilder.create();
			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			
			//解决用post提交的中文乱码问题
			ContentType contentType=ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8);
			
			//multipartEntityBuilder.setContentType(contentType);
			//给post提交的请求体添加提交的数据
			multipartEntityBuilder.addTextBody("loginName", "测试111@126.com",contentType);
			multipartEntityBuilder.addTextBody("password", "123",contentType);
			//Charset chars=Charset.forName("UTF-8");
			//multipartEntityBuilder.setCharset(chars);
			httpPost.setEntity(multipartEntityBuilder.build());
			
			HttpResponse response=httpClient.execute(httpPost);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
				httpClient.getConnectionManager().shutdown();
			}
			System.out.println("content-->"+content);
			if(content!=null){
				contentMap=(Map<String,Object>)JSON.parse(content.trim());
				System.out.println("conentMap-->"+contentMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 分页查询视频的信息
	 */
	@Test
	public void testFindVideosByPage(){
		String content=null;
		Map<String,Object> contentMap=null;
		try{
			HttpClient httpClient=HttpClients.createDefault();
			HttpGet httpGet=new HttpGet(host+basePath+"/free/findAllVideos/currentPage/1");
			HttpResponse response=httpClient.execute(httpGet);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
				httpClient.getConnectionManager().shutdown();
			}
			System.out.println("content-->"+content);
			if(content!=null){
				contentMap=(Map<String,Object>)JSON.parse(content.trim());
				System.out.println("conentMap-->"+contentMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 分页查询视频的信息
	 */
	@Test
	public void testFindAllCourse(){
		String content=null;
		Map<String,Object> contentMap=null;
		try{
			HttpClient httpClient=HttpClients.createDefault();
			HttpGet httpGet=new HttpGet(host+basePath+"/free/findAllCourse");
			HttpResponse response=httpClient.execute(httpGet);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
				httpClient.getConnectionManager().shutdown();
			}
			System.out.println("content-->"+content);
			if(content!=null){
				contentMap=(Map<String,Object>)JSON.parse(content.trim());
				System.out.println("conentMap-->"+contentMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
