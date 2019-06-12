package com.t3w.haveadinner.filter;

import com.t3w.haveadinner.core.ShiroManager;
import com.t3w.haveadinner.token.JWToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @description 过滤器
 *
 * @author umr
 * @date 2019/5/29
 */
@Component
public class  StatelessAuthcFilter extends AccessControlFilter {

    @Value("t3w.filter.loginUrl")
    private static String loginUrl;

    private final ShiroManager shiroManager;

    @Autowired
    public StatelessAuthcFilter(ShiroManager shiroManager) {
        this.shiroManager = shiroManager;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //是否可以绕过该过滤器,false就执行onAccessDenied方法
        return false;
//        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1.获取token
        HttpServletRequest r = (HttpServletRequest) request;
        String authentication = r.getHeader("Authentication");
        if (null == authentication){
            System.out.println("has no token");
            String requestURI = ((HttpServletRequest) request).getRequestURI();
            System.out.println(requestURI);
            if (loginUrl.equals(requestURI))
                return true;
            redirect(request,response);
        }else {
            System.out.println("hasToken!");
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

    private void redirect(ServletRequest request, ServletResponse response) throws IOException {
        //doNothing
        System.out.println("hasNoToken!");
            HttpServletResponse r = (HttpServletResponse) response;
            r.sendRedirect("http://47.104.241.168:80");
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
