package com.zzmj.service;

import java.util.HashMap;
import java.util.List;

import com.zzmj.pojo.entity.ZZModuleEntity;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.util.exception.DoSqlFailedException;

public interface ZZModuleService {
	/**
	 * @Title: addModule
	 * @Description: 新增模块
	 * @param: entity
	 * @return: int
	 * @throws DoSqlFailedException
	 */
	SysResult addModule(ZZModuleEntity entity) throws DoSqlFailedException;

	/**
	 * @Title: delModule
	 * @Description: 根据模块ID逻辑删除模块
	 * @param: @param moduleId
	 * @param: @return
	 * @return: int
	 * @throws DoSqlFailedException
	 */
	SysResult delModule(String moduleId) throws DoSqlFailedException;

	/**
	 * @Title: updateModule
	 * @Description: 根据主键更新模块
	 * @param: @param entity
	 * @param: @return
	 * @return: int
	 * @throws DoSqlFailedException
	 */
	SysResult updateModule(ZZModuleEntity entity) throws DoSqlFailedException;

	/**
	 * @Title: list
	 * @Description: 根据模块名称分页查询符合条件的模块 keyword为"nil"或者为null时,非条件查询
	 * @param: keyword 搜索关键字
	 * @param: pageNo 分页显示的页码
	 * @param: pageSize 每页展示的结果条数
	 * @return: List<ZZModuleEntity>
	 */
	SysResult listModule(String keyword, Integer pageNo, Integer pageSize);

	/**
	 * // 根据用户userId 获取用户的角色，所对应的 所有模块资源
	 * 
	 * @param userId 用户Id
	 * @return
	 */
	List<HashMap<String, Object>> getUserMoudel(String userId);

}
