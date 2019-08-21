//用于保存授权时用户的ID
var USER_ID = '';
var ORG_PID = '';
var tree1=null;
var tree2=null;
var keyword = '';
var t3w = null;
$(document).ready(function(){
	
	userempty ();
	
	loadOrgTree();
	
	// 进来需要请求的方法  加载所用的用户信息
	loadUsersByOrgId();
});

/**
 * 添加的时候，清空
 * @returns
 */
function userempty (){
	$('#userAddBtn').on('click',function(){
		/**
		 * 每次点击添加按钮，都需要清空
		 * @returns
		 */
		$('#subflag').val('0');
		$("#orgName").val('');
		$("#userName").val('');
		$("#userAccount").val('');
		$("#userTel").val('');
		$("#userJob").val('');
		$('#addModal').modal('show');
		$("#pass").attr("style","");	// 用户密码框
		$("#myModalLabel").html('添加用户');	// 更改标题
		$("#orgName").attr("readonly",false);  // 添加的时候重置该属性
		$("#select").attr("style","padding-top:10px");  // 选择框
		
		
	});
}

function loadOrgTree(){
	
	var setting = {
			
			data:{
				key:{
					name:'orgName',
				},
				simpleData: {
					enable: true,
					idKey:"orgId",
					pIdKey:"orgPid"
				}
			},
			callback:{
				onClick:onTreeClick
			}
	};
	
	$.get('/SysBase/Org/getOrgTree',null,function(data){
		tree1 = $.fn.zTree.init($("#orgTree"), setting,data);
		setting.callback.onClick = onInputTreeClick;
	    tree2 =$.fn.zTree.init($("#orgTree2"), setting,data);
		tree1.expandAll(true);
		tree2.expandAll(true);
	},'json');
	
	
}
/**
 * 选择加载树
 */
function onInputTreeClick(event,treeId,treeNode,clickFlag){
	$('#orgName').val(treeNode.orgName);
	ORG_PID = treeNode.orgId;
	console.log(ORG_PID);
	hideMenu();
}

/***
 * 提交
 * @returns
 */
function addUser(){
	var key =  $('#subflag').val();
	//根据subflag选择是新增还是修改
	if($('#subflag').val()==0){
		submitAddUser();
		//$('#addModal').modal('hide');
	}else{
		submitEditUser();
		//$('#addModal').modal('hide');
	};
	
}

/**
 * 添加用户的方法
 * @returns
 */
function submitAddUser(){
	//1、获取表单的内容
	var org_Name = $("#orgName").val();
	var user_Name  = $("#userName").val();
	var user_Account = $("#userAccount").val();
	var user_Tel = $("#userTel").val();
	var user_Job = $("#userJob").val();
	var user_Password = $("#userPassword").val();
	// 非空校验
	if(org_Name==""){
		alert("所属公司不能为空,请先选择所属的公司,并重新提交");
		return;
	}
	if(user_Name==""){
		alert("用户名称不能为空,请先填写用户名称,并重新提交");
		return;
	}
	if(user_Account==""){
		alert("用户账号不能为空,请先填写用户账号,并重新提交");
		return;
	}
	if(user_Tel==""){
		alert("用户电话不能为空,请先填写用户电话,并重新提交");
		return;
	}
	if(user_Password==""){
		alert("用户密码不能为空,请先填写用户电话,并重新提交");
		return;
	}
	if(user_Job==""){
		alert("用户职位不能为空,请先填写用户职位,并重新提交");
		return;
	}
	$.post('/SysBase/User/addUserEntity',{
		orgId:ORG_PID,
		userName:user_Name,
		userAccount:user_Account,
		userTel:user_Tel,
		userJob:user_Job,
		userPassword:user_Password,
		},function(data){
		if(data.Code=='2000'){
			$('#addModal').modal('hide');
			alert(data.Error_Msg);
			// 刷新方法
			loadUsersByOrgId(ORG_PID);
		}else{
			$('#sss').alert();
			alert(data.Error_Msg);
		}
	},'json');
}

/**
 * 修改模块
 * @returns
 */
