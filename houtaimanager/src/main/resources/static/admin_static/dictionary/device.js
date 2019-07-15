var t3w=null;
//当前页
var pageNo = 1;
//页面显示的条数
var pageSize = 11;
//总页数
var pageCount = null;
//关键字
var keyword ='';
//总条数
var count = null;
var deviceTypeId = '';
var deviceTypeList = null;
var deviceAttributeList = null;
var lock = 0;
var lockNum = 2;

$(document).ready(function(){
    //加载页面数据
    loadData();
});

//加载页面数据
function loadData(){
    doAjax(deviceTypeId,keyword,pageNo,pageSize);
    listDeviceTypeSelect();
    selectDeviceAttributeByTypeId();
}

//生成操作的html
function operate(){
    var   html = '';
    // var   html = '<a  href="javascript: void(0);" onclick="propertyManage(this)"> 属性 </a>';
    html += '<a  href="javascript: void(0);" onclick="editmethod(this)"> 编辑 </a>';
    html += '<a  href="javascript: void(0);" onclick="deleteMethod(this)"> 删除 </a>';
    return html;
}

//绘制表格内容
function printTableBody(data){
    var row = '';
    for(var i in data){
        row += '<tr ondblclick="showDeviceAccumulativeModal(this)">';
        row += '<td align="center" >'+'<input type="checkbox" class="deviceCheckBox deviceCheckBoxSon" onchange="doCheckBox()">'+'</td>';
        row += '<td align="center">'+parseInt(parseInt(i)+1)+'</td>';
        row += '<td align="center" hidden>'+data[i].deviceId+'</td>';
        row += '<td align="center">'+data[i].deviceName+'</td>';
        row += '<td align="center">'+data[i].deviceAlias+'</td>';
        row += '<td align="center">'+data[i].deviceModel+'</td>';
        row += '<td align="center">'+data[i].deviceFirm+'</td>';
        row += '<td align="center">'+getDeviceTypeNameById(data[i].deviceTypeId)+'</td>';
        row += '<td align="center">'+data[i].updateTime+'</td>';
        row += '<td align="center">'+data[i].sortCode+'</td>';
        row += '<td align="center">'+operate(data[i].deviceId)+'</td>';
        row += '</tr>';
    }
    $('#tableData').html(row);
}


/**
 * 显示添加表单
 */
function addmethod(){
    // $('#deviceTypeId').val('');
    listSelect('deviceTypeId',deviceTypeList);
    /**
     * 清空模态框
     * */
    clearModal();
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
    doEditForm(i);
    $('#subflag').val('1');
    $('#MyOnlyModal').modal('show');
}
//回溯表单内容
function doEditForm(i){
    listSelect('deviceTypeId',deviceTypeList);
    var rd = t3w.Data.records[i];
    $('#deviceTypeId').val(rd.deviceTypeId);
    $('#deviceId').val(rd.deviceId);
    $('#deviceName').val(rd.deviceName);
    $('#deviceAlias').val(rd.deviceAlias);
    $('#deviceFirm').val(rd.deviceFirm);
    $('#deviceModel').val(rd.deviceModel);
    $('#SortCode').val(rd.sortCode);
    // mapToDiv(rd.deviceAttribute);
    $.ajax({
        url : "/SysBase/Device/selectDeviceWithAttributeByDeviceId",
        type : "POST",
        data : {
            deviceId:rd.deviceId
        },
        success : function(result) {
            if(doResult(result)){
                mapToDiv(result.Data.deviceAttribute);
            }
        }});
}

/**
 * 提交表单
 */
