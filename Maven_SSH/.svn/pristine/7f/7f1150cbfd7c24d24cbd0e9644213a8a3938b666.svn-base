package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.DepartmentDao;
import cn.itcast.dao.EmployeeDao;
import cn.itcast.domain.Department;
import cn.itcast.service.DepartmentService;
import cn.itcast.utils.PageBean;
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public PageBean<Department> getPage(DetachedCriteria criteria, Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		//获得总记录数
		Integer totalCount =  (int) departmentDao.getTotalCount(criteria);
		//查询start
		int start = (currentPage-1)*pageSize;
		//获得部门集合
		List<Department> list = departmentDao.getList(criteria,start,pageSize);
		//封装PageBean
		PageBean<Department> page = new PageBean<Department>(currentPage, pageSize, totalCount, list);
		
		return page;
	}

	@Override
	public void add(Department department) {
		// TODO Auto-generated method stub
		departmentDao.save(department);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Department department = departmentDao.getById(id);
		departmentDao.delete(department);
	}

	@Override
	public Department getById(Integer id) {
		// TODO Auto-generated method stub
		
		Department department = departmentDao.getById(id);
		
		return department;
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		
		departmentDao.update(department);
		
	}

	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentDao.getAll();
	}

	

}
