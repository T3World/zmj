package com.t3w.shiro.shirospring.filter;

import com.t3w.shiro.shirospring.shiro.other.ShiroManager;
import com.t3w.shiro.shirospring.shiro.token.JWToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class  StatelessAuthcFilter extends AccessControlFilter {

    private static final String LOGIN_URL="/login";

    @Autowired
    private ShiroManager shiroManager;

    public StatelessAuthcFilter() {
        this.setLoginUrl(LOGIN_URL);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //是否可以绕过该过滤器,false就执行onAccessDenied方法
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1.获取token
        HttpServletRequest r = (HttpServletRequest) request;
        String authentication = r.getHeader("Authentication");
        if (null == authentication){
            hasNoToken(response);
            return true;
        }else {
            JWToken token = new JWToken(authentication);
            try {
                shiroManager.getSubject().login(token);
                return true;
            }catch (AuthenticationException e){
                tokenDenied(response);
            }
        }
        return false;
    }

    private void hasNoToken(ServletResponse response) throws IOException {
        //doNothing
        System.out.println("hasNoToken!");
//        HttpServletResponse r = (HttpServletResponse) response;
//        r.setStatus(200);
//        r.getWriter().write("hasNoToken!");
    }

    private void tokenDenied(ServletResponse response) throws IOException {
        HttpServletResponse r = (HttpServletResponse) response;
        r.setStatus(200);
        r.getWriter().write("tokenDenied!");
    }

}
