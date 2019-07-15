package com.zmj.web.microservice.zaokuangContract.entity;

import java.io.Serializable;

public class RoleModuleResult implements Serializable {

    private static final long serialVersionUID = 6080506117564829230L;
    private String RoleId;
    private String[] ModuleIds;

    public RoleModuleResult() {
        super();
    }

    public RoleModuleResult(String roleId) {
        super();
        RoleId = roleId;
    }

    public RoleModuleResult(String[] moduleIds) {
        super();
        ModuleIds = moduleIds;
    }

    public RoleModuleResult(String roleId, String[] moduleIds) {
        super();
        RoleId = roleId;
        ModuleIds = moduleIds;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String[] getModuleIds() {
        return ModuleIds;
    }

    public void setModuleIds(String[] moduleIds) {
        ModuleIds = moduleIds;
    }

}
