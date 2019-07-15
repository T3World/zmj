package com.zzmj.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZGlobalMapper;
import com.zzmj.pojo.entity.ZZGlobalEntity;
import com.zzmj.pojo.entity.ZZGlobalExample;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZGlobalService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

@Service("ZZGlobalService")
public class ZZGlobalServiceImpl implements ZZGlobalService {

	private static Logger logger = Logger.getLogger(ZZGlobalServiceImpl.class);
    @Autowired
    private ZZGlobalMapper zzGlobalMapper;

    /**
     * 新增全局变量方法
     * 
     * @param zzGlobalEntity
     * @return
     * @throws DoSqlFailedException
     */
    @Override
    public int addZZGlobal(ZZGlobalEntity zzGlobalEntity) throws DoSqlFailedException {
        String globalId = CodeUtil.createUuid36();
        String id = CodeUtil.createUuid36();
        zzGlobalEntity.setGlobalId(globalId);
        zzGlobalEntity.setId(id);
        int flag = zzGlobalMapper.insertSelective(zzGlobalEntity);
        if (flag < 1) {
            throw new DoSqlFailedException("全局参数配置失败!");
        }
        return flag;
    }

    /**
     * 根据globalId修改全局变量方法
     * 
     * @param zzGlobalEntity
     * @param globalId 全局变量Id
     * @return
     * @throws DoSqlFailedException
     */
    @Override
    public int updateZZGlobal(ZZGlobalEntity zzGlobalEntity) throws DoSqlFailedException {
        ZZGlobalExample example = new ZZGlobalExample();
        example.createCriteria().andGlobalIdEqualTo(zzGlobalEntity.getGlobalId());
        int flag = zzGlobalMapper.updateByExampleSelective(zzGlobalEntity, example);
        if (flag < 1) {
            throw new DoSqlFailedException("全局参数更新失败!");
        }
        return flag;
    }

    @Override
    public ZZGlobalEntity selectZZGlobal(String globalId) throws EmptyResultException {
        List<ZZGlobalEntity> list = this.zzGlobalMapper.selectByGlobalId(globalId);
        if (list == null) {
            throw new EmptyResultException("数据库为空!");
        }
        return list.get(0);
    }

    @Override
    public int countZZGlobal(String globalId) {
        int flag = this.zzGlobalMapper.countByGlobalId(globalId);
        return flag;
    }

    @Override
    public SysResult saveGlobalParam(ZZGlobalEntity zzGlobalEntity) throws DoSqlFailedException {
    	String globalId = zzGlobalEntity.getGlobalId();
    	int flag = 0;
    	try {
    		// 依据gid保存参数
    		/// 如果gid为空,则查询数据库是否有数据
    		if (null == globalId || globalId.equals("")) {
    			int rows = this.countZZGlobal();
    			// 若数据库没有数据,则插入
    			if (rows < 1) {
    				flag = this.addZZGlobal(zzGlobalEntity);
    				return new SysResult(ErrorUtil.CODE2000, "OK", flag);
    			}
    			// 若数据库有数据,则更新数据库第一条数据
    			ZZGlobalEntity selectGlobal = this.selectZZGlobal();
    			globalId = selectGlobal.getGlobalId();
    			zzGlobalEntity.setGlobalId(globalId);
    		}
    		flag = this.countZZGlobal(globalId);
    		if (flag < 1) {
    			flag = this.addZZGlobal(zzGlobalEntity);
    			return new SysResult(ErrorUtil.CODE2000, "OK", flag);
    		}
    		flag = this.updateZZGlobal(zzGlobalEntity);
    		return new SysResult(ErrorUtil.CODE2000, "OK", flag);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("全局参数更新失败!", e);
			throw new DoSqlFailedException("全局参数更新失败!", e);
		}
    }

    /*
     * @see com.zzmj.service.ZZGlobalService#selectZZGlobal()
     */
    @Override
    public ZZGlobalEntity selectZZGlobal() {
        return this.zzGlobalMapper.selectTheFirst();
    }

    @Override
    public int countZZGlobal() {
        return this.zzGlobalMapper.countAll();
    }

	@Override
	public SysResult select(String globalId) {
		ZZGlobalEntity entity = null;
		try {
			 // 依据gid查询,如果gid为空,则选择分页查询
	        if (null == globalId || globalId.equals("")) {
	            // 分页查询前先判断数据库中有没有数据
	            int rows = this.countZZGlobal();
	            // 若没有,返回
	            if (rows < 1) {
	                return new SysResult(ErrorUtil.CODE2001, "数据库为空", entity);
	            }
	            // 若有,查询第一条数据并返回
	            entity = this.selectZZGlobal();
	            if (null == entity.getGlobalId()) {
	                return new SysResult(ErrorUtil.CODE2001, "数据库参数错误!", entity);
	            }
	            return new SysResult(ErrorUtil.CODE2000, "OK", entity);
	        }
	        // gid 不为空,依据gid查询
	        try {
	            entity = this.selectZZGlobal(globalId);
	        } catch (EmptyResultException e) {
	            e.printStackTrace();
	            return new SysResult(ErrorUtil.CODE2001, "数据库参数错误!!", entity);
	        }
	        return new SysResult(ErrorUtil.CODE2000, "ok", entity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("全局参数查询失败!",e);
			throw new DoSqlFailedException("全局参数查询失败!", e);
		}
       
	}
}
