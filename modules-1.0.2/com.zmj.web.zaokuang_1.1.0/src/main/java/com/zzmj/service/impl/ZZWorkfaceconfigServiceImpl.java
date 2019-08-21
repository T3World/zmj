package com.zzmj.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZWorkfaceconfigMapper;
import com.zzmj.pojo.entity.ZZWorkfaceconfigEntity;
import com.zzmj.pojo.entity.ZZWorkfaceconfigExample;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceconfigService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@Service("ZZWorkfaceconfigService")
public class ZZWorkfaceconfigServiceImpl implements ZZWorkfaceconfigService {

	private static Logger logger = Logger.getLogger(ZZWorkfaceconfigServiceImpl.class);

	@Autowired
	private ZZWorkfaceconfigMapper zzWorkfaceconfigMapper;

	/**
	 * 根据workfaceId查询工作面的配置信息
	 * 
	 * @param workfaceId 工作面ID
	 * @return
	 */
	@Override
	public List<ZZWorkfaceconfigEntity> getPageConfig(String workfaceId) {
		ZZWorkfaceconfigExample example = new ZZWorkfaceconfigExample();
		example.createCriteria().andWorkfaceIdEqualTo(workfaceId);
		List<ZZWorkfaceconfigEntity> result = zzWorkfaceconfigMapper.selectByExample(example);
		if (result.isEmpty()) {
			return null;
		}
		return result;
	}

	/**
	 * 新增工作面配置 新增工作面的时候，级联增加。
	 * 
	 * @param zzWorkfaceconfigEntity 工作面配置实体类
	 * @return
	 */
	@Override
	public int addZZWorkfaceconfigEntity(ZZWorkfaceconfigEntity zzWorkfaceconfigEntity) {
		int result = zzWorkfaceconfigMapper.insert(zzWorkfaceconfigEntity);
		if (result > 0) {
			return result;
		}
		return 0;
	}

	/**
	 * 根据workfaceId修改配置文件
	 * 
	 * @param workfaceId 工作面配置id
	 * @return
	 */
	@Override
	public int updateZZWorkfaceconfig(ZZWorkfaceconfigEntity zzWorkfaceconfigEntity) {
		int result = zzWorkfaceconfigMapper.updateByPrimaryKey(zzWorkfaceconfigEntity);
		return result;
	}

	/**
	 * 根据workfaceId删除工作面的配置文件，这个删除是根据工作面删除级联的删除。不能单独做删除。逻辑删除
	 * 
	 * @param workfaceId
	 * @return
	 */
	@Override
	public int delZZWorkfaceconfig(String workfaceId) {
		// 逻辑删除
		return zzWorkfaceconfigMapper.delWorkfaceconfigByWorkfaceId(workfaceId);
	}

	@Override
	public SysResult selectConfigByWorkfaceId(String workfaceId) {
		try {
			Map<String, Object> config = zzWorkfaceconfigMapper.selectConfigByWorkfaceId(workfaceId);
			if (null == config)
				return new SysResult(ErrorUtil.CODE2001, "无符合条件的配置信息!", config);
			return new SysResult(ErrorUtil.CODE2000, "ok", config);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询工作面配置信息失败!", e);
			throw new DoSqlFailedException("查询工作面配置信息失败!", e);
		}

	}

	@Override
	public SysResult updataConfigByWorkfaceId(ZZWorkfaceconfigEntity entity) throws DoSqlFailedException {
		ZZWorkfaceconfigExample example = new ZZWorkfaceconfigExample();
		String workfaceId = entity.getWorkfaceId();
		example.createCriteria().andWorkfaceIdEqualTo(workfaceId);
		try {
			int flag = zzWorkfaceconfigMapper.updateByExampleSelective(entity, example);
			if (flag < 1)
				new SysResult(ErrorUtil.CODE2001, "未发现需要更新的配置!", flag);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new DoSqlFailedException("工作面配置更新失败!", e);
		}
	}

	@Override
	public SysResult selectWorkfaceConfigByWorkfaceId(String workfaceId) {
		try {
			ZZWorkfaceconfigEntity zzWorkfaceconfigEntity=zzWorkfaceconfigMapper.selectWorkfaceConfigByWorkfaceId(workfaceId);
			if(null!=zzWorkfaceconfigEntity)
				return new SysResult(ErrorUtil.CODE2000, "查询工作面配置成功!", zzWorkfaceconfigEntity);
			return new SysResult(ErrorUtil.CODE2001, "查询工作面配置失败", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new DoSqlFailedException("查询工作面配置失败!", e);
		}
	}

}
