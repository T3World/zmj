package com.zzmj.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZModuleMapper;
import com.zzmj.mapper.ZZRolemoduleMapper;
import com.zzmj.pojo.entity.ZZModuleEntity;
import com.zzmj.pojo.entity.ZZRolemoduleEntity;
import com.zzmj.pojo.vo.RoleModuleResult;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZRoleModuleService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

@Service
public class ZZRoleModuleServiceImpl implements ZZRoleModuleService {

	private static Logger logger = Logger.getLogger(ZZRoleModuleServiceImpl.class);
	@Autowired
	private ZZRolemoduleMapper zzRoleModuleMapper;
	@Autowired
	private ZZModuleMapper moduleMapper;

	@Override
	public SysResult selectModuleByRoleId(String roleId) throws EmptyResultException {
		if (null == roleId || roleId.equals(""))
			return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
		try {
			String[] ams = zzRoleModuleMapper.selectByRoleId(roleId);
			if (ams.length < 1)
				return new SysResult(ErrorUtil.CODE2001, "无已授权模块!", null);
			return new SysResult(ErrorUtil.CODE2000, "ok", ams);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("根据角色查询模块失败!", e);
			throw new DoSqlFailedException("根据角色查询模块失败!", e);
		}
	}

	@Override
	public int deleteModuleByRoleId(String roleId) {
		return zzRoleModuleMapper.deleteByRoleId(roleId);
	}

	@Override
	public int addModules(RoleModuleResult result) throws DoSqlFailedException {
		String roleId = result.getRoleId();
		String[] moduleIds = result.getModuleIds();
		ZZRolemoduleEntity record = new ZZRolemoduleEntity();
		int flag = 0;
		for (int i = 0; i < moduleIds.length; i++) {
			record.setId(CodeUtil.createUuid36());
			record.setRoleId(roleId);
			record.setModuleId(moduleIds[i]);
			flag = flag + this.addModule(record);
		}
		if (flag < 1)
			throw new DoSqlFailedException("授权模块失败!");
		// 返回插入条数
		return flag;
	}

	public int addModule(ZZRolemoduleEntity record) {
		return zzRoleModuleMapper.insert(record);
	}

	@Override
	public SysResult updateModules(RoleModuleResult result) throws DoSqlFailedException {
		if (null == result)
			return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
		String roleId = result.getRoleId();
		int flag = 0;
		flag = flag + this.deleteModuleByRoleId(roleId);
		try {
			flag = flag + this.addModules(result);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("根据角色查询模块失败!", e);
			throw new DoSqlFailedException("根据角色查询模块失败!", e);
		}
	}

	@Override
	public SysResult getRoleModuleTreeData(String roleId) throws EmptyResultException {
		if (null == roleId || roleId.equals(""))
			return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
		try {
			String[] mids = this.zzRoleModuleMapper.selectByRoleId(roleId);
			if (null == mids) {
				return new SysResult(ErrorUtil.CODE2001, "无已授权模块!", null);
			}
			List<ZZModuleEntity> list = this.moduleMapper.listModuleAll(0, 1000);
			Iterator<ZZModuleEntity> iterator = list.iterator();
			while (iterator.hasNext()) {
				ZZModuleEntity e = iterator.next();
				for (int i = 0; i < mids.length; i++) {
					String mid = e.getmId();
					System.out.println(mid);
					System.out.println(mids[i]);
					if (mid.equals(mids[i])) {
						System.out.println(mids[i]);
						e.setChecked(true);
					}
				}
			}
			return new SysResult(ErrorUtil.CODE2000, "ok", list);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("角色模块树查询失败!", e);
			throw new DoSqlFailedException("角色模块树查询失败!", e);
		}
	}
}
