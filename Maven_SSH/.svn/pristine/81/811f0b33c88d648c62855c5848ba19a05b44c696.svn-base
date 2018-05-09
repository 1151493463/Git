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
	<form action="${pageContext.request.contextPath }/employee/employeeAction_update.action" 
	method="post" enctype="multipart/form-data">
	ID:<s:property value="employee.employeeId"/><br>
	<input type="hidden" name="employeeId" value='<s:property value="employee.employeeId"/>'>
	员工姓名：<input type="text" name="employeeName" value="<s:property value="employee.employeeName"/>"><br>
	<input type="hidden" name="employeePassword" value='<s:property value="employee.employeePassword"/>'><br>
	<s:if test="employee.employeeImg == null">
	员工照片：<input type="file" name="photo"><br>
	</s:if>
	<s:if test="employee.employeeImg != null">
		<input type="hidden" name="employeeImg" value='<s:property value="employee.employeeImg"/>'>
		员工照片：<img width="300" height="150" alt="" src='${pageContext.request.contextPath }/upload/<s:property value="employee.employeeImg"/>'><br>
	</s:if>
	<span id="area">
	家庭住址：<select id="pro">
    	<option value="">请选择省</option>
	</select>
	<select id="city">
   	 	<option value="">请选择市</option>
	</select>
	<select id="dis">
    	<option value="">请选择区</option>
	</select>
	</span>
	<br>
	<input type="hidden" id="address" name="employeeAddress">
	出生日期：<input type="date" name="employeeBrithday" value='<s:date name="employee.employeeBrithday" format="yyyy-MM-dd"/>'><br>
	入职日期：<input type="date" name="joinDate" value='<s:date name="employee.joinDate" format="yyyy-MM-dd"/>'><br>
	
	<s:select label="部门" name="department.departmentId" value="%{employee.department.departmentId}" list="list" listKey="%{departmentId}" listValue="%{departmentName}" />
		<input onclick="getValue()" type="submit" value="提交">
	</form>
<s:debug></s:debug>
</body>
</html>