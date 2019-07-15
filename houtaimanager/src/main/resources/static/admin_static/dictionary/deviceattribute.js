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
}

//生成操作的html
function operate(){
    var   html = '<a  href="javascript: void(0);" onclick="editmethod(this)"> 编辑 </a>';
    html += '<a  href="javascript: void(0);" onclick="deleteMethod(this)"> 删除 </a>';
    return html;
}

//绘制表格内容
function printTableBody(data){
    var row = '';
    for(var i in data){
        row += '<tr>';
        row += '<td align="center" >'+'<input type="checkbox" class="deviceAttributeCheckBox deviceAttributeCheckBoxSon" onchange="doCheckBox()">'+'</td>';
        row += '<td align="center">'+parseInt(parseInt(i)+1)+'</td>';
        row += '<td align="center" hidden>'+data[i].attributeId+'</td>';
        row += '<td align="center">'+data[i].attributeName+'</td>';
        row += '<td align="center">'+data[i].attributeAlias+'</td>';
        row += '<td align="center">'+data[i].attributeUnit+'</td>';
        row += '<td align="center">'+getDeviceTypeNameById(data[i].deviceTypeId)+'</td>';
        row += '<td align="center">'+data[i].sortCode+'</td>';
        row += '<td align="center">'+data[i].updateTime+'</td>';
        row += '<td align="center">'+operate(data[i].attributeId)+'</td>';
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
    $('#attributeId').val('');
    $('#attributeName').val('');
    $('#attributeAlias').val('');
    $('#attributeUnit').val('');
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
    doEditForm(i);
    $('#subflag').val('1');
    $('#MyOnlyModal').modal('show');
}
//回溯表单内容
function doEditForm(i){
    var rd = t3w.Data.records[i];
    console.log(rd);
    listSelect('deviceTypeId',deviceTypeList);
    $('#deviceTypeId').val(rd.deviceTypeId);
    $('#attributeId').val(rd.attributeId);
    $('#attributeName').val(rd.attributeName);
    $('#attributeAlias').val(rd.attributeAlias);
    $('#attributeUnit').val(rd.attributeUnit);
    $('#SortCode').val(rd.sortCode);
}

/**
 * 提交表单
 */
function submitModal(){
    //表单验证
    var data = {
        deviceTypeId:$('#deviceTypeId').val(),
        attributeId:$('#attributeId').val(),
        attributeName: $('#attributeName').val(),
        attributeAlias: $('#attributeAlias').val(),
        attributeUnit: $('#attributeUnit').val(),
        sortCode:$('#SortCode').val()
    };
    if(data.attributeName.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.attributeAlias.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.attributeUnit.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.attributeUnit.length==0){
        alert("内容不能为空!");
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
    $.post('/SysBase/DeviceAttribute/updateZZDeviceAttribute',data,function(data){
        if(doResult(data)){
            alert("修改成功!");
            freshMeat();
        }
    },'json');
}

//提交添加表单
function submitAdd(data){
    $.post('/SysBase/DeviceAttribute/addZZDeviceAttribute',data,function(data){
        if(doResult(data)){
            alert("添加成功!");
            freshMeat();
        }
    },'json');
}
//删除模块
function deleteMethod(obj){
    var n = $(obj).parent().parent().children("td").get(1).innerHTML;
    console.log(n);
    var i= n-((pageNo-1)*pageSize)-1;
    var id =  t3w.Data.records[i].attributeId;
    if(confirm('确定要删除吗?')){
        $.post('/SysBase/DeviceAttribute/delZZDeviceAttribute',{
            attributeId:id
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
        url : "/SysBase/DeviceAttribute/getDeviceAttributePage",
        type : "POST",
        data : {
            deviceTypeId : deviceTypeId,
            keyword: keyword,
            pageNo : pageNo,
            pageSize : pageSize
        },
        success : function(result) {
            if (doResult(result)){
                //接受数据先处理
                t3w = result;
                console.log(t3w);
                page(result.Data);
                doLock();
            }
        },
        error:function () {
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
    doAjax(deviceTypeId,keyword,pageNo,pageSize);
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
        $('.deviceAttributeCheckBox').prop('checked',true);
    } else {
        $('.deviceAttributeCheckBox').prop('checked',false);
    }
}

/**
 * 删除选中的设备种类
 * */
function delChecked() {
    var ids = [];
    $('.deviceAttributeCheckBoxSon').each(function (i) {
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
    var url = '/SysBase/DeviceAttribute/batDelDeviceAttribute';
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
    doAjax(deviceTypeId,keyword,pageNo, pageSize);
    lockPartner();
}

function doLock(){
    lock = lock + 1;
    if (lock == lockNum){
        printTableBody(t3w.Data.records);
        lock = 0;
    }
}
function lockPartner(){
    doLock();
}

/**
 * 更改全选框的选中状态
 * */
function doCheckBox(){
    if (isAllCheck()) {
        $('#deviceAttributeAllCheckBox').prop('checked',true);
    }else {
        $('#deviceAttributeAllCheckBox').prop('checked',false);
    }
}
/**
 * 判断是否全选
 * */
function isAllCheck(){
    var $workfaceDeviceCheckBoxSon = $('.deviceAttributeCheckBoxSon');
    for (var i = 0;i<$workfaceDeviceCheckBoxSon.length;i++){
        if (!$($workfaceDeviceCheckBoxSon[i]).prop('checked'))
            return false;
    }
    return true;
}