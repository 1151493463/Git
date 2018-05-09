package cn.itcast.web.action;

import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Department;
import cn.itcast.service.DepartmentService;
import cn.itcast.utils.PageBean;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{

	private static final long serialVersionUID = 8868363131273440926L;
	private Department department = new Department();
	private DepartmentService departmentService;
	
	private Integer currentPage = 1;
	private Integer pageSize = 5;
	private String keyWord = null;
	private String id = null;
	
	public Department getDepartment() {
		return department;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	@Override
	public Department getModel() {
		// TODO Auto-generated method stub
		return department;
	}
	
	/**
	 * 部门分页
	 * @throws UnsupportedEncodingException 
	 */
	public String page() throws UnsupportedEncodingException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		if(StringUtils.isNoneBlank(keyWord)) {
			//String method = ServletActionContext.getRequest().getMethod();
			criteria.add(Restrictions.like("departmentName", "%"+new String(keyWord.getBytes("ISO8859-1"),"UTF-8")+"%"));
		}
		PageBean<Department> page = departmentService.getPage(criteria,currentPage,pageSize);
		page.setKeyWord(StringUtils.isNoneBlank(keyWord)?new String(keyWord.getBytes("ISO8859-1"),"UTF-8"):"");
		ActionContext.getContext().getValueStack().push(page);
		
		return "list";
	}
	/**
	 * 添加部门
	 * @return 
	 */
	public String add() {
		if(StringUtils.isNoneBlank(department.getDepartmentName()) && 
				StringUtils.isNoneBlank(department.getDepartmentName())) {
			departmentService.add(department);
			
			return "toList";
		}
		else {
			addActionError("信息不能为空！");
			return "add";
		}
	}
	/**
	 * 删除部门
	 */
	public String delete() {
		
		if(StringUtils.isNoneBlank(id)) {
			departmentService.delete(Integer.parseInt(id));
		}
		
		return "toList";
	}
	/**
	 * 獲取部門by id
	 */
	public String getById() {
		
		if(StringUtils.isNoneBlank(id)) {
			this.department = departmentService.getById(Integer.parseInt(id));
		}
		
		return "updateMsg";
	}
	/**
	 * 更新部門
	 */
	public String update() {
		
		departmentService.update(department);
			
		return "toList";
		}
	/**
	 * test
	 */
	public String test() {
		
		ActionContext.getContext().getValueStack().set("demo", "aaaa");
		
		return SUCCESS;
	}
}
