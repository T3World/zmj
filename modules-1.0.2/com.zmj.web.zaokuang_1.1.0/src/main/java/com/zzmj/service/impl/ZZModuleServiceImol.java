package com.zzmj.service.impl;

import com.zzmj.mapper.ZZModuleMapper;
import com.zzmj.mapper.ZZRolemoduleMapper;
import com.zzmj.pojo.entity.ZZModuleEntity;
import com.zzmj.pojo.entity.ZZRolemoduleExample;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZModuleService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("ZZModuleService")
public class ZZModuleServiceImol implements ZZModuleService {

	private static Logger logger = Logger.getLogger(ZZModuleServiceImol.class);

	@Autowired
	private ZZRolemoduleMapper zzRolemoduleMapper;
	@Autowired
	private ZZModuleMapper zzModuleMapper;

	@Override
	public SysResult addModule(ZZModuleEntity entity) throws DoSqlFailedException {
		try {
			String id = CodeUtil.createUuid36();
			String mId = CodeUtil.createUuid36();
			entity.setId(id);
			entity.setmId(mId);
			entity.setIsdel(0);
			entity.setCreateperson("umr");
			String pid = entity.getmPid();
			// 如果pid为空,则设置为0
			if (null == pid || pid.equals("")) {
				pid = "0";
				entity.setmPid(pid);
			}
			int flag = zzModuleMapper.insert(entity);
			if (flag < 1)
				return new SysResult(ErrorUtil.CODE2001,"添加失败!",null);
			return new SysResult(ErrorUtil.CODE2000,"ok",flag);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("模块添加失败!",e);
			throw new DoSqlFailedException("模块添加失败!", e);
		}
	}

	/**
	 * 删除模块时要注意两点:
	 * 1. 如果是父模块,先删除子模块才行
	 * 2. 如果与角色关联,删除模块角色关系表中的数据
	 *
	 * */
	@Override
	public SysResult delModule(String mId) throws DoSqlFailedException {
		try {
			//先查询模块有没有儿子
			int num = this.zzModuleMapper.countSonModuleByMid(mId);
			if (num != 0)
				return new SysResult(ErrorUtil.CODE2001,"请先删除该模块下的子模块!");
			//删除关系表中的数据
			this.zzRolemoduleMapper.deleteByMid(mId);

			int flag = zzModuleMapper.delByIsDel(mId);
			if (flag < 1)
				return new SysResult(ErrorUtil.CODE2001,"删除模块失败!",flag);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("模块删除失败!",e);
			throw new DoSqlFailedException("删除模块失败!",e);
		}
	}

	@Override
	public SysResult updateModule(ZZModuleEntity entity) throws DoSqlFailedException {
		try {
			int flag = zzModuleMapper.updateByMId(entity);
			if (flag < 1)
				return new SysResult(ErrorUtil.CODE2001, "模块更新失败!", flag);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("模块数据更新失败!",e);
			throw new DoSqlFailedException("模块数据更新失败!",e);
		}
	}

	@Override
	public SysResult listModule(String keyword, Integer pageNo, Integer pageSize) {
		PageObject<ZZModuleEntity> pageObject = null;
		if (null == pageNo || pageNo == 0)
			pageNo = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 11;
		Integer rowNo = (pageNo - 1) * pageSize;
		try {
			// 无关键字查询
			if (null == keyword || keyword.equals("")) {
				int rowCount = zzModuleMapper.countModuleAll();
				if (rowCount > 0) {
					List<ZZModuleEntity> moduleList = zzModuleMapper.listModuleAll(rowNo, pageSize);
					pageObject = new PageObject<ZZModuleEntity>(pageNo, pageSize, rowCount, moduleList);
				}
				return new SysResult(ErrorUtil.CODE2000, "ok", pageObject);
				// 关键字查询
			} else {
				int rowCount = zzModuleMapper.countModuleByKeyword(keyword);
				if (rowCount > 0) {
					List<ZZModuleEntity> moduleList = zzModuleMapper.listModuleByKeyword(keyword, rowNo, pageSize);
					pageObject = new PageObject<ZZModuleEntity>(pageNo, pageSize, rowCount, moduleList);

				}
				return new SysResult(ErrorUtil.CODE2000, "ok", pageObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("模块数据查询失败!",e);
			throw new DoSqlFailedException("模块数据查询失败!请重试",e);
		}
	}

	/**
	 * 根据用户userId 获取用户的角色，所对应的 所有模块资源
	 *
	 * @param userId 用户Id
	 * @return
	 */
	@Override
	public List<HashMap<String, Object>> getUserMoudel(String userId) {
		List<HashMap<String, Object>> result = zzModuleMapper.getUserMoudel(userId);
		return result;
	}

	/**
	 * 根据角色Id查询跟该角色关联的所有模块值，返回值List<String>
	 * @param roleId
	 * @return
	 */
	@Override
	public List<String> getModuleValuesByRoleId(String roleId) {
		if (null == roleId || roleId.equals("")) {
			return null;
		}
		List<String> list = this.zzModuleMapper.getModuleValuesByRoleId(roleId);
		//System.out.println("list的值是：" + list.toString());
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}


}