function openEditDialog(obj){
	var i  =$(obj).parent().parent().children("td").get(0);
	var n = parseInt($(i).html())-1;
	
	//将模块信息回溯在表单
	doEditForm(n);
	$('#subflag').val('1');
	$("#pass").attr("style","display: none");	// 用户密码框
	$("#myModalLabel").html('修改用户');	// 更改标题
	$("#orgName").attr("readonly","readonly");  // 公司名称，修改的时候，不能修改
	$("#select").attr("style","display: none");  // 选择框
	$('#addModal').modal('show');
}
//回溯表单内容
function doEditForm(i){
	var gd = t3w.Data.records[i];
	pname = getPNameByPId(gd.orgId);
	ORG_PID = gd.orgId;
	USER_ID=gd.userId;
	$('#orgName').val(pname);
	$('#userName').val(gd.userName);
	$('#userAccount').val(gd.userAccount);
	$('#userTel').val(gd.userTel);
	$('#userJob').val(gd.userJob);
}

function getPNameByPId(pid){
	var list = t3w.Data.records;
	var pname = '';
	for(var i =0;i<list.length;i++){
		if(list[i].orgId==pid){
			pname=list[i].zzOrgEntity.orgName;
		}
	}
	return pname;
}

//修改表单
function submitEditUser(){
	var user_Name  = $("#userName").val();
	var user_Account = $("#userAccount").val();
	var user_Tel = $("#userTel").val();
	var user_Job = $("#userJob").val();
	var user_Password = $("#userPassword").val();
	
	// 非空校验
	if(user_Name==""){
		alert("用户名称不能为空,请先填写用户名称,并重新提交");
		return;
	}
	if(user_Account==""){
		alert("用户账号不能为空,请先填写用户账号,并重新提交");
		return;
	}
	if(user_Tel==""){
		alert("用户电话不能为空,请先填写用户电话,并重新提交");
		return;
	}
	if(user_Job==""){
		alert("用户职位不能为空,请先填写用户职位,并重新提交");
		return;
	}
	$.post('/SysBase/User/updateUserEntity',{
		userId:USER_ID,
		userName:user_Name,
		userAccount:user_Account,
		userTel:user_Tel,
		userJob:user_Job,
		userPassword:user_Password
	},function(data){
		if(data.Code==2000){
			alert("修改成功!");
			$('#addModal').modal('hide')
			loadUsersByOrgId(ORG_PID);
		}else{
			$('#addModal').modal('show')
			alert(data.Error_Msg);
		}
	},'json');
}

/**
 * 用户删除方法
 */
function delUser(userId){
	if(confirm('确定要删除该用户吗？')){
		$.post('/SysBase/User/delUserEntity',{
			userId:userId,
			},function(data){
			if(data.Code=='2000'){
				alert(data.Error_Msg);
				// 刷新方法
				console.log(ORG_PID);
				loadUsersByOrgId(ORG_PID);
			}else{
				$('#sss').alert();
				alert(data.Error_Msg);
			}
		},'json');
	}
}

//提交搜索ajax
function doSearch(){
	console.log("search!");
	keyword = $("#doSearch").val();
	console.log("keyword: "+keyword);
	loadUsersByOrgId(ORG_PID,keyword,1,11);
}


//向前翻页
function doFrontPage(){
	var pageNo = t3w.Data.pageNo;
	var pageSize = t3w.Data.pageSize;
	if(pageNo!=1){
		pageNo=pageNo-1;
	}
	loadUsersByOrgId(ORG_PID,keyword,pageNo,pageSize);
}

//向后翻页
function doNextPage(){
	var pageNo = t3w.Data.pageNo;
	var pageSize = t3w.Data.pageSize;
	var pageCount = t3w.Data.pageCount;
	console.log("pageNo:  "+pageNo);
	console.log("pageCount: "+pageCount);
	if(pageNo!=pageCount){
		pageNo=pageNo+1;
	}
	loadUsersByOrgId(ORG_PID,keyword,pageNo,pageSize);
}

//根据页码翻页
function doThisPage(pageNo){
	var pageSize = t3w.Data.pageSize;
	loadUsersByOrgId(ORG_PID,keyword,pageNo,pageSize);
}



/**
 * 点击树节点触发的事件
 * @param event
 * @param treeId
 * @param treeNode
 * @returns
 */
function onTreeClick(event, treeId, treeNode){
	
	var orgId = treeNode.orgId;
	console.log(orgId);
	//根据组织机构id加载该机构下的用户信息
	loadUsersByOrgId(orgId);
	
}
/**
 * 根据组织机构Id获取用户数据
 * @param orgId
 * @returns
 */
