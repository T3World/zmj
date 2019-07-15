/**
 **
 **魏宗杰
 **/

$(document).ready(function(){

	loadGlobalParameters();

	$('#saveBtn').on('click',function(){

		saveGlobalParameters();

	});
});

/**
 * 加载全局参数
 */
function loadGlobalParameters(){
	var GlobalId = $('#Global_Id').val();
	$.get('/SysBase/Global/select',{
		globalId: GlobalId
	},function(data){
		console.log(data);
		if(data.Code==2000){
			$('#Global_MinePressureTime').val(data.Data.globalMinepressuretime);
			$('#Global_SettingPressureTime').val(data.Data.globalSettingpressuretime);
			$('#Global_DiffPressureTime').val(data.Data.globalDiffpressuretime);
			$('#Global_CheTime').val(data.Data.globalChetime);
			$('#Global_ShearerPosTime').val(data.Data.globalShearerpostime);
			$('#Global_Id').val(data.Data.globalId);
		}

	},'json');

}

/**
 * 保存全局参数
 */
function saveGlobalParameters(){

	var GlobalMinePressureTime = $('#Global_MinePressureTime').val();
	var GlobalSettingPressureTime = $('#Global_SettingPressureTime').val();
	var GlobalDiffPressureTime = $('#Global_DiffPressureTime').val();
	var GlobalCheTime = $('#Global_CheTime').val();
	var GlobalShearerPosTime = $('#Global_ShearerPosTime').val();
	var GlobalId = $('#Global_Id').val();
	var gd={
			globalMinepressuretime: GlobalMinePressureTime,
			globalSettingpressuretime: GlobalSettingPressureTime,
			globalDiffpressuretime: GlobalDiffPressureTime,
			globalChetime: GlobalCheTime,
			globalShearerpostime: GlobalShearerPosTime,
			globalId: GlobalId
		};
	console.log(gd);
	$.post('/SysBase/Global/saveGlobalParameters',gd,function(data){

		if(data.Code == 2000){
			saveId(data.Data.globalId);
			alert('参数配置成功!');
		}else{
			alert('参数配置失败!');
		}
	},'json');
}
function saveId(id){
	$('#Global_Id').val(id);
}