package com.tarena.test;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class TestCarouselController {
	private final String host="http://localhost:8080";
	private final String basePath="/tes_server";
	/**
	 * 测试轮播图片
	 */
	@Test
	public void testMethod(){
		String content=null;
		Map<String,Object> contentMap=null;
		try{
			//用httpClient模拟http请求,相当于用浏览器发送请求
			HttpClient httpClient=HttpClients.createDefault();
			HttpGet httpGet=new HttpGet(host+basePath+"/carousel/carouselImage");
			//发送一个get请求,返回的结果是一个HttpResponse响应对象
			HttpResponse response=httpClient.execute(httpGet);
			int resStatus=response.getStatusLine().getStatusCode();
			System.out.println("status="+resStatus);
			if(resStatus==HttpStatus.SC_OK){
				//sc_ok=200,说明响应状态码 是200
				//从响应对象中获取响应体内容
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
			    //关闭httpClient的连接
				httpClient.getConnectionManager().shutdown();
			}
			System.out.println("content="+content);
			if(content!=null){
				contentMap=(Map<String,Object>)JSON.parse(content.trim());
				System.out.println("contentMap="+contentMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
