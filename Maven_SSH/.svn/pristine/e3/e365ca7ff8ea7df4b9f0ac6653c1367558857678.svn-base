package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.EmployeeDao;
import cn.itcast.domain.Employee;

public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

	@Override
	public Employee getEmployee(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		List<Employee> list = (List<Employee>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size()>0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
}
