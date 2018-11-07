package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.VideoDao;
import dto.Execution;
import dto.Page;
import entity.Video;
import enums.StateEnum;
import service.VideoService;
import util.CommonValue;
@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoDao videoDao;
	@Autowired
	private CommonValue commonValue;

	@Override
	public Execution<Page<Video>> getVideoByPage(int currentPage, Map<String, Object> keyWords) {
		// TODO Auto-generated method stub
		Execution<Page<Video>> ve = null;
		try {
			Page<Video> page = new Page<Video>(currentPage,keyWords,commonValue.getPageSize()*2);
			int totalCount = videoDao.getTotalCount(keyWords);
			page.setTotalCount(totalCount);
			List<Video> videoList = videoDao.queryVideoList(page.getOffset(),page.getPageSize(),keyWords);
			page.setData(videoList);
			ve = new Execution<>(StateEnum.SUCCESS,page);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ve = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		
		return ve;
	}

	@Override
	public Execution<Video> getVideoById(String videoId) {
		// TODO Auto-generated method stub
		Execution<Video> ve = null;
		try {
			Video videoCondition = new Video();
			videoCondition.setId(videoId); 
			videoCondition.setClickCount(1);
			int updateVideo = videoDao.updateVideo(videoCondition);
			if(updateVideo<1) {
				throw new RuntimeException("更新视频点击数失败！");
			}
			Video video = videoDao.queryVideoByCondition(videoCondition);
			if(video==null) {
				throw new RuntimeException("为查询到该视频！");
			}
			ve = new Execution<>(StateEnum.SUCCESS,video);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ve = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		
		return ve;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<Video> passVideo(String videoId,String status) {
		// TODO Auto-generated method stub
		Execution<Video> ve = null;
		try {
			Video video = new Video();
			video.setIsPass(status);
			video.setId(videoId);
			int updateVideo = videoDao.updateVideo(video);
			if(updateVideo<1) {
				throw new RuntimeException("通过视频失败");
			}
			ve = new Execution<>(StateEnum.SUCCESS);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ve = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return ve;
	}
	
	

}
