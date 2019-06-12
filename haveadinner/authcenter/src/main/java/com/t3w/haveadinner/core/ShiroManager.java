package com.t3w.haveadinner.core;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.WebSecurityManager;

public class ShiroManager {


    private final WebSecurityManager manager;

    public ShiroManager(WebSecurityManager manager) {
        this.manager = manager;
        SecurityUtils.setSecurityManager(manager);
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static org.apache.shiro.mgt.SecurityManager getSecurityManager() throws UnavailableSecurityManagerException {
        return SecurityUtils.getSecurityManager();
    }
}
