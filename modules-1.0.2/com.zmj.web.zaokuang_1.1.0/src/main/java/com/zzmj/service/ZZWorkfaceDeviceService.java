package com.zzmj.service;

import com.zzmj.pojo.dto.WorkfaceDeviceAccumulative;
import com.zzmj.pojo.entity.ZZWorkfaceDevice;
import com.zzmj.pojo.vo.SysResult;

public interface ZZWorkfaceDeviceService {

	/**
	 * x 新增工作面设备信息表
	 * 
	 * @param zzWorkfaceDevice
	 * @return
	 */
	SysResult addZZWorkfaceDevice(ZZWorkfaceDevice zzWorkfaceDevice);

	/**
	 * d 删除工作面设备信息表
	 * 
	 * @param id
	 * @return
	 */
	SysResult delZZWorkfaceDevice(String id);

	/**
	 * u 修改工作面设备信息表
	 * 
	 * @param zzWorkfaceDevice
	 * @return
	 */
	SysResult updateZZWorkfaceDevice(ZZWorkfaceDevice zzWorkfaceDevice);

	/**
	 * s 查询工作面设备信息表
	 * 
	 * @param workfaceDeviceId
	 * @return
	 */
	SysResult selectById(String workfaceDeviceId);
	
	
	/**
     * 根据设备表的workfaceId查询设备表集合
     * 现根据workfaceId查询多个deviceId
     */
	SysResult selectByWorkfaceId(String workfaceId);

	/**
	 * 分页查询workfaceDevice
	 * */

    SysResult listWorkfaceDeviceByPage(String workfaceId,String deviceTypeId, Integer pageNo, Integer pageSize);

    SysResult selectWorkfaceDeviceInfo(String workfaceId,String deviceTypeId, String code);

	SysResult selectWorkfaceDeviceInfoSimple(String workfaceId,String deviceTypeId, String code);

    SysResult updateDeviceAccumulative(WorkfaceDeviceAccumulative workfaceDeviceAccumulative);
	/**
	 * 批量删除设备属性表中数据
	 * 
	 * @param ids  获取设备属性id的字符串
	 * @return
	 */
	SysResult batchDel(String ids);
}
