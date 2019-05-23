package com.t3w.shiro.shirospring.service;

import com.t3w.shiro.shirospring.pojo.User;

import java.util.Set;

/**
 * @description:    用户CRUD
 * @author:         umr
 * @date:           2019/5/15
 */
public interface UserService {
    /**
     * 方法实现说明
     * @param id user id
     * @return  resources
     * @date        2019/5/17 9:16
     */
    Set<String> getUserResourceById(Integer id);

    /**
     * 方法实现说明
     * @param id user id
     * @return string 类型的 roles 集合
     * @date        2019/5/17 9:16
     */
    Set<String> getUserRolesById(Integer id);

    /**
     * 方法实现说明
     * @param username 用户名
     * @param password 密码
     * @return
     * @exception
     * @date        2019/5/17 9:16
     */
    User doLoginByUsernamePassword(String username,String password);

}
