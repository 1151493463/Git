package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	//save
	void save(T t);
	//delete
	void delete(T t);
	//update
	void update(T t);
	//getById
	T getById(Integer id);
	//get page
	List<T> getList(DetachedCriteria criteria,Integer start,Integer pageSize);
	//getTotalCount
	long getTotalCount(DetachedCriteria criteria);
	//criteria
	List<T> getListByCriteria(DetachedCriteria criteria);
	//criteria
	T getByCriteria(DetachedCriteria criteria);
}

