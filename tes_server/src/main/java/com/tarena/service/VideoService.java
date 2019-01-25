package com.tarena.service;

import com.tarena.entity.Video;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

public interface VideoService {
	//无需登录查询推荐的视频信息
	public Result findRecommendVideos();
	//分页查询视频信息
	public Result findAllVideosByPage(Page page);
	//添加视频信息
	public boolean save(Video metaData);
	//更新视频的状态
	public void updateVideoState(Video video);
	//更新视频信息,把cc的消息更新到数据库中
	public void updateCCVideo(Video video);
	//根据视频id查询cc的信息
	public Video findPartCCInfoByVideoId(String videoId);
	/**
	 * 跟新cc播放地址
	 * @param video
	 */
	public void updateCCVideoUrlCC(Video video);

}
