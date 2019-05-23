package com.t3w.shiro.shirospring;

import com.zmj.microservice.common.history.pojo.VO.SysResult;

public class Father {

    Father() {
        System.out.println("cons");
        a();
    }
    public void a(){
        System.out.print("Father");
    }
}
