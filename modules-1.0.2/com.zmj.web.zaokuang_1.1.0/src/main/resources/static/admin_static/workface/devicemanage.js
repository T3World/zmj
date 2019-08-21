var t3w=null;
//当前页
var pageNo = 1;
//页面显示的条数
var pageSize = 20;
//总页数
var pageCount = null;
//关键字
var keyword ='';
//总条数
var count = null;
var deviceTypeId = '';
var deviceTypeList = null;
var wid = '';
var wAlias = '';
var lock = 0;
var lockNum = 2;

$(document).ready(function(){
    //初始化
    wid = GetQueryString('wId');
    wAlias = GetQueryString('wAlias');
    //绘制title
    printTitle();
    //加载页面数据
    loadData();
});

//加载页面数据
function loadData(){
    doAjax(deviceTypeId,wid,pageNo,pageSize);
    listDeviceTypeSelect();
}

//生成操作的html
function operate(){
    // var   html = '<a  href="javascript: void(0);" onclick="propertyManage(this)"> 属性 </a>';
    var   html = '';
    html += '<a  href="javascript: void(0);" onclick="editmethod(this)"> 编辑 </a>';
    html += '<a  href="javascript: void(0);" onclick="deleteMethod(this)"> 删除 </a>';
    return html;
}

//绘制表格内容
function printTableBody(data){
    var row = '';
    for(var i = 0;i<data.length;i++){
        row += '<tr ondblclick="showDeviceAccumulativeModal(this)" >';
        row += '<td align="center" >'+'<input type="checkbox" class="workfaceDeviceCheckBox workfaceDeviceCheckBoxSon" onchange="doCheckBox()">'+'</td>';
        row += '<td align="center">'+parseInt((pageNo - 1) * pageSize + i + 1)+'</td>';
        row += '<td align="center" hidden>'+data[i].id+'</td>';
        row += '<td align="center">'+getDeviceTypeNameById(data[i].deviceTypeId)+'</td>';
        row += '<td align="center">'+data[i].deviceName+'</td>';
        row += '<td align="center">'+data[i].deviceModel+'</td>';
        row += '<td align="center">'+data[i].deviceCount+'</td>';
        row += '<td align="center">'+parseDeviceCodes(data[i].deviceCodes)+'</td>';
        row += '<td align="center">'+data[i].sortCode+'</td>';
        row += '<td align="center">'+data[i].updateTime+'</td>';
        row += '<td align="center">'+operate(data[i].deviceId)+'</td>';
        row += '</tr>';
    }
    $('#tableData').html(row);
}


/**
 * 显示添加表单
 */
function addmethod(){

    listSelect('deviceTypeId',deviceTypeList);
    listDeviceNameSelect();
    $('#deviceTypeId').val('');
    $('#deviceId').val('');
    $('#deviceCount').val('');
    $('#deviceCodes').val('');
    $('#SortCode').val('');
    $('#subflag').val('0');
    $('#MyOnlyModal').modal('show');
}

//显示编辑表单
function editmethod(obj){
    var pageNo = t3w.Data.pageNo;
    var pageSize = t3w.Data.pageSize;
    var n = $(obj).parent().parent().children("td").get(1).innerHTML;
    console.log(n);
    var i= n-((pageNo-1)*pageSize)-1;
    console.log("i: " + i);
    //将模块信息回溯在表单
    listSelect('deviceTypeId',deviceTypeList);
    listDeviceNameSelect();
    doEditForm(i);
    $('#subflag').val('1');
    $('#MyOnlyModal').modal('show');
}
//回溯表单内容
function doEditForm(i){
    var rd = t3w.Data.records[i];
    console.log(t3w);
    console.log(rd);
    $('#deviceTypeId').val(rd.deviceTypeId);
    $('#deviceId').val(rd.deviceId);
    $('#deviceCount').val(rd.deviceCount);
    $('#deviceCodes').val(parseDeviceCodes(rd.deviceCodes));
    $('#SortCode').val(rd.sortCode);
}

/**
 * 提交表单
 */
function submitModal(){
    //表单验证
    var data = {
        workfaceId:wid,
        deviceTypeId:$('#deviceTypeId').val(),
        deviceId:$('#deviceId').val(),
        deviceCount: $('#deviceCount').val(),
        deviceCodes: formatDeviceCodes($('#deviceCodes').val()),
        sortCode:$('#SortCode').val()
    };
    if(data.deviceTypeId.length==0){
        alert("设备类型不能为空!");
        return;
    }else if(data.deviceId.length==0){
        alert("设备不能为空!");
        return;
    }else if(data.deviceCount.length==0){
        alert("设备数量不能为空!");
        return;
    }else if(data.deviceCodes.length==0){
        alert("设备编码不能为空!");
        return;
    }else if(data.sortCode.length==0){
        alert("排序码不能为空!");
        return;
    }

    //根据subflag选择是新增还是修改
    if($('#subflag').val()==0){
        submitAdd(data);
        $('#MyOnlyModal').modal('hide');
    }else{
        submitEdit(data);
        $('#MyOnlyModal').modal('hide');
    }
}

