package com.zzmj.controller.SysBase;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZDeviceAttribute;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceAttributeService;
import com.zzmj.util.ErrorUtil;

@RestController
@RequestMapping("/SysBase/DeviceAttribute")
public class DeviceAttributeController {

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ZZDeviceAttributeService zzDeviceAttributeService;

	@RequestMapping(value = "/addZZDeviceAttribute", method = RequestMethod.POST)
	public SysResult addZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute) {

		try {
			return this.zzDeviceAttributeService.addZZDeviceAttribute(zzDeviceAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/delZZDeviceAttribute", method = RequestMethod.POST)
	public SysResult delZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute) {
		try {
			return this.zzDeviceAttributeService.delZZDeviceAttribute(zzDeviceAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/updateZZDeviceAttribute", method = RequestMethod.POST)
	public SysResult updateZZDeviceAttribute(ZZDeviceAttribute zzDeviceAttribute) {
		try {
			return this.zzDeviceAttributeService.updateZZDeviceAttribute(zzDeviceAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/selectById", method = RequestMethod.POST)
	public SysResult selectById(String deviceAttributeId) {
		try {
			return this.zzDeviceAttributeService.selectById(deviceAttributeId);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/getDeviceAttributePage", method = RequestMethod.POST)
	public SysResult getDeviceAttributePage(@RequestParam(name = "deviceTypeId", required = false) String deviceTypeId,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		try {
			return this.zzDeviceAttributeService.listDeviceAttribute(deviceTypeId, keyword, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}
	
	
	
	@RequestMapping(value = "/batDelDeviceAttribute", method = RequestMethod.POST)
	public SysResult batDelDeviceAttribute(@RequestParam(name = "ids") String ids){
		try {
			return this.zzDeviceAttributeService.batDelDeviceAttribute(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), ids);
		}
	}
	
	
}
