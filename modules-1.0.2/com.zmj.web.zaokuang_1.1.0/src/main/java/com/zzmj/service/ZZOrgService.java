package com.zzmj.service;

import com.zzmj.pojo.entity.ZZOrgEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author hushixian 组织机构Service
 */
public interface ZZOrgService {

	/**
	 * 根据pid，查询到集团,公司集合，返回集合对象，当Pid为0时，说明是集团对象。
	 * 
	 * @param pid 父级ID
	 * @return
	 */
	List<ZZOrgEntity> listOrgTree(String pid);

	/**
     * 根据orgId依次查询组织机构及其父级机构
     * */
	ZZOrgEntity getOrgWithParents(String orgId);
	/**
	 * 新增集团，公司
	 * 
	 * @param orgEntity 实体类
	 * @return
	 */
	SysResult addZZOrgEntity(ZZOrgEntity orgEntity) throws DoSqlFailedException;

	/**
	 * 根据orgId修改组织结构表
	 * 
	 * @param zzOrgEntity d
	 * @return
	 */
	SysResult updateZZOrgById(ZZOrgEntity zzOrgEntity) throws DoSqlFailedException;

	/**
	 * 根据orgId删除组织结构表，如果orgid是集团的话，集团下面有公司就不予删除。没有，则可以删除，物理删除
	 * 
	 * @param zzOrgEntity 组织机构表
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
	
	List<Map<String, Object>> selectByPid();

	Map<String, Object> getOrgByOrgId(String orgId);

	/**
	 * 根据orgId查找对象
	 * 
	 * @param orgId
	 * @return
	 */
	ZZOrgEntity getOrgIdByPId(String orgId);

	/**
	 * 获取所有的集团
	 * @return
	 */
	List<ZZOrgEntity> listAllGroup();

	/**
	 * 对组织机构(集团)进行分页查询
	 * @param keyword   关键字模糊查询
	 * @param page      从第几页开始查
	 * @param pageSize  每页显示几条
	 * @return
	 */
	SysResult listOrgPage(String keyword, Integer page, Integer pageSize);


	/**
	 * 根据groupId查询该集团下所有矿井公司
	 * @param groupId
	 * @return
	 */
	SysResult listOrgsByGroupId(String groupId);


	/**
	 * 获取所选集团下的矿井公司，分页【实用】
	 * @param groupId
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	SysResult listOrgsByGroupIdPage(String groupId, String keyword, Integer pageNo, Integer pageSize);

	/**
	 * 查询组织名称
	 * @param orgId
	 * @return
	 */
	SysResult selectByOrgId(String orgId);
	/**
	 *  根据页面传过来的集团的Id 查询集团下所有的矿井公司的Id
	 * @param orgPid
	 * @return
	 */
	SysResult selectOrgIdsByOrgId(String orgPid);

}