//提交编辑表单
function submitEdit(data){
    $.post('/SysBase/WorkfaceDevice/updateZZWorkfaceDevice',data,function(data){
        if(data.Code==2000){
            alert('修改成功!');
            page(data.Data);
            printTableBody(data.Data);
        }else{
            alert(data.Error_Msg);
        }
    },'json');
}

//提交添加表单
function submitAdd(data){
    $.post('/SysBase/WorkfaceDevice/addZZWorkfaceDevice',data,function(result){
        if(doResult(result)){
            alert('添加成功!');
            freshMeat();
        }
    },'json');
}
//删除模块
function deleteMethod(obj){
    var id = $(obj).parent().parent().children("td").get(2).innerHTML;
    if(confirm('确定要删除吗?')){
        $.post('/SysBase/WorkfaceDevice/delZZWorkfaceDevice',{
            id:id
        },function(result){
            if(doResult(result)){
                alert('删除成功!');
                freshMeat();
            }
        });
    }
}
//发起ajax请求,分页
function doAjax(deviceTypeId,workfaceId,pageNo, pageSize) {
    $("tbody").empty();
    $.ajax({
        url : "/SysBase/WorkfaceDevice/listWorkfaceDeviceByPage",
        type : "POST",
        data : {
            deviceTypeId : deviceTypeId,
            workfaceId:workfaceId,
            pageNo : pageNo,
            pageSize : pageSize
        },
        success : function(result) {
            //接受数据先处理
            doResult(result);
            t3w = result;
            console.log(t3w);
            page(result.Data);
            doLock();
        }
    })
}

//处理结果弹框提示错误信息
function doResult(result){
    var code = result.Code;
    var msg = result.Error_Msg;
    if(code!="2000"){
        alert(msg);
        return false;
    }
    return true;
}

//向前翻页
function doFrontPage(){
    // var pageNo = t3w.Data.pageNo;
    // var pageSize = t3w.Data.pageSize;
    if(pageNo!=1){
        pageNo=pageNo-1;
    }
    lockPartner();
    doAjax(deviceTypeId,wid,pageNo,pageSize);
}

//向后翻页
function doNextPage(){
    // var pageNo = t3w.Data.pageNo;
    // var pageSize = t3w.Data.pageSize;
    // var pageCount = t3w.Data.pageCount;
    if(pageNo!=pageCount){
        pageNo=pageNo+1;
    }
    lockPartner();
    doAjax(deviceTypeId,wid,pageNo,pageSize);
}

//根据页码翻页
function doThisPage(pageNo){
    // var pageSize = t3w.Data.pageSize;
    lockPartner();
    doAjax(deviceTypeId,wid,pageNo,pageSize);
}

//分页
function page(data){
    console.log("paging!!!");
    $('#pageBtn').empty();
    //当前页
    pageNo = data.pageNo;
    console.log(pageNo);
    //页面显示的条数
    pageSize = data.pageSize;
    //总页数
    pageCount=data.pageCount;
    //总条数
    count = data.rowCount;
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
 * 全选框
 * */
function selectAllWorkfaceDevice(dom) {
    var isChecked = $(dom).prop('checked');
    if (isChecked){
        $('.workfaceDeviceCheckBox').prop('checked',true);
    } else {
        $('.workfaceDeviceCheckBox').prop('checked',false);
    }
}

/**
 * 删除选中的设备种类
 * */
function delChecked() {
    var ids = [];
    $('.workfaceDeviceCheckBoxSon').each(function (i) {
        if ($(this).prop('checked')){
            console.log($(this).parent().nextAll());
            ids.push($(this).parent().nextAll()[1].innerHTML);
        }
    });
    console.log(ids.toString());
    if (ids.toString() === ''){
        alert('没有选中的元素');
    }else{
        batDel(ids.toString());
    }
}



function batDel(ids){
    var url = '/SysBase/WorkfaceDevice/batDelZZWorkfaceDevice';
    if (confirm('确定要删除选中的设备属性吗?')){
        $.post(url,{
            ids:ids
        },function (result) {
            if (doResult(result)) {
                alert('删除成功!');
            }
            freshMeat();
        })
    }
}


/**
 * 刷新页面
 * */
function freshMeat(){
    doAjax(deviceTypeId,wid,pageNo,pageSize);
    listDeviceTypeSelect(deviceTypeId);
}

/**
 * 获取设备类型列表
 * */
function getDeviceTypeList(){
    var dt = null;
    var url = 'selectDeviceTypeAll';
    $.get(url, function (result) {
        doResult(result);
        deviceTypeList = result.Data;
        dt = result.Data;
    });
    return dt;
}

//在id=id的select元素下生成下拉单
function listSelect(id,list,selectId){
    var item = "<option value='' selected>"+'请选择'+"</option>";
    for(var i in list){
        item+= "<option value='"+list[i].deviceTypeId+"'>"+list[i].deviceTypeName+"</option>";
    }
    var $list = $("#"+id);
    $list.empty();
    $list.append(item);
    if (selectId != null) {
        return;
    } else {
        $('#deviceTypeSelect').val(selectId);
    }

}

function getDeviceTypeNameById(dtId){
    for(var i in deviceTypeList){
        if (dtId === deviceTypeList[i].deviceTypeId){
            return deviceTypeList[i].deviceTypeName;
        }
    }
    return '';
}

function selectDeviceType(){
    deviceTypeId = $('#deviceTypeSelect').val();
    doAjax(deviceTypeId,wid,pageNo,pageSize);
    lockPartner();
}

function propertyManage(){
    alert('属性管理');
}

/**
 * 获取设备类型列表
 * */
function listDeviceTypeSelect(deviceTypeId){
    $.ajax({
        url : '/SysBase/DeviceType/listDevicePage',
        type : "POST",
        data : {
            keyword: '',
            pageNo : 1,
            pageSize : 20
        }, success : function (result) {
            if (doResult(result)) {
                deviceTypeList = result.Data.records;
                console.log(deviceTypeList);
                listSelect('deviceTypeSelect',deviceTypeList,deviceTypeId);
                doLock();
            }
        }, error:function (r) {
            doLock();
        }
    })
}

function listDeviceNameSelect(){
    $.ajax({
        url : '/SysBase/Device/deviceTypeId',
        type : "POST",
        data : {
            deviceTypeId: $('#deviceTypeId').val(),
        }, success : function (result) {
            var deviceList = result.Data;
            console.log(deviceList);
            var item = '';
            for(var i in deviceList){
                item+= "<option value='"+deviceList[i].deviceId+"'>"+deviceList[i].deviceName+"</option>";
            }
            $('#deviceId').empty();
            $('#deviceId').append(item);
        }
    });
}
/**
 * 同步小锁
 * */
function doLock(){
    lock = lock + 1;
    if (lock == lockNum){
        printTableBody(t3w.Data.records);
        lock = 0;
    }
}

function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]);
    return null;
}

