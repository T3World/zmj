package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.FilterDataVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.common.history.util.CommonUtil;
import com.zmj.microservice.shearersystem.controller.LoginResult;
import com.zmj.microservice.shearersystem.controller.UserInfo;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class T2 {


    @Test
    public void transfer(){
        RestTemplate restTemplate = new RestTemplate();
        String loginUrl = "http://47.104.241.168:8086/API/Auth/doLogin?userAccount=admin&userPassword=123456";
        ResponseEntity<String> userString = restTemplate.getForEntity(loginUrl,
                String.class);
        JSONObject result =(JSONObject) JSON.parse(userString.getBody());
        LoginResult loginResult = JSONObject.toJavaObject(result, LoginResult.class);
        UserInfo userInfo = loginResult.getData();
        String user_id = userInfo.getUser_Id();
        Boolean edit_flag = userInfo.getEdit_Flag();
        Long login_time = userInfo.getLogin_Time();
        System.out.println(JSON.toJSONString(loginResult));
    }

    @Test
    public void testCopy(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        SysResult<String> result = new SysResult<>(list);
        result.setData(list);
        System.out.println(JSON.toJSONString(result));
//        list.clear();
        list.add("d");
        System.out.println(JSON.toJSONString(result));

        CommonVO<String> vo = new CommonVO<>();
        String s = new String("abc");
        vo.setValue(s);
        System.out.println(JSON.toJSONString(vo));
        s = new String("bcd");
        System.out.println(JSON.toJSONString(vo));
        s = "abcd";
        System.out.println(JSON.toJSONString(vo));

    }

}
