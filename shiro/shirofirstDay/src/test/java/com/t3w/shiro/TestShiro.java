package com.t3w.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TestShiro {

    @Test
    public void testHelloWorld(){
        //1.获取SecurityManager工厂,此处使用ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2. 得到SecurityManager实例,并绑定给SecrutiyUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);
        //3. 得到Subject及创建用户名/密码身份验证Token(即用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken zhang = new UsernamePasswordToken("zhang", "123");
        try {
            //登录,身份验证
            subject.login(zhang);

            subject.hasRole("");
            subject.isPermitted("/lala");
            System.out.println("登陆成功!");
        }catch (AuthenticationException e){
            //身份验证失败
            System.out.println("登陆失败!");
        }

        //断言已经登录
        Assert.assertTrue(subject.isAuthenticated());


        //6. 退出登录

        subject.logout();
    }

    @Test
    public void testAuthorizer() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        long time = format.parse("20190515").getTime();
        System.out.println(time);
    }
}
