$(document).ready(function(){
	var loginUrl= getLoginUrl();
	var userId = GetQueryString('User_Id');
	var loginTime= new Date().getTime();
	var userIdOld = localStorage.getItem("userId",userId);
	var loginTimeOld = localStorage.getItem("loginTime",loginTime);
	localStorage.setItem("userId",userId);
	if(userId==null){
		localStorage.clear();
		window.location.href=loginUrl;
	}else if(loginTimeOld==null||userIdOld==null){
		initModules(userId);
		localStorage.setItem("loginTime",loginTime);
	}else if(userId==userIdOld){
		if((loginTime-loginTimeOld)>1800000){
			localStorage.clear();
			window.location.href=loginUrl;
		}else{
			initModules(userId);
		}
	}else{
		initModules(userId);
		localStorage.setItem("loginTime",loginTime);
	}

});

function myclose(){
	localStorage.clear();
}
/**
 * 根据用户Id获取所对应的模块资源
 * @param USER_ID
 * @returns
 */
function initModules(userId){
	$.get('/SysBase/User/getUserModule',{userId:userId},function(data){
		if(data.Code==2000){
			createMenu(data.Data);
		}else{
			alert(data.Error_Msg);
		}
	},'json');
}

function createMenu(data){
	var html = "";

	//二级循环
	var subData = data;

	for(var i in data){

		if(data[i].M_PId==0){
			html+='<div class="sBox">\n' +
			'\t\t<div class="subNav sublist-up" id="subNav">\n' +
			'\t\n' +
			'\t<span class="title-icon glyphicon glyphicon-chevron-up"></span>\n' +
			'\t<span class="sublist-title">'+data[i].M_Name+'</span>\n' +
			'\t\n' +
			'</div>';
			html+='<ul class="navContent" style="display: none">';
			for(var j in subData){
				if(subData[j].M_PId == data[i].M_Id){
					html+='<li >\n' +
					'\t <a href="'+subData[j].M_Url+'" target="main">\n' +
					'\t  <span class="sublist-icon" style="padding-left:24px;padding-right:24px;"></span>\n' +
					'\t  <span class="sub-title">'+subData[j].M_Name+'</span>\n' +
					'\t  </a>\n' +
					'\t</li>';
				}

			}
			html+='</ul>';
		}
	}

	$('.subNavBox').html(html);

}
function getLoginUrl(){
	var loginUrl ='';
	$.ajax({
		async:false,
		url:'/Admin/index/getLoginUrl',
		success:function(result){
							loginUrl=result.Data;
					},
		type:'get'
	});
	return loginUrl;
}
function GetQueryString(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); 
	return null;
}