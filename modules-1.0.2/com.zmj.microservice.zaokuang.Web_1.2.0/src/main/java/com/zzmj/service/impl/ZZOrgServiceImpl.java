package com.zzmj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZOrgEntity;
import com.zzmj.mapper.ZZOrgMapper;
import com.zzmj.pojo.entity.ZZOrgExample;
import com.zzmj.pojo.entity.ZZWorkfaceEntity;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.service.ZZOrgService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

@Service("ZZOrgService")
public class ZZOrgServiceImpl implements ZZOrgService {

	private static final Logger logger = LoggerFactory.getLogger(ZZOrgServiceImpl.class);

	@Autowired
	private ZZOrgMapper zzorgMapper;

	@Autowired
	private ZZWorkfaceServiceImpl zzWorkfaceImpl;

	/**
	 * 根据pid为0，查询到集团集合，返回集合对象。
	 */
	@Override
	public List<ZZOrgEntity> getOrgList(String pId) {
		ZZOrgExample example = new ZZOrgExample();
		List<ZZOrgEntity> result = new ArrayList<ZZOrgEntity>();
		example.createCriteria().andOrgPidEqualTo(pId);
		example.createCriteria().andIsdelNotEqualTo(1);
		example.setOrderByClause(new ZZOrgEntity().getSortcode());// null!
		result = zzorgMapper.selectByExample(example);
		if (result.size() > 0) {
			return result;
		} else {
			return null;
		}

	}

	/**
	 * 新增集团，公司
	 * 
	 * @param zzOrgEntity 实体类
	 * @return
	 */
	@Override
	public SysResult addZZOrgEntity(ZZOrgEntity orgEntity) throws DoSqlFailedException {
		SysResult sysResult = new SysResult();
		try {
			ZZOrgEntity zzOrgEntity = new ZZOrgEntity(); // 新建实体类名称
			zzOrgEntity.setId(CodeUtil.createUuid36()); // Id 没有作用
			zzOrgEntity.setOrgId(CodeUtil.createUuid36()); // Orgid 集团id，公司id
			zzOrgEntity.setOrgAlias(orgEntity.getOrgAlias()); // 公司，集团别称
			zzOrgEntity.setOrgInfo(orgEntity.getOrgInfo().trim()); // 公司，集团简介
			zzOrgEntity.setOrgName(orgEntity.getOrgName()); // 公司，集团名称
			zzOrgEntity.setSortcode(orgEntity.getSortcode()); // 排序码
			zzOrgEntity.setIsdel(0); // 是否删除，0是默认值（没有删除），1为删除。
			zzOrgEntity.setUpdatetime(new Date());
			if (orgEntity.getOrgPid().equals("") || null == orgEntity.getOrgPid()) {
				zzOrgEntity.setOrgPid("0");
			} else {
				zzOrgEntity.setOrgPid(getOrgList("0").get(0).getOrgId());
			}
			zzOrgEntity.setCreatetime(new Date());
			int result = zzorgMapper.insert(zzOrgEntity); // 调用mapper里面方法，数据库操作
			if (result > 0) {
				return new SysResult(ErrorUtil.CODE2000, "组织结构添加成功", zzOrgEntity);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "组织结构添加失败", null);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("组织机构添加失败出现异常", e);
			throw new DoSqlFailedException("组织机构添加失败");
		}

	}

