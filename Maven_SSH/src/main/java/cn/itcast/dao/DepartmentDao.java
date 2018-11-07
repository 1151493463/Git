package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Department;

public interface DepartmentDao extends BaseDao<Department>{

	List<Department> getAll();


}
