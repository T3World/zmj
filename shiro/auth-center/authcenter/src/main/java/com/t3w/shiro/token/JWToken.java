package com.t3w.shiro.token;

import com.zmj.microservice.common.history.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationToken;

/**
* @description:    java类作用描述
* @author:         umr
* @date:           2019/5/17
*/
public class JWToken implements AuthenticationToken {

    private String token;

    private String userId;

    public JWToken() {
    }

    public JWToken(String token) {
        this.token = token;
        this.userId = JwtUtil.getId(token);
    }

    public void setCredentials(String token) {
        this.token = token;
    }

    public void setPrincipal(String userId) {
        this.userId = userId;
    }

    /**
     * 这里一定要存放userId哟!
     * */
    @Override
    public Object getPrincipal() {
        return userId;
    }

    /**
    * 这里应该是完整的JWT 字符串
    * @return 签发的JWT
    * @date        2019/5/17 10:59
    */
    @Override
    public Object getCredentials() {
        return token;
    }
}
