package service;

import java.util.List;

import dto.Execution;
import vo.UserCartogram;
import vo.VideoCartogram;

public interface MainService {
	/**
	 * 用户统计图
	 * @return
	 */
	Execution<List<UserCartogram>> userCartogram();
	/**
	 * 视频统计图
	 * @return
	 */
	Execution<List<VideoCartogram>> videoCartogram();

}
