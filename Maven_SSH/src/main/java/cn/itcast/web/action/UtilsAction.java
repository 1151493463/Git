package cn.itcast.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.domain.Region;
import cn.itcast.service.UtilsService;
import net.sf.json.JSONArray;

public class UtilsAction extends ActionSupport {

	private static final long serialVersionUID = 1562215890828703443L;
	
	private UtilsService utilsService;
	
	private String regionId = "1";
	
	public void setUtilsService(UtilsService utilsService) {
		this.utilsService = utilsService;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	public String area() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Region.class);
			criteria.add(Restrictions.eq("parentId", Double.parseDouble(regionId)));
			List<Region> list = utilsService.getArea(criteria);
			String json = JSONArray.fromObject(list).toString();
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			try {
				ServletActionContext.getResponse().getWriter().write(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addActionError("内部错误，请联系管理员！");
			}
			return null;
		}
		
	

}
