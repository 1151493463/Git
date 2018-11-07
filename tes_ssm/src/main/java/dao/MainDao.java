package dao;

import java.util.List;

import vo.UserCartogram;
import vo.VideoCartogram;

public interface MainDao {
	/**
	 * 查询用户统计图数据
	 * @return
	 */
	List<UserCartogram> userCartogram();
	/**
	 * 用户统计图
	 * @return
	 */
	List<VideoCartogram> videoCartogram();

}
