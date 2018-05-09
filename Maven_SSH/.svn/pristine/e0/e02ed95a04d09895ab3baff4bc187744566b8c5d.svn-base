package cn.itcast.web.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Department;
import cn.itcast.domain.Employee;
import cn.itcast.service.DepartmentService;
import cn.itcast.service.EmployeeService;
import cn.itcast.utils.MD5Utils;
import cn.itcast.utils.PageBean;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee> {
	
	private static final long serialVersionUID = 1L;
	private Employee employee = new Employee();
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	private Integer currentPage = 1;
	private Integer pageSize = 5;
	private String keyWord = null;
	private String id;
	private File photo;
	
	

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Employee getEmployee() {
		return employee;
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

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return employee;
	}
	/**
	 * 登录
	 */
	public String login() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		if(StringUtils.isNoneBlank(employee.getEmployeeName()) &&
				StringUtils.isNoneBlank(employee.getEmployeePassword())) {
			
			criteria.add(Restrictions.eq("employeeName", employee.getEmployeeName())).
			add(Restrictions.eq("employeePassword", MD5Utils.md5(employee.getEmployeePassword())));
			
			Employee existEmployee = employeeService.getEmployee(criteria);
			if(existEmployee != null) {
				ActionContext.getContext().getSession().put("employee", existEmployee);
				return "toIndex";
			}
			else {
				addActionError("用户名或密码错误！");
				return "toLogin";
			}
		}
		else {
			addActionError("用户名或密码为空！");
			return "toLogin";
		}
	}
	/**
	 * page
	 * @throws UnsupportedEncodingException 
	 */
	public String page() throws UnsupportedEncodingException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		if(StringUtils.isNoneBlank(keyWord)) {
			criteria.add(Restrictions.like("employeeName", "%"+new String(keyWord.getBytes("ISO8859-1"),"UTF-8")+"%"));
		}
		
		PageBean<Employee> page = employeeService.getPage(currentPage,pageSize,criteria);
		page.setKeyWord(StringUtils.isNoneBlank(keyWord)?new String(keyWord.getBytes("ISO8859-1"),"UTF-8"):"");
		page.setPageSize(pageSize);
		ActionContext.getContext().getValueStack().push(page);
		
		return "list";
	}
	/**
	 * add
	 */
	public String add() {
		if(StringUtils.isNoneBlank(employee.getEmployeeName()) &&
				StringUtils.isNoneBlank(employee.getEmployeePassword())) {
			employee.setEmployeePassword(MD5Utils.md5(employee.getEmployeePassword()));
			employeeService.add(employee);
			return "toList";
		}
		else {
			addActionError("员工信息不能为空！");
			return "add";
		}
	}
	/**
	 * addUI
	 * 
	 */
	public String addUI() {
		List<Department> department = departmentService.getAll();
		ActionContext.getContext().getValueStack().set("list", department);
		return "addUI";
	}
	/**
	 * delete
	 */
	public String delete() {
		
		System.out.println("----------------------------------------------"+id);
		if(StringUtils.isNoneBlank(id)) {
			employeeService.delete(Integer.parseInt(id));
		}
		
		return "toList";
	}
	
	/**
	 * getById
	 */
	public String getById() {
		//String.valueOf()
		if(StringUtils.isNoneBlank(id)) {
			this.employee = employeeService.getById(Integer.parseInt(id));
			List<Department> department = departmentService.getAll();
			ActionContext.getContext().getValueStack().set("list", department);
		}
		
		return "toUpdate";
	}
	/**
	 * update
	 */
	public String update() {
		if(photo!=null) {
			String path=ServletActionContext.getServletContext().getRealPath("/");
			System.out.println(path);
			photo.renameTo(new File(path+"/upload/"+employee.getEmployeeId()+".jpg"));
			employee.setEmployeeImg(employee.getEmployeeId()+".jpg");
		}
		
		if(StringUtils.isNoneBlank(String.valueOf(employee.getEmployeeId())) &&
				StringUtils.isNoneBlank(employee.getEmployeeName()) &&
				StringUtils.isNoneBlank(employee.getEmployeePassword())) {
				employeeService.update(employee);
			return "toList";
		}
		else {
			addActionError("信息不能为空");
			return "toUpdate";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
