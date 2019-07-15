package com.zzmj.service;

import com.zzmj.pojo.entity.ZZUserEntity;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.util.exception.DoSqlFailedException;

import java.util.List;

public interface ZZUserService {

	int delUser(String userId) throws DoSqlFailedException;

	/**
	 * 添加用户的方法
	 *
	 * @param zzUserEntity 用户的实体类
	 * @return
	 */
	SysResult addZZUserEntity(ZZUserEntity zzUserEntity);

	/**
	 * 根据userId修改用户的方法
	 *
	 * @param zzUserEntity 修改之后的实体类
	 * @param userId       用户的Id
	 * @return
	 */
	SysResult updateZZUserEntity(ZZUserEntity zzUserEntity, String userId);

	/**
	 * 给用户赋予角色的权限
	 *
	 * @param userId  用户id
	 * @param roleIds 角色id串
	 * @return
	 */
	SysResult addUserRole(String userId, String roleIds);

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	List<ZZUserEntity> listZZuser();

	/**
	 * 根据userId 查找用户信息
	 *
	 * @param userId 用户id
	 * @return
	 */
	SysResult getZZUserById(String userId);

	/**
	 *
	 * @param keyword  关键字
	 * @param pageNo   从第几行开始显示
	 * @param pageSize 每页显示的条数
	 * @return
	 */
	SysResult listUserPage(String orgId, String keyword, Integer pageNo, Integer pageSize);

	/**
	 * 根据userId 查找用户所拥有的角色
	 *
	 * @param userId 用户id
	 * @return
	 */
	SysResult getUserRole(String userId);

	/**
	 * @Title: getOrgIdByUserId
	 * @Description:
	 * @param: @param userId
	 * @param: @return
	 * @return: String
	 * @throws DoSqlFailedException
	 */
	String getOrgIdByUserId(String userId);

	/**
	 * // 根据用户userId 获取用户的角色，所对应的 所有模块资源
	 *
	 * @param userId 用户Id
	 * @return
	 */
	List<ZZUserEntity> getUserMoudel(String userId);

	/**
	 * @Title: getifUserAccountExist
	 * @Description: 判断用户名是否存在
	 * @param: @param account
	 * @param: @return
	 * @param: @throws DoSqlFailedException
	 * @return: int
	 */
	int getifUserAccountExist(String account) throws DoSqlFailedException;

	int getIfPasswordRight(String userId,String password);
}
