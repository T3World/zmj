var MODULE_PID = 0;
var MODULE_ID = null;
var t3w = null;
var keyword = null;
var moduleTree1 = null;
var moduleTree2 = null;


$(document).ready(function(){
	loadModuleTreeData();
	doAjax(keyword, 1, 20);
});

/**
 * 加载系统资源数据
 * @returns
 */
function loadModuleTreeData(){
	var setting = {
			data:{
				key:{
					name:'mName',
				},
				simpleData: {
					enable: true,
					idKey:"mId",
					pIdKey:"mPid"
				}
			},
			callback:{
				onClick:onTreeClick
			}
	};
	$.get('/SysBase/Module/listModule',{
		keyword : null,
		pageNo : 1,
		pageSize : 1000
	},function(json){
		console.log(json);
		if(json.Code==2000){
			var data = json.Data.records;
			moduleTree1 = $.fn.zTree.init($("#moduleTree"), setting,data);
			setting.callback.onClick = onInputTreeClick;
			moduleTree2 =$.fn.zTree.init($("#moduleTree2"), setting,data);
			moduleTree1.expandAll(true);
			moduleTree2.expandAll(true);
		}else{
			alert(json.Code);
		}

	},'json');

}

//异步请求分页模块列表
function doAjax(keyword, pageNo, pageSize) {
	this.keyword=keyword;
	$("tableData").empty();
	$.ajax({
		url : '/SysBase/Module/listModule',
		type : "GET",
		data : {
			keyword : keyword,
			pageNo : pageNo,
			pageSize : pageSize
		},
		success : function(result) {
			console.log(result);
			console.log("pageCount:"+result.Data.pageCount);
			//接受数据先处理
			if(doResult(result)){
				t3w = result;
				console.log(t3w);
				page(result.Data);
				loadModuleTableData(result.Data);
			}
		}
	})
}

/**
 * 加载table数据
 * 同时生成分页
 * @param data
 * @returns
 */
function loadModuleTableData(json){
	var data = json.records;
	console.log(json);
	var pageNo = json.pageNo;
	var pageSize = json.pageSize;
	page(json);
	var row='';
	for(var i=0;i<data.length;i++){
		row += '<tr>';
		row += '<td>'+parseInt((pageNo - 1) * pageSize + i + 1)+'</td>';
		if(data[i].mPid!=0){
			row += '<td style="padding-left:5px"> --'+data[i].mName+'</td>';
		}else{
			row += '<td>'+data[i].mName+'</td>';
		}
		row += '<td>'+data[i].mValue+'</td>';
		row += '<td>'+data[i].mUrl+'</td>';
		row += '<td>'+data[i].sortcode+'</td>';
		row += '<td align="center">'+operate(data[i].mId)+'</td>';
		row += '</tr>';
	}
	$('#tableData').html(row);	

}

/**
 * @param moduleId
 * @returns
 */
function operate(moduleId){
	var html = '<a class="gongzuo" href="#" onclick="editModule(this);return false;">编辑</a> ';
	html += ' <a  href="javascript:delModule(\''+moduleId+'\')"  >删除</a>';
	return html;
}

/**
 * 提交表单
 * @returns
 */
function submitModule(){
	//表单验证
	var data = {
			moduleId:MODULE_ID,
			modulePid:MODULE_PID,
			moduleName:$('#moduleName').val(),
			moduleValue:$('#moduleValue').val(),
			moduleURL:$('#moduleURL').val(),
			moduleDes:$('#moduleDes').val(),
			sortCode:$('#SortCode').val()
	};
	if(data.moduleName.length==0){
		alert("模块名称不能为空!");
		return;
	}else if(data.moduleValue.length==0){
		alert("模块值不能为空!");
		return;
	}else if(data.moduleURL.length==0){
		alert("模块不能为空!");
		return;
	}else if(data.moduleDes.length==0){
		alert("模块描述不能为空!");
		return;
	}else if(data.sortCode.length==0){
		alert("排序码不能为空!");
		return;
	};

	//根据subflag选择是新增还是修改
	if($('#subflag').val()==0){
		submitAddModule(data);
		$('#ModuleModal').modal('hide');
	}else{
		submitEditModule(data);
		$('#ModuleModal').modal('hide');
	};
}

/**
 * 添加模块表单提交
 * @returns
 */
