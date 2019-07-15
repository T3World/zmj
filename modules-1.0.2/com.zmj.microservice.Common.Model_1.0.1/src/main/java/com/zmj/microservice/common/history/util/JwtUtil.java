package com.zmj.microservice.common.history.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

/**
 * @description:    JWT工具类,用于签发token,验证token
 * @author:         umr
 * @date:           2019/4/9
 */
public class JwtUtil {

    private static final long EXPIRES_TIME = 15 * 60 * 1000;

    private static final String TOKEN_SECRET = "THETHIRDWORLD";

    /**
     * 基于用户名,用户ID
     * 签发token
     * 签发失败返回null
     * */
    public static String sign(String userName,String userId){
        try{
            // 过期时间
            Date time = new Date(System.currentTimeMillis() + EXPIRES_TIME);
            // 私匙及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置header
            HashMap<String, Object> header = new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            // 附带tringusername,userId,,Payload生成签名
            return JWT.create()
                    .withHeader(header)
                    .withExpiresAt(time)
                    .withClaim("userName",userName)
                    .withClaim("userId",userId)
                    .withClaim("tokenId",UUID.randomUUID().toString())
                    .sign(algorithm);

        }catch (JWTCreationException e){
            return null;
        }
    }
    /**
     * 更灵活的签发方式
     * 签发token
     * */
    public static String sign(Map<String,String> map){
        try{
            // 过期时间
            Date time = new Date(System.currentTimeMillis() + EXPIRES_TIME);
            // 私匙及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置header
            HashMap<String, Object> header = new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            // 设置Payload
            JWTCreator.Builder builder = JWT.create()
                    .withHeader(header)
                    .withExpiresAt(time);

            Set<String> keySet = map.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                builder.withClaim(key,map.get(key));
            }

            return builder.sign(algorithm);
        }catch (JWTCreationException e){
            return null;
        }
    }

    /**
     * 校验token是否伪造
     *
     * @param token token
     * @return 是否正确
     * */
    public static boolean verify(String token){
        try {
            decodedJWT(token);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }


    /**
     * 解析token
     * 获取header中的Type
     * */
    public static String getType(String token){
        try {
            return decodedJWT(token).getType();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取header中的KeyId
     * */
    public static String getKeyId(String token){
        try {
            return decodedJWT(token).getKeyId();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义header
     * */
    public static String getHeaderClaim(String token,String claim){
        try {
            return decodedJWT(token).getHeaderClaim(claim).asString();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取header中的Issuer
     * */
    public static String getIssuer(String token){
        try {
            return decodedJWT(token).getIssuer();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取header中的Subject
     * */
    public static String getSubject(String token){
        try {
            return decodedJWT(token).getSubject();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取header中的Audience
     * */
    public static List<String> getAudience(String token){
        try {
            return decodedJWT(token).getAudience();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 返回token过期时间
     * */
    public static Date getExpiresAt(String token){
        try {
            return decodedJWT(token).getExpiresAt();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取token签发时间
     * */
    public static Date getIssuedAt(String token){
        try {
            return decodedJWT(token).getIssuedAt();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取JWTID
     * */
    public static String getId(String token){
        try {
            return decodedJWT(token).getId();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim
     * */
    public static String getClaimAsString(String token,String claim){
        try {
            return decodedJWT(token).getClaim(claim).asString();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim
     * */
    public static Boolean getClaimAsBoolean(String token,String claim){
        try {
            return decodedJWT(token).getClaim(claim).asBoolean();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim
     * */
    public static Integer getClaimAsInteger(String token,String claim){
        try {
            return decodedJWT(token).getClaim(claim).asInt();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim
     * */
    public static Double getClaimAsDouble(String token,String claim){
        try {
            return decodedJWT(token).getClaim(claim).asDouble();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim
     * */
    public static Long getClaimAsLong(String token,String claim){
        try {
            return decodedJWT(token).getClaim(claim).asLong();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim,返回Date值，
     * 如果无法转换，则返回null。
     * 这必须是NumericDate（Unix Epoch / Timestamp）。
     * 请注意，JWT标准指定所有NumericDate值必须以秒为单位。
     * */
    public static Date getClaimAsDate(String token, String claim){
        try {
            return decodedJWT(token).getClaim(claim).asDate();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim
     * */
    public static <T> List<T> getClaimAsLong(String token,String claim,Class<T> t){
        try {
            return decodedJWT(token).getClaim(claim).asList(t);
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim
     * */
    public static <T> T[] getClaimAsArray(String token,String claim,Class<T> t){
        try {
            return decodedJWT(token).getClaim(claim).asArray(t);
        }catch (JWTVerificationException e){
            return null;
        }
    }

    /**
     * 解析token
     * 获取自定义PayloadClaim
     * */
    public static Map<String,Object> getClaimAsArray(String token,String claim){
        try {
            return decodedJWT(token).getClaim(claim).asMap();
        }catch (JWTVerificationException e){
            return null;
        }
    }


    private static DecodedJWT decodedJWT(String token){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        return  jwt;
    }

}
