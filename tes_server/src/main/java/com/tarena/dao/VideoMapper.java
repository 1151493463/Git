package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Video;
import com.tarena.vo.Page;

public interface VideoMapper {
    //非登录下的查询推荐的视频信息
	public List<Video> findRecommendVideos();
    //分页查询视频信息
	public int getCount();
	public List getVideosByPage(Page page);
	//添加视频信息
	public void save(Video metaData);
	//更新视频的状态
	public void updateVideoState(Video video);
	//更新cc消息到video表中
	public void updateCCVideo(Video video);
	//根据视频id查询cc的信息
	public Video findPartCCInfoByVideoId(String videoId);
	/**
	 * 跟新cc播放地址
	 * @param video
	 */
	public void updateVideoUrlCC(Video video);

}
