package shiro;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

public class SystemLogoutFilter extends LogoutFilter{

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// 此方法中执行退出系统前需要清空的数据
		Subject subject = this.getSubject(request, response);
		String redirectUrl = this.getRedirectUrl(request, response, subject);
		ServletContext context = request.getServletContext();
		subject.logout();
		this.issueRedirect(request, response, redirectUrl);
		
		return false;
	}
	
}