/**
 * 将1,2,3,4,5变成1-5
 * */
function parseDeviceCodes(codes){
    var split = codes.split(",");
    var result = '';
    var s = '';
    var e = '';
    if (split.length<1){
        return '';
    } else {
        s = split[0];
        for(var i=1; i<= split.length;i++){
            if (s === ''){
                s = split[i-1];
            }else {
                e = split[i-1];
            }

            if (i === split.length || split[i]-split[i-1] != 1) {
                if (e === ''){
                    result += s + ',';
                } else {
                    if (i===1){
                        result += split[0] + ',';
                    }else {
                        result += s + '-' + e + ',';
                    }
                }
                s = '';
                e = '';
            }
        }
        return result.substr(0,result.length-1);
    }
}

/**
 * 将1-5变成1,2,3,4,5
 * */
function formatDeviceCodes(codes) {
    var regExp = new RegExp('(\\d)+\\-(\\d)+');
    var split = codes.split(',');
    for(var i = 0 ; i<split.length ; i++){
        if (regExp.test(split[i])){
            var reg = split[i];
            var strings = reg.split("-");
            var s ='';
            for (var m = parseInt(strings[0]); m<=parseInt(strings[1]);m++){
                s += m + ',';
            }
            split[i] = s.substr(0,s.length-1);
        }
    }
    return split.toString();
}

function lockPartner(){
    doLock();
}

function showDeviceAccumulativeModal(obj){
    var deviceId = $(obj).children().get(2).innerHTML;
    $.post('/SysBase/DeviceAccumulative/selectByDeviceId',{
        deviceId:deviceId
    },function (result) {
        if (doResult(result)){
            $('#cumulativeRunTime').val(result.Data.cumulativeRunTime);
            $('#cumulativeLoadCycles').val(result.Data.cumulativeLoadCycles);
            $('#cumulativeCoalWeight').val(result.Data.cumulativeCoalWeight);
            $('#deviceId_accu').val(result.Data.deviceId);
            $('#deviceAccumulativeModal').modal('show');
        }
    });
    //初始化changeFlag
    $('#workfaceAccumulateInfoFlag').val(0);
    $('#deviceId_accu').val(deviceId);
}

/**
 * 更改全选框的选中状态
 * */
function doCheckBox(){
   if (isAllCheck()) {
        $('#workfaceDeviceAllCheckBox').prop('checked',true);
   }else {
       $('#workfaceDeviceAllCheckBox').prop('checked',false);
   }
}
/**
 * 判断是否全选
 * */
function isAllCheck(){
    var $workfaceDeviceCheckBoxSon = $('.workfaceDeviceCheckBoxSon');
    for (var i = 0;i<$workfaceDeviceCheckBoxSon.length;i++){
        if (!$($workfaceDeviceCheckBoxSon[i]).prop('checked'))
            return false;
    }
    return true;
}

/**
 * 生成title
 * */
function printTitle(){
    var title = wAlias + "工作面-设备信息";
    $("#zmj-headTitle").text(title);
}