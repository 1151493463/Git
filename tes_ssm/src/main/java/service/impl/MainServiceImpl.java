package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MainDao;
import dto.Execution;
import enums.StateEnum;
import service.MainService;
import vo.UserCartogram;
import vo.VideoCartogram;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	private MainDao mainDao;
	
	@Override
	public Execution<List<UserCartogram>> userCartogram() {
		// TODO Auto-generated method stub
		Execution<List<UserCartogram>> me = null;
		try {
			List<UserCartogram> userCartogram = mainDao.userCartogram();
			me = new Execution<List<UserCartogram>>(StateEnum.SUCCESS,userCartogram);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			me = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		return me;
	}

	@Override
	public Execution<List<VideoCartogram>> videoCartogram() {
		// TODO Auto-generated method stub
		Execution<List<VideoCartogram>> me = null;
		try {
			List<VideoCartogram> videoCartogram = mainDao.videoCartogram();
			me = new Execution<>(StateEnum.SUCCESS,videoCartogram);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			me = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		return me;
	}
	
	

}
