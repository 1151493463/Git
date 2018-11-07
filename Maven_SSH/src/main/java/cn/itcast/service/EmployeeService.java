package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Employee;
import cn.itcast.utils.PageBean;

public interface EmployeeService {

	Employee getEmployee(DetachedCriteria criteria);

	PageBean<Employee> getPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria);

	void add(Employee employee);

	void delete(Integer employeeId);

	Employee getById(Integer employeeId);

	void update(Employee employee);

}
