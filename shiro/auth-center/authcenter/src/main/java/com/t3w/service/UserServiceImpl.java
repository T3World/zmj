package com.t3w.service;

import com.t3w.shiro.shirospring.mapper.RoleMapper;
import com.t3w.shiro.shirospring.mapper.UserMapper;
import com.t3w.shiro.shirospring.pojo.Role;
import com.t3w.shiro.shirospring.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> getUserResourceById(Integer id) {
        HashSet<String> resources = new HashSet<>();
        Role r;
        String resource;
        String[] split;
        Set<String> roles = getUserRolesById(id);
        for (String role : roles){
            r = roleMapper.selectRoleByName(role);
            resource = r.getResources();
            if (resource!=null){
                split = resource.split(",");
                Collections.addAll(resources,split);
            }
        }
        return resources;
    }

    @Override
    public Set<String> getUserRolesById(Integer id) {
        HashSet<String> result = new HashSet<>();
        String roles = userMapper.getUserRolesById(id);
        String[] split = roles.split(",");
        Collections.addAll(result, split);
        return result;
    }

    @Override
    public User doLoginByUsernamePassword(String username, String password) {
        User user = null;
        try {
             user = userMapper.loginByUsernamePassword(username, password);
        }catch (Throwable t){
            t.printStackTrace();
        }
        return user;
    }
}
