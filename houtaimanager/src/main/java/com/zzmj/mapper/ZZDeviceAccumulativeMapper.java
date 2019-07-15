package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZDeviceAccumulative;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ZZDeviceAccumulativeMapper {
   
	/**
	 * 添加设备累积量表
	 */
    int add(ZZDeviceAccumulative zzDeviceAccumulativeMapper);

	/**
	 * 根据Device_Id删除设备累积量表
	 */
    int delete(String deviceId);

	/**
	 * 根据Device_Id更改设备累积量表
	 */
    int update(ZZDeviceAccumulative zzDeviceAccumulativeMapper);

	/**
	 * 根据Device_Id查询设备累积量表
	 */
    List<ZZDeviceAccumulative> selectByDeviceId(String deviceId);

    /**
	 * count * by deviceId
	 * */
    int countByDeviceId(@Param("deviceId") String deviceId);
}