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

$(document).ready(function(){
    //加载页面数据
    loadData();
});

//加载页面数据
function loadData(){
    doAjax(keyword,pageNo,pageSize);
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
        row += '<td align="center" >'+'<input type="checkbox" class="deviceTypeCheckBox deviceTypeCheckBoxSon" onchange="doCheckBox()">'+'</td>';
        row += '<td align="center">'+parseInt(parseInt(i)+1)+'</td>';
        row += '<td align="center" hidden class="deviceTypeId">'+data[i].deviceTypeId+'</td>';
        row += '<td align="center">'+data[i].deviceTypeName+'</td>';
        row += '<td align="center">'+data[i].deviceTypeAlias+'</td>';
        row += '<td align="center">'+data[i].sortCode+'</td>';
        row += '<td align="center">'+data[i].updateTime+'</td>';
        row += '<td align="center">'+operate(data[i].deviceTypeId)+'</td>';
        row += '</tr>';
    }
    $('#deviceTypeTableBody').html(row);
}


/**
 * 显示添加表单
 */
function addmethod(){
    $('#deviceTypeName').val('');
    $('#deviceTypeAlias').val('');
    $('#deviceTypeSortCode').val('');
    $('#subflag').val('0');
    $('#deviceTypeModal').modal('show');
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
    $('#deviceTypeModal').modal('show');
}
//回溯表单内容
function doEditForm(i){
    var rd = t3w.Data.records[i];
    console.log(t3w);
    console.log(rd);
    $('#deviceTypeId').val(rd.deviceTypeId);
    $('#deviceTypeName').val(rd.deviceTypeName);
    $('#deviceTypeAlias').val(rd.deviceTypeAlias);
    $('#deviceTypeSortCode').val(rd.sortCode);
}

/**
 * 提交表单
 */
function submitModal(){
    //表单验证
    var data = {
        deviceTypeId:$('#deviceTypeId').val(),
        deviceTypeName:$('#deviceTypeName').val(),
        deviceTypeAlias:$('#deviceTypeAlias').val(),
        sortCode:$('#deviceTypeSortCode').val()
    };
    if(data.deviceTypeName.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.deviceTypeAlias.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.sortCode.length==0){
        alert("内容不能为空!");
        return;
    }

    //根据subflag选择是新增还是修改
    if($('#subflag').val()==0){
        submitAddDeviceType(data);
        $('#deviceTypeModal').modal('hide');
    }else{
        submitEditDeviceType(data);
        $('#deviceTypeModal').modal('hide');
    }
}

//提交编辑表单
function submitEditDeviceType(data){
    $.post('/SysBase/DeviceType/updateZZdeviceType',data,function(data){
        if(doResult(data)){
            alert('修改成功!');
            freshMeat();
        }
    },'json');
}

//提交添加表单
function submitAddDeviceType(data){
    $.post('/SysBase/DeviceType/addZZdeviceType',data,function(data){
        if(doResult(data)){
            alert("添加成功!");
            freshMeat();
        }
    });
}
//删除模块
function deleteMethod(obj){
    var n = $(obj).parent().parent().children("td").get(1).innerHTML;
    console.log(n);
    var i= n-((pageNo-1)*pageSize)-1;
    var id =  t3w.Data.records[i].deviceTypeId;
    if(confirm('确定要删除吗?')){
        $.post('/SysBase/DeviceType/delZZdeviceType',{
            deviceTypeId:id
        },function(result){
            if(result.Code==2000){
                alert("删除成功!");
                location.reload();
            }else{
                alert(result.Error_Msg);
            }
        });
    }
}
//发起ajax请求,分页
function doAjax(keyword,pageNo, pageSize) {
    $("tbody").empty();
    $.ajax({
        url : "/SysBase/DeviceType/listDevicePage",
        type : "POST",
        data : {
            keyword: keyword,
            pageNo : pageNo,
            pageSize : pageSize
        },
        success : function(result) {
            //接受数据先处理
            doResult(result);
            t3w = result;
            console.log(t3w);
            page(result.Data);
            printTableBody(result.Data.records);
        }

    })
}

//处理结果弹框提示错误信息
function doResult(result){
    if(result.Code!="2000"){
        alert(result.Error_Msg);
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
    doAjax(keyword,pageNo,pageSize);
}

//向后翻页
function doNextPage(){
    // var pageNo = t3w.Data.pageNo;
    // var pageSize = t3w.Data.pageSize;
    // var pageCount = t3w.Data.pageCount;
    if(pageNo!=pageCount){
        pageNo=pageNo+1;
    }
    doAjax(keyword,pageNo,pageSize);
}

//根据页码翻页
function doThisPage(pageNo){
    doAjax(keyword,pageNo,pageSize);
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

function selectAllDeviceType(dom) {
    var isChecked = $(dom).prop('checked');
    if (isChecked){
        $('.deviceTypeCheckBox').prop('checked',true);
    } else {
        $('.deviceTypeCheckBox').prop('checked',false);
    }
}

/**
 * 删除选中的设备种类
 * */
function delChecked() {
    var ids = [];
    $('.deviceTypeCheckBoxSon').each(function (i) {
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
    var url = '/SysBase/DeviceType/batDelZZdeviceType';
    if (confirm('确定要删除选中的设备类型吗?')){
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
    debugger;
    doAjax(keyword,pageNo,pageSize);
}

/**
 * 更改全选框的选中状态
 * */
function doCheckBox(){
    if (isAllCheck()) {
        $('#deviceTypeAllCheckBox').prop('checked',true);
    }else {
        $('#deviceTypeAllCheckBox').prop('checked',false);
    }
}
/**
 * 判断是否全选
 * */
function isAllCheck(){
    var $workfaceDeviceCheckBoxSon = $('.deviceTypeCheckBoxSon');
    for (var i = 0;i<$workfaceDeviceCheckBoxSon.length;i++){
        if (!$($workfaceDeviceCheckBoxSon[i]).prop('checked'))
            return false;
    }
    return true;
}