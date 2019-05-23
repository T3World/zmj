package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class T12 {

    @Test
    public void testHelloWorld() {
        //1.获取SecurityManager工厂,此处使用INI配置文件初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.得到SecurityManager实例, 并绑定给SecuriyUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject及创建用户名/密码身份验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken zhang = new UsernamePasswordToken("zhang", "123");
        try {
            //4.登录,身份验证
            subject.login(zhang);

        } catch (AuthenticationException e) {
            //5.身份验证失败
        }
        Assert.assertTrue(subject.isAuthenticated());
        //6.退出登录
        subject.logout();
    }
}
