package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

public class TestJwt {
    @Test
    public void test12() throws InterruptedException {
       synchronized (this){
           //超时验证
           // 过期时间
           Date time = new Date(System.currentTimeMillis() + 1000L);
           // 私匙及加密算法
           Algorithm algorithm = Algorithm.HMAC256("umr");
           // 设置header
           HashMap<String, Object> header = new HashMap<>(2);
           header.put("typ","JWT");
           header.put("alg","HS256");
           // 附带username,userId,,Payload生成签名
           String token = JWT.create()
                   .withHeader(header)
                   .withExpiresAt(time)
                   .withClaim("userName", "liukang")
                   .withClaim("userId", "123456")
                   .sign(algorithm);

           this.wait(2000L);
           DecodedJWT jwt = JWT.require(algorithm).build().verify(token);


       }

    }

}
