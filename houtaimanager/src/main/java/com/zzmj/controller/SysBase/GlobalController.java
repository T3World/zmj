package com.zzmj.controller.SysBase;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZGlobalEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZGlobalService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@RestController
@RequestMapping("/SysBase/Global")
public class GlobalController {
    Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private ZZGlobalService globalService;

    @RequestMapping(value = "/saveGlobalParameters", method = RequestMethod.POST)
    public SysResult saveGlobalParameters(ZZGlobalEntity zzGlobalEntity) {
        try {
            return this.globalService.saveGlobalParam(zzGlobalEntity);
        } catch (DoSqlFailedException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/select")
    public SysResult select(@RequestParam(name="globalId",required=false) String globalId) {
    	try {
    		return this.globalService.select(globalId);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
}
