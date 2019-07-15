package com.zmj.pojo.DO;

import java.util.List;

public class WorkfaceDO implements BaseDO{
    private String name;

    public WorkfaceDO() {
    }

    public WorkfaceDO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List getChildrenList() {
        return null;
    }
}
