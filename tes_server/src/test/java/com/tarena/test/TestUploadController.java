package com.tarena.test;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.tarena.util.BlockStreamBody;
import com.tarena.util.Md5Util;
import com.tarena.util.UploadFileUtil;

public class TestUploadController {
	private final String host="http://localhost:8080";
	private final String basePath="/tes_server";
	/**
	 * 专门用来给post方式添加提交的数据(仅限文本)
	 * @param params 提交的数据
	 * @param multipartEntityBuilder  把提交的数据存储到此对象中
	 */
	private void buildMultipartEntity(
			Map<String,String> params,
			MultipartEntityBuilder multipartEntityBuilder){
		//解决找中文乱码问题
		ContentType contentType=ContentType.create(HTTP.PLAIN_TEXT_TYPE,HTTP.UTF_8);
		
		//把map集合参数的key转换成list集合
		List<String> keys=new ArrayList<String>(params.keySet());
		Collections.sort(keys,String.CASE_INSENSITIVE_ORDER);
		for(Iterator<String> iterator=keys.iterator();iterator.hasNext();){
			String key=iterator.next();
			String value=params.get(key);
			if(StringUtils.isNoneBlank(value)){
				multipartEntityBuilder.addTextBody(key, value,contentType);
			}
		}
	}
	
	
	/**
	 * 1.创建视频
	 * @param params  服务器需要的参数
	 * @param serverUrlCreate  服务器的url地址
	 * @return  返回结果
	 *          vidoeId
	 *          userId
	 *          metaUrl
	 *          chunkUrl
	 */
	public Map<String,String> videoCreate(
			Map<String,String> params,
			String serverUrlCreate){
		Map<String,String> returnMap=null;
		String content=null;
		try{
			HttpClient httpClient=HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(serverUrlCreate);
			//给post方式添加提交的数据
			MultipartEntityBuilder multipartEntityBuilder=MultipartEntityBuilder.create();
			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			//用buildMultipartEntity方法存储post中的数据					
			
			this.buildMultipartEntity(params,multipartEntityBuilder);
			
			httpPost.setEntity(multipartEntityBuilder.build());
			
			HttpResponse response=httpClient.execute(httpPost);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
				httpClient.getConnectionManager().shutdown();
			}
			//System.out.println("content-->"+content);
			if(content!=null){
				returnMap=(Map<String,String>)JSON.parse(content.trim());
				//System.out.println("conentMap-->"+returnMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnMap;
	}
	/**
	 * 上传视频文件的元数据
	 * @param params  服务器需要的参数
	 * @param videoPicture  视频的截图
	 * @param serverUrl  服务器url地址
	 * @return  返回的数据
	 *          result
	 *          msg
	 *          blockNumber
	 *          blockInde
	 */
	public Map<String,Object> uploadMetaData(
			Map<String,String> params,
			File videoPicture,
			String serverUrl){
		Map<String,Object> returnMap=null;
		String content=null;
		try{
			HttpClient httpClient=HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(serverUrl);
			//给post方式添加提交的数据
			MultipartEntityBuilder multipartEntityBuilder=MultipartEntityBuilder.create();
			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			//用buildMultipartEntity方法存储post中的数据					
			//添加文本
			this.buildMultipartEntity(params,multipartEntityBuilder);
			//添加文件
			ContentBody contentBody=new FileBody(videoPicture);
			multipartEntityBuilder.addPart("addPicture",contentBody);
						
			httpPost.setEntity(multipartEntityBuilder.build());
			
			HttpResponse response=httpClient.execute(httpPost);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
				httpClient.getConnectionManager().shutdown();
			}
			//System.out.println("content-->"+content);
			if(content!=null){
				returnMap=(Map<String,Object>)JSON.parse(content.trim());
				//System.out.println("conentMap-->"+returnMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnMap;
	}
	/**
	 * 3.上传文件块
	 * @param params  服务器需要的参数
	 * @param filePath  文件实际路径
	 * @param serverUrl  服务器url地址
	 * @return 返回的数据
	 *       result
	 *       msg
	 *       blockNumber
	 *       blockIndex
	 */
	public Map<String,String> uploadToServer(
			Map<String,String> params,
			String filePath,
			String serverUrl){
		Map<String,String> returnMap=null;
		String content=null;
		try{
			HttpClient httpClient=HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(serverUrl);
			//给post方式添加提交的数据
			MultipartEntityBuilder multipartEntityBuilder=MultipartEntityBuilder.create();
			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			//用buildMultipartEntity方法存储post中的数据					
			//添加文本
			this.buildMultipartEntity(params,multipartEntityBuilder);
			//添加文件,是整个视频的文件的某一块(4M)
			int blockNumber=Integer.parseInt(params.get("blockNumber"));
			int blockIndex=Integer.parseInt(params.get("blockIndex"));
			//构建文件块(4M)
			ContentBody contentBody=new BlockStreamBody(blockNumber,blockIndex,new File(filePath));
			multipartEntityBuilder.addPart("file",contentBody);
			multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
			
			httpPost.setEntity(multipartEntityBuilder.build());
			
			HttpResponse response=httpClient.execute(httpPost);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=response.getEntity();
				content=EntityUtils.toString(entity);
				httpClient.getConnectionManager().shutdown();
			}
			//System.out.println("content-->"+content);
			if(content!=null){
				returnMap=(Map<String,String>)JSON.parse(content.trim());
				//System.out.println("conentMap-->"+returnMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnMap;
	}
	
	/**
	 * 上传文件信息
	 */
	public static void main(String[] args) {
		try {
			TestUploadController  emulator=new TestUploadController();
			//0.准备数据
			String filePath="d:/ks.mp4";
			String fileName="ks.mp4";
			long fileSize=0;
			File targetFile=new File(filePath);
			if(targetFile.exists()){
				fileSize=targetFile.length();
				
				//1.创建视频
				String serverUrlCreate="http://localhost:8080/tes_server/video/create";
				Map<String,String> params=new HashMap<String,String>();
				params.put("userId", "7008ffa6-e01d-48ed-a460-dbf2a4908bfa");
				params.put("format", "json");
				Map<String,String> createMap=emulator.videoCreate(params, serverUrlCreate);
				System.out.println("createMap-->"+createMap);
				String msgerror=createMap.get("msgerror");
				if(msgerror==null){					
					//2.上传文件元数据	
					//构建所有的视频信息
					Map<String,String> metaParams=new HashMap<String,String>(createMap);
					String  serverUrl=metaParams.get("metaurl");
					
					metaParams.put("videoTitle", "快闪视频");
					metaParams.put("videoTag", "java娱乐");
					metaParams.put("videoDescription", "很好玩的视频");
					metaParams.put("videoFileName", fileName);
					metaParams.put("videoFileSize", fileSize+"");
					metaParams.put("courseId", "8c2ded0e-0455-4631-a3c4-b3c50aeda12f");
					metaParams.put("md5",Md5Util.getMd5(targetFile));
					metaParams.put("format","json");
					metaParams.put("first","1");
					metaParams.remove("metaurl");
					metaParams.remove("chunkurl");
					metaParams.remove("msgerror");
					File videoPicture=new File("d:/video.png");
				
					Map<String,Object> returnMetaMap=emulator.uploadMetaData(metaParams, videoPicture, serverUrl);
					System.out.println("returnMetaMap-->"+returnMetaMap);
					//3.循环上传文件块,之前,先把文件分块
				    //String result=returnMetaMap.get("result").toString();
				  
				    String msg=returnMetaMap.get("msg").toString();
				    if("success".equals(msg)){
				    	//说明元数据上传成功
				    	//准备数据
				    	//blockIndex代表的最后一个正确上传的文件块的索引
				    	//blockNumber代表文件分块的总块数
				    	//此值是从上传元数据的返回结果中得到的
				    	int blockIndex=(Integer)returnMetaMap.get("blockIndex")+1;
				    	int blockNumber=(Integer)(returnMetaMap.get("blockNumber"));
				    	if(blockNumber==0){
				    		blockNumber=UploadFileUtil.findBlockNumber(fileSize);
				    	}			    	
				    	
				    	Map<String,String> chunkParams=new HashMap<String,String>();
				    	chunkParams.put("userId", "7008ffa6-e01d-48ed-a460-dbf2a4908bfa");
				    	chunkParams.put("videoId", createMap.get("videoId"));
				    	chunkParams.put("blockNumber", blockNumber+"");
				    	//chunkParams.put("blockIndex", "");
				    	chunkParams.put("format", "json");
				    	//chunkParams.put("file", "");
				    	serverUrl=createMap.get("chunkurl");
				    	for(int i=blockIndex;i<=blockNumber;i++){
				    		chunkParams.put("blockIndex", i+"");
				    		Map<String,String> returnChunkMap=emulator.uploadToServer(chunkParams, filePath, serverUrl);
				    		System.out.println("returnChunkMap-->"+returnChunkMap);
				    		if("error".equals(returnChunkMap.get("msg"))){
				    			break;
				    		}
				    	}
				    	
				    }
					
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
