//@ sourceURL=role.js
$(function(){
	roleId = '';
	//没有模糊条件的第一页
	findRoleByPage(1);
	//给模糊搜索的按钮添加click事件
	$("#rolePanel .row button").click(function(){
		findRoleByPage(1);
	});
	//给添加角色的表单添加事件
	$("#addPanel button[type=button]").click(function(){
		addRole();
	});
	//给确认按钮添加click事件
	$(".bs-example-modal-sm button:eq(1)").click(function(){
		deleteRole();
	});
	//给修改的model框表单添加submit
	$("#editRole form").submit(function(){
		return updateRoleName();
	})
});
//
function updateRoleName(){
	//获取角色的新值
	var newRoleName = $("#editRole form #role_name").val();
	if(roleId==""){
		roleId = "undefined";
	}
	if(newRoleName == ""){
		newRoleName = "undefined";
	}
	//发送异步请求
	$.ajax({
		url:basePath+"role/"+roleId+"/"+newRoleName,
		type:"put",
		dataType:"json",
		success:function(data){
			if(data.success){
				$("#"+roleId).find("td:eq(2)").html(newRoleName);
				$("#editRole").modal('hide');
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败!");
		}
	})
	return false;
}
function updateRole(id){
	roleId = id;
	var oldRoleName = $("#"+roleId).find("td:eq(2)").text();
	$("#editRole form #role_name").val(oldRoleName);
}

function deleteRole(){
	if(roleId==""){
		roleId = "undefined";
	}
	$.ajax({
		url:basePath+"role/"+roleId,
		type:"delete",
		dataType:"json",
		success:function(data){
			if(data.success){
				$('.bs-example-modal-sm').modal('hide');
				$("#"+roleId).remove();
			}else{
				$('.bs-example-modal-sm').modal('hide');
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败!");
		}
	})
}
//根据当前页号查询当前页大的数据，并局部刷新页面
function findRoleByPage(currentPage){
	$("#content").append(`
	<td></td><td>
                    <div class="stage">
                        <div class="dot-spin"></div>
                    </div>
                </td><td></td><td></td>
	`);
	var rolekeyword = $("#rolePanel .row input[type=text]").val();
	if(rolekeyword==""){
		rolekeyword="undefined";
	}
	if(currentPage==""){
		currentPage = "undefined";
	}
	$.ajax({
		url:basePath+"role/"+rolekeyword+"/"+currentPage,
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.success){
				var tmp = '';
				$.each(data.page.data,function(index,item){
					if(item.name!='学院' && item.name!='讲师' && item.name!='超级管理员'){
						tmp+='<tr id="'+item.id+'"><td>'+(index+1)+'</td><td>'+item.id+'</td><td>'+item.name+'</td><td> <a href="" onclick="updateRole(\''+item.id+'\')" data-toggle="modal" data-target="#editRole" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a> <a href="" onclick="getRole(\''+item.id+'\')" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a></td></tr>'
					}else{
						tmp+='<tr><td>'+(index+1)+'</td><td>'+item.id+'</td><td>'+item.name+'</td><td></td></tr>'
					}
				});
				$("#content").empty();
				$("#content").append(tmp);
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
            	findRoleByPage(page);
            }
        }
        $('#example').bootstrapPaginator(options);
}
function addRole(){
	var newRoleName = $("#addPanel input[type=text]").val();
	if(newRoleName==""){
		newRoleName="undefined";
	}
	$.ajax({
		url:basePath+"role/"+newRoleName,
		type:'post',
		dataType:"json",
		success:function(data){
			if(data.success){
				alert("添加成功");
			}else{
				alert(data.errMsg);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	});
}
function getRole(id){
	roleId = id;
}
