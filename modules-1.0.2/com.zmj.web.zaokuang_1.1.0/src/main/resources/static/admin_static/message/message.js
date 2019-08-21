var t3w=null;
//当前页
var pageNo = 1;
//页面显示的条数
var pageSize = 20;
//总页数
var pageCount = null;
//消息类型
var messageType ='1';
//总条数
var count = null;
var date = getToday();
//组织机构列表
var orgList = null;

$(document).ready(function(){
    //加载页面数据
    loadData();
});

//加载页面数据
function loadData(){
    doAjax(date,messageType,pageNo,pageSize);
    // listOrgSelect();
}

//生成操作的html
function operate(){
    var   html = '';
    // html += '<a  href="javascript: void(0);" onclick="editmethod(this)"> 编辑 </a>';
    html += '<a  href="javascript: void(0);" onclick="deleteMethod(this)"> 删除 </a>';
    return html;
}

//绘制表格内容
function printTableBody(data){
    var row = '';
    for(var i = 0;i<data.length;i++){
        row += '<tr>';
        row += '<td align="center" >'+'<input type="checkbox" class="messageCheckBox messageCheckBoxSon" onchange="doCheckBox()">'+'</td>';
        row += '<td align="center">'+parseInt((pageNo - 1) * pageSize + i + 1)+'</td>';
        row += '<td align="center" hidden>'+data[i].id+'</td>';
        row += '<td align="center">'+data[i].orgName+'</td>';
        row += '<td align="center">'+data[i].message+'</td>';
        row += '<td align="center">'+data[i].userName+'</td>';
        row += '<td align="center">'+data[i].updateTime+'</td>';
        row += '<td align="center">'+operate(data[i].id)+'</td>';
        row += '</tr>';
    }
    $('#tableData').html(row);
}


/**
 * 显示添加表单
 */
function addmethod(){
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
    listSelect('date',orgList);
    var rd = t3w.Data.records[i];
    $('#date').val(rd.date);
    $('#messageId').val(rd.messageId);
    $('#messageName').val(rd.messageName);
    $('#messageAlias').val(rd.messageAlias);
    $('#messageFirm').val(rd.messageFirm);
    $('#messageModel').val(rd.messageModel);
    // mapToDiv(rd.messageAttribute);
    $.ajax({
        url : "/SysBase/Message/selectMessageWithAttributeByMessageId",
        type : "POST",
        data : {
            messageId:rd.messageId
        },
        success : function(result) {
            if(doResult(result)){
                mapToDiv(result.Data.messageAttribute);
            }
        }});
}

/**
 * 提交表单
 */
function submitModal(){
    //表单验证
    var data = {
        date:$('#date').val(),
        messageId:$('#messageId').val(),
        messageName: $('#messageName').val(),
        messageAlias: $('#messageAlias').val(),
        messageFirm: $('#messageFirm').val(),
        messageModel: $('#messageModel').val(),
        messageAttribute: getMessageAttributeMaps(),
        sortCode:$('#SortCode').val()
    };
    if(data.messageName.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.messageAlias.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.messageFirm.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.messageModel.length==0){
        alert("内容不能为空!");
        return;
    }else if(data.date==null||data.date.length==0){
        alert("设备消息类型不能为空!");
        return;
    }else if(data.sortCode.length==0){
        alert("排序码不能为空!");
        return;
    }else if (checkAttribute(data.messageAttribute)){
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
        url : "/SysBase/Message/updateMessageWithAttributeByMessageId",
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
        url : "/SysBase/Message/addMessageWithAttribute",
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
        $.post('/SysBase/MineMessage/remove',{
            id:id
        },function(result){
            if(doResult(result)){
                alert("删除成功!");
                freshMeat();
            }
        });
    }
}
//发起ajax请求,分页
function doAjax(date,messageType,pageNo, pageSize) {
    $("tbody").empty();
    $.ajax({
        url : "/SysBase/MineMessage/getMineMessageList",
        type : "POST",
        data : {
            date : date,
            messageType:messageType,
            pageNo : pageNo,
            pageSize : pageSize
        },
        success : function(result) {
            //接受数据先处理
            doResult(result);
            t3w = result;
            console.log(t3w);
            printTableBody(t3w.Data.records);
            page(result.Data);
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
    lockPartner();
    doAjax(date,messageType,pageNo,pageSize);
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
    doAjax(date,messageType,pageNo,pageSize);
}

//根据页码翻页
function doThisPage(pageNo){
    // var pageSize = t3w.Data.pageSize;
    lockPartner();
    doAjax(date,messageType,pageNo,pageSize);
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

function selectAllMessage(dom) {
    var isChecked = $(dom).prop('checked');
    if (isChecked){
        $('.messageCheckBox').prop('checked',true);
    } else {
        $('.messageCheckBox').prop('checked',false);
    }
}

/**
 * 删除选中的消息
 * */
function delChecked() {
    var ids = [];
    $('.messageCheckBoxSon').each(function (i) {
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
    var url = '/SysBase/MineMessage/batDelZZminemessage';
    if (confirm('确定要删除选中的消息吗?')){
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
    doAjax(date,messageType,pageNo, pageSize);
}

function clearModal(){
    $('#messageId').val('');
    $('#messageName').val('');
    $('#messageAlias').val('');
    $('#messageFirm').val('');
    $('#messageModel').val('');
    $('#SortCode').val('');
    $('#messageAttribute').empty();
}

/**
 * 更改全选框的选中状态
 * */
function doCheckBox(){
    if (isAllCheck()) {
        $('#messageAllCheckBox').prop('checked',true);
    }else {
        $('#messageAllCheckBox').prop('checked',false);
    }
}
/**
 * 判断是否全选
 * */
function isAllCheck(){
    var $messageCheckBoxSon = $('.messageCheckBoxSon');
    for (var i = 0;i<$messageCheckBoxSon.length;i++){
        if (!$($messageCheckBoxSon[i]).prop('checked'))
            return false;
    }
    return true;
}

function getToday(){
   return new Date().toLocaleDateString();
}
function changeDate(){
    date = $("#dateSelector").val().replace("-","/").replace("-","/");
}
function changeMessageType() {
    messageType = $("#messageTypeSelect").val();
}