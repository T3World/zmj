package com.zzmj.service;

import com.zzmj.pojo.entity.ZZDeviceAccumulative;
import com.zzmj.pojo.vo.SysResult;


public interface ZZDeviceAccumulativeService {

	/**
	 * 添加设备累积量表
	 */
    SysResult add(ZZDeviceAccumulative zzDeviceAccumulative);

	/**
	 * 根据Device_Id删除设备累积量表
	 */
    SysResult delete(String deviceId);

	/**
	 * 根据Device_Id更改设备累积量表
	 */
    SysResult update(ZZDeviceAccumulative zzDeviceAccumulative);

	/**
	 * 根据Device_Id查询设备累积量表
	 */
    SysResult selectByDeviceId(String deviceId);
	
}
