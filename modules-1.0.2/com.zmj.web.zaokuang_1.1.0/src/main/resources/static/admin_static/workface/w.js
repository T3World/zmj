var t3w = null;
var orgId = null;
var keyword = null;
var wId_edit =null;
$(document).ready(function() {
    doOrgTree();
    orgId = GetQueryString('orgId');
    doAjax(null, null, 1, 20);
    createOptions();
});
//向前翻页
function doFrontPage(){
    var pageNo = t3w.Data.pageNo;
    var pageSize = t3w.Data.pageSize;
    if(pageNo!=1){
        pageNo=pageNo-1;
    }
    doAjax(orgId,keyword,pageNo,pageSize);
}

//向后翻页
function doNextPage(){
    var pageNo = t3w.Data.pageNo;
    var pageSize = t3w.Data.pageSize;
    var pageCount = t3w.Data.pageCount;
    if(pageNo!=pageCount){
        pageNo=pageNo+1;
    }
    doAjax(orgId,keyword,pageNo,pageSize);
}

//根据页码翻页
function doThisPage(pageNo){
    var pageSize = t3w.Data.pageSize;
    doAjax(orgId,keyword,pageNo,pageSize);
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

//将数据展示成表格
function listWorkface(data) {
    $("#tbody").html("");
    var pageNo = data.pageNo;
    var pageSize = data.pageSize;
    var records = data.records;
    //展开分页按钮
    var row='';
    for(var i=0;i<records.length;i++){
        row += '<tr ondblclick="showWorkfaceInfoModal(this)">';
        row += '<td>'+parseInt((pageNo - 1) * pageSize + i + 1)+'</td>';
        row += '<td>'+records[i].workfaceName+'</td>';
        row += '<td>'+records[i].workfaceAlias+'</td>';
        row += '<td>'+records[i].orgName+'</td>';
        row += '<td>'+doState(records[i].workfaceState)+'</td>';
        row += '<td align="center">'+doType(records[i].workfaceType)+'</td>';
        row += '<td align="center">'+records[i].sortcode+'</td>';
        row += '<td align="center"><a href="#" onclick="deviceManage(this)" >设备</a>    <a href="#" onclick="editWorkface(this)" >编辑</a>      <a href="#" onclick="delWorkface(this)">删除</a>   <a href="#" onclick="stopWorkface(this)">启停</a></td>';
        row += '</tr>';
    }
    $('#tbody').html(row);
}


//在id=id的select元素下生成公司下拉单
function listOrg(id,orgTree){
    var item = "";
    for(var i = 0;i<orgTree.length;i++){
        if(orgTree[i].orgPid==0){
            continue;
        }
        item+= "<option value='"+orgTree[i].orgId+"'>"+orgTree[i].orgName+"</option>"
    }
    $("#"+id).empty();
    $("#"+id).append(item);
}


//提交搜索ajax
function doSearch(){
    var keyword = $("#doSearch").val();
    doAjax(orgId, keyword, 1, 11);
}

//提交并检验
function addWorkface(){
    var v1 = $("#orgList option:selected").val();
    var v2 = $("#workfaceName").val();
    var v3 =$("#workfaceAlias").val();
    var v4 = $('input[name="workfaceType"]:checked').val();
    var v5 = $("#supportCount").val()
    var v6 = $('input[name="supportDir"]:checked').val();
    var v7 = $('input[name="conveyorDir"]:checked').val();
    var v8 = $("#shearerposCachetime option:selected").val();
    var v9 = $('input[name="beltType"]:checked').val();
    var v10 = $("#fontMinpressure").val();
    var v11 = $("#fontMaxpressure").val();
    var v12 = $("#backMinpressure").val();
    var v13 = $("#backMaxpressure").val();
    var v14 = $("#workfaceLength").val();
    var v15 = $("#workfaceAbleLength").val();
    var v16 = $("#workfaceCoalThickness").val();
    var v17 = $("#workfaceInclination").val();
    var v18 = $("#workfaceAverageHeight").val();
    var v19 = $("#workfaceStartTime").val();
    var v20 = null;
    var v21 = $('#sortcode').val();
    var v22 = $('#morningShiftHourS').val();
    var v23 = $('#morningShiftMiniteS').val();
    var v24 = $('#morningShiftHourE').val();
    var v25 = $('#morningShiftMiniteE').val();
    var v26 = $('#afternoonShiftHourS').val();
    var v27 = $('#afternoonShiftMiniteS').val();
    var v28 = $('#afternoonShiftHourE').val();
    var v29 = $('#afternoonShiftMiniteE').val();
    var v30 = $('#nightShiftHourS').val();
    var v31 = $('#nightShiftMiniteS').val();
    var v32 = $('#nightShiftHourE').val();
    var v33 = $('#nightShiftMiniteE').val();
    var arr = $('input[name="pressureCharttype"]');
    if(arr.length==0){
        alert("参数不能为空!");
        return;
    }else if(arr.length==1){
        v20 = $('input[name="pressureCharttype"]:checked').val();
    }else{
        v20="11";
    }
    if(v2.length==0){
        alert("工作面名称不能为空!");
        return;
    }else if(v3.length==0){
        alert("工作面别名不能为空!");
        return;
    }else if(v5.length==0){
        alert("支架数量不能为空!");
        return;
    }else if(v10.length==0){
        alert("前柱撑力最小值不能为空!");
        return;
    }else if(v11.length==0){
        alert("前柱撑力最大值不能为空!");
        return;
    }else if(v14.length==0){
        alert("工作面长度不能为空!");
        return;
    }else if(v15.length==0){
        alert("工作面可采走向长度不能为空!");
        return;
    }else if(v16.length==0){
        alert("煤层厚度不能为空!");
        return;
    }else if(v17.length==0){
        alert("工作面倾角不能为空!");
        return;
    }else if(v18.length==0){
        alert("平均采高不能为空!");
        return;
    }else if(v19.length==0){
        alert("工作面开始时间不能为空!");
        return;
    }else if(v21.length==0){
        alert("排序码不能为空!");
        return;
        // }else if(v22<v24 ||v23<v25||v26<v28||v27<v29||v30<v32||v31<v33){
        //     alert("排班结束时间不能早于开始时间!");
        //     return;
        // }else if(parseInt(v22)>parseInt(v24) ||parseInt(v23)>parseInt(v25)||parseInt(v26)>parseInt(v28)||parseInt(v27)>parseInt(v29)||parseInt(v30)>parseInt(v32)||parseInt(v31)>parseInt(v33)){
        //     alert("排班结束时间不能早于开始时间!");
        //     return;
    }
    var fd={
        orgId:v1,
        workfaceName:v2,
        workfaceAlias:v3,
        workfaceType:v4,
        supportCount:v5,
        supportDir:v6,
        conveyorDir:v7,
        shearerposCachetime:v8,
        beltType:v9,
        fontMinpressure:v10,
        fontMaxpressure:v11,
        backMinpressure:v12,
        backMaxpressure:v13,
        workfaceLength:v14,
        workfaceAbleLength:v15,
        workfaceCoalThickness:v16,
        workfaceInclination:v17,
        workfaceAverageHeight:v18,
        workfaceStartTime:v19,
        pressureCharttype:v20,
        sortcode:v21,
        morningShift:formShiftTime(v22,v23,v24,v25),
        afternoonShift:formShiftTime(v26,v27,v28,v29),
        nightShift:formShiftTime(v30,v31,v32,v33)
    };
    $.post("/SysBase/workface/addZZWorkface",fd,function(result){
        if(doResult(result)){
            alert("添加成功!");
            console.log('hide');
            $('#addModal').modal('hide');
            doAjax(orgId,keyword,t3w.Data.pageNo,t3w.Data.pageSize);
        }
    });

}

function editWorkfaceSubmit(){
    var v1 = $("#orgList_edit option:selected").val();
    var v2 = $("#workfaceName_edit").val();
    var v3 =$("#workfaceAlias_edit").val();
    var v4 = $('input[name="workfaceType_edit"]:checked').val();
    var v5 = $("#supportCount_edit").val()
    var v6 = $('input[name="supportDir_edit"]:checked').val();
    var v7 = $('input[name="conveyorDir_edit"]:checked').val();
    var v8 = $("#shearerposCachetime_edit option:selected").val();
    var v9 = $('input[name="beltType_edit"]:checked').val();
    var v10 = $("#fontMinpressure_edit").val();
    var v11 = $("#fontMaxpressure_edit").val();
    var v12 = $("#backMinpressure_edit").val();
    var v13 = $("#backMaxpressure_edit").val();
    var v14 = $("#workfaceLength_edit").val();
    var v15 = $("#workfaceAbleLength_edit").val();
    var v16 = $("#workfaceCoalThickness_edit").val();
    var v17 = $("#workfaceInclination_edit").val();
    var v18 = $("#workfaceAverageHeight_edit").val();
    var v19 = $("#workfaceStartTime_edit").val();
    var v20 = null;
    var v21 = $("#sortcode_edit").val();
    var v22 = $('#morningShiftHourS_edit').val();
    var v23 = $('#morningShiftMiniteS_edit').val();
    var v24 = $('#morningShiftHourE_edit').val();
    var v25 = $('#morningShiftMiniteE_edit').val();
    var v26 = $('#afternoonShiftHourS_edit').val();
    var v27 = $('#afternoonShiftMiniteS_edit').val();
    var v28 = $('#afternoonShiftHourE_edit').val();
    var v29 = $('#afternoonShiftMiniteE_edit').val();
    var v30 = $('#nightShiftHourS_edit').val();
    var v31 = $('#nightShiftMiniteS_edit').val();
    var v32 = $('#nightShiftHourE_edit').val();
    var v33 = $('#nightShiftMiniteE_edit').val();
    var arr = $('input[name="pressureCharttype_edit"]:checked');
    if(arr.length==1){
        v20 = $('input[name="pressureCharttype_edit"]:checked').val();
    }else{
        v20="11";
    }

    if(v2.length==0){
        alert("工作面名称不能为空!");
        return;
    }else if(v3.length==0){
        alert("工作面别名不能为空!");
        return;
    }else if(v5.length==0){
        alert("支架数量不能为空!");
        return;
    }else if(v10.length==0){
        alert("前柱撑力最小值不能为空!");
        return;
    }else if(v11.length==0){
        alert("前柱撑力最大值不能为空!");
        return;
    }else if(v14.length==0){
        alert("工作面长度不能为空!");
        return;
    }else if(v15.length==0){
        alert("工作面可采走向长度不能为空!");
        return;
    }else if(v16.length==0){
        alert("煤层厚度不能为空!");
        return;
    }else if(v17.length==0){
        alert("工作面倾角不能为空!");
        return;
    }else if(v18.length==0){
        alert("平均采高不能为空!");
        return;
    }else if(v19.length==0){
        alert("工作面开始时间不能为空!");
        return;
    }else if(v21.length==0){
        alert("排序码不能为空!");
        return;
    }
    // else if(parseInt(v22)>parseInt(v24) ||parseInt(v23)>parseInt(v25)||parseInt(v26)>parseInt(v28)||parseInt(v27)>parseInt(v29)||parseInt(v30)>parseInt(v32)||parseInt(v31)>parseInt(v33)){
    //     alert("排班结束时间不能早于开始时间!");
    //     return;
    // }
    var fd={
        workfaceId:wId_edit,
        orgId:v1,
        workfaceName:v2,
        workfaceAlias:v3,
        workfaceType:v4,
        supportCount:v5,
        supportDir:v6,
        conveyorDir:v7,
        shearerposCachetime:v8,
        beltType:v9,
        fontMinpressure:v10,
        fontMaxpressure:v11,
        backMinpressure:v12,
        backMaxpressure:v13,
        workfaceLength:v14,
        workfaceAbleLength:v15,
        workfaceCoalThickness:v16,
        workfaceInclination:v17,
        workfaceAverageHeight:v18,
        workfaceStartTime:v19,
        pressureCharttype:v20,
        sortcode:v21,
        morningShift:formShiftTime(v22,v23,v24,v25),
        afternoonShift:formShiftTime(v26,v27,v28,v29),
        nightShift:formShiftTime(v30,v31,v32,v33)
    };
    $.post("/SysBase/workface/update",fd,function(result){
        if(doResult(result)){
            alert("修改成功!");
            $('#editModal').modal('hide');
            doAjax(orgId,keyword,t3w.Data.pageNo,t3w.Data.pageSize);
        }
    });

}

//展示添加表单,并初始化默认值
function showAddModel() {
    if (orgId!=null){
        $("#orgList").val(orgId);
    }
    $('#addModal').modal('show');
}

//编辑工作面信息在表单回溯
function editWorkface(obj) {
    var pageNo = t3w.Data.pageNo;
    var pageSize = t3w.Data.pageSize;
    var n = $(obj).parent().parent().children("td").get(0);
    var i= parseInt($(n).html())-((pageNo-1)*pageSize)-1;
    var wd = t3w.Data.records[i];
    wId_edit = wd.workfaceId;
    var wconf = wd.zzWorkfaceconfigEntity;
    $("#orgList_edit").val(wd.orgId);
    $("#workfaceName_edit").val(wd.workfaceName);
    $("#workfaceAlias_edit").val(wd.workfaceAlias);
    $("#supportCount_edit").val(wconf.supportCount);
    $("#fontMinpressure_edit").val(wconf.fontMinpressure)	;
    $("#fontMaxpressure_edit").val(wconf.fontMaxpressure);
    $("#backMinpressure_edit").val(wconf.backMinpressure);
    $("#backMaxpressure_edit").val(wconf.backMaxpressure);
    $("#shearerposCachetime_edit").val(wconf.shearerposCachetime);
    $("#workfaceLength_edit").val(wconf.workfaceLength);
    $("#workfaceAbleLength_edit").val(wconf.workfaceAbleLength);
    $("#workfaceCoalThickness_edit").val(wconf.workfaceCoalThickness);
    $("#workfaceInclination_edit").val(wconf.workfaceInclination);
    $("#workfaceAverageHeight_edit").val(wconf.workfaceAverageHeight);
    $("#workfaceStartTime_edit").val(wconf.workfaceStartTime);
    $("#sortcode_edit").val(wd.sortcode);
    $('#morningShiftHourS_edit').val(getShiftHourS(wconf.morningShift));
    $('#morningShiftMiniteS_edit').val(getShiftMinS(wconf.morningShift));
    $('#morningShiftHourE_edit').val(getShiftHourE(wconf.morningShift));
    $('#morningShiftMiniteE_edit').val(getShiftMinE(wconf.morningShift));
    $('#afternoonShiftHourS_edit').val(getShiftHourS(wconf.afternoonShift));
    $('#afternoonShiftMiniteS_edit').val(getShiftMinS(wconf.afternoonShift));
    $('#afternoonShiftHourE_edit').val(getShiftHourE(wconf.afternoonShift));
    $('#afternoonShiftMiniteE_edit').val(getShiftMinE(wconf.afternoonShift));
    $('#nightShiftHourS_edit').val(getShiftHourS(wconf.nightShift));
    $('#nightShiftMiniteS_edit').val(getShiftMinS(wconf.nightShift));
    $('#nightShiftHourE_edit').val(getShiftHourE(wconf.nightShift));
    $('#nightShiftMiniteE_edit').val(getShiftMinE(wconf.nightShift));
    if(wd.workfaceType=="0"){
        selectRadio("workfaceType_edit","workfaceType2_edit");
    }
    if(wconf.supportDir=="1"){
        selectRadio("supportDir_edit","supportDir2_edit");
    }
    if(wconf.conveyorDir=="0"){
        selectRadio("conveyorDir_edit","conveyorDir2_edit");
    }
    if(wconf.beltType=="1"){
        selectRadio("beltType_edit","beltType2_edit");
    }
    $("#workfaceType2_edit").attr("checked");
    if(wconf.pressureCharttype=="01"){
        $("#inlineCheckbox1_edit").attr("checked",false);
        $("#inlineCheckbox2_edit").attr("checked",true);
    }else if(wconf.pressureCharttype=="11"){
        $("#inlineCheckbox1_edit").attr("checked",true);
        $("#inlineCheckbox2_edit").attr("checked",true);
    }
    $('#editModal').modal('show');
}

function selectRadio(name,id){
    $("input[name="+name+"]").each(function(){
        $(this).prop("checked",false);
    });
    $("#"+id).prop("checked",true);
}

//删除
function delWorkface(obj){
    var pageNo = t3w.Data.pageNo;
    var pageSize = t3w.Data.pageSize;
    var n = $(obj).parent().parent().children("td").get(0);
    var i= parseInt($(n).html())-((pageNo-1)*pageSize)-1;
    var wid = t3w.Data.records[i].workfaceId;
    if(confirm('确定要删除该工作面吗?')){
        $.post("/SysBase/workface/deleteWorkface",{
            workfaceId : wid
        },function(result){
            doResult(result);
            alert("删除成功!");
            doAjax(orgId,keyword,t3w.Data.pageNo,t3w.Data.pageSize);
        })
    }

}

//启停
function stopWorkface(obj) {
    var pageNo = t3w.Data.pageNo;
    var pageSize = t3w.Data.pageSize;
    var n = $(obj).parent().parent().children("td").get(0);
    var i= parseInt($(n).html())-((pageNo-1)*pageSize)-1;
    var records = t3w.Data.records;
    var wid = records[i].workfaceId;
    $.post("/SysBase/workface/stopWorkface", {
        workfaceId : wid,
        workfaceState : records[i].workfaceState
    }, function(result) {
        alert(result.Error_Msg);
        doAjax();
    })
}

function doState(state) {
    if (state == 0) {
        return "停用";
    } else {
        return "启用";
    }
}

function doType(type) {
    if (type == 0) {
        return "自动化工作面";
    } else {
        return "智能化工作面";
    }
}

//异步请求工作面列表
function doAjax(orgId, keyword, pageNo, pageSize) {
    this.orgId=orgId;
    this.keyword=keyword;
    $("tbody").empty();
    $.ajax({
        url : "/SysBase/workface/listWorkface",
        type : "POST",
        data : {
            orgId : orgId,
            keyword : keyword,
            pageNo : pageNo,
            pageSize : pageSize
        },
        success : function(result) {
            //接受数据先处理
            doResult(result);
            t3w = result;
            console.log(t3w);
            page(result.Data);
            listWorkface(result.Data);
        }
    })
}

//得到Orgtree
function doOrgTree(){
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
        callback: {
            onClick: zTreeOnClick
        }
    };
    $.post('/SysBase/Org/getOrgTreeData',null,function(result){
        if(result.Code==2000){
            var zNodes = result.Data.records;
            zTreeObj = $.fn.zTree.init($('#orgTree'), setting, zNodes);

            //给工作面所属的公司初始化
            listOrg("orgList",zNodes);
            listOrg("orgList_edit",zNodes);

            zTreeObj.expandAll(true);

        }else{
            console.log('没有数据');
        }

    });
};

