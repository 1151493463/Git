package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.DepartmentDao;
import cn.itcast.domain.Department;

public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {
	
	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().loadAll(Department.class);
	}

}
