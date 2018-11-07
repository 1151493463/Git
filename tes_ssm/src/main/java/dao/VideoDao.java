package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import entity.Video;

public interface VideoDao {
	/**
	 * 获取总记录数
	 * @param keyWords
	 * @return
	 */
	int getTotalCount(Map<String, Object> keyWords);
	/**
	 * 按添加查询视频
	 * @param offset
	 * @param pageSize
	 * @param keyWords
	 * @return
	 */
	List<Video> queryVideoList(@Param("offset")int offset, @Param("pageSize")int pageSize, @Param("keyWords")Map<String, Object> keyWords);
	/**
	 * 根据添加查询视频
	 * @param video
	 */
	Video queryVideoByCondition(Video videoCondition);
	/**
	 * 更新课程
	 * @param video
	 * @return
	 */
	int updateVideo(Video video);

}
