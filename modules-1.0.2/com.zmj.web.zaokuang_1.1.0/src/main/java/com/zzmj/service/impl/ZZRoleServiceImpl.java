package com.zzmj.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzmj.mapper.ZZRoleMapper;
import com.zzmj.pojo.entity.ZZRoleEntity;
import com.zzmj.pojo.entity.ZZRoleExample;
import com.zzmj.pojo.entity.ZZRoleuserEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZRoleModuleService;
import com.zzmj.service.ZZRoleService;
import com.zzmj.service.ZZRoleUserService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;
import com.zzmj.util.exception.IllegalParamException;

@Service("ZZRoleService")
public class ZZRoleServiceImpl implements ZZRoleService {

	private static Logger logger = Logger.getLogger(ZZRoleServiceImpl.class);

	@Autowired
	private ZZRoleMapper roleMapper;

	@Autowired
	private ZZRoleUserService zzRoleUserServiceImpl;

	@Autowired
	private ZZRoleModuleService roleModuleService;

	/**
	 * 根据roleId来修改角色信息
	 * 
	 * @param roleId 角色id
	 * @return
	 * @throws DoSqlFailedException
	 */
	@Override
	public SysResult updateZZRoleEntity(ZZRoleEntity roleEntity) throws DoSqlFailedException {
		String roleId = roleEntity.getRoleId();
		ZZRoleExample example = new ZZRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		try {
			int flag = roleMapper.updateByExampleSelective(roleEntity, example);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("角色更新失败!", e);
			throw new DoSqlFailedException("角色更新失败!",e);
		}
	}

	/**
	 * 新增角色方法
	 * 
	 * @param roleEntity
	 * @return
	 * @throws DoSqlFailedException
	 */
	@Override
	public SysResult addZZRoleEntity(ZZRoleEntity roleEntity) throws DoSqlFailedException {
		try {
			String roleId = CodeUtil.createUuid36();
			String id = CodeUtil.createUuid36();
			roleEntity.setId(id);
			roleEntity.setRoleId(roleId);
			roleEntity.setIsdel(0);
			roleEntity.setCreateperson("umr");
			int flag = roleMapper.insert(roleEntity);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("角色添加失败!", e);
			throw new DoSqlFailedException("角色添加失败!", e);
		}
	}

	/**
	 * 查询角色列表
	 * 
	 * @return
	 */
	@Override
	public List<ZZRoleEntity> listRoleEntity() {
		ZZRoleExample example = new ZZRoleExample();
		example.createCriteria().andIsdelNotEqualTo(1);
		example.setOrderByClause("sortcode");
		List<ZZRoleEntity> listRole = roleMapper.selectByExample(example);
		if (listRole.size() > 0) {
			return listRole;
		}
		return null;
	}

	@Override
	public SysResult listRoleAll() {
		try {
			List<ZZRoleEntity> list = this.roleMapper.listRoleAll();
			if (list.size() < 1)
				return new SysResult(ErrorUtil.CODE2001, "无角色信息!", list);
			return new SysResult(ErrorUtil.CODE2000, "ok", list);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("角色列表查询失败!", e);
			throw new DoSqlFailedException("角色列表查询失败!", e);
		}
	}

	@Override
	public SysResult delRole(String roleId)  {
		if (null == roleId || roleId.equals("")) {
			return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
		}
		try {
			String[] userIds = this.zzRoleUserServiceImpl.listUserIdsByRoleId(roleId);
			// no user ,could be deleted
			if (null == userIds || userIds.length == 0) {
				ZZRoleExample example = new ZZRoleExample();
				example.createCriteria().andRoleIdEqualTo(roleId);
				int flag1 = this.roleMapper.deleteByExample(example);
				int flag2 = this.roleModuleService.deleteModuleByRoleId(roleId);
				if (flag1 > 0)
					return new SysResult(ErrorUtil.CODE2000, "ok", flag1 + flag2);
				return new SysResult(ErrorUtil.CODE5000, "角色删除失败", flag1 + flag2);
			}
			return new SysResult(ErrorUtil.CODE2001, "该角色下面有用户，如要删除该角色，请先把该角色下的用户给清空", null);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("角色删除失败!", e);
			throw new DoSqlFailedException("角色删除失败!", e);
		}
	}

	/**
	 * 根据userId查询关联的所有角色，返回一个角色集合List<Role>
	 * (角色集合里包含角色Id、角色名、角色值)
	 * @param userId
	 * @return
	 */
    @Override
    public List<ZZRoleEntity> listRolesByUserId(String userId) {
	    if (null == userId || userId.equals("")) {
	        return null;
        }
        List<ZZRoleEntity> list = this.roleMapper.listRolesByUserId(userId);
	    if (list.size() > 0) {
	        return list;
        } else {
	        return null;
        }
    }

}
