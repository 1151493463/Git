<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/area.jsp"></script>
</head>
<body>
<span>	<h2>欢迎${employee.employeeName}使用</h2> &nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath }/employee/employeeAction_addUI.action">添加</a></span>
	<form action="${pageContext.request.contextPath }/employee/employeeAction_page.action" method="get">
	<span>部门名称：<input type="text" value='<s:property value="keyWord" />' name="keyWord">&nbsp;&nbsp;<input type="submit" value="搜索"></span>
		<table>
			<tr>
				<th>员工ID</th>
				<th>员工姓名</th>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;出生日期</th>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;入职日期</th>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;所属部门</th>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;【修改】</th>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;【删除】</th>
			</tr>
			<s:iterator value="list" var="d">
				<tr>
					<td><s:property value="#d.employeeId"/></td>
					<td><s:property value="#d.employeeName"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;<s:date name="%{#d.employeeBrithday}" format="yyyy-MM-dd"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;<s:date name="%{#d.joinDate}" format="yyyy-MM-dd"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#d.department.departmentName"/></td>
					<td><a href="${pageContext.request.contextPath }/employee/employeeAction_getById.action?id=<s:property value="#d.employeeId"/>">&nbsp;&nbsp;&nbsp;&nbsp; 【修改】</a></td>
					<td><a href="${pageContext.request.contextPath }/employee/employeeAction_delete.action?id=<s:property value="#d.employeeId"/>">&nbsp;&nbsp;&nbsp;&nbsp; 【删除】</a></td>
				</tr>
			</s:iterator>
		</table>
		<span>第<s:property value="currentPage" />/<s:property value="totalPage" /></span>
		<span>总记录数:<s:property value="totalCount" />&nbsp;&nbsp;&nbsp;&nbsp;每页显示<select onchange="changePageSize()" id="pageSize"></select>条&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span>
		<s:if test="currentPage != 1">
		<a href="${pageContext.request.contextPath }/employee/employeeAction_page.action?currentPage=1&keyWord=<s:property value="keyWord" />">首页</a>
		<a href="${pageContext.request.contextPath }/employee/employeeAction_page.action?currentPage=<s:property value="currentPage-1" />&keyWord=<s:property value="keyWord" />">上一页</a>
		</s:if>
		<s:if test="currentPage != totalPage">
		<a href="${pageContext.request.contextPath }/employee/employeeAction_page.action?currentPage=<s:property value="currentPage+1" />&keyWord=<s:property value="keyWord" />">下一页</a>
		<a href="${pageContext.request.contextPath }/employee/employeeAction_page.action?currentPage=<s:property value="totalPage" />&keyWord=<s:property value="keyWord" />">页尾</a>
		</s:if>
		</span>
	</form>
	<script type="text/javascript">
	$(document).ready(function(){

		for(var i= 5;i<=30;i=i+5){
			if(i=='<s:property value="pageSize"/>'){
				$option="<option selected>"+i+"</option>";
			}
			else{
				$option="<option>"+i+"</option>";
			}
			$("#pageSize").append($option);
		}
				});
	function changePageSize(){
		$pageSize="";
			$("#pageSize").find("option").each(function(){
				if($(this).is(":checked")==true){
					$pageSize=$(this).text();
				}
			})
			window.location.href="${pageContext.request.contextPath }/employee/employeeAction_page.action?pageSize="+$pageSize;
	}
	</script>
	<s:debug></s:debug>
</body>
</html>