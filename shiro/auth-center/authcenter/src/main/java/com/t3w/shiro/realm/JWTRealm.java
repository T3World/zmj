package com.t3w.shiro.realm;

import com.t3w.shiro.shirospring.pojo.UserInfo;
import com.t3w.shiro.shirospring.service.UserService;
import com.t3w.shiro.shirospring.shiro.token.JWToken;
import com.zmj.microservice.common.history.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    public JWTRealm() {
        super();
        this.setCredentialsMatcher(new AllowAllCredentialsMatcher());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = null;
        Set<String> resources = null;
        UserInfo userInfo;
        if (principals.getPrimaryPrincipal() instanceof UserInfo){

            userInfo = (UserInfo)principals.getPrimaryPrincipal();
            String userId = userInfo.getUserId();
            roles = userService.getUserRolesById(Integer.valueOf(userId));
            resources = userService.getUserResourceById(Integer.valueOf(userId));
        }
        info.setRoles(roles);
        info.setStringPermissions(resources);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //得到用户ID
        //TODO 这里很矛盾,解析token是在这个位置进行的,不行就前台给一个ID?
//        String userId = (String) token.getPrincipal();
        //得到用户token
        String jwtoken =(String) token.getCredentials();

        String userId = JwtUtil.getClaimAsString(jwtoken, "userId");
        if (!JwtUtil.verify(jwtoken))
            throw new AuthenticationException("token过期!请重新登录!");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        //凭证,不知道将来有啥用,先放着
        //TODO 这里先拿来存放JWT吧
        info.setCredentials(jwtoken);
        //盐好像是加密密码用的,
        //TODO 搞清楚此处盐的作用
        info.setCredentialsSalt(null);
        //userId作为用户内部权限判断的唯一标识
        info.setPrincipals(new SimplePrincipalCollection(String.valueOf(userId),getName()));
        return info;
    }

    @Override
    public String getName() {
        return "JWTRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWToken;
    }
}
