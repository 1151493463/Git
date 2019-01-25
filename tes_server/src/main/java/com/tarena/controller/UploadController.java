package com.tarena.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarena.entity.Course;
import com.tarena.entity.User;
import com.tarena.entity.Video;
import com.tarena.service.VideoService;
import com.tarena.util.UUIDUtil;
import com.tarena.util.UploadCCUtil;
import com.tarena.util.UploadFileUtil;
import com.tarena.util.UrlUtil;
import com.tarena.vo.ReceiveInfo;
import com.tarena.vo.UploadInfo;
import com.tarena.vo.VideoBlockMessage;

@Controller
@RequestMapping("/video")
public class UploadController {
	@Resource(name="urlUtil")
	private UrlUtil urlUtil;
	@Resource(name="amqpTemplate")
	private AmqpTemplate amqpTemplate;
	@Resource(name="redisTemplage")
	private RedisTemplate redisTemplate;
	@Autowired
	private UploadCCUtil uploadCCUtil;
	@Autowired
	private VideoService videoService;
	/**
	 * 1.创建视频
	 * @param request
	 * @param response
	 * @param userId
	 * @param format
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public UploadInfo videoCreate(HttpServletRequest request,
			                      HttpServletResponse response,
			                      String userId,
			                      String format){
		UploadInfo uploadInfo=new UploadInfo();
		//开始创建视频的信息
		if(userId==null || "".equals(userId) || userId.length()!=36){
			uploadInfo.setMetaurl("用户不合法");
			return uploadInfo;
		}
		uploadInfo.setUserId(userId);
		uploadInfo.setVideoId(UUIDUtil.getUUID());
		uploadInfo.setMetaurl(urlUtil.getMetaurl());
		uploadInfo.setChunkurl(urlUtil.getChunkurl());
		uploadInfo.setMsgerror(null);
		return uploadInfo;
	}
	/**
	 * 2.上传视频的元数据,即视频的原始信息
	 * @param request
	 * @param response
	 * @param userId
	 * @param videoId
	 * @param videoTitle
	 * @param videoTag
	 * @param videoDescription
	 * @param videoFileName
	 * @param videoFileSize
	 * @param courseId
	 * @param md5
	 * @param format
	 * @param first
	 * @param addPicture
	 * @return
	 */
	@RequestMapping(value="/uploadmeta",method=RequestMethod.POST)
	@ResponseBody
	public ReceiveInfo videoUploadMeta(
			HttpServletRequest request,
            HttpServletResponse response,
            String userId,
            String videoId,
            String videoTitle,
            String videoTag,
            String videoDescription,
            String videoFileName,
            String videoFileSize,
            String courseId,
            String md5,
            String format,
            String first
          ){
		ReceiveInfo receiveInfo=new ReceiveInfo();
		String originalFileName=null;
		try {
			/*String realPath=request.getServletContext().getRealPath("/videoimage");
			File realFile=new File(realPath);
			if(!realFile.exists()){
				realFile.mkdir();
			}*/
			/*if(addPicture==null || addPicture.isEmpty()){
				receiveInfo.setResult(-1);
				receiveInfo.setMsg("error");
				return receiveInfo;
			}else{
				//说明上传文件存在
				originalFileName=addPicture.getOriginalFilename();
				boolean flag=UploadFileUtil.uploadImage(addPicture, videoId, true, 64, realPath);
				if(!flag){
					receiveInfo.setResult(-1);
					receiveInfo.setMsg("error");
					return receiveInfo;
				}
			}*/
			//视频的截图图片上传成功了,准备把视频的信息放在队列中
			Video video=new Video();
			video.setId(videoId);
			video.setUser(new User());
			video.getUser().setId(userId);
			video.setTitle(videoTitle);
			video.setTag(videoTag);
			video.setIntroduction(videoDescription);
			video.setFileName(videoId+videoFileName.substring(videoFileName.lastIndexOf(".")));
			video.setFileSize(Long.parseLong(videoFileSize));
			video.setCourse(new Course());
			video.getCourse().setId(courseId);
			video.setMd5(md5);
			video.setFirst(first);
			//String extName=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
			video.setPicture("hello");
			if("1".equals(first)){
				//第一次上传
				//构建head对象,进队列,即就是把vidoe队象送进队列
				this.amqpTemplate.convertAndSend("videoCacheQueue",video);
			}else if("2".equals(first)){
				//非第一次,需要断点续传
				//从redis中获取最后上传完的那个快的信息
				//这个块可能是head(Video),也可能是VideoBlockMessage
				List<Serializable> currengVideoAllBlock=this.redisTemplate.boundListOps(videoId).range(0, -1);
				if(currengVideoAllBlock.size()==1){
					//只有head(Video)信息
					receiveInfo.setBlockIndex(0);
					receiveInfo.setBlockNumber(UploadFileUtil.findBlockNumber(Long.parseLong(videoFileSize)));
				}else if(currengVideoAllBlock.size()>1){
					//至少有一个文件块需要处理                                                                                        0(video) 1vbm   2vbm   3vbm 4vbm   5vbm
					VideoBlockMessage vbm=(VideoBlockMessage)currengVideoAllBlock.get(currengVideoAllBlock.size()-1);
					receiveInfo.setBlockIndex(vbm.getBlockIndex());
					receiveInfo.setBlockNumber(UploadFileUtil.findBlockNumber(Long.parseLong(videoFileSize)));
				}
			}
			//视频文件的消息已经送到消息队列
			receiveInfo.setResult(0);
			receiveInfo.setMsg("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			receiveInfo.setResult(-1);
			receiveInfo.setMsg("error");
			e.printStackTrace();
		}
		return receiveInfo;
	}
	/**
	 * 3.上传文件块,一次只能上传一个块
	 * @param userId
	 * @param videoId
	 * @param blockNumber
	 * @param blockIndex
	 * @param format
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/chunk",method=RequestMethod.POST)
	@ResponseBody
	public ReceiveInfo videoChunk(@RequestParam(required=false)CommonsMultipartFile file,String videoBlockMessageStr){
		ReceiveInfo receiveInfo=new ReceiveInfo();
		VideoBlockMessage videoBlockMessage = null;
		try {
			videoBlockMessage = new ObjectMapper().readValue(videoBlockMessageStr, VideoBlockMessage.class);
			System.out.println(videoBlockMessage.getBlockIndex());
			byte[] data=file.getBytes();
			videoBlockMessage.setData(data);
			videoBlockMessage.setLength(data.length);
			//当前的视频块进入缓存队列
			this.amqpTemplate.convertAndSend("videoCacheQueue",videoBlockMessage);
			receiveInfo.setResult(0);
			receiveInfo.setMsg("success");
			receiveInfo.setBlockIndex(videoBlockMessage.getBlockIndex());
			receiveInfo.setBlockNumber(videoBlockMessage.getBlockNumber());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			receiveInfo.setResult(-1);
			receiveInfo.setMsg("error");
			receiveInfo.setBlockIndex(videoBlockMessage.getBlockIndex());
			receiveInfo.setBlockNumber(videoBlockMessage.getBlockNumber());
			e.printStackTrace();
		}
		return receiveInfo;
	}
	/**
	 * videoid	视频 id，16位 hex 字符串
status	视频状态。”OK”表示视频处理成功，”FAIL”表示视频处理失败。
duration	片长（单位:秒）
image	视频截图地址
	 * @return
	 */
	@RequestMapping(value="/notify_url",method = RequestMethod.GET,produces={"application/xml; charset=UTF-8"})
	@ResponseBody
	public String notify_url(String status,String videoid,String duration,String image){
		String returnValue=null;
		System.out.println("status:"+status+" videoid:"+videoid+" image:"+image+" duration"+duration);
		if("OK".equals(status)) {
			Map<String,String> paramsMap = new HashMap<>();
			paramsMap.put("videoid", videoid);
			paramsMap.put("format", "json");
			new UploadCCUtil();
			String videoUrlCC = uploadCCUtil.getPlayCode(paramsMap);
			if(videoUrlCC!=null) {
				Video video = new Video();
				video.setCcvid(videoid);
				video.setState(6);
				video.setVideoUrlcc(videoUrlCC);
				video.setPicturecc(image);
				videoService.updateCCVideoUrlCC(video);
				returnValue = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><video>OK</video>";
			}
		}
		return returnValue;
	}
}
