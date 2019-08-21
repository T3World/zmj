package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZCoalMiningRate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @description 综采率
 * @author umr
 * @date 2019/7/26
 */
@Mapper
public interface ZZCoalMiningRateMapper {
    /**
     * 新增单条记录
     * */
    int addCoalMiningRate(ZZCoalMiningRate coalMiningRate);

    /**
     * 根据id 更新单条记录
     * */
    int updateCoalMiningRateById(ZZCoalMiningRate coalMiningRate);

    /**
     * 根据Id 删除单条记录
     * */
    int deleteCoalMiningRateById(@Param("id") String id);

    /**
     *  根据工作面ID,rateType,sourceTime 查询综采率值
     *  结果唯一
     * */
    List<ZZCoalMiningRate> selectWorkfaceRate(@Param("workfaceId") String workfaceId, @Param("rateType") String rateType, @Param("sourceTime") String sourceTime);
    String selectWorkfaceRatee(@Param("workfaceId") String workfaceId, @Param("sourceTime") String sourceTime ,@Param("rateType") String rateType);

    /**
     * 根据workfaceId rateType 起止时间 查询rateValue
     * @param workfaceId
     * @param rateType
     * @param sourceTimeeee
     * @param sourceTimeeeee
     * @return
     */
	List<String> selectWorkfaceRateThird
	(@Param("workfaceId")String workfaceId, @Param("rateType")String rateType, @Param("end")String end,@Param("start")String start);

	List<ZZCoalMiningRate> selectAll();
    
}
