var USER_ID ='';
var old = '';
var newPass ='';
var newAgainPass ='';
$(document).ready(function(){
    //从浏览器localStorage中获取用户的id用来显示个人信息
    USER_ID = localStorage.userId;
});
// 鼠标移除的时候，判断两次密码输入是否一致
function contrast(){
    // 修改密码验证
    newPass = $("#New_PassWord").val();	// 获取新密码
    newAgainPass = $("#New_AgainPassWord").val(); // 再输入一次新密码
    if(newPass!=newAgainPass){
        alert("两次输入密码,不一致,请重新输入");
    }
}

function submitForm(){

    USER_ID = localStorage.userId;
    // 修改密码验证
    old = $("#Old_PassWord").val(); // 获取旧密码
    newPass = $("#New_PassWord").val();	// 获取新密码
    newAgainPass = $("#New_AgainPassWord").val(); // 再输入一次新密码
    if(old==""){
        alert("旧密码不能为空,请输入密码");
        return;
    }
    if(newPass==""){
        alert("新密码不能为空,请输入密码");
        return;
    }
    if(newAgainPass==""){
        alert("再输入一次新密码不能为空,请输入密码");
        return;
    }
//	contrast();
//	console.log(USER_ID);
    if(newPass!=newAgainPass){
        alert("两次输入密码,不一致,请重新输入");
        return ;
    }
    //1、获取登录用户的id,可以从cookie中获取
    //2、根据用户id获取登录用户的信息并写入到表单中
    $.post('/Personal/Reset/resetPassword',{
        userId:USER_ID,
        oldPassword:old,
        userPassword:newAgainPass
    },function(data){
        if(data.Data==2000){
            console.log(userId);
            alert("修改成功");
            location.reload();

        }else{
            alert(data.Error_Msg);
        }
    },'json');
}
