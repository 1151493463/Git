$(function(){
	//每次访问login.html时都要从key=loginName的cookie的value取值并放在文本框中
	$("#inputName").val(getCookie("loginName"));
	$("form").submit(function(){
		return login();
	});
});
function findBackPassword(){
	var loginName = $("#inputEmail").val();
	var activeCode = $("#active-code").val();
	var password = $("#new-password").val();
	if(loginName==""){
		loginName="undefined";
	}
	if(activeCode==""){
		activeCode="undefined";
	}
	if(password==""){
		password="undefined";
	}
	$.ajax({
		url:basePath+"user/password",
		type:"post",
		data:{"loginName":loginName,"activeCode":activeCode,"password":password},
		dataType:"json",
		success:function(data){
			if(data.success){
				alert("修改成功！");
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
	
}
function sendVerifyCode(){
	$('#myButton').text("发送中...");
	var loginName = $("#inputEmail").val();
	$.ajax({
		url:basePath+"user/active",
		type:"post",
		data:{"loginName":loginName},
		dataType:"json",
		success:function(data){
			if(data.success){
				$('#myButton').text("成功！");
			}else{
				$('#myButton').text("失败！");
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
}

function login(){
	//用户名和密码
	var loginName=$("#inputName").val();
	var password=$("#inputPassword").val();	
	var verifyCode = $("#code").val();
	var remember=$("form input[type=checkbox]").get(0).checked;
	//发送异步请求
	$.ajax({
		url:basePath+"user/login/"+loginName+"/"+password+"/"+verifyCode,
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.success){
				if(remember){
					//记住密码打上对勾了，添加cookie
					addCookie("loginName",loginName,5);
				}
				window.location.href="index.jsp"
				//window.location.href="index.html"
					
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！")
		}
	});
	
	return false;
}