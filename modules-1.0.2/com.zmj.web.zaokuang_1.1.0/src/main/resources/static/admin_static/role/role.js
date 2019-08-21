var ROLE_ID ='';
var t3w=null;
var moduleTree = null;
$(document).ready(function(){
	loadRoleData();
});

function loadRoleData(){
	var row ='';

	$.get('/SysBase/Role/listRole',null,function(result){
		t3w = result;
		console.log(t3w);
		if(result.Code==2000){
			var data = result.Data;
			for(var i in data){
				row += '<tr>';
				row += '<td align="center">'+parseInt(parseInt(i)+1)+'</td>';
				row += '<td align="center">'+data[i].roleName+'</td>';
				row += '<td align="center">'+data[i].roleValue+'</td>';
				row += '<td align="left">'+data[i].roleDes+'</td>';
				row += '<td align="center">'+data[i].sortcode+'</td>';
				row += '<td align="center">'+operate(data[i].roleId)+'</td>';
				row += '</tr>';
			}
			$('#tableData').html(row);	

		}


	},'json');

}

function operate(roleId){

	var html = '<a class="gongzuo" href="javascript:permitRole(\''+roleId+'\')"> 授权 </a>';
	html += '<a  href="javascript: void(0);" onclick="editmethod(this)"> 编辑 </a>';
	html += '<a  href="javascript:delmethod(\''+roleId+'\');" > 删除 </a>';
	return html;
}

function permitRole(roleId){

	ROLE_Id = roleId;
	if($('#moduleTreeDiv').css('display')=='block'){
		$('#moduleTreeDiv').css('display','none');
		$('#roleData').css('width','calc(100% - 0px)');

	}else{
		$('#moduleTreeDiv').css('display','block');
		$('#roleData').css('width','calc(100% - 400px)');

		loadRoleModuleTreeData(roleId);

	}
	

}

/**
 * 获取角色拥有的资源权限
 * @param roleId
 * @returns
 */
function loadRoleModuleTreeData(roleId){
	var setting = {
			check:{
				enable: true
			},
			data:{
				key:{
					name:'mName',
				},
				simpleData: {
					enable: true,
					idKey:"mId",
					pIdKey:"mPid"
				}
			}
	};

	$.get('/SysBase/RoleModule/getRoleModuleTreeData',{
		roleId:roleId
	},function(data){
		console.log(data);
		var treedata = data.Data;
		moduleTree = $.fn.zTree.init($("#moduleTree"), setting,treedata);
		moduleTree.expandAll(true);
	},'json');	
}

/**
 * 给角色授权
 * @returns
 */
function permitRoleModules(){

	//获取选中的模块资源节点
	var nodes = moduleTree.getCheckedNodes();
	
	var moduleIds = [];
	for(var i in nodes){
		moduleIds.push(nodes[i].mId);
	}
	
	
	$.post('/SysBase/RoleModule/permitRoleModules',{
		RoleId:ROLE_Id,
		ModuleIds:moduleIds.join(',')
	},function(data){

		if(data.Code == 2000){
			alert('修改成功');
		}else{

		}

	},'json');

}


/**
 * 修改角色
 * @returns
 */
function addmethod(){
	$('#roleName').val('');
	$('#roleValue').val('');
	$('#roleDes').val('');
	$('#SortCode').val('');
	$('#subflag').val('0');
	$('#roleModal').modal('show');
}

//编辑表单
function editmethod(obj){
	var i  =$(obj).parent().parent().children("td").get(0);
	var n = parseInt($(i).html())-1;
	//将模块信息回溯在表单
	doEditForm(n);
	$('#subflag').val('1');
	$('#roleModal').modal('show');
}
//回溯表单内容
function doEditForm(i){
	var rd = t3w.Data[i];
	console.log(rd);
	ROLE_ID=rd.roleId;
	$('#roleName').val(rd.roleName);
	$('#roleValue').val(rd.roleValue);
	$('#roleDes').val(rd.roleDes);
	$('#SortCode').val(rd.sortcode);
}

/**
 * 提交表单
 * @returns
 */
function submitRole(){
	//表单验证
	var data = {
			roleId:ROLE_ID,
			roleName:$('#roleName').val(),
			roleValue:$('#roleValue').val(),
			roleDes:$('#roleDes').val(),
			sortCode:$('#SortCode').val()
	};
	if(data.roleName.length==0){
		alert("内容不能为空!");
		return;
	}else if(data.roleValue.length==0){
		alert("内容不能为空!");
		return;
	}else if(data.roleDes.length==0){
		alert("内容不能为空!");
		return;
	}else if(data.sortCode.length==0){
		alert("内容不能为空!");
		return;
	}

	//根据subflag选择是新增还是修改
	if($('#subflag').val()==0){
		submitAddRole(data);
		$('#roleModal').modal('hide');
	}else{
		submitEditRole(data);
		$('#roleModal').modal('hide');
	};
}

//提交编辑表单
function submitEditRole(data){
	$.post('/SysBase/Role/updateRole',data,function(data){
		if(data.Code==2000){
			alert('修改成功!');
			loadRoleData();
		}else{
			alert(data.Error_Msg);
		}
	},'json');
}

//提交添加表单
function submitAddRole(data){
	$.post('/SysBase/Role/addZZRole',{
		roleName:data.roleName,
		roleValue:data.roleValue,
		roleDes:data.roleDes,
		sortcode:data.sortCode
	},function(data){
		if(data.Code==2000){
			alert("添加成功!");
			loadRoleData();
		}else{
			alert(data.Error_Msg);
		}
	},'json');
}
//删除模块
function delmethod(id){
	if(confirm('确定要删除吗?')){
		$.get('/SysBase/Role/delRole',{
			roleId:id
		},function(result){
			if(result.Code==2000){
				alert("删除成功!");
				location.reload();
			}else{
				alert(result.Error_Msg);
			}
		});
	}
}

