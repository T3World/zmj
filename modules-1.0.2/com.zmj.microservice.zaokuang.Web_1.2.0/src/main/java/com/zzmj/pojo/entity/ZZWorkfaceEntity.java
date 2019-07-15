package com.zzmj.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ZZWorkfaceEntity
 * @Description: 工作面表实体类
 * @author umr
 * @date 2018年12月7日
 *
 */
public class ZZWorkfaceEntity implements Serializable {

    /**
     * @Fields 序列化UID
     */
    private static final long serialVersionUID = 3998222860934107749L;

    /**
     * ZZ_Workface.Id (主键)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String id;

    /**
     * ZZ_Workface.Workface_Id (工作面Id)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String workfaceId;

    /**
     * ZZ_Workface.Org_Id (工作面所在组织机构的Id)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String orgId;

    /**
     * ZZ_Workface.Workface_Name (工作面名称)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String workfaceName;

    /**
     * ZZ_Workface.Workface_Alias (工作面代号)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String workfaceAlias;

    /**
     * ZZ_Workface.Workface_Type (工作面类型:0:自动化工作面;1:智能化工作面)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Integer workfaceType;

    /**
     * ZZ_Workface.Workface_State (工作面状态:0:停用;1:启用)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Integer workfaceState;

    /**
     * ZZ_Workface.CreatePerson (创建人)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String createperson;

    /**
     * ZZ_Workface.CreateTime (创建时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Date createtime;

    /**
     * ZZ_Workface.UpdateTime (修改时间)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Date updatetime;

    /**
     * ZZ_Workface.SortCode (排序码)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String sortcode;

    /**
     * ZZ_Workface.Status (状态码)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private String status;

    /**
     * ZZ_Workface.IsDel (是否删除;0,未删除;1删除)
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    private Integer isdel;

    /**
     * @Fields: 用于关联查询返回工作面配置参数
     */
    private ZZWorkfaceconfigEntity zzWorkfaceconfigEntity;

    /**
     * @Fields: 公司名称
     */
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public ZZWorkfaceconfigEntity getZzWorkfaceconfigEntity() {
        return zzWorkfaceconfigEntity;
    }

    public void setZzWorkfaceconfigEntity(ZZWorkfaceconfigEntity zzWorkfaceconfigEntity) {
        this.zzWorkfaceconfigEntity = zzWorkfaceconfigEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}