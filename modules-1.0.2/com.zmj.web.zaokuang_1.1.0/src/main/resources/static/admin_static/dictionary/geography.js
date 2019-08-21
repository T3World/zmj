var t3w = null;
//当前页
var pageNo = 1;
//页面显示的条数
var pageSize = 20;
//总页数
var pageCount = null;
//总条数
var count = null;
//关键字
var keyword = '';
var groupId = '';
var groupList = null;
var orgList = null;
var orgList2 = null;
var lock = 0;
var lockNum = 2;

/**
 * 页面加载完后执行函数
 */
$(document).ready(function () {
    //加载页面数据
    loadData();
});

/**
 * 加载页面数据
 */
function loadData() {
    doAjax(groupId, keyword, pageNo, pageSize);  //加载并显示所有的矿井信息
    listGroupSelect();     //获取所有集团信息
}

/**
 * 生成操作的html
 * @returns {string}
 */
function operate(){
    var html = '<a href="javascript: void(0);" onclick="editmethod(this)"> 编辑 </a>';
    html += '<a href="javascript: void(0);" onclick="deleteMethod(this)"> 删除 </a>';
    return html;
}

/**
 * 绘制表格内容
 * @param data
 */
function printTableBody(data) {
    //alert(data);
    var row = '';
    for (var i = 0; i < data.length; i++) {
        row += '<tr>';
        row += '<td align="center">' + '<input type="checkbox" class="geographyCheckBox geographyCheckBoxSon">' + '</td>';
        row += '<td align="center">' + parseInt((pageNo - 1) * pageSize + i + 1) + '</td>';
        row += '<td align="center" hidden>' + data[i].orgId + '</td>';
        row += '<td align="center">' + data[i].zzOrgEntity.orgName + '</td>';
        row += '<td align="center">' + data[i].orgLatitude + '</td>';
        row += '<td align="center">' + data[i].orgLongitude + '</td>';
        row += '<td align="center">' + data[i].updateTime + '</td>';
        row += '<td align="center">' + operate(data[i].orgId) + '</td>';
        row += '</tr>';
    }
    $('#tableData').html(row);
}

/**
 * 显示添加表单
 */
function addmethod() {
    //显示添加模态框
    $('#MyOnlyModal').modal('show');
    //在id是groupId的select元素下生成下拉单，用于选择集团
    listSelect('groupId', groupList);
    //添加模态框中，通过集团选矿井公司
    selectOrgsByGroup();
    $('#gongsiName').val('');     //选择矿井公司
    $('#latitude').val('');       //手动填经度
    $('#longitude').val('');      //手动填纬度
    $('#sortCode').val('');       //手动填排序码
    $('#subflag').val('0');
}

//添加模态框消失后刷新，解决编辑按钮失效问题
//存在的问题：刷新后，集团选择的下拉框显示不正确，予以修改
$(function () {
    $("#MyOnlyModal").on("hide.bs.modal", function () {
        refresh();
    });
});

//添加模态框中，通过集团选矿井公司
function selectOrgsByGroup() {
    var mySelect = document.getElementById("groupId");
    var index = mySelect.selectedIndex;
    var groupId = mySelect.options[index].value;    //获取当前集团的id
    //alert(groupId);    //查看当前获取的集团id
    //获取所选集团下矿井公司的方法
    doAjaxSelectFromOrgTable(groupId);
    //doAjaxSelectOrgs(groupId);
}

/**
 * 显示编辑表单
 * @param obj
 */
function editmethod(obj) {
    var pageNo = t3w.Data.pageNo;
    var pageSize = t3w.Data.pageSize;
    var n = $(obj).parent().parent().children("td").get(1).innerHTML;
    //alert(n);
    var i = n - ((pageNo - 1) * pageSize) - 1;
    //alert(i);  //i: 0~9
    console.log("i: " + i);
    var rd = t3w.Data.records[i];
    //两种不同的回溯内容加if判断
    if (rd.zzOrgEntity.orgPid != 0) {
        //将模块信息回溯到表单(当编辑的是公司表单时)
        doEditForm_orgs(rd);
    } else {
        //将模块信息回溯到表单(当编辑的是集团表单时)
        doEditForm_groups(rd);
    }
}

/**
 * 回溯表单内容(当编辑的是公司表单时)
 * @param rd
 */