	/**
	 * 根据orgId修改组织结构表
	 * 
	 * @param orgId组织结构id
	 * @return
	 */
	@Override
	public SysResult updateZZOrgById(ZZOrgEntity orgEntity) throws DoSqlFailedException {
		SysResult sysResult = new SysResult();
		try {
			ZZOrgEntity zzOrgEntity = new ZZOrgEntity();
			String orgId = orgEntity.getOrgId(); // 得到orgId
			zzOrgEntity.setOrgAlias(orgEntity.getOrgAlias()); // 公司，集团别称
			zzOrgEntity.setOrgInfo(orgEntity.getOrgInfo()); // 公司，集团简介
			zzOrgEntity.setOrgName(orgEntity.getOrgName()); // 公司，集团名称
			zzOrgEntity.setSortcode(orgEntity.getSortcode()); // 排序码
			if (null == orgEntity.getIsdel()) {
				zzOrgEntity.setIsdel(0); // 是否删除，0是默认值（没有删除），1为删除。
			} else {
				zzOrgEntity.setIsdel(orgEntity.getIsdel()); // 是否删除，0是默认值（没有删除），1为删除。
			}
			zzOrgEntity.setCreatetime(new Date());
			ZZOrgExample example = new ZZOrgExample();
			example.createCriteria().andOrgIdEqualTo(orgId);
			int result = zzorgMapper.updateByExampleSelective(zzOrgEntity, example);
			if (result > 0) {
				return new SysResult(ErrorUtil.CODE2000, "组织结构修改成功", zzOrgEntity);
			} else {
				return new SysResult(ErrorUtil.CODE2000, "组织结构修改失败", null);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("组织机构修改失败，服务器异常" + e);
			throw new DoSqlFailedException("服务器异常");
		}
	}

	/**
	 * 根据orgId删除组织结构表，如果orgid是集团的话，集团下面有公司就不予删除。没有，则可以删除，
	 * 
	 * @param orgId 组织机构表
	 * @return
	 */
	@Override
	public SysResult delZZOrgEntity(ZZOrgEntity orgEntity) {
		SysResult result = new SysResult();
		String orgId = orgEntity.getOrgId(); // 得到orgId
		try {
			int count = getCount(orgId);
			if (count > 0) {
				// ，删除失败
				return new SysResult(ErrorUtil.CODE2001, "该集团下面有公司,请先把该集团下面的公司删除之后,才能执行这步操作", null);
			} else if (count == 0) {
				// ，就证明不是集团,是公司，调用删除公司的方法，删除公司
				if (this.delCmp(orgEntity, orgId)) {
					// true;能删除
					orgEntity.setIsdel(1);
					SysResult sysResult = updateZZOrgById(orgEntity); //
					if (sysResult.getData() != null) {
						return new SysResult(ErrorUtil.CODE2000, "删除成功", sysResult);
					}
				} else {
					return new SysResult(ErrorUtil.CODE2001, "该公司下面有工作面,请先把该公司下面的工作面删除之后,才能执行这步操作", null);
				}
			}
		} catch (DoSqlFailedException e) {
			logger.info("服务器异常,数据库操作。");
			e.printStackTrace();
			throw new DoSqlFailedException("服务器异常,数据库操作");
		}
		return result;
	}

	/**
	 * 删除公司判断下面有没有工作面
	 * 
	 * @param orgId
	 * @return
	 */
	public boolean delCmp(ZZOrgEntity orgEntity, String orgId) {
		if (orgId.equals("") || null == orgId) {
			orgId = orgEntity.getOrgId();
		} else {
			orgId = orgId;
		}
		List<ZZWorkfaceEntity> wokefaceList;
		try {
			wokefaceList = ((PageObject) zzWorkfaceImpl.listWorkface(orgId, null, 1, 10).getData()).getRecords();
			if (wokefaceList.size() > 0) {
				return false;
			} else {
				return true;
			}
		} catch (EmptyResultException e) {
			logger.info("删除公司出现异常" + e);
			throw new DoSqlFailedException("删除公司出现异常");
//			return true;
		}

	}

	/**
	 * 列表里面渲染的值
	 */
	@Override
	public List<ZZOrgEntity> getTreeDate() {
		ZZOrgExample example = new ZZOrgExample();
		example.createCriteria().andIsdelEqualTo(0);
		example.setOrderByClause("sortcode");
		List<ZZOrgEntity> list = zzorgMapper.selectByExample(example);
		return list;
	}

	/**
	 * 统计orgId等于orgPId的数量，方便判断，集团下面有没有公司，用于删除
	 * 
	 * @param orgId
	 * @return
	 */
	@Override
	public int getCount(String orgId) {
		int a = zzorgMapper.getCount(orgId);
		return a;
	}

	@Override
	public SysResult listOrg(String keyWord, Integer pageNo, Integer pageSize) {
		try {
			// int rowCount = 0;
			if (null == pageNo || pageNo == 0) {
				pageNo = 1;
			} else {
				pageNo = pageNo;
				// rowCount = (pageNo - 1) * pageSize;
			}
			if (null == pageSize || pageSize == 0) {
				pageSize = 11;
			} else {
				pageSize = pageSize;
			}
			if (keyWord == null || keyWord.equals("")) {
				keyWord = "%%";
			} else {
				keyWord = "%" + keyWord + "%";
			}
			Integer rowNo = (pageNo - 1) * pageSize;
			List<ZZOrgEntity> list = zzorgMapper.getlikeKeyWord(keyWord, rowNo, pageSize);
			int rowCount = zzorgMapper.allCount();
			return new SysResult(ErrorUtil.CODE2000, "查询成功",
					new PageObject<ZZOrgEntity>(pageNo, pageSize, rowCount, list));
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("分页查询出现异常查询失败" + e);
			throw new DoSqlFailedException("分页查询出现异常查询失败");
		}

	}

	@Override
	public List<Map<String, Object>> listOrgByPid(String orgId) {
		List<Map<String, Object>> orglist = this.zzorgMapper.listOrgByPId(orgId);
		return orglist;
	}

	@Override
	public Map<String, Object> getOrgByOrgId(String orgId) {
		Map<String, Object> entity = this.zzorgMapper.getOrgByOrgId(orgId);
		return entity;
	}

	@Override
	public ZZOrgEntity getOrgIdByPId(String orgId) {
		ZZOrgEntity orgEntity = this.zzorgMapper.getOrgIdByPId(orgId);
		return orgEntity;
	}

}
