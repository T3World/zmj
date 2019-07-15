package com.zzmj.controller.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.LoginService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.EmptyResultException;

/**
 * 
 * @author hushixian umr 用户验证controller
 */
@RestController
@RequestMapping("/API/Auth")
public class AuthController {
    @Autowired
    private LoginService loginservice;

    @RequestMapping("/doLogin")
    public SysResult doLogin(@RequestParam(name="userAccount",required=true) String userAccount,
    		@RequestParam(name="userPassword",required=true) String userPassword) {
        try {
        	SysResult result = loginservice.doLogin(userAccount, userPassword);
        	return result;
        } catch (EmptyResultException e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }
}