function doEditForm_orgs(rd) {
    //var rd = t3w.Data.records[i];
    console.log(rd);
    listSelect('groupId2', groupList);    //生成集团下拉单
    $('#groupId2').val(rd.zzOrgEntity.orgPid);       //集团下拉单，显示所属的集团名字
    $('#gongsiName2').val(rd.zzOrgEntity.orgName);   //当前编辑的矿井公司名字
    $('#latitude2').val(rd.orgLatitude);
    $('#longitude2').val(rd.orgLongitude);
    $('#sortCode2').val(rd.sortCode);
    $('#subflag2').val('1');
    //显示编辑模态框
    $('#MyOnlyModal2').modal('show');
}

/**
 * 回溯表单内容(当编辑的是集团表单时)
 * @param rd
 */
function doEditForm_groups(rd) {
    console.log(rd);
    listSelect('groupId3', groupList);    //生成集团下拉单
    $('#groupId3').val(rd.orgId);         //集团下拉单，显示集团的名字
    $('#gongsiName3').val("");        //此处是集团信息，公司名为空
    $('#latitude3').val(rd.orgLatitude);
    $('#longitude3').val(rd.orgLongitude);
    $('#sortCode3').val(rd.sortCode);
    //显示编辑模态框
    $('#MyOnlyModal3').modal('show');
}

/**
 * 在id=id的select元素下生成下拉单
 * @param id
 * @param list
 */
function listSelect(id, list) {
    var item = "<option value='' selected>" + '请选择' + "</option>";
    for (var i in list) {
        item += "<option value='" + list[i].orgId + "'>" + list[i].orgName + "</option>";
    }
    var $list = $("#"+id);
    $list.empty();
    $list.append(item);
}

/**
 * 在id=id的select元素下生成下拉单
 * 用于显示指定集团下的矿井公司
 * @param id
 * @param list
 */
function listSelectOrgs(id, list) {
    var item = "<option value='' selected>" + '请选择' + "</option>";
    for (var i in list) {
        item += "<option value='" + list[i].orgId + "'>" + list[i].orgName + "</option>";
    }
    var $list = $("#"+id);
    $list.empty();
    $list.append(item);
}

/**
 * 提交编辑表单(编辑矿井公司地理信息)
 */
function submitModal2() {
    var data = {
        groupId: $('#groupId2').val(),
        orgName: $('#gongsiName2').val(),
        orgLatitude: $('#latitude2').val(),
        orgLongitude: $('#longitude2').val(),
        sortCode: $('#sortCode2').val(),
        currentTime: ""
    };
    if (data.orgLatitude.length == 0) {
        alert("矿井公司经度不能为空！");
        return;
    } else if (data.orgLongitude.length == 0) {
        alert("矿井公司纬度不能为空！");
        return;
    }
    $.post('/SysBase/WorkfaceGeography/updateZZWorkfaceGeographies', data, function (data) {
        if (doResult(data)) {
            alert("矿井公司地理信息修改成功！");
            freshMeat();
        }
    }, 'json');
    $('#MyOnlyModal2').modal('hide');
}

/**
 * 提交编辑表单(编辑矿井集团地理信息)
 */
function submitModal3() {
    var data = {
        groupId: $('#groupId3').val(),
        orgLatitude: $('#latitude3').val(),
        orgLongitude: $('#longitude3').val(),
        sortCode: $('#sortCode3').val(),
        currentTime: ""
    };
    if (data.orgLatitude.length == 0) {
        alert("矿井集团经度不能为空！");
        return;
    } else if (data.orgLongitude.length == 0) {
        alert("矿井集团纬度不能为空！");
        return;
    }
    //处理数据
    $.post('/SysBase/WorkfaceGeography/updateZZGroupsGeographies', data, function (data) {
        if (doResult(data)) {
            alert("矿井集团地理信息修改成功！");
            freshMeat();
        }
    }, 'json');
    $('#MyOnlyModal3').modal('hide');
}

/**
 * 提交添加表单
 */