//ztree点击事件
function zTreeOnClick(event, treeId, treeNode, clickFlag) {
    if(treeNode.isParent){
        doAjax(null, null, 1,20);
    }else{
        doAjax(treeNode.orgId, null, 1, 20);
    }
}

//处理结果弹框提示错误信息
function doResult(result){
    var code = result.Code;
    var msg = result.Error_Msg;
    if(code=="2000"){
        return true;
    }else {
        alert(msg);
        return false;
    }
}

//从地址栏截取参数
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
}

//创建select下拉菜单
function createOptions(){
    var t = '';
    for(var i =1;i<25;i++){
        t+='<option value=\"'+3600*i+'\">'+i+'小时</option>';
    }
    $('#shearerposCachetime').empty();
    $('#shearerposCachetime_edit').empty();
    $('#shearerposCachetime').html(t);
    $('#shearerposCachetime_edit').html(t);
}

function deviceManage(obj){
    var data = t3w.Data;
    var pageNo = data.pageNo;
    var pageSize = data.pageSize;
    var n = $(obj).parent().parent().children("td").get(0);
    var i= parseInt($(n).html())-((pageNo-1)*pageSize)-1;
    //获取工作面ID
    var wid = data.records[i].workfaceId;
    var wAlias = data.records[i].workfaceAlias;
    //设备管理
    window.location.href="./devicemanage.html?wId="+wid+"&wAlias="+wAlias;
}

