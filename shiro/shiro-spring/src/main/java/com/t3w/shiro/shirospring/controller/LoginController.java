package com.t3w.shiro.shirospring.controller;

import com.t3w.shiro.shirospring.pojo.UserInfo;
import com.t3w.shiro.shirospring.service.LoginService;
import com.t3w.shiro.shirospring.service.UserService;
import com.t3w.shiro.shirospring.shiro.other.ShiroManager;
import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @description:    登录
 * @author:         umr
 * @date:           2019/5/17
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroManager manager;
    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public SysResult doLogin(@RequestParam("username") String username,@RequestParam("password") String password){
        ArrayList<UserInfo> list = new ArrayList<>();
        try {
            UserInfo userInfo = loginService.doLogin(username, password);
            list.add(userInfo);
        } catch (IllegalParamException e) {
            e.printStackTrace();
            return new SysResult(ResponseCodeEnum.C_204,"用户名或密码错误!");
        }
        return new SysResult(ResponseCodeEnum.C_200,"登陆成功!",list);
    }

    @GetMapping("/find")
    public SysResult find(){
        String principal =(String) manager.getSubject().getPrincipal();
        return new SysResult(ResponseCodeEnum.C_200,principal,null);
    }


}
