package com.zzmj.controller.SysBase;

import com.zzmj.pojo.entity.ZZOrgEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.impl.ZZOrgServiceImpl;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * @author hushixian 组织机构管理controller
 *
 */
@RestController
@RequestMapping("/SysBase/Org")
public class OrgController {

	@Autowired
	private ZZOrgServiceImpl orgServiceImpl;

	/**
	 * 新增集团，公司
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addZZOrgEntity", method = RequestMethod.POST)
	public SysResult addZZOrgEntity(ZZOrgEntity orgEntity) {
		try {
			return this.orgServiceImpl.addZZOrgEntity(orgEntity); // 新增方法
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 根据orgId修改集团，公司的信息。
	 * 
	 */
	@RequestMapping("/updateZZOrgById")
	public SysResult updateZZOrgById(ZZOrgEntity orgEntity) {
		try {
			return this.orgServiceImpl.updateZZOrgById(orgEntity);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 根据orgId进行删除 对集团，公司，工作面进行删除。
	 * 
	 */
	@RequestMapping("/delZZOrgEntityById")
	public SysResult delZZOrgEntityById(ZZOrgEntity orgEntity) {
		try {
			return this.orgServiceImpl.delZZOrgEntity(orgEntity);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 获得组织结构列表值右面的方法。，并加上分页判断
	 * 
	 * @return
	 */
	@RequestMapping("/getOrgTreeData")
	public SysResult getOrgTreeData(@RequestParam(name = "keyword", required = false) String keyWord,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		try {
			return this.orgServiceImpl.listOrg(keyWord, pageNo, pageSize);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping("/getOrgTree")
	public List<ZZOrgEntity> getOrgTree() {
		List<ZZOrgEntity> list = orgServiceImpl.getTreeDate();
		return list;
	}

    /**
     * 次序查询组织机构表:
     *  先查询PID为0的组织机构,得到orgID
     *  然后查询PID 为该orgID的组织机构,达到子机构orgID
     *  再查询PID为子机构orgID的组织机构 .... 递推
     *  直到 不含有子机构
     *  将结果树形结构返回
     *  即 [{
     *       childrenOrg: [{
     *           childrenOrg: [{...},{...}]
     *      },
     *     {
     *       childrenOrg: [{
     *           childrenOrg: [{...},{...}]
     *     },..}
     *     }]
     * */
	@RequestMapping(value = "/listOrgTree")
    public SysResult listOrgTree(@RequestParam(value = "pid",required = false) String pid){
		try {
			if(pid == null || pid.equals(""))
				pid = "0";
			List<ZZOrgEntity> orgTree = orgServiceImpl.listOrgTree(pid);
			return new SysResult(ErrorUtil.CODE2000,null,orgTree);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }

    @RequestMapping("/getOrgWithParents")
    public SysResult getOrgWithParents(@RequestParam("orgId") String orgId){
	    try {
            ZZOrgEntity org = orgServiceImpl.getOrgWithParents(orgId);
            return new SysResult(ErrorUtil.CODE2000, "ok", org);
        }catch (Exception e){
	        return new SysResult(ErrorUtil.CODE5000,e.getMessage(),null);
        }

    }



    /**
	 * 从组织机构中获取所有的集团
	 * @return
	 */
	@RequestMapping(value = "/listAllGroup", method = RequestMethod.POST)
	public SysResult listAllGroup() {
		try {
			List<ZZOrgEntity> list = this.orgServiceImpl.listAllGroup();
			if (list.size() > 0) {
				return new SysResult(ErrorUtil.CODE2000, "从组织机构中获取所有的集团成功", list);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "从组织机构中获取所有的集团失败", null);
			}
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 对组织机构(集团)
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/listAllGroupPage", method = RequestMethod.POST)
	public SysResult listAllGroupPage(
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		try {
			return orgServiceImpl.listOrgPage(keyword, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 根据groupId查询该集团下所有矿井公司
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/listOrgsByGroupId", method = RequestMethod.POST)
	public SysResult listOrgsByGroupId(@RequestParam(name = "groupId", required = false) String groupId) {
		try {
			return orgServiceImpl.listOrgsByGroupId(groupId);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
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
	@RequestMapping(value = "/listOrgsByGroupIdPage", method = RequestMethod.POST)
	public SysResult listOrgsByGroupIdPage(
			@RequestParam(name = "groupId", required = false) String groupId,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		try {
			return this.orgServiceImpl.listOrgsByGroupIdPage(groupId, keyword, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}}
