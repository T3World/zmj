package com.zzmj.controller.SysBase;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmj.web.microservice.zaokuangContract.SysBase.GlobalContract;
import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZGlobalEntity;
import com.zzmj.service.ZZGlobalService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@RestController
@RequestMapping("/SysBase/Global")
public class GlobalController implements GlobalContract {
	Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private ZZGlobalService globalService;

	@Override
	public SysResult saveGlobalParameters(ZZGlobalEntity zzGlobalEntity) {
		try {
			return this.globalService.saveGlobalParam(zzGlobalEntity);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@Override
	public SysResult select(String globalId) {
		try {
			return this.globalService.select(globalId);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}
}
