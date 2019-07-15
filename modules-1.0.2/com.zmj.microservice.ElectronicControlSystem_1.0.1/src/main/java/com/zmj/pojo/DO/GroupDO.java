package com.zmj.pojo.DO;

import java.util.List;

public class GroupDO implements BaseDO{

    private String name;

    private List<CompanyDO> companyList;

    public GroupDO() {
    }

    public GroupDO(String name) {
        this.name = name;
    }

    public GroupDO(String name, List<CompanyDO> companyList) {
        this.name = name;
        companyList = companyList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompanyDO> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyDO> companyList) {
        this.companyList = companyList;
    }

    @Override
    public List getChildrenList() {
        return this.getCompanyList();
    }
}
