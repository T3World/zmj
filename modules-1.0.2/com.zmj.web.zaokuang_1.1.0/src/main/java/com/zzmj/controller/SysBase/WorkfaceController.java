package com.zzmj.controller.SysBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.WorkfaceAndConfig;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceService;
import com.zzmj.util.ErrorUtil;

/**
 * 
 * @author hushixian umr
 *
 */

@RestController
@RequestMapping("/SysBase/workface")
public class WorkfaceController {
	@Autowired
	private ZZWorkfaceService zzWorkfaceService;

	@RequestMapping(value = "/addZZWorkface", method = RequestMethod.POST)
	public SysResult addZZWorkface(WorkfaceAndConfig wac) {
		try {
			return zzWorkfaceService.addWorkface(wac);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/listWorkface") // , method = RequestMethod.POST
	public SysResult listWorkface(@RequestParam(name="orgId",required=false) String orgId,
			@RequestParam(name="keyword",required=false) String keyword,
			@RequestParam(name="pageNo",required=false) Integer pageNo,
			@RequestParam(name="pageSize",required=false) Integer pageSize) {
		try {
			return this.zzWorkfaceService.listWorkface(orgId, keyword, pageNo, pageSize);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public SysResult updateWorkface(WorkfaceAndConfig wac) {
		try {
			return zzWorkfaceService.updateWorkface(wac);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * @Title: deleteWorkface
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param workfaceId
	 * @param: @return
	 * @return: SysResult
	 */
	@RequestMapping(value = "/deleteWorkface", method = RequestMethod.POST)
	public SysResult deleteWorkface(@RequestParam(name="workfaceId",required=true)String workfaceId) {
		try {
			return zzWorkfaceService.delWorkface(workfaceId);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/stopWorkface", method = RequestMethod.POST)
	public SysResult stopWorkface(@RequestParam(name="workfaceId",required=true) String workfaceId,
			@RequestParam(name="workfaceState",required=true) Integer workfaceState) {
		try {
			return zzWorkfaceService.stopWorkface(workfaceId, workfaceState);
		} catch (RuntimeException e) {
			e.getMessage();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}

	}
}