function loadUsersByOrgId(orgId,keyword,pageNo,pageSize){
	ORG_PID = orgId ;
	$.post('/SysBase/User/getUserListData',{
		orgId:orgId,
		keyword:keyword,
		pageNo:pageNo,
		pageSize:pageSize
	},function(data){
		console.log(data);
		t3w = data;
		page(data.Data);
		if(data.Code==2000){
			//填充用户表
			fillUserTableData(data.Data);
		}else{
			
		}
		
	},'json');
}

/**
 * 填充用户表
 * @param data
 * @returns
 */
function fillUserTableData(data){
	var pageNo = data.pageNo;
	var pageSize = data.pageSize;
	var records = data.records;
	var row = '';
	for(var i = 0; i < records.length; i++ ){
		row += '<tr>';
		row += '<td>' +parseInt((pageNo - 1) * pageSize+ i+ 1 ) + '</td>';
		row += '<td>' + records[i].userName + '</td>';
		row += '<td>' + records[i].userAccount + '</td>';
		row += '<td>' + records[i].userJob + '</td>';
        row += '<td>' + formatState(records[i].isuse) + '</td>';
		row += '<td>' + records[i].zzOrgEntity.orgName+ '</td>';
		row += '<td align="center">' + operate(records[i]) + '</td>';
		row += '</tr>';
	}
	$('#tableData').html(row);
}

/** 格式化用户状态 */
function formatState(state) {
    if (state == 0) {
        return "停用";
    } else {
        return "启用";
    }
}

function operate(data){
	var html = ' <a href="javascript:openPermitDialog(\''+data.userId+'\')">授权</a> ';
		html += ' <a href="javascript:void(0);" onclick="openEditDialog(this)">编辑</a> ';
		html += ' <a href="javascript:delUser(\''+data.userId+'\')">删除</a> ';
        html += '<a href="javascript:isUse(\'' + data.userId +'\',\'' + data.isuse +'\')">启停</a> ';
    return html;
}

/*用户状态启用禁用*/
function isUse(userId,isuse){
    if(confirm('确定要启用/禁用该用户吗？')) {
        console.info(userId);
        console.info(isuse);
        $.ajax({
            type: "POST",
            url: "/SysBase/User/updateIsUse",
            data: {
                userId: userId,
                isuse: isuse
            },
            dataType: "json",
            success: function (data) {
                if (data.Code == 2000) {
                    alert(data.Error_Msg);
                    // 刷新方法
                    console.log(ORG_PID);
                    loadUsersByOrgId(ORG_PID);
                } else {
                    alert(data.Error_Msg);
                }
            },
            error: function (e) {
                console.info(e);
            }
        })
    }
}

function openPermitDialog(userId){
	USER_ID = userId;
	//根据用户id获取用户所拥有的角色
	$.get('/SysBase/User/getUserRole',{userId:userId},function(data){
		console.log(data);
		if(data.Code==2000){
			
			fillRoleTableData(data.Data);
			
		}else{
			
		}
		
	},'json')
	
	$('#permitModal').modal('show');
}

/**
 * 填充用户角色表
 * @param data
 * @returns
 */
function fillRoleTableData(data){
	var row = '';
	for(var i in data){
		row += '<tr>';
		if(data[i].checked){
			
			row += '<td><input class="rolecb" type="checkbox" checked value="'+data[i].roleId+'"/></td>';
			
		}else{
			row += '<td><input class="rolecb" type="checkbox" value="'+data[i].roleId+'"/></td>';
		}
		row += '<td>' + parseInt(parseInt(i)+1) + '</td>';
		row += '<td>' + data[i].roleName + '</td>';
		row += '<td>' + data[i].roleDes + '</td>';
		row += '</tr>';
	}
	$('#roleTable').html(row);
}

/**
 * 给用户授权
 * @returns
 */
function permitUser(){
	
	//获取选中的角色Id
	var roleIds = [];
	 $('.rolecb:checked').each(function(k,v){
		 roleIds.push(v.value);
	 });
 	var roleIdsStr = roleIds.join(',');
	
 	//发送授权
 	
 	$.post('/SysBase/User/addUserRole',{userId:USER_ID,roleIds:roleIdsStr},function(data){
 		if(data.Code == 2000){
 			
 			alert('授权成功');
 			$('#permitModal').modal('hide');
 			loadUsersByOrgId();
 			
 		}else{
 			
 			alert(data.Error_Msg);	
 		}
 		
 		
 		
 		
 	},'json');
	
}



