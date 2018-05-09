package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Region;

public interface UtilsService {

	List<Region> getArea(DetachedCriteria criteria);

}
