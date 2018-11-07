<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/employee/employeeAction_add.action" method="post">
		
	员工姓名：<input type="text" name="employeeName"><br>
	密码：<input type="text" name="employeePassword"><br>
	员工照片：<input type="text" name="employeeImg"><br>
	出生日期：<input type="date" name="employeeBrithday"><br>
	入职日期：<input type="date" name="joinDate"><br>
	
	<s:select label="部门" name="department.departmentId" list="list" listKey="%{departmentId}" listValue="%{departmentName}" /><br>		
		
		<input type="submit" value="提交">
	</form>
</body>
</html>