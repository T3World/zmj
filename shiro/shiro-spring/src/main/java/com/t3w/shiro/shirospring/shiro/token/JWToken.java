package com.t3w.shiro.shirospring.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
* @description:    java类作用描述
* @author:         umr
* @date:           2019/5/17
*/
public class JWToken implements AuthenticationToken {

    private final String token;

    public JWToken(String token) {
        this.token = token;
    }

    /**
     * 这里一定要存放userId哟!
     * */
    @Override
    public Object getPrincipal() {
        return null;
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
