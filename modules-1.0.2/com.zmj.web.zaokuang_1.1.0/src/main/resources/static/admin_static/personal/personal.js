var t3w = null;
var ORG_NAME='';
var USER_ID = '';
$(document).ready(function(){
	//从浏览器localStorage中获取用户的id用来显示个人信息
	USER_ID = localStorage.userId;
	loadUserData();
});





function loadUserData(){
	//1、获取登录用户的id,可以从cookie中获取
	//2、根据用户id获取登录用户的信息并写入到表单中
	$.get('/Personal/Profile/getUserInfo',{userId:USER_ID},function(result){
		t3w = result;
		
		if(doResult(result)){
			doEditForm(result.Data);
		}
	},'json');
	
}

//回溯表单内容
function doEditForm(data){
	$('#User_Name').val(data.userName);
	$('#User_Account').val(data.userAccount);
	$('#Org_Name').val(data.orgName);
	$('#User_Job').val(data.userJob);
	$('#User_Tel').val(data.userTel);
}
//处理结果
function doResult(result){
	var code = result.Code;
	var msg = result.Error_Msg;
	if(code!="2000"){
		alert(msg);
		return false;
	}
	return true;
}
//提交表单
function submitForm(){
	
	var userTel = $("#User_Tel").val();
	if(userTel==""){
		alert("联系电话不能为空,请输入联系电话!");
		return ;
	}
	
	$.post('/Personal/Profile/setUserInfo',{
		userId:t3w.Data.userId,
		userTel:userTel
	},function(result){
		console.log(result);
		if(doResult(result)){
			alert("修改成功");
			loadUserData();
		}
	},'json');
}



