package dao;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import vo.UserCartogram;
import vo.VideoCartogram;

public class MainDaoTest extends BaseTest{
	@Autowired
	private MainDao mainDao;
	@Test
	public void test() {
		try {
			List<UserCartogram> userCartogram = mainDao.userCartogram();
			System.out.println(userCartogram);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Test
	public void test1() {
		try {
			List<VideoCartogram> videoCartogram = mainDao.videoCartogram();
			System.out.println(videoCartogram);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
