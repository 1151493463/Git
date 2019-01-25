package com.tarena.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tarena.dao.VideoMapper;
import com.tarena.entity.Video;
import com.tarena.service.VideoService;
import com.tarena.util.PageUtil;
import com.tarena.vo.Page;
import com.tarena.vo.Result;
@Service("videoService")
public class VideoServiceImpl implements VideoService {
	@Resource(name="videoMapper")
	private VideoMapper videoMapper;
	@Resource(name="pageUtil")
	private PageUtil pageUtil;
	@Override
	public Result findRecommendVideos() {
		Result result=new Result();
		List<Video> videos=null;
		videos=this.videoMapper.findRecommendVideos();
		if(videos!=null){
			result.setStatus(1);
			result.setData(videos);
		}else{
			result.setStatus(0);
			result.setMessage("没有视频信息");
		}
		return result;
	}
	@Override
	public Result findAllVideosByPage(Page page) {
		Result result=new Result();
		page.setPageSize(pageUtil.getPageSize());
		
		int totalCount=this.videoMapper.getCount();
		page.setTotalCount(totalCount);
		
		int totalPage=(totalCount%pageUtil.getPageSize()==0)? (totalCount/pageUtil.getPageSize()) :(totalCount/pageUtil.getPageSize())+1;
		page.setTotalPage(totalPage);
		
		if(page.getCurrentPage()==1){
			page.setPreviousPage(1);
		}else{
			page.setPreviousPage(page.getCurrentPage()-1);
		}
		
		if(page.getCurrentPage()==totalPage){
			page.setNextPage(totalPage);
		}else{
			page.setNextPage(page.getCurrentPage()+1);
		}
		
		page.setData(this.videoMapper.getVideosByPage(page));
		
		page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));
		if(page.getData()!=null){
			result.setStatus(1);
			result.setData(page);
		}else{
			result.setStatus(0);
			result.setMessage("没有视频信息");
		}
		
		return result;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public boolean save(Video metaData) {
		boolean flag=false;		
		this.videoMapper.save(metaData);
		flag=true;		
		return flag;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void updateVideoState(Video video) {
		this.videoMapper.updateVideoState(video);		
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void updateCCVideo(Video video) {
		this.videoMapper.updateCCVideo(video);
		
	}
	@Override
	public Video findPartCCInfoByVideoId(String videoId) {
		Video video=null;
		video=this.videoMapper.findPartCCInfoByVideoId(videoId);
		return video;
	}
	@Override
	public void updateCCVideoUrlCC(Video video) {
		// TODO Auto-generated method stub
		this.videoMapper.updateVideoUrlCC(video);
	}
	
	

}
