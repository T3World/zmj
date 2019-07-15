package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T2 {


    @Test
    public void transfer(){
//        RestTemplate restTemplate = new RestTemplate();
//        String loginUrl = "http://47.104.241.168:8086/API/Auth/doLogin?userAccount=admin&userPassword=123456";
//        ResponseEntity<String> userString = restTemplate.getForEntity(loginUrl,
//                String.class);
//        JSONObject result =(JSONObject) JSON.parse(userString.getBody());
//        LoginResult loginResult = JSONObject.toJavaObject(result, LoginResult.class);
//        UserInfo userInfo = loginResult.getData();
//        String user_id = userInfo.getUser_Id();
//        Boolean edit_flag = userInfo.getEdit_Flag();
//        Long login_time = userInfo.getLogin_Time();
//        System.out.println(JSON.toJSONString(loginResult));
    }
    @Test
    public void time() throws ParseException {
        String day = "2019/06/09";
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(new Date().getTime());
        System.out.println(format.parse(day).getTime());
    }
}
