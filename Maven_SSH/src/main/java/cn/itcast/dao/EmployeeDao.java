package cn.itcast.dao;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Employee;

public interface EmployeeDao extends BaseDao<Employee>{

	Employee getEmployee(DetachedCriteria criteria);

}
