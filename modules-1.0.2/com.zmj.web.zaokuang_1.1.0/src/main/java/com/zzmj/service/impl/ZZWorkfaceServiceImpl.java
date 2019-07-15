package com.zzmj.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZWorkfaceMapper;
import com.zzmj.mapper.ZZWorkfaceconfigMapper;
import com.zzmj.pojo.entity.WorkfaceAndConfig;
import com.zzmj.pojo.entity.ZZWorkfaceEntity;
import com.zzmj.pojo.entity.ZZWorkfaceconfigEntity;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceService;
import com.zzmj.service.ZZWorkfaceconfigService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.IllegalParamException;

@Service("WorkfaceService")
public class ZZWorkfaceServiceImpl implements ZZWorkfaceService {

	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ZZWorkfaceMapper zzWorkfaceMapper; // 工做面mapper

	@Autowired
	private ZZWorkfaceconfigService zzWorkfaceconfigService;

	@Autowired
	private ZZWorkfaceconfigMapper configMapper;

	/**
	 * @see com.zzmj.service.ZZWorkfaceService#countWorkface(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public int countWorkface(String orgId, String keyword) {
		if (null == orgId || orgId.equals("")) {
			if (null == keyword || keyword.equals("")) {
				return zzWorkfaceMapper.countWorkfaceAll();
			}
			return zzWorkfaceMapper.countWorkfaceByKeyword(keyword);
		}
		if (null == keyword || keyword.equals("")) {
			return zzWorkfaceMapper.countWorkfaceByOrgId(orgId);
		}
		return zzWorkfaceMapper.countWorkfaceByOrgIdAndByKeyword(orgId, keyword);
	}

	@Override
	public SysResult listWorkface(String orgId, String keyword, Integer pageNo, Integer pageSize) {
		try {
			// 分页查询先查询结果总数
			int count = this.countWorkface(orgId, keyword);
			// 为pageNo和pageSize赋默认值
			if (null == pageNo || pageNo == 0)
				pageNo = 1;
			if (null == pageSize || pageSize == 0)
				pageSize = 11;
			// 封装到分页查询包装类中
			Integer rowNo = (pageNo - 1) * pageSize;
			List<ZZWorkfaceEntity> list = listWorkfaceByPage(orgId, keyword, rowNo, pageSize);
			return new SysResult(ErrorUtil.CODE2000, "ok",
					new PageObject<ZZWorkfaceEntity>(pageNo, pageSize, count, list));
		} catch (RuntimeException e) {
			logger.error("工作面列表查询失败!", e);
			throw new DoSqlFailedException("工作面列表查询失败!", e);
		}
	}

	private List<ZZWorkfaceEntity> listWorkfaceByPage(String orgId, String keyword, Integer rowNo, Integer pageSize) {
		if (null == orgId || orgId.equals("")) {
			if (null == keyword || keyword.equals("")) {
				List<ZZWorkfaceEntity> list = zzWorkfaceMapper.listWorkfaceAll(rowNo, pageSize);
				logger.info(String.valueOf(list.size()));
				return list;
			}
			return zzWorkfaceMapper.listWorkfaceByKeyword(keyword, rowNo, pageSize);
		} else {
			if (null == keyword || keyword.equals("")) {
				List<ZZWorkfaceEntity> list = zzWorkfaceMapper.listWorkfaceByOrgId(orgId, rowNo, pageSize);
				logger.info(String.valueOf(list.size()));
				return list;
			}
			return zzWorkfaceMapper.listWorkfaceByOrgIdAndKeyword(orgId, keyword, rowNo, pageSize);
		}
	}

	@Override
	public SysResult addWorkface(WorkfaceAndConfig wac) throws IllegalParamException, DoSqlFailedException {
		if (null == wac)
			return new SysResult(ErrorUtil.CODE2001, "传入参数不能为空!新增失败!", null);
		String id = CodeUtil.createUuid36();
		String wid = CodeUtil.createUuid36();
		ZZWorkfaceEntity workface = getset(wac);
		workface.setId(id);
		workface.setWorkfaceId(wid);
		try {
			ZZWorkfaceconfigEntity zzWorkfaceconfigEntity = workface.getZzWorkfaceconfigEntity();
			zzWorkfaceconfigEntity.setId(CodeUtil.createUuid36());
			zzWorkfaceconfigEntity.setWorkfaceId(wid);
			int flag2 = this.zzWorkfaceconfigService.addZZWorkfaceconfigEntity(zzWorkfaceconfigEntity);
			int flag1 = zzWorkfaceMapper.insert(workface);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag1 + flag2);
		} catch (RuntimeException e) {
			logger.error("工作面新增失败!", e);
			throw new DoSqlFailedException("工作面新增失败!");
		}
	}

	@Override
	public SysResult updateWorkface(WorkfaceAndConfig wac) {
		try {
			ZZWorkfaceEntity zzWorkfaceEntity = this.getset(wac);
			ZZWorkfaceconfigEntity zzWorkfaceconfigEntity = zzWorkfaceEntity.getZzWorkfaceconfigEntity();
			int flag1 = zzWorkfaceconfigService.updateZZWorkfaceconfig(zzWorkfaceconfigEntity);
			int flag2 = zzWorkfaceMapper.updateByPrimaryKeySelective(zzWorkfaceEntity);
			if (flag1 < 1 || flag2 < 1)
				throw new DoSqlFailedException("工作面配置更新失败!");
			return new SysResult(ErrorUtil.CODE2000, "ok", flag1 + flag2);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new DoSqlFailedException(e.getMessage(), e);
		}
	}

	@Override
	public SysResult delWorkface(String workfaceId) {
		// 验证参数合法性
		if (null == workfaceId || workfaceId.equals("")) {
			return new SysResult(ErrorUtil.CODE2001, "参数为空,删除失败!", null);
		}
		try {
			int flag = this.zzWorkfaceMapper.delWorkfaceByWorkfaceId(workfaceId);
			if (flag < 1)
				return new SysResult(ErrorUtil.CODE2001, "删除失败,没有符合条件的工作面!", flag);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("工作面删除失败!", e);
			throw new DoSqlFailedException("工作面删除失败!");
		}
	}

	@Override
	public SysResult stopWorkface(String workfaceId, Integer workfaceState) {
		// 参数合法性校验
		if (null == workfaceId || null == workfaceState)
			return new SysResult("2001", "工作面状态更新失败", null);
		if (workfaceState == 1) {
			workfaceState = 0;
		} else {
			workfaceState = 1;
		}
		try {
			return new SysResult(ErrorUtil.CODE2000, "ok", zzWorkfaceMapper.stopWorkface(workfaceId, workfaceState));
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("工作面启停操作失败!", e);
			throw new DoSqlFailedException("工作面启停操作失败!", e);
		}
	}

	private ZZWorkfaceEntity getset(WorkfaceAndConfig wac) {
		ZZWorkfaceEntity workface = new ZZWorkfaceEntity();
		ZZWorkfaceconfigEntity wc = new ZZWorkfaceconfigEntity();
		workface.setWorkfaceId(wac.getWorkfaceId());
		workface.setOrgId(wac.getOrgId());
		workface.setWorkfaceName(wac.getWorkfaceName());
		workface.setWorkfaceAlias(wac.getWorkfaceAlias());
		workface.setWorkfaceState(1);
		workface.setWorkfaceType(wac.getWorkfaceType());
		workface.setIsdel(0);
		workface.setSortcode(wac.getSortcode());
		wc.setWorkfaceId(wac.getWorkfaceId());
		wc.setSupportCount(wac.getSupportCount());
		wc.setSupportDir(wac.getSupportDir());
		wc.setBeltType(wac.getBeltType());
		wc.setFontMaxpressure(wac.getFontMaxpressure());
		wc.setFontMinpressure(wac.getFontMinpressure());
		wc.setBackMaxpressure(wac.getBackMaxpressure());
		wc.setBackMinpressure(wac.getBackMinpressure());
		wc.setConveyorDir(wac.getConveyorDir());
		wc.setPressureCharttype(wac.getPressureCharttype());
		wc.setShearerposCachetime(wac.getShearerposCachetime());
		workface.setZzWorkfaceconfigEntity(wc);
		return workface;
	}

	@Override
	public List<Map<String, Object>> listWorkfaceNoPage(String orgId) {
		List<Map<String, Object>> wlist = this.zzWorkfaceMapper.listWorkfaceNoPage(orgId);
		if (null == wlist || wlist.isEmpty())
			return null;
		Iterator<Map<String, Object>> it = wlist.iterator();
		while (it.hasNext()) {
			Map<String, Object> w = it.next();
			String wid = (String) w.get("Workface_Id");
			Map<String, Object> config = this.configMapper.selectConfigByWorkfaceId(wid);
			w.put("Workface_Config", config);
		}
		return wlist;
	}
}
