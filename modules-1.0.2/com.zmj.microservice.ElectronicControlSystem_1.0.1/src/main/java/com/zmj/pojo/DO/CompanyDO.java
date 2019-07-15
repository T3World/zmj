package com.zmj.pojo.DO;

import java.util.List;

public class CompanyDO implements BaseDO{

    private String name;

    private List<WorkfaceDO> workfaceList;

    public CompanyDO() {
    }

    public CompanyDO(String name) {
        this.name = name;
    }

    public CompanyDO(String name, List<WorkfaceDO> workfaceList) {
        this.name = name;
        this.workfaceList = workfaceList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getWorkfaceList() {
        return workfaceList;
    }

    public void setWorkfaceList(List<WorkfaceDO> workfaceList) {
        this.workfaceList = workfaceList;
    }

    @Override
    public List getChildrenList() {
        return this.getWorkfaceList();
    }
}