function submitModal() {
    var mySelect2 = document.getElementById("groupId");
    var index2 = mySelect2.selectedIndex;
    var groupId = mySelect2.options[index2].value;  //获取集团的id
    var mySelect1 = document.getElementById("gongsiName");
    var index1 = mySelect1.selectedIndex;
    var orgId = mySelect1.options[index1].value;    //获取当前公司的id
    var data = {
        groupId: groupId,
        orgId: orgId,
        orgLatitude: $('#latitude').val(),
        orgLongitude: $('#longitude').val(),
        sortCode: $('#sortCode').val(),
    };
    if (groupId.length == 0) {
        alert("矿井集团不能为空，请选择！");
        return;
    }
    if (data.orgLatitude.length == 0) {
        alert("矿井经度不能为空！");
        return;
    } else if (data.orgLongitude.length == 0) {
        alert("矿井纬度不能为空！");
        return;
    }
    if (data.orgId.length != 0) {
        $.post('/SysBase/WorkfaceGeography/addZZWorkfaceGeographies', data, function (data) {
            if (doGeographyResult(data)) {
                alert("矿井公司地理信息添加成功！");
                freshMeat();
            } else {
                alert("该矿井公司地理信息已存在！请检查后重新操作！");
                freshMeat();
            }
        }, 'json');
    } else {
        if (confirm("矿井公司未选择，您添加的是矿井集团地理信息！确定要添加吗？")) {
            $.post('/SysBase/WorkfaceGeography/addZZGroupsGeographies', data, function (data) {
                if (doGeographyResult(data)) {
                    alert("矿井集团地理信息添加成功！");
                    freshMeat();
                } else {
                    alert("该矿井集团地理信息已存在！请检查后重新操作！");
                    freshMeat();
                }
            }, 'json');
        }
    }
    $('#MyOnlyModal').modal('hide');
}

/**
 * 删除模块
 * @param obj
 */
function deleteMethod(obj) {
    var n = $(obj).parent().parent().children("td").get(1).innerHTML;
    console.log(n);
    var i = n - ((pageNo - 1) * pageSize) - 1;
    var id = t3w.Data.records[i].orgId;
    //alert(id);
    if (confirm("确定要删除吗？")) {
        $.post('/SysBase/WorkfaceGeography/delGeography', {
            orgId: id
        }, function (result) {
            if (doResult(result)) {
                alert("删除成功！");
                freshMeat();
            }
        });
    }
}

//全选，全不选
function selectAllGeography(dom) {
    var isChecked = $(dom).prop('checked');
    if (isChecked) {
        $('.geographyCheckBox').prop('checked', true);
    } else {
        $('.geographyCheckBox').prop('checked', false);
    }
}

/**
 * 批量删除选中的地理信息
 */
function delChecked() {
    var ids = [];
    $('.geographyCheckBoxSon').each(function (i) {
        if ($(this).prop('checked')) {
            console.log($(this).parent().nextAll());
            ids.push($(this).parent().nextAll()[1].innerHTML);
        }
    });
    //alert(ids.toString());
    console.log(ids.toString());
    if (ids.toString() === '') {
        alert("没有选中的元素");
    } else {
        //alert(ids[1]);
        batDel(ids.toString());
    }
}

//批量删除
function batDel(ids) {
    var url = '/SysBase/WorkfaceGeography/batDelGeography';
    if (confirm("确定要删除选中的地理信息吗？")) {
        $.post(url,{
            ids: ids
        }, function (result) {
            if (doResult(result)) {
                alert("批量删除成功！");
            }
            freshMeat();
        })
    }
}

/**
 * 在集团下拉框中选择集团
 */
function selectGroup() {
    groupId = $('#groupSelect').val();
    //alert(groupId);
    doAjax(groupId, keyword, pageNo, pageSize);
    lockPartner();
}

/**
 * 获取所有集团列表(参考：获取设备类型列表)
 */
function listGroupSelect() {
    $.ajax({
        url: "/SysBase/Org/listAllGroupPage",
        type: "POST",
        data: {
            keyword: '',
            pageNo: 1,
            pageSize: 20
        },
        success: function (result) {
            if (doResult(result)) {
                groupList = result.Data.records;
                console.log(groupList);
                listSelect('groupSelect', groupList);
                doLock();
            }
        },
        error: function (r) {
            doLock();
        }
    })
}

/**
 * 根据groupId获取所选集团下所有矿井地理信息，发送ajax请求，分页
 * @param groupId
 * @param keyword
 * @param pageNo
 * @param pageSize
 */
function doAjax(groupId, keyword, pageNo, pageSize) {
    $("tbody").empty();
    $.ajax({
        url: "/SysBase/WorkfaceGeography/getGeographyPage",
        type: "POST",
        data: {
            groupId: groupId,
            keyword: keyword,
            pageNo: pageNo,
            pageSize: pageSize
        },
        success: function (result) {
            if (doResult(result)) {
                //接受数据先处理
                t3w = result;
                console.log(t3w);
                //alert(t3w.Data.records);
                //orgList.length = 0;
                //orgList = result.Data.records;
                page(result.Data);  //分页
                doLock();      //展示表格数据
            }
        },
        error: function () {
            doLock();
        }
    })
}

