package com.zzmj.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZDeviceAccumulativeMapper;
import com.zzmj.pojo.entity.ZZDeviceAccumulative;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceAccumulativeService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;


@Service
public class ZZDeviceAccumulativeServiceImpl implements ZZDeviceAccumulativeService {

	private static Logger logger = Logger.getLogger(ZZGlobalServiceImpl.class);
	@Autowired
	private ZZDeviceAccumulativeMapper zzDeviceAccumulativeMapper;
	
	@Override
	public SysResult add(ZZDeviceAccumulative zzDeviceAccumulative) {
		
		try {
			zzDeviceAccumulative.setId(CodeUtil.createUuid36());
			int flag=zzDeviceAccumulativeMapper.add(zzDeviceAccumulative);
			if (flag > 0) {
				return new SysResult(ErrorUtil.CODE2000, "添加设备累积量表成功", zzDeviceAccumulative);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "设备累积量表添加失败", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("设备添加失败，出现异常", e);
	        throw new DoSqlFailedException("设备添加失败");
		}
	}

	@Override
	public SysResult delete(String deviceId) {

		try {
			int flag=zzDeviceAccumulativeMapper.delete(deviceId);
			if (flag > 0) {
				return new SysResult(ErrorUtil.CODE2000, "删除设备累积量表成功", deviceId);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "设备累积量表删除失败", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("设备删除失败，出现异常", e);
	        throw new DoSqlFailedException("设备删除失败");
		}
	}

	
	/**
	 * 根据deviceId更改设备累计量表   
	 * 先根据deviceId到数据库查询  如果存在就进行更新  
	 * 不存在直接添加更改的数据
	 */
	@Override
	public SysResult update(ZZDeviceAccumulative zzDeviceAccumulative) {

			String deviceId = zzDeviceAccumulative.getDeviceId();
			int i = zzDeviceAccumulativeMapper.countByDeviceId(deviceId);
			if (i<1){
				//记录不存在,则 进行添加传过来的对象
				try {
					zzDeviceAccumulative.setId(CodeUtil.createUuid36());
					int flag=zzDeviceAccumulativeMapper.add(zzDeviceAccumulative);
					if (flag > 0) {
						return new SysResult(ErrorUtil.CODE2000, "添加设备累积量表成功", zzDeviceAccumulative);
					} else {
						return new SysResult(ErrorUtil.CODE2001, "添加设备累积量表失败 失败", zzDeviceAccumulative);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("设备累积量添加失败，出现异常", e);
			        throw new DoSqlFailedException("设备累积量添加失败");
				}
			}else {
				//记录存在则进行更改
				try {
					int flag = zzDeviceAccumulativeMapper.update(zzDeviceAccumulative);
					if (flag > 0) {
						return new SysResult(ErrorUtil.CODE2000, "更改设备累积量表成功", zzDeviceAccumulative);
					} else {
						return new SysResult(ErrorUtil.CODE2001, "更改设备累积量表失败 失败", zzDeviceAccumulative);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("设备累积量更改失败，出现异常", e);
			        throw new DoSqlFailedException("设备累积量更改失败");
				}		
			}	
	}
	
	/**
	 * 根据deviceId 查询设备累积量表   如皋存在查询返回
	 * 没有的话  往数据库插入0   在将该插入的进行返回
	 */
	@Override
	public SysResult selectByDeviceId(String deviceId) {
			if(null==deviceId || deviceId.equals("")){
				  return new SysResult(ErrorUtil.CODE2001, "deviceId不可为空", null);
			}
	
			 List<ZZDeviceAccumulative> zzDeviceAccumulativeList=zzDeviceAccumulativeMapper.selectByDeviceId(deviceId);
			 if (zzDeviceAccumulativeList.size() != 0){
				  return new SysResult(ErrorUtil.CODE2000, "查询成功ok", zzDeviceAccumulativeList.get(0));
			 }else{
				 	ZZDeviceAccumulative zzDeviceAccumulative=new ZZDeviceAccumulative();
				 	zzDeviceAccumulative.setId(CodeUtil.createUuid36());
					zzDeviceAccumulative.setDeviceId(deviceId);
					zzDeviceAccumulative.setCumulativeRunTime("0");
					zzDeviceAccumulative.setCumulativeLoadCycles("0");
					zzDeviceAccumulative.setCumulativeCoalWeight("0");
				 
					try {
						int flag=zzDeviceAccumulativeMapper.add(zzDeviceAccumulative);
						if (flag > 0) {
							 return new SysResult(ErrorUtil.CODE2000, "查询设备累积量表成功",zzDeviceAccumulative);
						} else {
							return new SysResult(ErrorUtil.CODE2001, "添加设备累积量表失败 查询失败", zzDeviceAccumulative);
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("设备累积量添加失败，出现异常", e);
				        throw new DoSqlFailedException("设备累积量添加失败");
					}
			 }
	}
}
