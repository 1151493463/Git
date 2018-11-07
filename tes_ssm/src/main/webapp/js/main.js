//@ sourceURL=main.js

$(function(){
	// 注册面板关闭事件
	$(".panel .close").click(function(){
		close_panel(this);
	});
	// 注册面板重置事件
	$(".glyphicon-refresh").click(function(){
		reset_panel();
	});
	// 画用户统计饼状图
	draw_user();
	// 画视频统计环行图
	draw_video();
	//给登出超链接添加事件
	$("#logout").click(function(){
		logout();
	});
	todayDynamic();
});
function todayDynamic(){
	$.ajax({
		url:basePath+"main/TodayDynamicServlet.do",
		type:"get",
		dataType:"json",
		success:function(data){
			var item = data.data;
			$(".nav-pills span")[0].innerText=item.onlineCount;
			$(".nav-pills span")[1].innerText=item.registerNum;
			$(".nav-pills span")[2].innerText=item.uploadNum;
			$(".nav-pills span")[3].innerText=item.collectionNum;
			$(".nav-pills span")[4].innerText=item.purchanseNum;
			$(".nav-pills span")[5].innerText=item.latestActivity;
			$(".nav-pills span")[6].innerText=item.latestComment;
		},
		error:function(){
		}
	})
}


//登出方法
/*function logout(){
	$.ajax({
		url:basePath+"user",
		type:"delete",
		dataType:"json",
		success:function(data){
			if(data.success){
				window.location.href="login.html";
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
}*/

// 关闭面板
function close_panel(btn) {
	$(btn).parent().parent().parent().fadeOut(200);
}

// 重置面板
function reset_panel() {
	$(".panel").parent().fadeIn(200);
}
function randomRgbColor() { //随机生成RGB颜色
	 var r = Math.floor(Math.random() * 256); //随机生成256以内r值
	 var g = Math.floor(Math.random() * 256); //随机生成256以内g值
	 var b = Math.floor(Math.random() * 256); //随机生成256以内b值
	 return 'rgb('+r+','+g+','+b+')'; //返回rgb(r,g,b)格式颜色
	}

function draw_user() {
	$.ajax({
		url:basePath+"main/userCartogram",
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.success){
				var tmp = '[';
				$.each(data.userCartogram,function(index,item){
					tmp+='{value:'+item.userNum+','
		    			+'label: "'+item.roleName+'"},';
				});
				tmp = tmp.substring(0,tmp.length-1)+']';
		    	var ctx = document.getElementById("chart-user").getContext("2d");
		    	window.myDoughnut = new Chart(ctx).Pie(eval('(' + tmp + ')'), {responsive : true});
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("加载用户统计图失败！");
		}
	})
	
}

function draw_video() {
	$.ajax({
		url:basePath+"main/videoCartogram",
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.success){
				var tmp='[';
				$.each(data.videoCartogram,function(index,item){
					tmp+='{value:'+item.videoNum+','
		    			+'label: "'+item.courseName+'"},';
				});
				tmp = tmp.substring(0,tmp.length-1)+']';
				var ctx = document.getElementById("chart-video").getContext("2d");
				window.myDoughnut = new Chart(ctx).Doughnut(eval('(' + tmp + ')'), {responsive : true});
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败");
		}
	})
	
	/*var data = [
		{
			value: 100,
			color:"#F7464A",
			highlight: "#FF5A5E",
			label: "Java"
		},
		{
			value: 300,
			color: "#46BFBD",
			highlight: "#5AD3D1",
			label: "IOS"
		},
		{
			value: 250,
			color: "#FDB45C",
			highlight: "#FFC870",
			label: "Android"
		},
		{
			value: 200,
			color: "#949FB1",
			highlight: "#A8B3C5",
			label: ".NET"
		},
		{
			value: 150,
			color: "#4D5360",
			highlight: "#616774",
			label: "UID"
		},
		{
			value: 100,
			color: "#4D5360",
			highlight: "#616774",
			label: "网络营销"
		},
		{
			value: 80,
			color: "#4D5360",
			highlight: "#616774",
			label: "C++"
		},
		{
			value: 70,
			color: "#4D5360",
			highlight: "#616774",
			label: "软件测试"
		},
		{
			value: 60,
			color: "#4D5360",
			highlight: "#616774",
			label: "php"
		},
		{
			value: 50,
			color: "#4D5360",
			highlight: "#616774",
			label: "大数据"
		},
		{
			value: 40,
			color: "#4D5360",
			highlight: "#616774",
			label: "WEB前端"
		},
		{
			value: 30,
			color: "#4D5360",
			highlight: "#616774",
			label: "Unity3D"
		},
		{
			value: 20,
			color: "#4D5360",
			highlight: "#616774",
			label: "智能硬件"
		}

	];*/

}