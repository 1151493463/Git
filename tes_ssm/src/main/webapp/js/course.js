//@ sourceURL=course.js
$(function(){
	courseId = '';
	getCourseByPage(1);
/*	$("#searchBtn").click(function(){
		getCourseByPage(1);
	});*/
})
function test(){
	getCourseByPage(1);
}
function changeClick(id){
	courseId=id;
	var status = $("#"+courseId+" td:eq(6) ").text();
	var nowStatus='';
	if(status=="上线"){
		$("#changCourseStatus h4").text("下线课程");
		$("#changCourseStatus .modal-body").text("确定下线该课程");
	}else{
		$("#changCourseStatus h4").text("上线课程");
		$("#changCourseStatus .modal-body").text("确定上线该课程");
	}
}
function changeCourseStatus(){
	var id=courseId;
	var status = $("#"+courseId+" td:eq(6) ").text();
	var nowStatus='';
	if(status=="上线"){
		nowStatus="下线";
	}else{
		nowStatus="上线";
	}
	var course={};
	course.id=id;
	course.status=nowStatus;
	var formData = new FormData();
	formData.append("courseStr",JSON.stringify(course));
	$.ajax({
		url:basePath+"course",
		type:"post",
		data:formData,
		contentType:false,
		processData:false,
		cache:false,
		dataType:"json",
		success:function(data){
			if(data.success){
				if(nowStatus=="上线"){
					$("#"+id+" #changeStatus").html('<span class="glyphicon glyphicon-object-align-top" aria-hidden="true"></span>下线');
				}else{
					$("#"+id+" #changeStatus").html('<span class="glyphicon glyphicon-object-align-bottom" aria-hidden="true"></span>上线');
				}
				$("#"+courseId+" td:eq(6) ").text(nowStatus);
				$("#changCourseStatus").modal('hide');
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
}

function updateCourse(){
	var course = {};
	var name=$("#edit-courseName").val();
	var order=$("#edit-recommended").val();
	var desc=$("#edit-courseDesc").val();
	course.id=courseId;
	
	course.name=name;
	course.order=order;
	course.desc=desc;  
	var file = $("#picture").get(0).files[0];
	var formData = new FormData();
	if(file!=undefined){
		formData.append("courseImg",file);
	}
	formData.append("courseStr",JSON.stringify(course));
	$.ajax({
		url:basePath+"course",
		type:"post",
		data:formData,
		contentType:false,
		processData:false,
		cache:false,
		dataType:"json",
		success:function(data){
			if(data.success){
				alert("更新成功");
				$("#editCourse").modal('hide');
				if(file!=undefined){
					$("#"+courseId+" td:eq(1) img").attr("src",data.imgAddr);
				}
				$("#"+courseId+" td:eq(2) ").text(name);
				$("#"+courseId+" td:eq(3) ").text(order);
				$("#"+courseId+" td:eq(5) ").text(desc);
			}else{
				alert(data.errMsg)
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
}

function editCourse(id){
	if(id==""){
		id="undefined";
	}
  	 $.ajax({
  		 url:basePath+"course/"+id,
  		 type:"get",
  		 dataType:"json",
  		 success:function(data){
  			 if(data.success){
  				 var course = data.course;
  				$("#edit-courseName").val(course.name);
  				$("#edit-recommended").val(course.order);
  				$("#edit-courseDesc").val(course.desc);
  				courseId=id;
  			 }else{
  				 alert(data.errMsg);
  			 }
  		 },
  		 error:function(){
  			 alert("请求失败！");
  		 }
  	 })
}
function addCourse(){
	var course={};
	course.name=$("#add-courseName").val();
	course.order=$("#add-recommended").val();
	course.desc=$("#add-courseDesc").val();
	var file = $("#add-picture").get(0).files[0];
	var formData = new FormData();
	formData.append("courseStr",JSON.stringify(course));
	formData.append("courseImg",file);
	$.ajax({
		url:basePath+"course",
		data:formData,
		contentType:false,
		processData:false,
		cache:false,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.success){
				alert("课程添加成功！");
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
	
}

function getCourseByPage(currentPage){
	$("#course tbody").append(`
			<td></td><td></td><td></td><td></td><td>
		                    <div class="stage">
		                        <div class="dot-spin"></div>
		                    </div>
		                </td><td></td><td></td>
			`);
	var keyWord = $("#courseSearch").val();
	if(keyWord==""){
		keyWord="undefined";
	}
	$.ajax({
		url:basePath+"course/"+currentPage+"/"+keyWord,
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.success){
				var courseList = data.page.data;
				var tmp = '';
				$.each(courseList,function(index,course){
					index++;
					
					tmp+=`<tr id='${course.id}'>
                <td>${index}</td>
                <td><img src="${course.picture}"></td>
                <td>${course.name}</td>
                <td>${course.order}</td>
                <td>`+(new Date(course.onlineTime).toLocaleDateString().replace("/","-").replace("/","-"))+`</td>
                <td>${course.desc}</td>
                 <td>${course.status}</td>
                <td>
                  <a href="" data-toggle="modal" onclick="editCourse(\'${course.id}\')"  data-target="#editCourse"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>&nbsp;&nbsp;`;
					if(course.status=="下线"){
						tmp+=`<a href="" id="changeStatus" onclick="changeClick(\'${course.id}\')" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-object-align-bottom" aria-hidden="true"></span>上线</a></td></tr>`;
					}else{
						tmp+=`<a href="" id="changeStatus" onclick="changeClick(\'${course.id}\')" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-object-align-top" aria-hidden="true"></span>下线</a></td></tr>`;
					}
					//glyphicon glyphicon-object-align-top
				});
				$("#course tbody").html('');
				$("#course tbody").append(tmp);
				paginator(data.page.totalPage,data.page.currentPage);
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
            	getCourseByPage(page);
            }
        }
        $('#course-page').bootstrapPaginator(options);
}