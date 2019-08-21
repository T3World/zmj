var ORG_PID = '';
var t3w = null;
var keyword = null;
var xie = "";
var orgTreeData = null;
var orgTree1 = null;
var orgTree2 = null;

$(document).ready(function(){
	initPage();
});
// 初始化页面
function initPage(){
	loadOrgTreeData();
	loadOrgTableData(null,1,20);
	update();
	$('#orgAddBtn').on('click',function(){
		/**
		 * 每次点击添加按钮，都需要清空
		 * @returns
		 */
		 $('#Org_PId').val('');
		 $('#Org_PName').val('');
		 $('#Org_Name').val('');
		 $('#Org_Alias').val('');
		 $('#Org_Info').val('');
		$('#myModal').modal('show');
	});
	$('#saveOrgBtn').on('click',function(){
		//1、获取表单的内容
		var Org_PId = $('#Org_PId').val();
		var Org_Name =  $('#Org_Name').val();
		var Org_Alias =  $('#Org_Alias').val();
		var Org_Info =  $('#Org_Info').val();
		var SortCode =  $('#SortCode').val();
		// 非空校验
		if(Org_Name==""){
			alert("组织机构名称不能为空,请填写组织机构名称，再重新提交");
			return;
		}
		if(Org_Alias==""){
			alert("组织机构别名不能为空,请填写组织机构别名，再重新提交");
			return;
		}
		if(Org_Info==""){
			alert("组织机构简介不能为空,请填写组织机构简介，再重新提交");
			return;
		}
		if(SortCode==""){
			alert("组织机构排序码不能为空,请填写组织机构排序码，再重新提交");
			return;
		}
		$.post('/SysBase/Org/addZZOrgEntity',{
			orgPid:Org_PId,
			orgName:Org_Name,
			orgAlias:Org_Alias,
			orgInfo:Org_Info,
			sortcode:SortCode,
			},function(data){
			if(data.Code=='2000'){
				$('#myModal').modal('hide');
				alert(data.Error_Msg);
				location.reload();
			}else{
				$('#sss').alert();
				alert(data.Error_Msg);
			}
		},'json');
	});
}

// 加载table列表页
function loadOrgTableData(keyword,pageNo,pageSize){
	$.get('/SysBase/Org/getOrgTreeData',{
		keyword:keyword,
		pageNo:pageNo,
		pageSize:pageSize
	},function(json){
		var row = '';
		console.log(json);
		var data = json.Data.records;
		for(var i in data){
			row += '<tr>';
			row += '<td style="display:none" >'+data[i].orgId+'</td>';
			row += '<td>'+parseInt(parseInt(i)+1)+'</td>';
//			if(data[i].orgPid!=0){
//				row += '<td>'+'--'+data[i].orgName+'</td>';
//			}
			if(data[i].orgPid!=0){
				xie = "--";
			}
			row += '<td>'+xie+data[i].orgName+'</td>';
			row += '<td>'+data[i].orgAlias+'</td>';
			row += '<td>'+data[i].orgInfo+'</td>';
			row += '<td>'+data[i].sortcode+'</td>';
			row += '<td>'+caozuo(data[i])+'</td>';
			row += '<td hidden>'+data[i].orgPid+'</td>';
			row += '</tr>';
		}
		// 接收传递过来的参数
		t3w = json;
		console.log(t3w);
		page(json.Data);
		$('#tableData').html(row);
	},'json');
}

//提交搜索ajax
function doSearch(){
	console.log("search!");
	keyword = $("#doSearch").val();
	console.log("keyword: "+keyword);
	loadOrgTableData(keyword,1,11);
}

//根据页码翻页
function doThisPage(pageNo){
	var pageSize = t3w.Data.pageSize;
    loadOrgTableData(keyword,pageNo,pageSize);
}


//向前翻页
function doFrontPage(){
	var pageNo = t3w.Data.pageNo;
	var pageSize = t3w.Data.pageSize;
	if(pageNo!=1){
		pageNo=pageNo-1;
	}
	loadOrgTableData(keyword,pageNo,pageSize);
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
	loadOrgTableData(keyword,pageNo,pageSize);
}

/**
 * 修改页面模态框传值问题
 */
