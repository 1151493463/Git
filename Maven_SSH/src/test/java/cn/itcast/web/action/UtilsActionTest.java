package cn.itcast.web.action;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.domain.Region;
import cn.itcast.service.UtilsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UtilsActionTest {
	@Resource(name="utilsService")
	private UtilsService us;
	@Test
	public void test() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Region.class);
		double id=29;
		criteria.add(Restrictions.eq("parentId", id));
		List<Region> area = us.getArea(criteria);
		for(Region r : area) {
			System.out.println(r.getRegionName());
		}
	}

}
