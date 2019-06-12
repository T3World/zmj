package com.t3w.haveadinner.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.t3w.haveadinner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class PermissionController {

@Autowired
private UserService service;
    @RequestMapping("/test")
    public String test(){
        Set<String> userRolesById = service.getUserRolesById(1);
        return JSONUtils.toJSONString(userRolesById);
    }
}
