<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/htmleaf-demo.css">
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jqPaginator.js"></script>
</head>
<body>
<h2>欢迎${employee.employeeName}使用</h2>
	<form action="${pageContext.request.contextPath }/department/departmentAction1_page.action" method="get" id="form1">
	<span>部门名称：<input type="text" value='<s:property value="keyWord" />' name="keyWord">&nbsp;&nbsp;<input type="submit" value="搜索"></span>
		<table>
			<tr>
				<th>部门编号</th>
				<th>部门名称</th>
				<th>部门描述</th>
			</tr>
			<s:iterator value="list" var="d">
				<tr>
					<td><s:property value="#d.departmentId"/></td>
					<td><s:property value="#d.departmentName"/></td>
					<td><s:property value="#d.departmentDesc"/></td>
				</tr>
			</s:iterator>
		</table>
	</form>
			<ul class="pagination" id="pagination1"></ul>
	</div>
	<script type="text/javascript">
	var if_firstime = true;
	$('#pagination1').jqPaginator({
		totalPages: <s:property value="totalPage" />,
	    visiblePages: 3,
	    currentPage: <s:property value="currentPage" />,
	    first: '<li class="first"><a href="javascript:void(0);">首页</a></li>',
	    prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
	    next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
	    last: '<li class="last"><a href="javascript:void(0);">页末</a></li>',
	    page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
	    onPageChange: function (num) {
	    	 if(if_firstime){  
	                if_firstime = false;  
	            }else if(!if_firstime){  
	            	var url="${pageContext.request.contextPath}/department/departmentAction1_page.action?currentPage="+num+"&keyWord="+'<s:property value="keyWord" />';
	            	location = url;
	            }  
	    }
	});
	</script>
	
</body>
</html>