function js_method() {
	var re = '';
	$('#editOrgModal').modal('show');
	$("table tr").click(function(){
		var td = $(this).find("td");
		var orgId = td[0].innerHTML;
		var orgName = td[2].innerHTML;
		var sub = orgName.substring(0,2);
		var orgpname =getPnameFromPid(td[7].innerHTML);
		if(sub=="--"){
			re = orgName.replace("--","");
		}else{
			re = orgName;
		}
		var orgAlias = td[3].innerHTML;
		var orgInfo = td[4].innerHTML;
		var sortcode = td[5].innerHTML;
		$("#Org_Id2").val(orgId);
		$("#Org_PName2").val(orgpname);
		$("#Org_Name2").val(re);
		$('#Org_Alias2').val(orgAlias);
		$('#Org_Info2').val(orgInfo);
		$('#SortCode2').val(sortcode);
	});
}

/**
 * 修改组织结构按钮
 */
function update(){
	$('#updateOrgBtn').on('click',function(){
		//1、获取表单的内容
		var Org_Id2 = $("#Org_Id2").val();
		var Org_Name2 =  $('#Org_Name2').val();
		var Org_Alias2 =  $('#Org_Alias2').val();
		var Org_Info2 =  $('#Org_Info2').val();
		var SortCode2 =  $('#SortCode2').val();
		// 非空校验
		if(Org_Name2==""){
			alert("组织机构名称不能为空,请填写组织机构名称，再重新提交");
			return;
		}
		if(Org_Alias2==""){
			alert("组织机构别名不能为空,请填写组织机构别名，再重新提交");
			return;
		}
		if(Org_Info2==""){
			alert("组织机构简介不能为空,请填写组织机构简介，再重新提交");
			return;
		}
		if(SortCode2==""){
			alert("组织机构排序码不能为空,请填写组织机构排序码，再重新提交");
			return;
		}
		
		$.post('/SysBase/Org/updateZZOrgById',{
			orgId:Org_Id2,
			orgName:Org_Name2,
			orgAlias:Org_Alias2,
			orgInfo:Org_Info2,
			sortcode:SortCode2,
			},function(data){
			if(data.Code=='2000'){
				$('#myModal').modal('hide');
				alert(data.Error_Msg);
				location.reload();
			}else{
				alert(data.Error_Msg);
				location.reload();
			}
		},'json');
	});
}

/**
 * 操作菜单栏
 */
function caozuo(data){
	var html = "";
	if(data.orgPid!=0){
		html += '<a class="gongzuo" href="/Admin/Workface/workface.html?orgId='+data.orgId+'" name="main" title="工作">工作面管理</a>';
	}
		html += '<a data-toggle="modal" data-id="edit" href="#" onclick="js_method()">修改</a>';
		html += '<a  href="javascript:delmethod('+'&#39;'+data.orgId+'&#39;'+');" >删除</a>';
	return html;
}
/**
 * 删除功能
 */
function delmethod(orgId){
	$.post('/SysBase/Org/delZZOrgEntityById',{
		orgId:orgId
		},function(data){
		if(data.Code=='2000'){
			alert(data.Error_Msg);
			location.reload();
		}else{
			alert(data.Error_Msg);
			location.reload();
		}
		
	},'json');
}

/**
 * 搜索框
 */
// function search(){
// 	$.post('',{data},function(data){
//
// 	},'json');
// }

/**
 * 加载组织机构树
 * @returns
 */
function loadOrgTreeData (){
	
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
        orgTreeData = data;
		orgTree1 = $.fn.zTree.init($(".org_tree"), setting,data);
		setting.callback.onClick = onInputTreeClick;
		orgTree2 = $.fn.zTree.init($("#orgTree2"), setting,data);
		orgTree1.expandAll(true);
		orgTree2.expandAll(true);
		
	},'json');
	
	
}

function onTreeClick(event,treeId,treeNode,clickFlag){
	console.log("pid: "+treeNode.orgId);
	
}
function onInputTreeClick(event,treeId,treeNode,clickFlag){
	$('#Org_PName').val(treeNode.orgName);
	$('#Org_PName2').val(treeNode.orgName);
	$('#Org_PId').val(treeNode.orgId);
	$('#Org_PId2').val(treeNode.orgId);
	ORG_PID = treeNode.orgId;
	console.log(ORG_PID);
	hideMenu();
}

function showMenu() {
	var orgPId = $("#Org_PName");
	var orgPIdOffset = $("#Org_PName").offset();
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

function  getPnameFromPid(pid) {
    for (var i in orgTreeData){
       if(orgTreeData[i].orgId == pid) {
           return orgTreeData[i].orgName;
       }
    }
}


