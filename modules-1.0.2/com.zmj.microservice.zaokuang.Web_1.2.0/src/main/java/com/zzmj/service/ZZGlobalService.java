package com.zzmj.service;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZGlobalEntity;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

public interface ZZGlobalService {

	/**
	 * 业务service 查询全局参数
	 * 
	 * @param globalId
	 * @return
	 */
	SysResult select(String globalId);

	/**
	 * 新增全局变量方法
	 * 
	 * @param zzGlobalEntity
	 * @return
	 * @throws DoSqlFailedException
	 */
	int addZZGlobal(ZZGlobalEntity zzGlobalEntity) throws DoSqlFailedException;

	/**
	 * 根据globalId修改全局变量方法
	 * 
	 * @param zzGlobalEntity
	 * @param globalId       全局变量Id
	 * @return
	 * @throws DoSqlFailedException
	 */
	int updateZZGlobal(ZZGlobalEntity zzGlobalEntity) throws DoSqlFailedException;

	/**
	 * @Title: selectZZGlobal
	 * @Description: selectById
	 * @param: @param globalId
	 * @param: @return
	 * @return: int
	 * @throws EmptyResultException
	 */
	ZZGlobalEntity selectZZGlobal(String globalId) throws EmptyResultException;

	/**
	 * @Title: selectZZGlobal
	 * @Description: 查询第一条
	 * @param: @return
	 * @return: ZZGlobalEntity
	 */
	ZZGlobalEntity selectZZGlobal();

	/**
	 * @Title: countZZGlobal
	 * @Description: 在更新数据之前先检查是否存在,不存在就新增,存在就更新
	 * @param: @param globalId
	 * @param: @return 结果条数
	 * @return: int
	 */
	int countZZGlobal(String globalId);

	/**
	 * @Title: doGlobalParam
	 * @Description: 依据GlobalI,数据存在执行updata,不存在执行insert.
	 * @param: @param zzGlobalEntity
	 * @param: @return
	 * @return: int
	 * @throws DoSqlFailedException
	 */
	SysResult saveGlobalParam(ZZGlobalEntity zzGlobalEntity) throws DoSqlFailedException;

	/**
	 * @Title: countZZGlobal
	 * @Description: 查询所有记录
	 * @param: @return
	 * @return: int
	 */
	int countZZGlobal();

}
