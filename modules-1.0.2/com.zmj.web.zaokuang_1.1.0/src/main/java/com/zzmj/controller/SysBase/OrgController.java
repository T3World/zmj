package com.zzmj.controller.SysBase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzmj.pojo.entity.ZZOrgEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.impl.ZZOrgServiceImpl;
import com.zzmj.service.impl.ZZWorkfaceServiceImpl;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

/**
 * 
 * @author hushixian 组织机构管理controller
 *
 */
@Controller
@RequestMapping("/SysBase/Org")
public class OrgController {

	@Autowired
	private ZZOrgServiceImpl orgServiceImpl;

	@Autowired
	private ZZWorkfaceServiceImpl zzWorkfaceImpl;

	/**
	 * 新增集团，公司
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addZZOrgEntity", method = RequestMethod.POST)
	@ResponseBody
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
	 * @param orgEntity
	 */
	@RequestMapping("/updateZZOrgById")
	@ResponseBody
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
	 * @param response
	 * @param request
	 */
	@RequestMapping("/delZZOrgEntityById")
	@ResponseBody
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
	@ResponseBody
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
	@ResponseBody
	public List<ZZOrgEntity> getOrgTree(HttpServletResponse response, HttpServletRequest request) {
		List<ZZOrgEntity> list = orgServiceImpl.getTreeDate();
		return list;
	}

}
