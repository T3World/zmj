package com.zmj.microservice.arithmetic.shearer.core;

import com.zmj.microservice.common.history.pojo.VO.CommonVO;

/**
* @description:    采煤机开机率算法
 *          `       <T> 单条数据对象
* @author:         umr
* @date:           2019/4/30
*/
public interface StatisticsPeriod<T> {
    /**
     * 向条件集中添加一条数据
     * @param record 单条数据
     * @param time 产生数据的时间
     * @return 是否添加成功,数据要求必须按时间顺序排序
     */
    boolean addRecord(T record,long time);

    /**
    * 获取最近的
    * @return 当前数据
    * @date        2019/4/30 9:28
    */
    T getCurrentRecord();

    /**
     * 获取上条记录
     * @return 上条记录
     * @date        2019/4/30 9:28
     */
    T getPreRecord();



}
