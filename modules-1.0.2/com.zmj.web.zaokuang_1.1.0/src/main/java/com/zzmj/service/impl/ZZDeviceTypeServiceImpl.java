package com.zzmj.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZDeviceAttributeMapper;
import com.zzmj.mapper.ZZDeviceMapper;
import com.zzmj.mapper.ZZDeviceTypeMapper;
import com.zzmj.pojo.entity.ZZDeviceType;
import com.zzmj.pojo.entity.ZZDeviceTypeExample;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceTypeService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.FormatDateUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@Service
public class ZZDeviceTypeServiceImpl implements ZZDeviceTypeService {

	private static Logger logger = Logger.getLogger(ZZDeviceTypeServiceImpl.class);
	@Autowired
	private ZZDeviceTypeMapper zzDeviceTypeMapper;
	@Autowired
	private ZZDeviceMapper zzDeviceMapper;
	@Autowired
	private ZZDeviceAttributeMapper zzDeviceAttributeMapper;
	
	
	/**
	 * 新添加一个 设置isDel为0
	 */
	@Override
	public SysResult addZZdeviceType(ZZDeviceType zzDeviceType) {

		try {
			String deviceTypeId = CodeUtil.createUuid36();
			String id = CodeUtil.createUuid36();
			zzDeviceType.setId(id);
			zzDeviceType.setDeviceTypeId(deviceTypeId);
			zzDeviceType.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
			zzDeviceType.setIsDel("0");
			int flag = zzDeviceTypeMapper.insert(zzDeviceType);
			if (flag < 1) {
				throw new DoSqlFailedException("设备类型添加失败");
			}
			return new SysResult(ErrorUtil.CODE2000, "设备类型添加成功", zzDeviceType);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("设备类型添加失败，出现异常", e);
			throw new DoSqlFailedException("设备类型添加失败");
		}

	}

	/**
	 * 单个删除设备类型  
	 * 分别判断设备表和设备属性表与设备类型表关联(查看isdel为0的数量)
	 */
	@Override
	public SysResult delZZdeviceType(ZZDeviceType zzDeviceType) {
		try {
			String deviceTypeId=zzDeviceType.getDeviceTypeId();
			//查询设备属性表中和设备类型表相关联且isDel为0的数量
			int a=zzDeviceAttributeMapper.countDeviceAttributeByDeviceTypeId(deviceTypeId);
			//判断设备表中和设备类型表相关联且isDel为0的个数
			int b=zzDeviceMapper.getCountBydeviceTypeId(deviceTypeId);
			if(a>0 && b>0)
				return new SysResult(ErrorUtil.CODE2001, "设备属性表和设备表中都存在相关联的数据!", deviceTypeId);
			if(a>0)
				return new SysResult(ErrorUtil.CODE2001, "设备属性表中有相关联的数据!", deviceTypeId);
			if(b>0)
				return new SysResult(ErrorUtil.CODE2001, "设备表中有相关联的数据!", deviceTypeId);
			
		
			zzDeviceType.setIsDel("1");
			zzDeviceType.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
			ZZDeviceTypeExample example=new ZZDeviceTypeExample();
			example.createCriteria().andDevicetypeIdEqualTo(deviceTypeId);
			int flag=zzDeviceTypeMapper.updateByExampleSelective(zzDeviceType, example);
			if(flag<1)
				return new SysResult(ErrorUtil.CODE2001, "设备类型删除失败!", flag);
			return new SysResult(ErrorUtil.CODE2000, "设备类型删除成功  成功!", zzDeviceType);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设备类型删除失败  出现异常!", e);
			throw new DoSqlFailedException("设备类型删除失败  出现异常!", e);
		}	
	}

	@Override
	public SysResult updateZZdeviceType(ZZDeviceType zzDeviceType) {

		try {
			zzDeviceType.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
			ZZDeviceTypeExample example = new ZZDeviceTypeExample();
			example.createCriteria().andDevicetypeIdEqualTo(zzDeviceType.getDeviceTypeId());
			int flag = zzDeviceTypeMapper.updateByExampleSelective(zzDeviceType, example);
			if (flag < 1)
				return new SysResult(ErrorUtil.CODE2001, "设备类型更新失败!", flag);
			return new SysResult(ErrorUtil.CODE2000, "设备类型更改成功ok", zzDeviceType);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设备类型更新失败!", e);
			throw new DoSqlFailedException("设备类型更新失败!", e);
		}
	}

