package com.zzmj.service;

import com.zzmj.mapper.ZZCoalMiningRateMapper;
import com.zzmj.pojo.entity.ZZCoalMiningRate;
import com.zzmj.pojo.vo.SysResult;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description 对综采率表的相关操作
 * @author umr
 * @date 2019/7/26
 */
public interface ZZCoalMiningRateService {
    /**
     * 新增单条记录
     * */
    int addCoalMiningRate(ZZCoalMiningRate coalMiningRate);

    /**
     * 根据Id 更新单条记录
     * */
    int updateCoalMiningRateById(ZZCoalMiningRate coalMiningRate);

    /**
     * 根据Id 删除单条记录
     * */
    int deleteCoalMiningRateById(String id);

    /**
     *  根据工作面ID,rateType,sourceTime 查询综采率
     *  结果唯一
     * */
    List<ZZCoalMiningRate> selectWorkfaceRate(String workfaceId,String sourceTime,String rateType);

    /**
     *  根据工作面ID,rateType,sourceTime 查询综采率
     *  结果唯一
     * */
	SysResult selectRateValue(String workfaceId, String sourceTime, String rateType);

	SysResult selectRateValueSecond(String workfaceId,String rateType,String end,String start);

	//查询所有 无用 自己测试用
	List<ZZCoalMiningRate> selectAll();

}
