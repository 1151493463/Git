//@ sourceURL=comment.js
$(function(){
	loadCourseList();
	//loadSensitiveWord();
	selected=true;
	pushComment(1);
	totalPage = 1000;
	id='';
	pushNum = 0;
	currentComments='';
	
	$("#refresh_btn").click(function(){
		pushNum+=1;
		if(totalPage<pushNum){
			alert("没有数据了！");
			return;
		}
		pushComment(pushNum);
	});
	
});

function loadSensitiveWord(){
	$.ajax({
		url:basePath+"comment/word",
		type:"get",
		async:false,
		dataType:"json",
		success:function(data){
			if(data.success){
				console.log("加载成功");
				$("#sensitiveWord").val(data.word);
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
}

function save(){
	var word=$("#sensitiveWord").val();
	$.ajax({
		url:basePath+"comment/"+word,
		type:"put",
		dataType:"json",
		success:function(data){
			if(data.success){
				alert("保存成功！");
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败");
		}
	})
}

function batchVerify(advice){
	$.ajax({
		url:basePath+"comment/"+currentComments.substring(0,currentComments.length-1)+"/"+advice==1?"通过":"未通过",
		type:"put",
		dataType:"json",
		success:function(data){
			if(data.success){
				$("#all-agree").modal('hide');
				$("#all-reject").modal('hide');
				batchChangeStatus(advice);
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
		
	})
}
function batchChangeStatus(advice){
	var status='';
	var array = new Array();
	array = (currentComments.substring(0,currentComments.length-1)).split(",");
	if(advice==1){
		status="通过";
		for(var i=0;i<array.length;i++){
			$("#"+array[i]+" .btn-success").attr("disabled","true");
			$("#"+array[i]+" .btn-danger").removeAttr("disabled");
			$("#"+array[i]+" #status")[0].innerText=' 当前状态：'+status;
		}
	}else{
		status="不通过";
		for(var i=0;i<array.length;i++){
		$("#"+array[i]+" .btn-success").removeAttr("disabled");
		$("#"+array[i]+" .btn-danger").attr("disabled","true");
		$("#"+array[i]+" #status")[0].innerText=' 当前状态：'+status;
		}
	}
	
}
function managePage(){
	loadSensitiveWord();
	manageComment(1);
}
function manageVerify(advice){
	$.ajax({
		url:basePath+"comment/"+id+"/"+(advice==1?"通过":"未通过"),
		type:"put",
		dataType:"json",
		success:function(data){
			if(data.success){
				//alert(data.statusInfo);
				$("#manage-agree").modal('hide');
				$("#manage-reject").modal('hide');
				var status='';
				if(advice==1){
					status="通过";
					$("#"+id+" .btn-success").attr("disabled","true");
					$("#"+id+" .btn-danger").removeAttr("disabled");
				}else{
					status="不通过";
					$("#"+id+" .btn-success").removeAttr("disabled");
					$("#"+id+" .btn-danger").attr("disabled","true");
				}
				$("#"+id+" #status")[0].innerText=' 当前状态：'+status;
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
		
	})
}
function verify(advice){
	$.ajax({
		url:basePath+"comment/"+id+"/"+advice,
		type:"put",
		dataType:"json",
		success:function(data){
			if(data.success){
				//alert(data.statusInfo);
				$("#agree").modal('hide');
				$("#reject").modal('hide');
				$("#"+id).remove();
			}
		},
		error:function(){
			alert("请求失败！");
		}
		
	})
}
function setId(currentId){
	id=currentId;
}
function loadCourseList(){
	$.ajax({
		url:basePath+"course",
		type:"get",
		dataType:"json",
		success:function(data){
			var tmp = '';
			$.each(data.courseList,function(index,item){
				tmp+='<button type="button" onclick="courseMenu(this)" class="btn btn-default">'+item.name+'</button>';
			});
			$('.tab-pane .panel-body .course-list').append(tmp);
			$("#comment-category button").click(function(){
				pushComment(1);
			});
			$("#managePanel .course-list button").click(function(){
				$("#manage-content").html('');
				manageComment(1);
			});
		}
	});
}

function manageComment(currentPage){
	var courseList = "";
	var selectNum=0;
	$.each($("#managePanel .course-list button"),function(index){
		if($(this).hasClass("active")){
			courseList+=$(this).text()+",";
			selectNum++;
		}
	});
	if($("#managePanel .course-list button").size()==selectNum){
		courseList='';
	}
	var keyWord = $("#search").val();
	keyWord=keyWord==""?"undefined":keyWord;
	var isVerify='';
	var vefityNum=0;
	if($(".checkbox-inline input[type=checkbox]")[0].checked==true && 
	$(".checkbox-inline input[type=checkbox]")[1].checked==false){
		isVerify = $(".checkbox-inline input[type=checkbox]")[0].value;
	}else if($(".checkbox-inline input[type=checkbox]")[0].checked==false && 
	$(".checkbox-inline input[type=checkbox]")[1].checked==true){
		isVerify = $(".checkbox-inline input[type=checkbox]")[1].value;
	}
	isVerify=isVerify==''?"undefined":isVerify;
	var order='DESC';
	if($("#comment_time span").hasClass("glyphicon-chevron-up")) {
		order='ASC';
	} 
	var course = courseList.length==0?"undefined":courseList.substring(0,courseList.length-1);
	$.ajax({
		url:basePath+"comment/"+course+"/"+currentPage+"/"+keyWord+"/"+isVerify+"/"+order,
		type:"get",
		dataType:"json",
		success:function(data){
			var tmp='';
			$("#manage-content").html('');
			currentComments='';
			var sensitiveWord = $("#sensitiveWord").val().split("，");
			$.each(data.page.data,function(index,item){
				currentComments+=item.id+'，';
				/*var state = '';
				if(item.hasOwnProperty("isPass")){
					if(item.isPass=="是"){
						state='通过'
					}else{
						state='未通过';
					}
				}else{
					state = "未审核";
				}*/
				var content=item.content;
				for(i in sensitiveWord){
					content = content.replace(sensitiveWord[i], "<span style='color:red;'>"+sensitiveWord[i]+"</span>");
					/*console.log(sensitiveWord[i]);
					console.log(content);*/
				}
				tmp+='<div id='+item.id+' class="panel panel-default"><div class="text-center">'
			          +'<h3><a href="#">'+item.video.title+'</a></h3>'
			          +'<div><span>用户：<a href="#">'+item.user.nickName+'</a></span> • <span>'+(new Date(item.timeStamp).toLocaleDateString().replace("/","-").replace("/","-"))+'</span>'
						+'&nbsp;<span id="status"> 当前状态：'+item.isPass+'</span></div></div><div class="panel-body">'
			           +'<h4>'+content+'</h4>'
			            +'<div class="text-right">';
				
					if(item.isPass=="通过"){
						tmp+='<button onclick="setId(\''+item.id+'\')" data-toggle="modal" disabled="disabled" data-target="#manage-agree" type="button" class="btn btn-success">&nbsp;&nbsp;通&nbsp;&nbsp;过&nbsp;&nbsp;</button> '
				             +'<button onclick="setId(\''+item.id+'\')" type="button" data-toggle="modal" data-target="#manage-reject" class="btn btn-danger">&nbsp;&nbsp;拒&nbsp;&nbsp;绝&nbsp;&nbsp;</button></div></div></div>';
					}else if(item.isPass=="未通过"){
						tmp+='<button onclick="setId(\''+item.id+'\')" data-toggle="modal"  data-target="#manage-agree" type="button" class="btn btn-success">&nbsp;&nbsp;通&nbsp;&nbsp;过&nbsp;&nbsp;</button> '
				             +'<button onclick="setId(\''+item.id+'\')" type="button" disabled="disabled" data-toggle="modal" data-target="#manage-reject" class="btn btn-danger">&nbsp;&nbsp;拒&nbsp;&nbsp;绝&nbsp;&nbsp;</button></div></div></div>';
					}else{
						tmp+='<button onclick="setId(\''+item.id+'\')" data-toggle="modal" data-target="#manage-agree" type="button" class="btn btn-success">&nbsp;&nbsp;通&nbsp;&nbsp;过&nbsp;&nbsp;</button> '
			             +'<button onclick="setId(\''+item.id+'\')" type="button" data-toggle="modal" data-target="#manage-reject" class="btn btn-danger">&nbsp;&nbsp;拒&nbsp;&nbsp;绝&nbsp;&nbsp;</button></div></div></div>';
				}

			});
			$("#manage-content").append(tmp);
			paginator(data.page.totalPage,data.page.currentPage);
		},
		error:function(){
			alert("请求失败！");
		}
	})
	
}
function paginator(totalPage,currentPage){
	var options = {
            currentPage: currentPage,
            totalPages: totalPage,
            numberOfPages:5,
            onPageClicked: function(e,originalEvent,type,page){
            	manageComment(page);
            }
        }
        $('#example').bootstrapPaginator(options);
	
}

function pushComment(num){
	var courseList = "";
	var selectNum=0;
	$.each($("#comment-category button"),function(index){
		if($(this).hasClass("active")){
			courseList+=$(this).text()+",";
			selectNum++;
		}
	});
	if($("#comment-category button").size()==selectNum){
		courseList='';
	}
	var course = courseList.length==0?"undefined":courseList.substring(0,courseList.length-1);
	$.ajax({
		url:basePath+"comment/"+course+"/"+num+"/undefined/未审核/DESC",
		type:"get",
		dataType:"json",
		success:function(data){
			var tmp='';
			$.each(data.page.data,function(index,item){
				tmp+='<div id='+item.id+' class="panel panel-default"><div class="text-center">'
			          +'<h3><a href="#">'+item.video.title+'</a></h3>'
			          +'<div><span>用户：<a href="#">'+item.user.nickName+'</a></span> • <span>'+(new Date(item.timeStamp).toLocaleDateString().replace("/","-").replace("/","-"))+'</span>'
						+'</div></div><div class="panel-body">'
			           +'<h4>'+item.content+'</h4>'
			            +'<div class="text-right"><a href="#" onclick="setId(\''+item.id+'\')" data-toggle="modal" data-target="#agree"><button type="button"  class="btn btn-success">&nbsp;&nbsp;通&nbsp;&nbsp;过&nbsp;&nbsp;</button></a> '
			             +'<a href="#" onclick="setId(\''+item.id+'\')" data-toggle="modal" data-target="#reject"><button type="button" class="btn btn-danger">&nbsp;&nbsp;拒&nbsp;&nbsp;绝&nbsp;&nbsp;</button></div></div></div></a>';
			});/*onclick="verify(\''+item.id+'\',1)"*/
			totalPage = data.page.totalPage;
			pushNum=data.page.currentPage;
			$("#push_panels").append(tmp);
		},
		error:function(){
			alert("请求失败！");
		}
	})
	
}
function manageAllSelect(obj){
	if(selected){
		$("#managePanel #comment-category").children().each(function(){
			$(this).addClass("active");
		});
		$(obj).addClass("active");
		selected=false;
	}else{
		$("#managePanel #comment-category").children().each(function(){
			$(this).removeClass("active");
		});
		selected=true;
		$(obj).removeClass("active");
	}
	manageComment(1);
}

function pushAllSelect(obj){
	if(selected){
		$("#comment-category").children().each(function(){
			$(this).addClass("active");
		});
		$(obj).addClass("active");
		selected=false;
	}else{
		$("#comment-category").children().each(function(){
			$(this).removeClass("active");
		});
		selected=true;
		$(obj).removeClass("active");
	}
	pushComment(1);
}

function courseMenu(obj){
	$(obj).toggleClass("active");
	$("#push_panels").html('');
	pushNum=0;
}

function time_order(btn) {
	var $span = $(btn).children("span");
	if($span.hasClass("glyphicon-chevron-up")) {
		$span.removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
	} else {
		$span.removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
	}
	manageComment(1);
}


/*$(function(){
	$('[data-toggle="popover"]').popover();
	
	*//**
	$("#chk_all").click(function() {
		if ($(":checkbox").prop("checked")){
			$(":checkbox").prop("checked",true);
		}
		else{
			$(":checkbox").removeAttr("checked");
		}
	});
	*//*
	
	$(".btn-group button").click(function(){
		active($(this));
	});
	
	$("#pushPanel .btn-group button:last").click(function(){
		check_all(this);
	});
	
	$("#refresh_btn").click(function(){
		if($(this).children("i").hasClass("icon-spin")) {
			$(this).children("i").removeClass("icon-spin");
			end_push();
		} else {
			$(this).children("i").addClass("icon-spin");
			start_push();
		}
	});
	
	$(".nav-tabs li:last").click(function(){
		$("#refresh_btn i").removeClass("icon-spin");
		end_push();
	});
	
	$("#push_panels .btn-success").click(function(){
		pass(this);
	});
	
	$("#push_panels .btn-danger").click(function(){
		reject(this);
	});
	
	$("#comment_time").click(function(){
		time_order(this);
	});
	
});

function check_all(btn) {
	if($(btn).hasClass("active")) {
		$(btn).siblings("button").addClass("active");
	} else {
		$(btn).siblings("button").removeClass("active");
	}
}

function start_push() {
	// 拉取数据
	var $panel = 
          '<div class="panel panel-default">'+
            '<div class="text-center">' +
              '<h3><a href="#">Spring MVC 对异常处理的支持</a></h3>' +
              '<div>' +
                  '<span>用户：<a href="#">曹操</a></span> • <span>2015年5月18日</span>' +
              '</div>' +
            '</div>' +      
            '<div class="panel-body">' +
              '<h4>' +
                 '讲的太好了，我都听懂了！老师讲的非常的细致耐心，画的图十分的容易理解。' +
              '</h4>' +
              '<div class="text-right">' +
                  '<button type="button" class="btn btn-success">&nbsp;&nbsp;通&nbsp;&nbsp;过&nbsp;&nbsp;</button> ' +
                  '<button type="button" class="btn btn-danger">&nbsp;&nbsp;拒&nbsp;&nbsp;绝&nbsp;&nbsp;</button>' +
              '</div>' +
            '</div>' +
          '</div>';
	push_timer = setInterval(function(){
		var n = $("#push_panels").children("div").length;
		if(n <= 30) {
			$("#push_panels").append($panel);
			$("#push_panels .btn-success").click(function(){
				pass(this);
			});
			
			$("#push_panels .btn-danger").click(function(){
				reject(this);
			});
		}
	}, 2000);
}

function end_push() {
	clearInterval(push_timer);
}

function pass(btn) {
	$(btn).parent().parent().parent().remove();
}

function reject(btn) {
	$(btn).parent().parent().parent().remove();
}

function time_order(btn) {
	var $span = $(btn).children("span");
	if($span.hasClass("glyphicon-chevron-up")) {
		$span.removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down");
	} else {
		$span.removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up");
	}
}*/