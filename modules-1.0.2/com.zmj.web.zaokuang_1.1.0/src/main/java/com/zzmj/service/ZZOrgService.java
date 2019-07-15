package com.zzmj.service;

import java.util.List;
import java.util.Map;

import com.zzmj.pojo.entity.ZZOrgEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

/**
 * 
 * @author hushixian 组织机构Service
 */
public interface ZZOrgService {

	/**
	 * 根据pid，查询到集团,公司集合，返回集合对象，当Pid为0时，说明是集团对象。
	 * 
	 * @param pId 父级ID
	 * @return
	 */
	List<ZZOrgEntity> getOrgList(String pId);

	/**
	 * 新增集团，公司
	 * 
	 * @param zzOrgEntity 实体类
	 * @return
	 */
	SysResult addZZOrgEntity(ZZOrgEntity orgEntity) throws DoSqlFailedException;

	/**
	 * 根据orgId修改组织结构表
	 * 
	 * @param orgId组织结构id
	 * @return
	 */
	SysResult updateZZOrgById(ZZOrgEntity zzOrgEntity) throws DoSqlFailedException;

	/**
	 * 根据orgId删除组织结构表，如果orgid是集团的话，集团下面有公司就不予删除。没有，则可以删除，物理删除
	 * 
	 * @param orgId 组织机构表
	 * @return
	 */
	SysResult delZZOrgEntity(ZZOrgEntity zzOrgEntity) throws DoSqlFailedException;

	/**
	 * 树形结构图
	 */
	List<ZZOrgEntity> getTreeDate();

	/**
	 * 统计orgId等于orgPId的数量，方便判断，集团下面有没有公司，用于删除
	 * 
	 * @param orgId
	 * @return
	 */
	int getCount(String orgId);

	/**
	 * @Title: getAllWorkface
	 * @Description: 条件查询工作面; String类型 nil 表示 空值; int类型 0 表示 空值;
	 * @param orgId 工作面所属的组织机构Id, keyword 查询关键字, pageNumber 分页页码, pageSize 分页单页条数
	 * @return List<ZZWorkfaceEntity> 符合条件的工作面集合
	 */
	SysResult listOrg(String keyword, Integer pageNo, Integer pageSize);

	/**
	 * @Title: listOrgByPid
	 * @Description: 用于前端,返回所有pid=orgid的orglist
	 * @param: @param orgId
	 * @param: @return
	 * @return: List<ZZOrgEntity>
	 * @throws EmptyResultException
	 */
	List<Map<String, Object>> listOrgByPid(String orgId);

	Map<String, Object> getOrgByOrgId(String orgId);

	/**
	 * 根据orgId查找对象
	 * 
	 * @param orgId
	 * @return
	 */
	ZZOrgEntity getOrgIdByPId(String orgId);

}