	@Override
	public SysResult selectById(String deviceTypeId) {

		try {
			ZZDeviceTypeExample example = new ZZDeviceTypeExample();
			example.createCriteria().andDevicetypeIdEqualTo(deviceTypeId);
			List<ZZDeviceType> list = zzDeviceTypeMapper.selectByExample(example);
			if (list != null)
				return new SysResult(ErrorUtil.CODE2000, "查询设备类型成功ok", list);
			return new SysResult(ErrorUtil.CODE2001, "查询设备类型失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询失败!", e);
			throw new DoSqlFailedException("查询失败!", e);
		}

	}

	/**
	 * 对设备类型表进行分页处理 关键字 第几页 每页显示几条
	 */
	@Override
	public SysResult ListZZDeviceTypePage(String keyword, Integer page, Integer pageSize) {
		PageObject<ZZDeviceType> pageObject = null;
		// 判断参数
		if (null == keyword || keyword.equals("")) {
			keyword = "%%";
		} else {
			keyword = "%" + keyword + "%";
		}
		if (null == page || page == 0)
			page = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 11;

		// 从第几条开始
		int rowNum = (page - 1) * pageSize;

		try {
			if (null == keyword || keyword.equals("")) {
				// 先进行无关键字查询 rowCount总条数
				int rowCount = zzDeviceTypeMapper.getAllCount();
				if (rowCount > 0) {
					List<ZZDeviceType> zzDeviceTypeList = zzDeviceTypeMapper.selectZZDeviceTypeList(rowNum, pageSize);
					pageObject = new PageObject<ZZDeviceType>(page, pageSize, rowCount, zzDeviceTypeList);
				}
				return new SysResult(ErrorUtil.CODE2000, "ok", pageObject);
			} else {
				int rowCount = zzDeviceTypeMapper.getCountByKeyWord(keyword);
				if (rowCount > 0) {
					List<ZZDeviceType> zzDeviceTypeList = zzDeviceTypeMapper.selectZZDeviceTypeListByKeyword(keyword,
							rowNum, pageSize);
					pageObject = new PageObject<ZZDeviceType>(page, pageSize, rowCount, zzDeviceTypeList);
				}
				return new SysResult(ErrorUtil.CODE2000, "ok", pageObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("模块数据查询失败!", e);
			throw new DoSqlFailedException("模块数据查询失败!请重试", e);
		}
	}

	/**
	 * 查询所有设备种类 集合
	 */
	@Override
	public SysResult selectAll() {
		try {
			List<ZZDeviceType> zzDeviceTypeList = zzDeviceTypeMapper.selectAll();
			if (zzDeviceTypeList != null)
				return new SysResult(ErrorUtil.CODE2000, "查询所有设备类型成功ok", zzDeviceTypeList);
			return new SysResult(ErrorUtil.CODE2001, "查询所有设备类型失败", null);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设备类型查询所有失败!", e);
			throw new DoSqlFailedException("设备类型查询所有失败!", e);
		}
	}

	
	/**
	 * 批量删除设备类型  
	 * 循环调用单个删除设备类型的方法即可
	 */
	@Override
	public SysResult batchDel(String ids) {
		
	
			/*String[] id=ids.split(",");			
			//String数组转list集合
			List<String> idToList= Arrays.asList(id);
			
			//对获取到的id进行遍历  循环的调用单个删除设备类型的方法
			for(String deviceTypeId:idToList){
				ZZDeviceType zzDeviceType= zzDeviceTypeMapper.selectByDeviceTypeId(deviceTypeId);
			    this.delZZdeviceType(zzDeviceType);
		}
		
			return new SysResult(ErrorUtil.CODE2000, "批量删除设备类型成功  成功！！", ids);*/
		
		
		
		try {
			String[] id=ids.split(",");			
			//String数组转list集合
			List<String> idToList= Arrays.asList(id);
			
			//对获取到的id进行遍历  循环的调用单个删除设备类型的方法
			for(String deviceTypeId:idToList){
				ZZDeviceType zzDeviceType= zzDeviceTypeMapper.selectByDeviceTypeId(deviceTypeId);
				SysResult sysResult=this.delZZdeviceType(zzDeviceType);
				if(sysResult.getCode().equals("2001"))
					return new SysResult(ErrorUtil.CODE2001, "批量删除设备类型失败！！！",zzDeviceType );
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("批量删除设备类型失败!", e);
			throw new DoSqlFailedException("批量删除设备类型失败!！！", e);
		}
		
			return new SysResult(ErrorUtil.CODE2000, "批量删除设备类型成功  成功！！", ids);
	}
}
