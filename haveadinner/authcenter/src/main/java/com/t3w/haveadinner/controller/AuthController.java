package com.t3w.haveadinner.controller;

import com.t3w.haveadinner.exception.TheBadTokenException;
import com.t3w.haveadinner.pojo.UserInfo;
import com.t3w.haveadinner.pojo.vo.CommonResult;
import com.t3w.haveadinner.service.AuthService;
import com.t3w.haveadinner.service.LoginService;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthService authService;

    @RequestMapping("/getTokenByUsernamePassword")
    public CommonResult<UserInfo> getTokenByUsernamePassword(@RequestParam("username") String username,
                                       @RequestParam("password") String password){

        CommonResult<UserInfo> result = null ;
        try {
            UserInfo userInfo = loginService.doLogin(username, password);
            result =  new CommonResult<>(userInfo);
        } catch (IllegalParamException e) {
            e.printStackTrace();
            result = new CommonResult<>(400,"用户名密码错误",null);
        }
        return result;
    }

    @RequestMapping("/verifyToken")
    public CommonResult verifyToken(@RequestParam("token") String token){
        CommonResult<UserInfo> result = null ;
        try {
            UserInfo userInfo = authService.verfyToken(token);
            result = new CommonResult<>(userInfo);
        } catch (TheBadTokenException e) {
            e.printStackTrace();
            result = new CommonResult<>(400,e.getMessage(),null);
        }
        return result;
    }
}