/**
 * 弹出设备累计表,并显示信息
 * */
function showWorkfaceInfoModal(obj) {
    var i = $(obj).children().get(0).innerHTML-1;
    var wf = t3w.Data.records[i];
    var wid = wf.workfaceId;
    $.post('/SysBase/WorkfaceAccumulative/selectWorkfaceAccumuLativeByWorkfaceId',{
        workfaceId:wid
    },function (result) {
        if (doResult(result)){
            $('#workfaceRunningTime').val(result.Data.workfaceRunningTime);
            $('#workfaceCycleNumber').val(result.Data.workfaceCycleNumber);
            $('#workfaceAccuPropulsion').val(result.Data.workfaceAccuPropulsion);
            $('#workfaceExcessCoal').val(result.Data.workfaceExcessCoal);
            $('#workfaceAccumulateInfoModalTitle').val(wf.workfaceName+'-运行累计');
            $('#workfaceAccumulateInfoModal').modal('show');
        }
    });
    //初始化changeFlag
    $('#workfaceAccumulateInfoFlag').val(0);
    $('#workfaceAccumulateInfoId').val(wid);

}

function commitWorkfaceAccumulateInfo(){
    if ($('#workfaceAccumulateInfoFlag').val() == 1){
        var data = {
            workfaceId:$('#workfaceAccumulateInfoId').val(),
            workfaceRunningTime:$('#workfaceRunningTime').val(),
            workfaceAccuPropulsion:$('#workfaceAccuPropulsion').val(),
            workfaceExcessCoal:$('#workfaceExcessCoal').val(),
            workfaceCycleNumber:$('#workfaceCycleNumber').val()
        };
        $.post('/SysBase/WorkfaceAccumulative/updateZZWofkfaceAccumulative',data,function (result) {
            if (doResult(result)){
                alert('工作面累计信息保存成功!');
                $('#workfaceAccumulateInfoModal').modal('hide');
            }
        })
    }else {
        $('#workfaceAccumulateInfoModal').modal('hide');
    }
}
function changeFlag() {
    $('#workfaceAccumulateInfoFlag').val(1);
}

