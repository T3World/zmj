package com.zzmj.controller.SysBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmj.web.microservice.zaokuangContract.SysBase.ModuleContract;
import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZModuleEntity;
import com.zzmj.service.ZZModuleService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

/**
 *
 * @author hushixian umr
 *
 */
@RestController
public class ModuleController implements ModuleContract {
	@Autowired
	private ZZModuleService zzModuleService;

	@Override
	public SysResult addModule(ZZModuleEntity zzModuleEntity) {
		try {
			return zzModuleService.addModule(zzModuleEntity);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@Override
	public SysResult delModule(@RequestParam(name = "mId", required = true) String mId) {
		try {
			return zzModuleService.delModule(mId);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}

	}

	@Override
	public SysResult updateModule(ZZModuleEntity zzModuleEntity) {
		try {
			return zzModuleService.updateModule(zzModuleEntity);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@Override
	public SysResult listModule(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		try {
			return zzModuleService.listModule(keyword, pageNo, pageSize);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

}
