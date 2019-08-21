package com.zzmj.service.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZOrgMapper;
import com.zzmj.pojo.entity.ZZOrgEntity;
import com.zzmj.pojo.entity.ZZOrgExample;
import com.zzmj.pojo.entity.ZZWorkfaceEntity;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
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
     * 根据pid,查询组织机构树
     */
    @Override
    public List<ZZOrgEntity> listOrgTree(String pid) {
        List<ZZOrgEntity> orgs = selectByPid(pid);
        bornChildren(orgs);
        return orgs;
    }

    /**
     * listOrgTree 是向下递推,这个方法是向上递推,
     * 无限层级向上寻找,直到pid = 0;
     * */
    @Override
    public ZZOrgEntity getOrgWithParents(String orgId){
        // 根据orgId 查询得到自己
        ZZOrgEntity org = zzorgMapper.getOrgIdByPId(orgId);
        if (org == null){
            return org;
        }else {
           return doParent(org);
        }
    }

    private ZZOrgEntity doParent(ZZOrgEntity org){
        String pid = org.getOrgPid();
        if ("0".equals(pid)){
            return org;
        }else {
            ZZOrgEntity pOrg = zzorgMapper.getOrgIdByPId(pid);
            if (pOrg == null)
                return org;
            LinkedList<ZZOrgEntity> list = new LinkedList<>();
            list.add(org);
            pOrg.setOrgChildren(list);
            return doParent(pOrg);
        }
    }




    private void bornChildren(List<ZZOrgEntity> orgs){
        for (ZZOrgEntity pOrg: orgs){
            String pid = pOrg.getOrgId();
            List<ZZOrgEntity> children = selectByPid(pid);
            pOrg.setOrgChildren(children);
            if (children != null && children.size() != 0){
                bornChildren(children);
            }
        }
    }

    private List<ZZOrgEntity> selectByPid(String pid){
        return zzorgMapper.selectAll(pid);
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
            orgEntity.setId(CodeUtil.createUuid36()); // Id 没有作用
            orgEntity.setOrgId(CodeUtil.createUuid36()); // Orgid 集团id，公司id
            //移除了trim代码,应该放到前端去做
            orgEntity.setIsdel(0); // 是否删除，0是默认值（没有删除），1为删除。
            orgEntity.setUpdatetime(new Date());
            if (orgEntity.getOrgPid().equals("") || null == orgEntity.getOrgPid()) {
                orgEntity.setOrgPid("0");
            }
            orgEntity.setCreatetime(new Date());
            int result = zzorgMapper.insert(orgEntity); // 调用mapper里面方法，数据库操作
            if (result > 0) {
                return new SysResult(ErrorUtil.CODE2000, "组织结构添加成功", result);
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
	public List<Map<String, Object>> selectByPid() {
		return zzorgMapper.selectByPid();
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

    /**
     * 从组织机构中获取所有的集团
     * @return
     */
    @Override
    public List<ZZOrgEntity> listAllGroup() {
        try {
            return this.zzorgMapper.listAllGroup();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("集团信息检索失败，出现异常", e);
            throw new DoSqlFailedException("集团信息检索失败");
        }
    }

    /**
     * 对组织机构(集团)进行分页查询
     * @param keyword   关键字模糊查询
     * @param page      从第几页开始查
     * @param pageSize  每页显示几条
     * @return
     */
    @Override
    public SysResult listOrgPage(String keyword, Integer page, Integer pageSize) {
        PageObject<ZZOrgEntity> pageObject = null;
        if (null == keyword || keyword.equals("")) {
            keyword = "%%";
            //keyword = "";
        } else {
            keyword = "%" + keyword + "%";
        }
        if (null == page || page == 0)
            page = 1;
        if (null == pageSize || pageSize == 0) {
            pageSize = 11;
        }
        int rowNum = (page - 1) * pageSize;
        try {
            if (null == keyword || keyword.equals("")) {
                //先进行无关键字查询，rowCount总条数
                int rowCount = zzorgMapper.getAllCount();
                if (rowCount > 0) {
                    List<ZZOrgEntity> zzOrgEntityList = zzorgMapper.selectOrgList(rowNum, pageSize);
                    pageObject = new PageObject<ZZOrgEntity>(page, pageSize, rowCount, zzOrgEntityList);
                }
                return new SysResult(ErrorUtil.CODE2000, "ok", pageObject);
            } else {
                int rowCount = zzorgMapper.getCountByKeyword(keyword);
                if (rowCount > 0) {
                    List<ZZOrgEntity> zzOrgEntityList = zzorgMapper.selectOrgListByKeyword(keyword, rowNum, pageSize);
                    pageObject = new PageObject<ZZOrgEntity>(page, pageSize, rowCount, zzOrgEntityList);
                }
                return new SysResult(ErrorUtil.CODE2000, "ok", pageObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("模块数据查询失败", e);
            throw new DoSqlFailedException("模块数据查询失败，请重试！");
        }
    }

    /**
     * 根据groupId查询该集团下所有矿井公司
     * @param groupId
     * @return
     */
    @Override
    public SysResult listOrgsByGroupId(String groupId) {
        List<ZZOrgEntity> list = zzorgMapper.listOrgsByGroupId(groupId);
        if (list.size() > 0) {
            return new SysResult(ErrorUtil.CODE2000, "查询指定集团下的矿井公司成功！", list);
        } else {
            return new SysResult(ErrorUtil.CODE2001, "查询指定集团下的矿井公司失败！", null);
        }
    }

    /**
     * 获取所选集团下的矿井公司，分页【实用】
     * @param groupId
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public SysResult listOrgsByGroupIdPage(String groupId, String keyword, Integer pageNo, Integer pageSize) {
        try {
            //分页查询，先查询结果总数
            int count = this.countOrgs(groupId, keyword);
            //为pageNo、pageSize赋默认值
            if (null == pageNo || pageNo == 0)
                pageNo = 1;
            if (null == pageSize || pageSize == 0)
                pageSize = 11;
            //封装到分页查询包装类中
            Integer rowNo = (pageNo - 1) * pageSize;
            List<ZZOrgEntity> list = listOrgsByPage(groupId, keyword, rowNo, pageSize);
            return new SysResult(ErrorUtil.CODE2000, "分页查询成功", new PageObject<ZZOrgEntity>(pageNo, pageSize, count, list));
        } catch (Exception e) {
            logger.error("组织机构信息查询失败！", e);
            throw new DoSqlFailedException("组织机构信息查询失败，抛出异常", e);
        }
    }

    private List<ZZOrgEntity> listOrgsByPage(String groupId, String keyword, Integer rowNo, Integer pageSize) {
        if (null == groupId || groupId.equals("")) {
            if (null == keyword || keyword.equals("")) {
                List<ZZOrgEntity> list = zzorgMapper.selectOrgList2(rowNo, pageSize);
                logger.info(String.valueOf(list.size()));
                return list;
            }
            return zzorgMapper.selectOrgListByKeyword2(keyword, rowNo, pageSize);
        } else {
            if (null == keyword || keyword.equals("")) {
                List<ZZOrgEntity> list = zzorgMapper.listOrgsByGroupId2(groupId, rowNo, pageSize);
                logger.info(String.valueOf(list.size()));
                return list;
            }
        }
        return zzorgMapper.listOrgsByGroupIdAndByKeyword(groupId, keyword, rowNo, pageSize);
    }

    //分页查询的总条数
    private int countOrgs(String groupId, String keyword) {
        if (null == groupId || groupId.equals("")) {
            if (null == keyword || keyword.equals("")) {
                //当groupId、关键字都为空时，查询表中所有个数
                return zzorgMapper.getAllCount2();
            }
            //当groupId为空、关键字不为空时，查询符合关键字的条数
            return zzorgMapper.getCountByKeyword2(keyword);
        }
        //当groupId不为空、关键字为空时，查询符合groupId的条数
        if (null == keyword || keyword.equals("")) {
            return zzorgMapper.getCountByGroupId(groupId);
        }
        //当groupId、关键字都不为空时，查询符合双条件的数量
        return zzorgMapper.getCountByGroupIdAndByKeyword(groupId, keyword);
    }

    @Override
	public SysResult selectByOrgId(String orgId) {
		try {
			 String result= zzorgMapper.selectByOrgId(orgId);
			 if(null ==result || result.equals("")){
				return new SysResult(ErrorUtil.CODE2001, "组织名称查询失败", null);
			} else {
				return new SysResult(ErrorUtil.CODE2000, "组织名称查询成功", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("组织名称查询失败", e);
			throw new DoSqlFailedException("组织名称查询失败，请重试！");
		}
	}

	@Override
	public SysResult selectOrgIdsByOrgId(String orgPid) {
		
		try {
			List<String> orgIdList=zzorgMapper.selectOrgIdsByOrgId(orgPid);
			for(String orgId:orgIdList){
				System.out.println(orgId+"   输出煤矿的orgId");
			}
			if(null==orgIdList || orgIdList.size()==0)
				return new SysResult(ErrorUtil.CODE2001, "子煤矿orgId查询失败", null);
			return new SysResult(ErrorUtil.CODE2000, "子煤矿orgId查询成功", orgIdList);
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("子煤矿orgId查询失败！！", e);
			throw new DoSqlFailedException("子煤矿orgId查询失败,请重试！！！");
		}
	}

}
