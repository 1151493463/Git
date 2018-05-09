package cn.itcast.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.DepartmentDao;
import cn.itcast.domain.Department;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class DepartmentDaoImplTest {
	@Resource(name="departmentDao")
	private DepartmentDao dao;
	@Test
	public void test() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
	long totalCount = dao.getTotalCount(criteria);
	int count = (int) totalCount;
	System.out.println(count);
	}
	@Test
	public void test2() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		//criteria.add(Restrictions.like("departmentName", "%事%"));
		List<Department> list = dao.getList(criteria, 0, 8);
		System.out.println(list);
		for(Department d : list) {
			System.out.println(d.getDepartmentName());
		}
	}
	@Test
	public void test3() {
		double i = (double)5/(double)3;
		double ceil = Math.ceil(i);
		System.out.println(ceil);
	}

}
