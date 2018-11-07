package service;

import java.util.Map;

import dto.Execution;
import dto.Page;
import entity.Video;

public interface VideoService {
	/**
	 * 根据条件分页查询视频信息
	 * @param currentPage
	 * @param keyWords
	 * @return
	 */
	Execution<Page<Video>> getVideoByPage(int currentPage, Map<String, Object> keyWords);
	/**
	 * 根据id查询视频
	 * @param videoId
	 * @return
	 */
	Execution<Video> getVideoById(String videoId);
	/**
	 * 通过视频
	 * @param videoId
	 * @return
	 */
	Execution<Video> passVideo(String videoId,String status);
	
}
