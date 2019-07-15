package com.zmj.web.microservice.zaokuangContract.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ZZOrgEntity implements Serializable {

    /**
     * @Fields: {todo}(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 2222058650035171481L;

    /**
     * ZZ_Org.Id (主键)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String id;

    /**
     * ZZ_Org.Org_Id (组织机构Id)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String orgId;

    /**
     * ZZ_Org.Org_PId (组织机构父Id)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String orgPid;

    /**
     * ZZ_Org.Org_Name (组织机构名称)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String orgName;

    /**
     * ZZ_Org.Org_Alias (组织机构别名)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String orgAlias;

    /**
     * ZZ_Org.Org_Info (组织机构简介)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String orgInfo;

    /**
     * ZZ_Org.CreateTime (创建时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Date createtime;

    /**
     * ZZ_Org.UpdateTime (修改时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Date updatetime;

    /**
     * ZZ_Org.SortCode (排序码)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String sortcode;

    /**
     * ZZ_Org.Status (状态码)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String status;

    /**
     * ZZ_Org.IsDel (删除标识:0:未删除;1:删除)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Integer isdel;

    /**
     * ZZ_Org.Org_PIds (组织机构所有的父Id,每个Id用","隔开)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String orgPids;

    private List<ZZOrgEntity> orgChildren;

    private Object workfaces;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgPid() {
        return orgPid;
    }

    public void setOrgPid(String orgPid) {
        this.orgPid = orgPid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAlias() {
        return orgAlias;
    }

    public void setOrgAlias(String orgAlias) {
        this.orgAlias = orgAlias;
    }

    public String getOrgInfo() {
        return orgInfo;
    }

    public void setOrgInfo(String orgInfo) {
        this.orgInfo = orgInfo;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSortcode() {
        return sortcode;
    }

    public void setSortcode(String sortcode) {
        this.sortcode = sortcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getOrgPids() {
        return orgPids;
    }

    public void setOrgPids(String orgPids) {
        this.orgPids = orgPids;
    }

    public List<ZZOrgEntity> getOrgChildren() {
        return orgChildren;
    }

    public void setOrgChildren(List<ZZOrgEntity> orgChildren) {
        this.orgChildren = orgChildren;
    }

    public Object getWorkfaces() {
        return workfaces;
    }

    public void setWorkfaces(Object workfaces) {
        this.workfaces = workfaces;
    }

}