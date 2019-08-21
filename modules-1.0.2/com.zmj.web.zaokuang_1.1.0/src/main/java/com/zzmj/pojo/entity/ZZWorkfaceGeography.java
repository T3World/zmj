package com.zzmj.pojo.entity;

import java.io.Serializable;

public class ZZWorkfaceGeography implements Serializable {

    /**
     * zz_workfacegeography.Id (Id主键)
     */
    private String id;

    /**
     * zz_workfacegeography.Org_Id (公司Id)
     */
    private String orgId;

    /**
     * zz_workfacegeography.Org_Latitude (公司纬度)
     */
    private String orgLatitude;

    /**
     * zz_workfacegeography.Org_Longitude (公司经度)
     */
    private String orgLongitude;

    /**
     * zz_workfacegeography.SortCode (排序码)
     */
    private String sortCode;

    /**
     * zz_workfacegeography.UpdateTime (更新时间)
     */
    private String updateTime;

    /**
     * zz_workfacegeography.IsDel (删除标志(0:未删除 1:已删除))
     */
    private String isDel;

    /**
     * zzOrgEntity (组织机构实体类)
     */
    private ZZOrgEntity zzOrgEntity;

    public ZZOrgEntity getZzOrgEntity() {
        return zzOrgEntity;
    }

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

    public String getOrgLatitude() {
        return orgLatitude;
    }

    public void setOrgLatitude(String orgLatitude) {
        this.orgLatitude = orgLatitude;
    }

    public String getOrgLongitude() {
        return orgLongitude;
    }

    public void setOrgLongitude(String orgLongitude) {
        this.orgLongitude = orgLongitude;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
}
