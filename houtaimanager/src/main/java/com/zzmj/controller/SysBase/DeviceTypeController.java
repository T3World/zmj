package com.zzmj.controller.SysBase;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZDeviceType;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceTypeService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@RestController
@RequestMapping("/SysBase/DeviceType")
public class DeviceTypeController {
	Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ZZDeviceTypeService deviceTypeService;

	@RequestMapping(value = "/addZZdeviceType", method = RequestMethod.POST)
	public SysResult addZZdeviceType(ZZDeviceType zzDeviceType) {
		try {
			return this.deviceTypeService.addZZdeviceType(zzDeviceType);			
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}

	}

	@RequestMapping(value = "/delZZdeviceType", method = RequestMethod.POST)
	public SysResult delZZdeviceType(ZZDeviceType zzDeviceType) {
		try {
			return this.deviceTypeService.delZZdeviceType(zzDeviceType);
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/updateZZdeviceType", method = RequestMethod.POST)
	public SysResult updateZZdeviceType(ZZDeviceType zzDeviceType) {
		try {
			return this.deviceTypeService.updateZZdeviceType(zzDeviceType);
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/selectById", method = RequestMethod.POST)
	public SysResult selectById(String deviceTypeId) {
		try {
			return this.deviceTypeService.selectById(deviceTypeId);
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}
	
	/**
	 * 对设备类型分页调用
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/listDevicePage",method = RequestMethod.POST)
    public SysResult listDevicePage(@RequestParam(name="keyword",required=false) String keyword,
                                @RequestParam(name="pageNo",required=false) Integer pageNo,
                                @RequestParam(name="pageSize",required=false) Integer pageSize) {
        try {
            return deviceTypeService.ListZZDeviceTypePage(keyword, pageNo, pageSize);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }
	
	/**
	 * 查询所有设备类型(集合)--调用
	 */
	@RequestMapping(value = "/selectDeviceTypeAll", method = RequestMethod.POST)
	public SysResult deviceTypeList(){
		try {
			return deviceTypeService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}
	
	
	@RequestMapping(value = "/batDelZZdeviceType", method = RequestMethod.POST)
	public SysResult batDelZZdeviceType(@RequestParam(name = "ids", required = false) String ids) {
		try {
			return this.deviceTypeService.batchDel(ids);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), ids);
		}
	}
}
