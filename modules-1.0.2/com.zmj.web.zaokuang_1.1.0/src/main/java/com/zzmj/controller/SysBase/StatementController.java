package com.zzmj.controller.SysBase;

import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.StatementService;
import com.zzmj.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description 报表相关
 * @author umr
 * @date 2019/7/16
 */
@RestController
@RequestMapping("/SysBase/Statement")
public class StatementController {

    @Autowired
    private StatementService statementService;


    @RequestMapping("/getWorkfaceConfig")
    public SysResult getWorkfaceConfig(@RequestParam("workfaceId") String workfaceId){
        try {
            return statementService.getWorkfaceConfig(workfaceId);
        }catch (Exception e){
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }
}