function addModule(){
	$('#modulePName').val('');
	$('#moduleName').val('');
	$('#moduleValue').val('');
	$('#moduleURL').val('');
	$('#moduleDes').val('');
	$('#SortCode').val('');
	$('#subflag').val('0');
	$('#ModuleModal').modal('show');
}


/**
 * 添加模块表单提交
 * @returns
 */
function submitAddModule(data){
	var pageNo = t3w.Data.pageNo;
	var pageSize = t3w.Data.pageSize;
	$.post('/SysBase/Module/addModule',{
		mPid:MODULE_PID,
		mName:data.moduleName,
		mValue:data.moduleValue,
		mUrl:data.moduleURL,
		mDes:data.moduleDes,
		sortcode:data.sortCode
	},function(data){
		if(data.Code==2000){
			alert("添加成功!");
            freshMeat();
		}else{
			alert(data.Error_Msg);
		}
	},'json');

}

/**
 * 修改模块
 * @returns
 */
function editModule(obj){
	var pageNo = t3w.Data.pageNo;
	var pageSize = t3w.Data.pageSize;
	var i  =$(obj).parent().parent().children("td").get(0);
	var n = parseInt($(i).html())-1-((pageNo-1)*pageSize);
	//将模块信息回溯在表单
	doEditForm(n);
	$('#subflag').val('1');
	$('#ModuleModal').modal('show');
}
//回溯表单内容
function doEditForm(i){
	var gd = t3w.Data.records[i];
	pname = getPNameByPId(gd.mPid);
	MODULE_PID=gd.mPid;
	MODULE_ID=gd.mId;
	$('#modulePName').val(pname);
	$('#moduleName').val(gd.mName);
	$('#moduleValue').val(gd.mValue);
	$('#moduleURL').val(gd.mUrl);
	$('#moduleDes').val(gd.mDes);
	$('#SortCode').val(gd.sortcode);
}
//提交表单
function submitEditModule(data){
	var pageNo = t3w.Data.pageNo;
	var pageSize = t3w.Data.pageSize;
	$.post('/SysBase/Module/updateModule',{
		mId:MODULE_ID,
		mPid:MODULE_PID,
		mName:data.moduleName,
		mValue:data.moduleValue,
		mUrl:data.moduleURL,
		mDes:data.moduleDes,
		sortcode:data.sortCode
	},function(data){
		if(data.Code==2000){
			alert("修改成功!");
            freshMeat();
		}else{
			alert(data.Error_Msg);
		}
	},'json');
}

//删除模块
function delModule(id){
	var pageNo = t3w.Data.pageNo;
	var pageSize = t3w.Data.pageSize;
	if(confirm('确定要删除吗?')){
		$.get('/SysBase/Module/delModule',{
			mId:id
		},function(result){
			if(result.Code==2000){
				alert("删除成功!");
				doAjax(keyword, pageNo, pageSize);
			}else{
				alert(result.Error_Msg);
			}
		});
	}
}

function onTreeClick(event,treeId,treeNode,clickFlag){
	console.log(treeNode.moduleId);
}

function onInputTreeClick(event,treeId,treeNode,clickFlag){
	$('#modulePName').val(treeNode.mName);
	MODULE_PID = treeNode.mId;
	hideMenu();
}

function showMenu() {
	var dom = $("#modulePName");
	var Offset = $("#modulePName").offset();
	$("#menuContent").css({left:Offset.left + "px", top:Offset.top + dom.outerHeight() + "px"}).slideDown("fast");
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

function getPNameByPId(pid){
	var list = t3w.Data.records;
	var pname = '';
	for(var i =0;i<list.length;i++){
		if(list[i].mId==pid){
			pname=list[i].mName;
		}
	}
	return pname;
}

//向前翻页
function doFrontPage(){
	var pageNo = t3w.Data.pageNo;
	var pageSize = t3w.Data.pageSize;
	if(pageNo!=1){
		pageNo=pageNo-1;
	}
	doAjax(keyword, pageNo, pageSize);
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
	doAjax(keyword, pageNo, pageSize);
}

//根据页码翻页
function doThisPage(pageNo){
	var pageSize = t3w.Data.pageSize;
	doAjax(keyword, pageNo, pageSize);
}

//分页
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

function freshMeat() {
    loadModuleTreeData();
    var pageNo = 1;
    var pageSize = 20;
    if (t3w!=null){
        pageNo = t3w.Data.pageNo;
        pageSize = t3w.Data.pageSize;
    }
    doAjax(keyword, pageNo, pageSize);
}
