package cn.itcast.web.action;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.domain.Department;
import cn.itcast.service.DepartmentService;
import cn.itcast.utils.PageBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class DepartmentActionTest {
	@Resource(name="departmentService")
	private DepartmentService ds;
	@Test
	public void test() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		PageBean<Department> page = ds.getPage(criteria, 0, 8);
		System.out.println(page.getList());
	}
	@Test
	public void test2() {
		Department d = ds.getById(1);
		System.out.println(d.getDepartmentName());
	}
}
