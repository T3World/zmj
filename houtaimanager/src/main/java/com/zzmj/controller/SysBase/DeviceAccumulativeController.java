package com.zzmj.controller.SysBase;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZDeviceAccumulative;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceAccumulativeService;
import com.zzmj.util.ErrorUtil;

@RestController
@RequestMapping("/SysBase/DeviceAccumulative")
public class DeviceAccumulativeController {

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ZZDeviceAccumulativeService zzDeviceAccumulativeService;

	@RequestMapping(value = "/addZZDeviceAccumulative", method = RequestMethod.POST)
	public SysResult addZZDeviceAccumulative(ZZDeviceAccumulative zzDeviceAccumulative) {
		try {
			return this.zzDeviceAccumulativeService.add(zzDeviceAccumulative);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/delZZDeviceAccumulative", method = RequestMethod.POST)
	public SysResult delZZDeviceAccumulative(String deviceId) {
		try {									 
			return this.zzDeviceAccumulativeService.delete(deviceId);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/updateZZDeviceAccumulative", method = RequestMethod.POST)
	public SysResult updateZZDeviceAccumulative(ZZDeviceAccumulative zzDeviceAccumulative) {
		try {
			return this.zzDeviceAccumulativeService.update(zzDeviceAccumulative);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/selectByDeviceId", method = RequestMethod.POST)
	public SysResult selectByDeviceId(String deviceId) {
		try {
			return this.zzDeviceAccumulativeService.selectByDeviceId(deviceId);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

}
