//@ sourceURL=video.js
$(function(){
	loadTite();
	$(".input-group .btn").click(function(){
		findVideoByPage(1);
	});
	findVideoByPage(1);
});
function changeStatus(){
	var nowStatus = $("#videoDeatil span")[8].innerText;
	var changeStatus='';
	if(nowStatus=="通过"){
		changeStatus='未通过';
	}else{
		changeStatus='通过';
	}
	changeStatus=changeStatus==""?"undefined":changeStatus;
	if($("#videoId").val()!=""){
		var videoId=$("#videoId").val();
		//alert(videoId);
		$.ajax({
			url:basePath+"video/"+videoId+"/"+changeStatus,
			type:"put",
			dataType:"json",
			success:function(data){
				if(data.success){
					if(nowStatus=="通过"){
						$("#videoDeatil span")[8].innerText="未通过";
						$("#option").html(`<button type="button" data-toggle="modal" data-target="#isPass" class="btn btn-success">通过</button>`);
						$("#reject").modal('hide');
					}else{
						$("#videoDeatil span")[8].innerText="通过";
						$("#option").html(`<button type="button" data-toggle="modal" data-target="#reject" class="btn btn-warning">拒绝</button>`);
						$("#isPass").modal('hide');
					}
				}else{
					alert(data.errMsg);
					$("#isPass").modal('hide');
				}
			},
			error:function(){
				alert("请求失败");
			}
		})
	}else{
		alert("未选择视频")
	}
	
}
function videoDetail(id){
	$("#tip").hide();
	$("#video-panel").show();
	$('.nav-tabs a[href="#videoDesc"]').tab("show");
	$.ajax({
		url:basePath+"video/"+id,
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.success){
				var video=data.video;
				$(".panel-body video").attr("src",video.urlCc);
				$('#videoDeatil h3').text(video.title);
				$("#videoDeatil span")[0].innerText=video.urlCc;
				$("#videoDeatil span")[1].innerText=video.course.name;
				$("#videoDeatil span")[2].innerText=video.user.nickName;
				$("#videoDeatil span")[3].innerText=video.special;
				$("#videoDeatil span")[4].innerText=video.difficulty;
				$("#videoDeatil span")[5].innerText=video.clickCount;
				$("#videoDeatil span")[6].innerText=(new Date(video.onTime).toLocaleDateString().replace("/","-").replace("/","-"));
				$("#videoDeatil span")[7].innerText=video.forSale;
				$("#videoDeatil span")[8].innerText=video.isPass;
				$("#videoDeatil span")[9].innerText=video.introduction;
				$("#videoId").val(video.id);
				if(video.isPass=="未通过"){
					$("#option").html(`<button type="button" data-toggle="modal" data-target="#isPass" class="btn btn-success">通过</button>`);
				}else{
					$("#option").html(`<button type="button"  data-toggle="modal" data-target="#reject" class="btn btn-warning">拒绝</button>`);
				}
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
	//alert(id);
}
function findVideoByPage(currentPage){
	$("#video-list").append(`<div class="stage">
		                        <div class="dot-spin"></div>
		                    </div>`);
	var course = $('#videoTitle .active').text();
	var keyWord = $(".input-group input[type=text]").val();
	course=course==""?"undefined":course;
	keyWord=keyWord==""?"undefined":keyWord;
	currentPage=currentPage==""?"undefined":currentPage;
	$.ajax({
		url:basePath+"video/"+course+"/"+keyWord+"/"+currentPage,
		type:"get",
		/*data:{"courseName":course,"keyWord":keyWord,"currentPage":currentPage},*/
		dataType:"json",
		success:function(data){
			if(data.success){
				var tmp='';
				$.each(data.page.data,function(index,item){
					
					tmp+=' <div class="col-md-3"><div class="thumbnail"><a href="#videoDesc"><img src="'+item.picture+'" alt="...">'
				         +'</a><div class="caption"><a href="#" onclick="videoDetail(\''+item.id+'\')"><h3>'+(item.title).substring(0,7)+"..."+'</h3></a><p><span>上传日期 : </span>'+(new Date(item.onTime).toLocaleDateString().replace("/","-").replace("/","-"))+'</p><p><span>视频介绍 : </span>'+(item.introduction).substring(0,8)+"..."+'</p></div></div></div>';
				});
				$("#video-list").html('');
				$("#video-list").append(tmp);
				paginator(data.page.totalPage,data.page.currentPage);
			}else{
				alert(data.errMsg);
			}
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
            	findVideoByPage(page);
            }
        }
        $('#example').bootstrapPaginator(options);
}
function selectCourse(obj){
	if($('#videoTitle .active').text()==$(obj).text()){
		$('#videoTitle .btn').removeClass("active");
		findVideoByPage(1);
	}else{
		$('#videoTitle .btn').removeClass("active");
		$(obj).addClass("active");
		findVideoByPage(1);
	}
}
function loadTite(){
	$.ajax({
		url:basePath+"course",
		type:"get",
		dataType:"json",
		success:function(data){
			var tmp = '';
			$.each(data.courseList,function(index,item){
				tmp+='<button type="button" onclick="selectCourse(this)" class="btn btn-default">'+item.name+'</button>';
			});
			$('#videoTitle').append(tmp);
		}
	});
}
