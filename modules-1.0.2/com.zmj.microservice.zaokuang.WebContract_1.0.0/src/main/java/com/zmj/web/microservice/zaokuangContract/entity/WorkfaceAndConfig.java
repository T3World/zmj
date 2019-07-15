package com.zmj.web.microservice.zaokuangContract.entity;

import java.io.Serializable;
import java.util.Date;

public class WorkfaceAndConfig implements Serializable {

    /**
     * @Fields: {todo}(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -9203275389576017042L;

    private String workfaceId;

    private String orgId;

    private String workfaceName;

    private String workfaceAlias;

    private Integer workfaceType;

    private Integer workfaceState;

    private String createperson;

    private Date createtime;

    private Date updatetime;

    private String sortcode;

    private String status;

    private Integer isdel;

    private String beltType;

    private String fontMinpressure;

    private String fontMaxpressure;

    private String backMinpressure;

    private String backMaxpressure;

    private Integer supportDir;

    private Integer supportCount;

    private Integer conveyorDir;

    private String pressureCharttype;

    private String shearerposCachetime;

    public String getWorkfaceId() {
        return workfaceId;
    }

    public void setWorkfaceId(String workfaceId) {
        this.workfaceId = workfaceId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getWorkfaceName() {
        return workfaceName;
    }

    public void setWorkfaceName(String workfaceName) {
        this.workfaceName = workfaceName;
    }

    public String getWorkfaceAlias() {
        return workfaceAlias;
    }

    public void setWorkfaceAlias(String workfaceAlias) {
        this.workfaceAlias = workfaceAlias;
    }

    public Integer getWorkfaceType() {
        return workfaceType;
    }

    public void setWorkfaceType(Integer workfaceType) {
        this.workfaceType = workfaceType;
    }

    public Integer getWorkfaceState() {
        return workfaceState;
    }

    public void setWorkfaceState(Integer workfaceState) {
        this.workfaceState = workfaceState;
    }

    public String getCreateperson() {
        return createperson;
    }

    public void setCreateperson(String createperson) {
        this.createperson = createperson;
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

    public String getBeltType() {
        return beltType;
    }

    public void setBeltType(String beltType) {
        this.beltType = beltType;
    }

    public String getFontMinpressure() {
        return fontMinpressure;
    }

    public void setFontMinpressure(String fontMinpressure) {
        this.fontMinpressure = fontMinpressure;
    }

    public String getFontMaxpressure() {
        return fontMaxpressure;
    }

    public void setFontMaxpressure(String fontMaxpressure) {
        this.fontMaxpressure = fontMaxpressure;
    }

    public String getBackMinpressure() {
        return backMinpressure;
    }

    public void setBackMinpressure(String backMinpressure) {
        this.backMinpressure = backMinpressure;
    }

    public String getBackMaxpressure() {
        return backMaxpressure;
    }

    public void setBackMaxpressure(String backMaxpressure) {
        this.backMaxpressure = backMaxpressure;
    }

    public Integer getSupportDir() {
        return supportDir;
    }

    public void setSupportDir(Integer supportDir) {
        this.supportDir = supportDir;
    }

    public Integer getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(Integer supportCount) {
        this.supportCount = supportCount;
    }

    public Integer getConveyorDir() {
        return conveyorDir;
    }

    public void setConveyorDir(Integer conveyorDir) {
        this.conveyorDir = conveyorDir;
    }

    public String getPressureCharttype() {
        return pressureCharttype;
    }

    public void setPressureCharttype(String pressureCharttype) {
        this.pressureCharttype = pressureCharttype;
    }

    public String getShearerposCachetime() {
        return shearerposCachetime;
    }

    public void setShearerposCachetime(String shearerposCachetime) {
        this.shearerposCachetime = shearerposCachetime;
    }
}
