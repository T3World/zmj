package com.zmj.microservice.history.consumer.service;


import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.ShearerPositionDTO;
import com.zmj.microservice.common.history.pojo.DTO.TractionSpeedDataDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;

/**
 * @description ShearerPosTrail
 * @author umr
 * @date 2019/6/10
 */
public interface ShearerPosTrailService {
    /**
     * 该接口直接调用电控系统信息微服务
     * */
    SysResult<CommonVO> getInfraredPosData(BaseUNDTO fs) ;
    /**
     * 用于查询在指定时间段内采煤机编码器的位置数据；
     * 业务逻辑说明:
     * 参考比率公式rate:(pos1-pos2)/(scuNo1-scuNo2)=((pos1-posX)/(scuNo1-scuNoX)
     * scuNoX取整
     * 1、	查询出来指定时间段的煤机编码器位置x；
     * 2、	使用公式参考比率公式换算编码器对应的位置;
     * */
    SysResult<CommonVO> getPositionData(ShearerPositionDTO fs) throws IllegalParamException;
    /**
     * 用于查询在指定时间段内煤机的红外位置与自动跟机的情况
     * 1、	先调用ElectronicControlSystemService获取红外煤机位置；
     * 2、	再调用ElectronicControlSystemService获取相同时间段内的所有跟机状
     * 3、	将红外煤机位置的数据与跟机状态的数据合并在一起，并按照时间顺序进行排序，返回结果
     * */
    SysResult<CommonVO> getInfraredPosWithAutoRunningData(BaseUNDTO fs);
    /**
     * 用于查询在指定时间段内煤机的红外位置与记忆截割的情况
     *
     * 1、	先调用ElectronicControlSystemService获取红外煤机位置；
     * 2、	再调用ShearerSystemService获取相同时间段内的所有记忆截割状态(状态3)；
     * 3、	将红外煤机位置的数据与记忆截割状态的数据合并在一起，并按照时间顺序进行排序，返回结果；
     * */
    SysResult<CommonVO> getInfraredPosWithAutoModeData(BaseUNDTO fs) ;
    /**
     * 用于查询在指定时间段编码器位置与自动跟机的情况；
     * 1、	先调用ElectronicControlSystemService获取支架跟机状态；
     * 2、	再调用ShearerSystemService获取相同时间段内的编码器位置 (状态3)；
     * 3、	将编码器的数据与自动跟机状态的数据合并在一起，并按照时间顺序进行排序，返回结果；
     * */
    SysResult<CommonVO> getPositionWithAutoRunningData(ShearerPositionDTO fs) throws IllegalParamException;
    /**
     * 用于查询在指定时间段编码器位置与记忆截割的情况；
     * 1、	先调用ShearerSystemService获取指定时间内的记忆截割状态；
     * 2、	再调用ShearerSystemService获取相同时间段内的编码器位置 (状态3)；
     * 3、	将编码器的数据与记忆截割状态的数据合并在一起，并按照时间顺序进行排序，返回结果；
     * */
    SysResult getPositionWithAutoModeData(ShearerPositionDTO fs) throws  IllegalParamException;
    /**
     * 用于查询在指定时间段的采煤机速度；
     * 1、	调用采煤机系统获取采煤机速度；
     * 2、  去重
     * */
    SysResult getTractionSpeedData(TractionSpeedDataDTO fs) ;

}
