package dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import entity.User;
import entity.Video;

public class VideoDaoTest extends BaseTest{
	@Autowired
	private VideoDao videoDao;
	@Test
	public void test() {
		Map<String, Object> keyWords = new HashMap<String,Object>();
		//keyWords.put("courseName", "java");
		//keyWords.put("keyWord", "管理");
		int totalCount = videoDao.getTotalCount(keyWords);
		System.out.println(keyWords);
	}
	@Test
	public void test1() {
		Map<String, Object> keyWords = new HashMap<String,Object>();
		keyWords.put("courseName", "java");
		keyWords.put("keyWord", "管理");
		List<Video> queryVideoList = videoDao.queryVideoList(0, 40, keyWords);
		System.out.println(queryVideoList);
	}
	
	@Test
	public void test2() {
	Video video = new Video();
	video.setId("04ad0719-2b0f-478c-9b04-11d0b35144c6");
	Video queryVideoByCondition = videoDao.queryVideoByCondition(video);
	System.out.println(queryVideoByCondition);
	}

}
