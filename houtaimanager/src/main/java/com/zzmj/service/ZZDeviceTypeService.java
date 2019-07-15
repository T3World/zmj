package com.zzmj.service;

import com.zzmj.pojo.entity.ZZDeviceType;
import com.zzmj.pojo.vo.SysResult;

public interface ZZDeviceTypeService {

	/**
	 * 增加设备类型 zzDeviceType 实体类
	 */

	SysResult addZZdeviceType(ZZDeviceType zzDeviceType);

	/**
	 * 删除设备类型
	 */
	SysResult delZZdeviceType(ZZDeviceType zzDeviceType);

	/**
	 * 修改设备类型
	 */
	SysResult updateZZdeviceType(ZZDeviceType zzDeviceType);

	/**
	 * 查找设备类型
	 */
	SysResult selectById(String deviceTypeId);
	
	/**
	 * 对设备类型表进行分页
	 * 关键字模糊查询
	 * 从第几页开始查
	 * 每页显示几条
	 */
	SysResult ListZZDeviceTypePage(String keyword,Integer page,Integer pageSize);

	/**
	 * 查询所有设备类型
	 */
	SysResult selectAll();
	
	/**
	 * 批量删除设备类型
	 * @param ids
	 * @return
	 */
	SysResult batchDel(String ids);
}
