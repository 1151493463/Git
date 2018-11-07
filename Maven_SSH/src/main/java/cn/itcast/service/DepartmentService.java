package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Department;
import cn.itcast.utils.PageBean;

public interface DepartmentService {

	PageBean<Department> getPage(DetachedCriteria criteria, Integer currentPage, Integer pageSize);

	void add(Department department);

	void delete(Integer id);

	Department getById(Integer id);

	void update(Department department);

	List<Department> getAll();

	


}