function showMenu() {
	var orgPId = $("#orgName");
	var orgPIdOffset = $("#orgName").offset();
	$("#menuContent").css({left:orgPIdOffset.left + "px", top:orgPIdOffset.top + orgPId.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}

/**
 * 分页开始
 */

function page(data){
	console.log("paging!!!");
	$('#pageBtn').empty();
	//当前页
	var pageNo = data.pageNo;
	console.log(pageNo);
	//页面显示的条数
	var pageSize = data.pageSize;
	//总页数
	var pageCount=data.pageCount;
	//总条数
	var count = data.count;
	//页码显示
	var i = 1;
	var item='';
	if(pageNo==1){
		item='';
	}else{
		item="<li id='frontPage' ><a  href='#' aria-label='Previous' onclick='doFrontPage()'> <span aria-hidden='true'>&laquo;</span></a></li>";
	}
	var id="pageNo"+i;
	if (pageCount <= 5 ) {//总页数小于五页，则加载所有页
		for (i; i <= pageCount; i++) {
			if (i == pageNo) {
				item +=  "<li class='active'><span>"+i+"</span></li>"; 
			}else{
				item += "<li><a href='#' onclick='doThisPage("+i+" )' >"+i+"</a></li>"; 
			}
		};
		if(pageNo==pageCount){
			item += '';
		}else{
			item += "<li><a id='nextPage' href='#' aria-label='Next' onclick='doNextPage()'> <span aria-hidden='true'>&raquo;</span></a></li>"; 
		}
		$('#pageBtn').append(item);
		return;
	}else if (pageCount > 5) {//总页数大于五页，则加载五页
		if (pageNo < 5) {//当前页小于5，加载1-5页
			for (i; i <= 5; i++) {
				if (i == pageNo) {
					item += "<li class='active'><span>"+i+"</span></li>"; 
				}else{
					item += "<li><a href='#' onclick='doThisPage("+i+" )' >"+i+"</a></li>"; 
				}
			};
			if (pageNo <= pageCount-2) {//最后一页追加“...”代表省略的页
				item += "<li class='disabled'><span> . . . </span></li>";
			}
			if(pageNo==pageCount){
				item += '';
			}else{
				item += "<li><a id='nextPage' href='#' aria-label='Next' onclick='doNextPage()'> <span aria-hidden='true'>&raquo;</span></a></li>"; 
			}
			$('#pageBtn').append(item);
			return;
		}else if (pageNo >= 5) {//当前页大于5页
			for (i; i <= 2; i++) {//1,2页码始终显示
				item += "<li><a href='#' onclick='doThisPage("+i+" )' >"+i+"</a></li>"; 
			}
			item += "<li class='disabled'><span> . . . </span></li>";//2页码后面用...代替部分未显示的页码
			if (pageNo+1 == pageCount) {//当前页+1等于总页码
				for(i = pageNo-1; i <= pageCount; i++){//“...”后面跟三个页码当前页居中显示
					if (i == pageNo) {
						item +=  "<li class='active'><span>"+i+"</span></li>"; 
					}else{
						item += "<li><a href='#' onclick='doThisPage("+i+" )' >"+i+"</a></li>"; 
					}
				}
			}else if (pageNo== pageCount) {//当前页数等于总页数则是最后一页页码显示在最后
				for(i = pageNo-2; i <= pageCount; i++){//...后面跟三个页码当前页居中显示
					if (i == pageNo) {
						item += "<li class='active'><span>"+i+"</span></li>"; 
					}else{
						item += "<li><a href='#' onclick='doThisPage("+i+" )' >"+i+"</a></li>"; 
					}
				}
			}else{//当前页小于总页数，则最后一页后面跟...
				for(i = pageNo-1; i <= pageNo+1; i++){//dqPage+1页后面...
					if (i == pageNo) {
						item += "<li class='active'><span>"+i+"</span></li>"; 
						
					}else{
						item +="<li><a href='#' onclick='doThisPage("+i+" )' >"+i+"</a></li>"; 
					}
				}
				item += "<li class='disabled'><span> . . . </span></li>";
			}
			if(pageNo==pageCount){
				item += '';
			}else{
				item += "<li><a id='nextPage' href='#' aria-label='Next' onclick='doNextPage()'> <span aria-hidden='true'>&raquo;</span></a></li>"; 
			}
			$('#pageBtn').append(item);
			return;
		}
	}
}
/**
 * 分页结束
 */ 




