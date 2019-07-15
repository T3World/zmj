package com.zmj.microservice.arithmetic.core;


/**
 * @description 一次计算周期
 * @author umr
 * @date 2019/6/14
 */
public interface Period {

    /**
     * 用于获取计算周期的起始时间,
     * 需要注意的时,在有些时候起始时间并不等于首条记录的时间
     * 比如,这是某日一天24小时的数据周期,但是在ancTime00:00,并没有相应的数据记录
     *
     * ancTime 应该成为数据有效性的条件
     * @return 返回计算周期的起始时间
     * */
    long getStartTime();

    /**
     * 用于获取计算周期的结束时间,
     * 需要注意的时,在有些时候起始时间并不等于末条记录的时间
     * endTime 应该成为数据有效性的条件
     * @return 返回计算周期的起始时间
     * */
    long getEndTime();

    /**
     * 用于获取首条记录时间
     * @return 首条记录时间
     * */
    long getFirstTime();
    /**
     *  用于获取末条记录时间
     * @return 末条记录时间
     * */
    long getLastTime();

    /**
     * 获取当前记录
     * */
    Record getCurRecord();

    /**
     * 获取上条记录
     * */
    Record getPreRecord();


   boolean filtrate(Record record);
}
