package com.zmj.microservice.shearersystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TransferController {

    @RequestMapping("/test")
    public void transfer(HttpServletRequest request, HttpServletResponse response){
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
        String url = "http://47.104.241.168/transfer/transfer.html?" +
                "obj={\"User_Id\":\"" + user_id +
                "\",\"Edit_Flag\":" + edit_flag +
                "}&" +
                "time=" + login_time;
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
