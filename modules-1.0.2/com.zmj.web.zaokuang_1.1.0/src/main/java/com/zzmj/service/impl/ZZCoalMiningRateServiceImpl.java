package com.zzmj.service.impl;

import com.zzmj.mapper.ZZCoalMiningRateMapper;
import com.zzmj.pojo.entity.ZZCoalMiningRate;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZCoalMiningRateService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZZCoalMiningRateServiceImpl implements ZZCoalMiningRateService {
	
	private static final Logger logger = LoggerFactory.getLogger(ZZUserServiceImpl.class);
	
	@Autowired
	private ZZCoalMiningRateMapper zzCoalMiningRateMapper;
	
    @Override
    public int addCoalMiningRate(ZZCoalMiningRate coalMiningRate) {
        return 0;
    }

    @Override
    public int updateCoalMiningRateById(ZZCoalMiningRate coalMiningRate) {
        return 0;
    }

    @Override
    public int deleteCoalMiningRateById(String id) {
        return 0;
    }

    @Override
    public List<ZZCoalMiningRate> selectWorkfaceRate(String workfaceId, String rateType, String sourceTime) {
        return null;
    }

	@Override
	public SysResult selectRateValue(String workfaceId, String sourceTime,
			String rateType) {
		/*try {
			List<ZZCoalMiningRate> rateList=zzCoalMiningRateMapper.selectWorkfaceRate(workfaceId,sourceTime,rateType);
			if(rateList!=null && rateList.size()!=0){
				return new SysResult(ErrorUtil.CODE2000, "查询综采率成功", rateList.get(0));
			} else {
				return new SysResult(ErrorUtil.CODE2001, "查询综采率成功失败", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询综采率成功失败，出现异常！！", e);
			throw new DoSqlFailedException("查询综采率成功失败！");
		}	*/
		
		
		
		if(("").equals(workfaceId) || null==workfaceId || ("").equals(sourceTime) 
				|| null==sourceTime || ("").equals(rateType) || null==rateType){
			return new SysResult(ErrorUtil.CODE2001, "你没有传入参数",null);
		}
		
		try {
			String rateValue=zzCoalMiningRateMapper.selectWorkfaceRatee(workfaceId, sourceTime, rateType);
				return new SysResult(ErrorUtil.CODE2000, "查询综采率成功",rateValue);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询综采率成功失败，出现异常！！", e);
			throw new DoSqlFailedException("查询综采率成功失败！");
		}
	}

	@Override
	public SysResult selectRateValueSecond(String workfaceId, String rateType,
			String end, String start) {
		if(("").equals(workfaceId) || null==workfaceId || ("").equals(rateType) || null==rateType){
			return new SysResult(ErrorUtil.CODE2001, "你没有传入参数",null);
		}
		
		try {
			return new SysResult
				(ErrorUtil.CODE2000,"查询综采率成功",zzCoalMiningRateMapper.selectWorkfaceRateThird(workfaceId, rateType,end,start));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询综采率成功失败，出现异常！！", e);
			throw new DoSqlFailedException("查询综采率成功失败！");
		}
	}

	@Override
	public List<ZZCoalMiningRate> selectAll() {
		return zzCoalMiningRateMapper.selectAll();
	}
}
