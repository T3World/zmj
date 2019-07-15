package com.zzmj.service;

import com.zzmj.pojo.entity.ZZDevice;
import com.zzmj.pojo.vo.DeviceWithAttribute;
import com.zzmj.pojo.vo.SysResult;

/**
 * 
 * @author hushixian 设备Service
 *
 */
public interface ZZDeviceService {

	/**
	 * 新增设备类型
	 * 
	 * @param zzDevice
	 * @return
	 */
	SysResult addZZDevice(ZZDevice zzDevice);

	/**
	 * 根据id删除设备信息表
	 */
	SysResult delZZDevice(ZZDevice zzDevice);

	/**
	 * 根据id修改设备信息表
	 */
	SysResult updateZZdevice(ZZDevice zzDevice);

	/**
	 * 根据id查询当前设备信息
	 * 
	 * @param deviceId
	 * @return
	 */
	SysResult selectByid(String deviceId);

	/**
	 * 根据devicetypeId查询设备集合
	 * 
	 * @param deviceTypeId
	 * @return
	 */
	SysResult selectByDeviceTypeId(String deviceTypeId);

	/**
	 * 对设备表进行分页查询
	 */
	SysResult listZZDevicePage(String devicetypeId, String keyword, Integer pageNo, Integer pageSize);

	/**
	 * 根据工作面id 设备类型id 查询所有设备集合
	 */
	SysResult selectDeviceByWorkfaceAndDeviceTypeId(String workfaceId, String deviceTypeId);

	/**
	 * 根据设备类型id批量删除
	 * 
	 * @param ids 形如id,id,id 这样的字符串
	 * @return
	 */
	SysResult batchDel(String ids);

	/**
	 * 连表查询attribute
	 * */
	SysResult listDeviceAttributeAllByDeviceId(String deviceId);

    /**
     * 根据deviceId查询device和attribute
     * */
    SysResult selectDeviceWithAttributeByDeviceId(String deviceId);

    /**
     * 新增设备及其属性
     * */
    SysResult addDeviceWithAttribute(DeviceWithAttribute deviceWithAttribute);
    /**
     * 更改设备及其属性
     * */
    SysResult updateDeviceWithAttributeByDeviceId(DeviceWithAttribute deviceWithAttribute);
    
    /**
     * 删除设备及其属性   
     */
    SysResult deleteDeviceWithAttributeByDeviceId(DeviceWithAttribute deviceWithAttribute);
    
    /**
     * 查询所有的设备表和每个设备对应的设备属性表
     * @return
     */
	SysResult selectDeviceWithAttributeAll();
	
	public SysResult selectDeviceWithAttributePage(String keyword,Integer pageNo,Integer pageSize);
    
}
