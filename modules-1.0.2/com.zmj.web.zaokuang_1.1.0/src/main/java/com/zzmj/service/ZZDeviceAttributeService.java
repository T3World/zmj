package com.zzmj.service;

import com.zzmj.pojo.entity.ZZDeviceAttribute;
import com.zzmj.pojo.vo.SysResult;

public interface ZZDeviceAttributeService {

	/**
	 * 添加设备属性信息
	 * 
	 * @param zzDeviceAttribute 设备属性实体类
	 * @return
	 */
	SysResult addZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute);

	/**
	 * 删除设备属性信息
	 * 
	 * @param zzDeviceAttribute 设备属性实体类
	 * @return
	 */
	SysResult delZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute);

	/**
	 * 修改设备属性信息
	 * 
	 * @param zzDeviceAttribute 设备属性实体类
	 * @return
	 */
	SysResult updateZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute);

	/**
	 * 查询设备属性信息
	 * 
	 * @param zzDeviceAttribute 设备属性实体类
	 * @return
	 */
	SysResult selectById(String deviceAttributeId);

	/**
	 * 
	 * @param deviceTypeId 设备类型id
	 * @param keyword      关键字
	 * @param pageNo       页数
	 * @param pageSize     每页显示的数量
	 * @return
	 */
	SysResult listDeviceAttribute(String deviceTypeId, String keyword, Integer pageNo, Integer pageSize);

	
	/**
	 * 批量删除设备属性表中数据
	 * 循环调用单个删除设备属性
	 * @param ids  获取设备属性id的字符串
	 * @return
	 */
	SysResult batDelDeviceAttribute(String ids);
}
