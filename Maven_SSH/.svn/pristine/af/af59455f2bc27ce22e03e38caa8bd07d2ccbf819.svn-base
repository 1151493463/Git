package cn.itcast.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

private Class<T> clazz;//用于接收运行时的泛型类型
	
	BaseDaoImpl(){
		// 通过反射得到T的真实类型  
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();  
        //this代表当前new的实例  
        //getGenericSuperclass()指的是获取泛型的父类，而如果用getSuperclass是获取父类  
        //(类型通常用class表示，jdk1.5之后增加了泛型，以前的class就不能表示带泛型信息的那个类了，  
        //所以创建了一个新的类型ParameterizedType pt)  
        //ParameterizedType获取java泛型参数类型,来表示类型参数的一个类型  
        this.clazz = (Class) pt.getActualTypeArguments()[0];  
        //pt有一个方法：getActualTypeArguments()[0]，获取真实的类型参数，返回的是一个数组零，
	}
	
	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T getById(Integer id) {
		// TODO Auto-generated method stub
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> getList(DetachedCriteria criteria, Integer start, Integer pageSize) {
		// TODO Auto-generated method stub
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria, start, pageSize);
	}

	@Override
	public long getTotalCount(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		long totalCount = (long) criteria.setProjection(Projections.rowCount()).
				getExecutableCriteria(this.getHibernateTemplate().getSessionFactory().
						getCurrentSession()).uniqueResult();
		criteria.setProjection(null);
		return totalCount;
	}

	@Override
	public List<T> getListByCriteria(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public T getByCriteria(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
