package com.zzmj.service;

import java.util.List;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zzmj.pojo.entity.ZZWorkfaceconfigEntity;
import com.zzmj.util.exception.DoSqlFailedException;

/**
 * 
 * @author hushixian
 *
 */
public interface ZZWorkfaceconfigService {

	/**
	 * 根据workfaceId查询工作面的配置信息
	 * 
	 * @param workfaceId 工作面ID
	 * @return
	 */
	List<ZZWorkfaceconfigEntity> getPageConfig(String workfaceId);

	/**
	 * 新增工作面配置 新增工作面的时候，级联增加。
	 * 
	 * @param zzWorkfaceconfigEntity 工作面配置实体类
	 * @return
	 */
	int addZZWorkfaceconfigEntity(ZZWorkfaceconfigEntity zzWorkfaceconfigEntity);

	/**
	 * 根据workfaceId修改配置文件
	 * 
	 * @param workfaceId 工作面配置id
	 * @return
	 */
	int updateZZWorkfaceconfig(ZZWorkfaceconfigEntity zzWorkfaceconfigEntity);

	/**
	 * 根据workfaceId删除工作面的配置文件，这个删除是根据工作面删除级联的删除。不能单独做删除。物理删除
	 * 
	 * @param workfaceId
	 * @return
	 */
	int delZZWorkfaceconfig(String workfaceId);

	SysResult selectConfigByWorkfaceId(String workfaceId) throws DoSqlFailedException;

	SysResult updataConfigByWorkfaceId(ZZWorkfaceconfigEntity entity) throws DoSqlFailedException;

}
