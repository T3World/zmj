package com.zzmj.controller.SysBase;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZWorkfaceAccumulative;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceAccumulativeService;
import com.zzmj.util.ErrorUtil;

@RestController
@RequestMapping("/SysBase/WorkfaceAccumulative")
public class WorkfaceAccumulativeController {

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ZZWorkfaceAccumulativeService workfaceAccumulativeService;

	@RequestMapping(value = "/addZZWofkfaceAccumulative", method = RequestMethod.POST)
	public SysResult addZZWofkfaceAccumulative(ZZWorkfaceAccumulative zzWorkfaceAccumulative) {
		try {
			return this.workfaceAccumulativeService.addZZWorkfaceAccumulative(zzWorkfaceAccumulative);
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/delZZWofkfaceAccumulative", method = RequestMethod.POST)
	public SysResult delZZWofkfaceAccumulative(ZZWorkfaceAccumulative zzWorkfaceAccumulative) {
		try {
			return this.workfaceAccumulativeService.delZZWorkfaceAccumulative(zzWorkfaceAccumulative);
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/updateZZWofkfaceAccumulative", method = RequestMethod.POST)
	public SysResult updateZZWofkfaceAccumulative(ZZWorkfaceAccumulative zzWorkfaceAccumulative) {
		try {
			return this.workfaceAccumulativeService.updateZZWorkfaceAccumulative(zzWorkfaceAccumulative);
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/selectWorkfaceAccumuLativeByWorkfaceId", method = RequestMethod.POST)
	public SysResult selectById(String workfaceId) {
		try {
			return this.workfaceAccumulativeService.selectById(workfaceId);
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

}
