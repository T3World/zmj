package com.t3w.shiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.junit.Test;

public class JWTUsernameToken implements RememberMeAuthenticationToken,HostAuthenticationToken {

    @Test
    public void t(){
    }

    /**
     *
     *
     * @return 描述 用户名
     * @date        2019/5/14 14:13
     */
    @Override
    public Object getPrincipal() {
        //TODO 大概就是用户名吧
        return null;
    }

    /**
     * @return 凭证
     * @date        2019/5/14 14:18
     */
    @Override
    public Object getCredentials() {
        //TODO 大概就是角色吧
        return null;
    }

    /**
     * @return 是否是记住我自动登录的
     * @date        2019/5/14 14:19
     */
    @Override
    public boolean isRememberMe() {
        return false;
    }

    @Override
    public String getHost() {
        return null;
    }
}
