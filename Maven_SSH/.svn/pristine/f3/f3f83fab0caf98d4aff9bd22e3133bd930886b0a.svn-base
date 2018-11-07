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
</head>
<body>
<select id="pro">
    <option value="">请选择省</option>
</select>
<select id="city">
    <option value="">请选择市</option>
</select>
<select id="dis">
    <option value="">请选择区</option>
</select>
       <script>
        $(function () {
            //获取省
            pro=$('#pro')
            $.get('${pageContext.request.contextPath}/utils/utilsAction_area.action',function (dic) {
            	alert(dic)
                $.each(dic,function (index,item) {
                    pro.append('<option value="'+item.regionId+'">'+item.regionName+'</option>')
                })
            })
           $('#pro').change(function () {
                //area140000/
                $.get('${pageContext.request.contextPath}/utils/utilsAction_area.action?regionId='+$(this).val(),function (list) {
                    city=$('#city');
                    city.empty().append('<option value="0">请选择市</option>')
                    $('#dis').empty().append('<option value="0">请选择区县</option>')
                    //{data:[{id:1,title:北京},{id:2,title:天津},...]}
                    $.each(list,function (index, item) {
                        //{id:1,title:北京}
                        city.append('<option value="'+item.regionId+'">'+item.regionName+'</option>');
                    });
                });
            });
             //查询区县的信息
            $('#city').change(function () {
                $.get('${pageContext.request.contextPath}/utils/utilsAction_area.action?regionId='+$(this).val(),function (list) {
                    dis=$('#dis').empty().append('<option value="0">请选择区县</option>');
                    $.each(list,function (index, item) {
                       dis.append('<option value="'+item.regionId+'">'+item.regionName+'</option>');
                    });
                });
            });
            })
    </script>
</body>
</html>