package com.zzmj.controller.API;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZWorkfaceconfigEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZOrgService;
import com.zzmj.service.ZZUserService;
import com.zzmj.service.ZZWorkfaceService;
import com.zzmj.service.ZZWorkfaceconfigService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@RestController
@RequestMapping("/API/BaseData")
public class BaseDataController {

	@Autowired
	private ZZOrgService orgServiceImpl;

	@Autowired
	private ZZWorkfaceconfigService workfaceconfigServiceImpl;

	@Autowired
	private ZZWorkfaceService zzWorkfaceServiceImpl;

	@Autowired
	private ZZUserService userService;

	/**
	 * @Title: getConfig
	 * @Description: 前端查询工作面配置信息接口
	 * @param: @param workfaceId
	 * @param: @return
	 * @return: SysResult
	 */
	@RequestMapping("/getConfig")
	public SysResult getConfig(@RequestParam(name="Workface_Id",required=true) String workfaceId) {
		try {
			return this.workfaceconfigServiceImpl.selectConfigByWorkfaceId(workfaceId);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping("/setConfig")
	public SysResult setConfig(
			@RequestParam(name="Workface_Id",required=false)String workfaceId,
			@RequestParam(name="Font_MinPressure",required=false)String fontMinPressure,
			@RequestParam(name="Font_MaxPressure",required=false)String fontMaxPressure,
			@RequestParam(name="Support_Dir",required=false)String supportDir,
			@RequestParam(name="ShearerPos_CacheTime",required=false)String shearerPosCacheTime,
			@RequestParam(name="Conveyor_Dir",required=false)String conveyorDir,
			@RequestParam(name="Support_Count",required=false)String supportCount) {
		ZZWorkfaceconfigEntity config = new ZZWorkfaceconfigEntity();
		config.setWorkfaceId(workfaceId);
		config.setFontMinpressure(fontMinPressure);
		config.setFontMaxpressure(fontMaxPressure);
		config.setSupportDir(Integer.parseInt(supportDir));
		config.setShearerposCachetime(shearerPosCacheTime);
		config.setConveyorDir(Integer.parseInt(conveyorDir));
		config.setSupportCount(Integer.parseInt(supportCount));
		try {
			return workfaceconfigServiceImpl.updataConfigByWorkfaceId(config);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * @Title: /getCompanyWithWorkfaceList
	 * @Description: 根据userId获取打包的数据
	 * @param: @param userId
	 * @param: @return
	 * @return: SysResult
	 */
	@RequestMapping("/getCompanyWithWorkfaceList")
	public SysResult getCompanyWithWorkfaceList(@RequestParam("User_Id") String userId) {
		if (null == userId || userId.equals(""))
			return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
		// 1.根据用户ID在用户表中查询OrgID
		String orgId = null;
		Map<String, Object> org = null;
		orgId = this.userService.getOrgIdByUserId(userId);
		if (null == orgId)
			return new SysResult(ErrorUtil.CODE5000, "SQL执行异常", null);
		// 2.根据orgId判断是否为集团
		org = this.orgServiceImpl.getOrgByOrgId(orgId);
		if (null == org)
			return new SysResult(ErrorUtil.CODE2001, "无组织机构信息!", null);
		if (org.get("Org_PId").equals("0")) {
			// 3.根据OrgID在org表中查询所有父ID为orgID的org
			List<Map<String, Object>> orgchildren = this.orgServiceImpl.listOrgByPid(orgId);
			if (null == orgchildren)
				return new SysResult(ErrorUtil.CODE2001, "无组织机构信息!", null);
			// 4.根据list中的OrgIDfor each查询工作面信息,封装到workfaceList属性中
			orgchildren = this.selectAndSetWorkfaceList(orgchildren);
			return new SysResult(ErrorUtil.CODE2000, "集团信息", orgchildren);
		} else {
			// 返回自己
			List<Map<String, Object>> orgList = new ArrayList<Map<String, Object>>();
			orgList.add(org);
			orgList = this.selectAndSetWorkfaceList(orgList);
			return new SysResult(ErrorUtil.CODE2000, "非集团信息", orgList);
		}
	}

	private List<Map<String, Object>> selectAndSetWorkfaceList(List<Map<String, Object>> orgList) {
		Iterator<Map<String, Object>> iterator = orgList.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> org = iterator.next();
			String orgId = (String) org.get("Org_Id");
			List<Map<String, Object>> workfaceList = zzWorkfaceServiceImpl.listWorkfaceNoPage(orgId);
			if (null != workfaceList) {
				org.put("WorkfaceList", workfaceList);
			} else {
				org.put("WorkfaceList", new String[] {});
			}
		}
		return orgList;
	}

}
