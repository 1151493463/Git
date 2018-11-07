package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.EmployeeDao;
import cn.itcast.domain.Employee;
import cn.itcast.service.EmployeeService;
import cn.itcast.utils.PageBean;
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao;
	
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * 登录
	 */
	@Override
	public Employee getEmployee(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployee(criteria);
	}

	@Override
	public PageBean<Employee> getPage(Integer currentPage, Integer pageSize, DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		//获取总记录数
		int totalCount = (int) employeeDao.getTotalCount(criteria);
		//查询start
		int start = (currentPage-1)*pageSize;
		//获得员工集合
		List<Employee> list = employeeDao.getList(criteria,start,pageSize);
		//封装PageBean
		PageBean<Employee> page = new PageBean<>(currentPage, pageSize, totalCount, list);
		
		return page;
	}

	@Override
	public void add(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.save(employee);		
	}

	@Override
	public void delete(Integer employeeId) {
		// TODO Auto-generated method stub
		Employee e = employeeDao.getById(employeeId);
		employeeDao.delete(e);
	}

	@Override
	public Employee getById(Integer employeeId) {
		// TODO Auto-generated method stub
		return employeeDao.getById(employeeId);
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.update(employee);
	}

}
