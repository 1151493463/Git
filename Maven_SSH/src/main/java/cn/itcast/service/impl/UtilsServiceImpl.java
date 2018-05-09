package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.UtilsDao;
import cn.itcast.domain.Region;
import cn.itcast.service.UtilsService;

public class UtilsServiceImpl implements UtilsService {
	
	private UtilsDao utilsDao;
	
	public void setUtilsDao(UtilsDao utilsDao) {
		this.utilsDao = utilsDao;
	}

	@Override
	public List<Region> getArea(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return utilsDao.getListByCriteria(criteria);
	}
}
