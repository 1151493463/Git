//@ sourceURL=user.js
$(function(){
	findUsersByPage(1);
	$("#search").click(function(){
		findUsersByPage(1);
	});
	showTitle();
	roleCategory = '';
	  $('#example-multiple').multiselect();
	  $('#add-role-Category').multiselect();
});
function changeUserState(userId){
	$("#lockUser").click(function(){
		var userState = $("#"+userId).children().eq(6).text();
		if(userId==""){
			userId="undefined";
		}
		var nowState = '';
		if(userState=="是"){
			nowState="否"
			$(".bs-example-modal-sm h4").text("解锁用户");
			$(".bs-example-modal-sm .modal-body").text("确定解锁该用户？");
		}else{
			$(".bs-example-modal-sm h4").text("锁定用户");
			$(".bs-example-modal-sm .modal-body").text("确定锁定该用户？");
			nowState="是";
		}
		var user = {};
		user.id=userId;
		user.isLock=nowState;
		$.ajax({
			url:basePath+"user/"+JSON.stringify(user)+"/changeState",
			type:"put",
			dataType:"json",
			success:function(data){
				if(data.success){
					$(".bs-example-modal-sm").modal('hide');
					$("#"+userId).children().eq(6).text(nowState);
					$("#"+userId+"#changeUserState").html('');
					if(nowState=="是"){
						$("#"+userId+" #changeUserState").html('<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>解锁');
					}else{
						$("#"+userId+" #changeUserState").html('<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>锁定');
					}
				}else{
					alert(data.errMsg);
				}
			},
			error:function(){
				alert("请求失败！");
			}
		})
	})
}
function importUser(){
	//alert("ff");
	var file  = $("#importUser").get(0).files[0];
	var formData = new FormData();
	formData.append("file",file);
	$.ajax({
		url:basePath+"user/import",
		type:"post",
		contentType:false,
		processData:false,
		cache:false,
		data:formData,
		dataType:"json",
		success:function(data){
			if(data.success){
				alert("导入成功！");
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
}

function downloadTemplate(){
	window.open(basePath+"user/template");
}
function download(){
	window.open(basePath+"user/download");
}
function updateUser(){
	var roles = '';
	var roleName='';   
	$.each($("#example-multiple option:selected"),function(index,item){
		roles+=$(item).val()+",";
		roleName+=$(item).text()+",";
	})
	roles = roles.length>0?roles.substring(0,roles.length-1):"undefined";
	roleName = roleName.substring(0,roles.length-1)
	var id = $("#userId").val();
	var loginName = $("#editLoginName").val();
	var nickName = $("#editNickName").val();
	var score = $("#editScore").val();
	var loginType = getLoginType(loginName);
	
	if(!getLoginType(loginName)=="手机" || !getLoginType(loginName)=="邮箱"){
		alert("用户名不符合类型，应为手机或邮箱！");
		return;
	}
	var user={};
	user.id=id;
	user.loginName=loginName;
	user.score=score;
	user.password=$("#editPassword").val();
	user.nickName=nickName;
	user.age=$("#editAge").val();
	user.sex=$('#editRadio input[type=radio]:checked').val();
	
	$.ajax({
		url:basePath+"user/"+JSON.stringify(user)+"/"+roles,
		type:"put",
		dataType:"json",
		success:function(data){
			if(data.success){
				alert("更新成功");
				$("#editUser").modal('hide');
				$("#"+id+" td")[1].innerText=loginName;
				$("#"+id+" td")[2].innerText=nickName;
				$("#"+id+" td")[3].innerText=loginType;
				$("#"+id+" td")[4].innerText=score;
				$("#"+id+" td")[7].innerText=roleName;
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	})
}
function uploadImg(img){
	var headImg = $(img).get(0).files[0];
	var userId = $("#userId").val();
	if(userId==""){
		undefined="undefined";
	}
	var formData = new FormData();
	formData.append("userId",userId);
	formData.append("userImg",headImg);
	console.log(formData);
	$.ajax({
		url:basePath+"user/head",
		type:"post",
		data:formData,
		contentType:false,
		processData:false,
		cache:false,
		dataType:"json",
		success:function(data){
			if(data.success){
				$("#img").css('background-image','url('+data.imgAddr+')');
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("上传图片失败");
		}
	})
}
function editUser(id){
	$.ajax({
		url:basePath+"user/"+id,
		type:"get",
		dataType:"json",
		success:function(data){
			var user = data.user;
			var roleName='';
			if(user.roles!=null){
				$.each(user.roles,function(index,item){
					roleName+=item.name+',';
				});
			}
			if(roleName.length!=0){
				roleName=roleName.substr(0,roleName.length-1);
				var arr = new Array();
				arr = roleName.split(",");
				var tmp = '';
				$("#example-multiple").html('');
				$.each(roleCategory,function(index,item){
					var flag = false;
					for(var i=0;i<arr.length;i++){
						if(arr[i]==item.name){
							tmp+='<option selected="selected" value="'+item.id+'">'+item.name+'</option>';
							flag = true;
						}
					}
					if(!flag){
						tmp+='<option value="'+item.id+'">'+item.name+'</option>'
					}
				});
				$("#example-multiple").append(tmp);
				$("#example-multiple").multiselect("destroy").multiselect({
					nonSelectedText : '--请选择--', 
					maxHeight : 350, 
					includeSelectAllOption : true, 
					numberDisplayed : 5

				});
			}
			$("#img").css('background-image','url('+user.head+')');
			$("#editLoginName").val(user.loginName);
			$("#editNickName").val(user.nickName);
			$("#editPassword").val(user.password);
			$("#editAge").val(user.age);
			$("#editScore").val(user.score);
			if("男"==user.sex){
				$('#editUser input[type=radio]')[0].checked=true;
			}else{
				$('#editUser input[type=radio]')[1].checked=true;
			}
			$("#userId").val(user.id);
		},
		error:function(){
			alert("请求失败！");
		}
	})
}
function showMessage(id){
	$('.nav-tabs a[href="#detailPanel"]').tab("show");
	$("#detailPanel .panel-body div:eq(0)").hide();
	$("#detailPanel .panel-body div:eq(1)").show();
	if(id==""){
		id="undefined";
	}
	$.ajax({
		url:basePath+"user/"+id,
		type:"get",
		dataType:"json",
		success:function(data){
			var user = data.user;
			var roleName='';
			if(user.roles!=null){
				$.each(user.roles,function(index,item){
					roleName+=item.name+',';
				});
			}
			if(roleName.length==0){
				roleName='无角色';
			}else{
				roleName=roleName.substr(0,roleName.length-1);
			}
			$("#detailPanel .media-object").attr("src",user.head);
			$("#detailPanel .media-heading").html(user.loginName);
			$('.media-body span')[0].innerHTML =user.loginType;
			$('.media-body span')[1].innerHTML =user.nickName;
			$('.media-body span')[2].innerHTML =user.sex;
			$('.media-body span')[3].innerHTML =user.age;
			$('.media-body span')[4].innerHTML =user.score;
			$('.media-body span')[5].innerHTML =(new Date(user.regDate).toLocaleDateString().replace("/","-").replace("/","-"));
			$('.media-body span')[6].innerHTML =user.isLock;
			$('.media-body span')[7].innerHTML =roleName;
		},
		error:function(){
			alert("请求失败！");
		}
	})
}
function addUser(){
	var file = $('#addPicture').get(0).files[0];
	var user={};
	user.loginName=$('#addLoginName').val();
	user.password=$('#addPassword').val();
	user.nickName=$('#addNickName').val();
	user.age=$('#addAge').val();
	user.sex=$('#addRadio input[name="user-type"]:checked').val();
	var role='';
	$.each($("#add-role-Category option:selected"),function(index,item){
		role+=$(item).val()+",";
	})
	role = role.length>0?role.substring(0,role.length-1):"undefined";
	console.log(user);
	console.log(file);
	var formData = new FormData();
	formData.append("userStr",JSON.stringify(user));
	formData.append("userImg",file);
	formData.append("userRole",role);
	$.ajax({
		url:basePath+"user",
		type:"post",
		data:formData,
		contentType : false,
		processData : false,
		cache : false,
		dataType:"json",
		success:function(data){
			alert(data);
		},
		error:function(){
			alert("请求失败！");
		}
	})
}
function showTitle(){
	$.ajax({
		url:basePath+"role",
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.success){
				var tmp='';
				var addTmp='';
				roleCategory=data.roleList;
				$.each(data.roleList,function(index,item){
					tmp+='<button type="button" class="btn btn-default">'+item.name+'</button>';
					if(item.name=="讲师"){
						addTmp+='<option selected="selected" value="'+item.id+'">'+item.name+'</option>';
					}else{
						addTmp+='<option value="'+item.id+'">'+item.name+'</option>';
					}
					
				});
				$('#add-role-Category').html(addTmp)
				$("#add-role-Category").multiselect("destroy").multiselect({
					nonSelectedText : '--请选择--', 
					maxHeight : 350, 
					includeSelectAllOption : true, 
					numberDisplayed : 5

				});
				$('#userPanel .btn-group').append(tmp);
				$('#userPanel .btn-group .btn').on('click',function(){
					 if($(this).hasClass("active")){
						 $(this).removeClass('active');
					 }else{
						 $('#userPanel .btn-group .btn').removeClass('active');
						 $(this).addClass('active');
					 }
					findUsersByPage(1);
				});
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("角色类别菜单加载失败");
		}
	});
}
function findUsersByPage(currentPage){
	$("#content").append(`
			<td></td><td></td><td></td><td></td><td></td><td>
		                
		                    <div class="stage">
		                        <div class="dot-spin"></div>
		                    </div>
		           
		                </td><td></td><td></td>
			`);
	if(currentPage==""){
		currentPage = "undefined";
	}
	var userkeyword = $("#userPanel form input[type=text]").val();
	if(userkeyword==""){
		userkeyword="undefined";
	}
	var roleCategory = $('#userPanel .btn-group .active').text();
	if(roleCategory==""){
		roleCategory="undefined";
	}
	$.ajax({
		url:basePath+"user/users/"+currentPage+"/"+userkeyword+"/"+roleCategory,
		type:"get",
		dataType:"json",
		success:function(data){
			var tmp = '';
			$.each(data.page.data,function(index,item){
				var roleName='';
				if(item.roles!=null){
					$.each(item.roles,function(index,item){
						roleName+=item.name+',';
					});
				}
				if(roleName.length==0){
					roleName='无角色';
				}else{
					roleName=roleName.substring(0,roleName.length-1);
				}
				tmp+='<tr id="'+item.id+'"><td>'+(index+1)+'</td><td>'+item.loginName+'</td><td>'+item.nickName+'</td><td>'+item.loginType+'</td><td>'+item.score+'</td><td>'+(new Date(item.regDate).toLocaleDateString().replace("/","-").replace("/","-"))+'</td>'
					+'<td>'+item.isLock+'</td><td>'+roleName+'</td><td><a onclick="showMessage(\''+item.id+'\')" href="#"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>详情</a> <a onclick="editUser(\''+item.id+'\')" href="" data-toggle="modal" data-target="#editUser"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>'
					+' <a href="#detailPanel" id="changeUserState"';
				if(item.isLock=="是"){
					tmp+='onclick="changeUserState(\''+item.id+'\')" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>解锁</a>'
					+'</td></tr>' ;
				}else{
					tmp+='onclick="changeUserState(\''+item.id+'\')" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span>锁定</a>'
					+'</td></tr>';
				}
			});
			$("#content").empty();
			$("#content").append(tmp);
			paginator(data.page.totalPage,data.page.currentPage);
		},
		error:function(){
			alert("请求失败！")
		}
	});
}
function paginator(totalPage,currentPage){
	var options = {
            currentPage: currentPage,
            totalPages: totalPage,
            numberOfPages:5,
            onPageClicked: function(e,originalEvent,type,page){
            	findUsersByPage(page);
            }
        }
        $('#example').bootstrapPaginator(options);
}



function getLoginType(loginName){
	var tel = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
	var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if(tel.test(loginName)){
		return "手机";
	}
	if(email.test(loginName)){
		return "邮箱";
	}

}