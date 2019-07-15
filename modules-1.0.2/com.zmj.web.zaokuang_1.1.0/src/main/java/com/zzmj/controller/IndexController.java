package com.zzmj.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.vo.SysResult;
import com.zzmj.util.ErrorUtil;

@RestController
@RequestMapping("/Admin/index")
public class IndexController {

    @Value("${zmj.loginUrl}")
    private String loginUrl;

    @RequestMapping("/getLoginUrl")
    public SysResult index() {
        System.out.println("loginurl" + loginUrl);
        return new SysResult(ErrorUtil.CODE2000, "ok", loginUrl);
    }
}
