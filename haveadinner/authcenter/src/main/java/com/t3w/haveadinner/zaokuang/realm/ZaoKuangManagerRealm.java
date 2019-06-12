package com.t3w.haveadinner.zaokuang.realm;

import com.t3w.haveadinner.pojo.User;
import com.t3w.haveadinner.pojo.UserInfo;
import com.t3w.haveadinner.service.UserService;
import com.t3w.haveadinner.zaokuang.token.ZaoKuangUsernamePasswordToken;
import com.zmj.microservice.common.history.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class ZaoKuangManagerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    public ZaoKuangManagerRealm() {
        super();
        this.setCredentialsMatcher(new AllowAllCredentialsMatcher());
    }

    /**
     * 授权,根据得到的principals,生成AuthorizationInfo,用于授权
     * @return AuthorizationInfo 用于授权
     * @date        2019/5/15 16:18
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //默认是获取第一个
        Integer userId =(Integer) principals.getPrimaryPrincipal();
        Set<String> roles = userService.getUserRolesById(userId);
        Set<String> resources = userService.getUserResourceById(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(resources);
        return info;
    }

    /**
     * 登录 根据得到的token,验证用户身份登录,
     * @return AuthenticationInfo,其中包含了principals,相当于用户的标识,credentials,用户的凭证
     * @exception AuthenticationException 当用户不被允许登录时抛出
     * @date        2019/5/15 16:19
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        //得到用户名
        String username = (String) token.getPrincipal();
        //得到用户密码
        String password =String.valueOf((char[]) token.getCredentials()) ;
        User user = userService.doLoginByUsernamePassword(username, password);
        if (null == user)
            throw new AuthenticationException("用户名密码错误!");


        String jwtoken = JwtUtil.sign(username, String.valueOf(user.getId()));
        //principal
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(String.valueOf(user.getId()));
        userInfo.setUsername(username);
        userInfo.setToken(jwtoken);
        userInfo.setRoles(user.getRoles().split(","));


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        //凭证,shiro的用户登录验证机制是,通过Matcher比较这个凭证和Token的凭证,来确定用户是否能成功登陆?
        //但是登陆不成功会抛出realm不能用的异常....,目前还是存在问题
        //应该重写matcher
        //TODO 这里先拿来存放password,

        info.setCredentials("lule");
        //盐好像是加密密码用的,
        //TODO 搞清楚此处盐的作用
        info.setCredentialsSalt(null);
        //userId作为用户内部权限判断的唯一标识
        info.setPrincipals(new SimplePrincipalCollection(userInfo,getName()));
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ZaoKuangUsernamePasswordToken;
    }

    @Override
    public String getName() {
        return "ZaoKuangManagerRealm";
    }
}