//获取所选集团下矿井公司的方法
//(注意：应该从zz_org表中获取集团下矿井公司，不能从zz_geography中查询)
function doAjaxSelectOrgs(groupId) {
    $.ajax({
        //暂时用分页查询的url请求，不分页的有些问题
        url: "/SysBase/WorkfaceGeography/getGeographyPage",
        type: "POST",
        data: {
            groupId: groupId,
            keyword: "",
            pageNo: 0,
            pageSize: 0
        },
        success: function (result) {
            if (doResult(result)) {
                //接受数据先处理
                t3w = result;
                console.log(t3w);
                orgList = result.Data.records;
                //alert(orgList);      //查看该集团下的公司地理信息
                listSelectOrgs('gongsiName', orgList);
            }
        },
        error: function () {
            return;
        }
    })
}

//获取所选集团下的矿井公司的方法
//(注意：应该从zz_org表中获取集团下矿井公司，不能从zz_geography中查询)
function doAjaxSelectFromOrgTable(groupId) {
    $.ajax({
        //暂时用分页查询的url请求，不分页的有些问题
        url: "/SysBase/Org/listOrgsByGroupIdPage",
        type: "POST",
        data: {
            groupId: groupId,
            keyword: "",
            pageNo: 0,
            pageSize: 0
        },
        success: function (result) {
            if (doResult(result)) {
                //接受数据先处理
                t3w = result;
                console.log(t3w);
                orgList2 = result.Data.records;
                //alert(orgList2);      //查看该集团下的公司地理信息
                //将所选集团下的公司信息加入到“矿井公司”下拉框里
                listSelectOrgs('gongsiName', orgList2);
            }
        },
        error: function () {
            return;
        }
    })
}

/**
 * 向前翻页
 */
function doFrontPage(){
    // var pageNo = t3w.Data.pageNo;
    // var pageSize = t3w.Data.pageSize;
    if(pageNo != 1){
        pageNo = pageNo - 1;
    }
    lockPartner();
    doAjax(groupId, keyword, pageNo, pageSize);
}

/**
 * 向后翻页
 */
function doNextPage() {
    // var pageNo = t3w.Data.pageNo;
    // var pageSize = t3w.Data.pageSize;
    // var pageCount = t3w.Data.pageCount;
    if(pageNo != pageCount) {
        pageNo = pageNo + 1;
    }
    lockPartner();
    doAjax(groupId, keyword, pageNo, pageSize);
}

/**
 * 根据页码翻页
 * @param pageNo
 */
function doThisPage(pageNo){
    //var pageSize = t3w.Data.pageSize;
    // doAjax(pageNo,pageSize);
    lockPartner();
    doAjax(groupId, keyword, pageNo, pageSize);
}

function doAjaxByGroup(groupId, keyword, pageNo, pageSize) {
    doAjax(groupId, keyword, pageNo, pageSize);
}

/**
 * 分页
 * @param data
 */
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
    count = data.count;
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
 * 处理结果弹框提示错误信息
 * @param result
 * @returns {boolean}
 */
function doResult(result) {
    var code = result.Code;
    var msg = result.Error_Msg;
    if (code != "2000") {
        alert(msg);
        return false;
    }
    return true;
}

/**
 * 处理结果弹框提示错误信息(地理信息模块实用)
 * @param result
 * @returns {boolean}
 */
function doGeographyResult(result) {
    var code = result.Code;
    var msg = result.Error_Msg;
    if (code != "2000") {
        return false;
    }
    return true;
}

/**
 * 刷新页面
 */
function freshMeat() {
    // orgList2 = null;
    doAjax(groupId, keyword, pageNo, pageSize);
    listGroupSelect();       //获取所有集团列表
}

/**
 * 添加模态框取消后刷新
 */
function refresh() {
    //alert(orgList2.toString());
    freshMeat();
    //orgList2 = null;
}

/**
 * 展示数据
 */
function doLock() {
    lock = lock + 1;
    if (lock == lockNum) {
        printTableBody(t3w.Data.records);
        lock = 0;
    }
}

/**
 * 展示数据
 */
function lockPartner() {
    doLock();
}