function formShiftTime(HH1,mm1,HH2,mm2){
    return HH1+':'+mm1+'-'+HH2+':'+mm2;
}


function getShiftHourS(HHMMHHMM){
    if (HHMMHHMM == null){
        return '00:00-00:00';
    }
    var split = HHMMHHMM.split("-");
    if (split.length>0){
        var strings = split[0].split(":");
        return strings[0];
    }else{
        return '00';
    }
}
function getShiftMinS(HHMMHHMM){
    if (HHMMHHMM == null){
        return '00:00-00:00';
    }
    var split = HHMMHHMM.split("-");
    if (split.length>0){
        var strings = split[0].split(":");
        if (strings.length>1){
            return strings[1];
        }
    }
    return '00';
}
function getShiftHourE(HHMMHHMM){
    if (HHMMHHMM == null){
        return '00:00-00:00';
    }
    var split = HHMMHHMM.split("-");
    if (split.length>1){
        var strings = split[1].split(":");
        return strings[0];
    }
    return '00';
}
function getShiftMinE(HHMMHHMM){
    if (HHMMHHMM == null){
        return '00:00-00:00';
    }
    var split = HHMMHHMM.split("-");
    if (split.length>1){
        var strings = split[1].split(":");
        if (strings.length>1){
            return strings[1];
        }
    }
    return '00';
}