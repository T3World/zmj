package com.t3w.haveadinner.service;

import com.t3w.haveadinner.mapper.RoleMapper;
import com.t3w.haveadinner.mapper.UserMapper;
import com.t3w.haveadinner.pojo.Role;
import com.t3w.haveadinner.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Primary
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    public UserServiceImpl() {
    }

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

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}