function submitModal(){
    //表单验证
    var data = {
        deviceTypeId:$('#deviceTypeId').val(),
        deviceId:$('#deviceId').val(),
        deviceName: $('#deviceName').val(),
        deviceAlias: $('#deviceAlias').val(),
        deviceFirm: $('#deviceFirm').val(),
        deviceModel: $('#deviceModel').val(),
        deviceAttribute: getDeviceAttributeMaps(),
        sortCode:$('#SortCode').val()
    };
    if(data.deviceName.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.deviceAlias.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.deviceFirm.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.deviceModel.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.deviceTypeId==null||data.deviceTypeId.length==0){
        alert("设备类型不能为空!");
        return;
    }else if(data.sortCode.length==0){
        alert("排序码不能为空!");
        return;
    }else if (checkAttribute(data.deviceAttribute)){
        alert("属性不允许重复!");
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
    $.ajax({
        url : "/SysBase/Device/updateDeviceWithAttributeByDeviceId",
        type : "POST",
        contentType:"application/json;charset=utf-8",
        data : JSON.stringify(data),
        success : function(result) {
            if(doResult(result)){
                alert('修改成功!');
                freshMeat();
            }
        }});
}

//提交添加表单
function submitAdd(data){
    $.ajax({
        url : "/SysBase/Device/addDeviceWithAttribute",
        type : "POST",
        contentType:"application/json;charset=utf-8",
        data : JSON.stringify(data),
        success : function(result) {
            if(doResult(result)){
                alert('添加成功!');
                freshMeat();
            }
        }});
}
//删除模块
function deleteMethod(obj){
    var id = $(obj).parent().parent().children("td").get(2).innerHTML;
    if(confirm('确定要删除吗?')){
        $.post('/SysBase/Device/delZZDevice',{
            deviceId:id
        },function(result){
            if(doResult(result)){
                alert("删除成功!");
                freshMeat();
            }
        });
    }
}
//发起ajax请求,分页
function doAjax(deviceTypeId,keyword,pageNo, pageSize) {
    $("tbody").empty();
    $.ajax({
        url : "/SysBase/Device/getDeviceData",
        type : "POST",
        data : {
            deviceTypeId : deviceTypeId,
            keyword:keyword,
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
    });
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
        pageNo = pageNo-1;
    }
}

//向后翻页
function doNextPage(){
    // var pageNo = t3w.Data.pageNo;
    // var pageSize = t3w.Data.pageSize;
    // var pageCount = t3w.Data.pageCount;
    if(pageNo!=pageCount){
        pageNo=pageNo+1;
    }
    doAjax(deviceTypeId,keyword,pageNo,pageSize);
}

//根据页码翻页
function doThisPage(pageNo){
    // var pageSize = t3w.Data.pageSize;
    doAjax(deviceTypeId,keyword,pageNo,pageSize);
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

function selectAllDeviceAttribute(dom) {
    var isChecked = $(dom).prop('checked');
    if (isChecked){
        $('.deviceCheckBox').prop('checked',true);
    } else {
        $('.deviceCheckBox').prop('checked',false);
    }
}

/**
 * 删除选中的设备种类
 * */
function delChecked() {
    var ids = [];
    $('.deviceCheckBoxSon').each(function (i) {
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
    var url = '/SysBase/Device/batDelZZDevice';
    if (confirm('确定要删除选中的设备属性吗?')){
        $.post(url,{
            ids:ids
        },function (result) {
            if (doResult(result)){
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
    doAjax(deviceTypeId,keyword,pageNo, pageSize);
    listDeviceTypeSelect();
}

/**
 * 获取设备类型列表
 * */
function listDeviceTypeSelect(){
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
                listSelect('deviceTypeSelect',deviceTypeList);
                doLock();
            }
        }, error:function (r) {
            doLock();
        }
    })
}

//在id=id的select元素下生成下拉单
function listSelect(id,list){
    var item = "<option value='' selected>"+'请选择'+"</option>";
    for(var i in list){
        item+= "<option value='"+list[i].deviceTypeId+"'>"+list[i].deviceTypeName+"</option>";
    }
    var $list = $("#"+id);
    $list.empty();
    $list.append(item);
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
    doAjax(deviceTypeId,keyword,pageNo,pageSize);
    lockPartner();
}

function selectDeviceAttributeByTypeId(){
    var tid = $('#deviceTypeId').val();
    if (tid == null)
        tid = '';
    $.post('/SysBase/DeviceAttribute/getDeviceAttributePage',{
        deviceTypeId : tid,
        keyword: keyword,
        pageNo : 1,
        pageSize : 20
    },function (result) {
        if (doResult(result)){
            deviceAttributeList = result.Data.records;
        }
    })
}

function doLock(){
    lock = lock + 1;
    if (lock == lockNum){
        printTableBody(t3w.Data.records);
        lock = 0;
    }
}

function propertyManage(){
    alert('属性管理');
}

function getDeviceAttributeSelectOptions(){
    var r = '' ;
    for (var i in deviceAttributeList){
        r += "<option value='"+deviceAttributeList[i].attributeId+"'>"+deviceAttributeList[i].attributeName+"</option>";
    }
    return r;
}

/**
 * 添加一行设备属性
 * */
function addAttributeSelect() {
    var opStr = getDeviceAttributeSelectOptions();
    console.log(opStr);
    var selectStr =
        '<div class="form-group">' +
        '<div class="col-lg-3 col-lg-offset-1">' +
        '<select class="attributeSelect" name="select">'+opStr+'</select>' +
        '</div>' +
        '<div class="col-lg-3">' +
        '<input type="text" class="form-control attributeValue" placeholder="请输入属性值" name="input">'+
        '</div>'+
        '<div class="col-lg-3">' +
        '<input type="button" class="btn btn-danger" value="-" onclick="deleteRow(this)">'+
        '</div>'+
        '</div>';
    $('#deviceAttribute').append(selectStr);
}

function getDeviceAttributeMaps(){
    var maps = [];
    var selects = $('.attributeSelect');
    var inputs = $('.attributeValue');
    for (var i = 0;i<selects.length;i++){
        var map = {};
        console.log(selects[i]);
        map.attributeValue = $(inputs[i]).val();
        map.attributeId = $(selects[i]).val();
        maps.push(map);
    }
    console.log(maps);
    return maps;
}

function deleteRow(obj){
    $(obj).parent().parent().remove();
}

/**
 * 将attribute map 映射到模态框
 * */
function mapToDiv(maps){
    $('#deviceAttribute').empty();
    for (var i =0;i<maps.length;i++){
        addAttributeSelect();
    }
    var selects = $('.attributeSelect');
    var values = $('.attributeValue');
    for (var i =0;i<maps.length;i++){
        $(selects[i]).val(maps[i].attributeId);
        $(values[i]).val(maps[i].attributeValue);
    }
}

function clearModal(){
    $('#deviceId').val('');
    $('#deviceName').val('');
    $('#deviceAlias').val('');
    $('#deviceFirm').val('');
    $('#deviceModel').val('');
    $('#SortCode').val('');
    $('#deviceAttribute').empty();
}

function addImage(){
    alert("上传图片!");
}

function checkAttribute(maps) {
    for (var i = 0; i < maps.length; i++) {
        for (var m = i+1; m < maps.length; m++) {
            if( maps[i].attributeId == maps[m].attributeId){
                return true;
            }
        }
    }
    return false;
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

function uploadImg(){

}

/**
 * 更改全选框的选中状态
 * */
function doCheckBox(){
    if (isAllCheck()) {
        $('#deviceAllCheckBox').prop('checked',true);
    }else {
        $('#deviceAllCheckBox').prop('checked',false);
    }
}
/**
 * 判断是否全选
 * */
function isAllCheck(){
    var $deviceCheckBoxSon = $('.deviceCheckBoxSon');
    for (var i = 0;i<deviceCheckBoxSon.length;i++){
        if (!$($deviceCheckBoxSon[i]).prop('checked'))
            return false;
    }
    return true;
}