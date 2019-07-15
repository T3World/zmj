package com.zmj.microservice.arithmetic.core;


import com.zmj.microservice.arithmetic.exception.UnSupportPeriodException;

/**
 * @description 算法
 * @author umr
 * @date 2019/6/14
 */
public interface Arithmetic<A> {

    /**
     * 主要的方法,对传入的数据周期计算,得出结果
     * 使用时应该先调用isSupportPeriod() 方法判断是否支持
     *
     * */
    A calculate(Period period) throws UnSupportPeriodException;

    /**
     * 判断是否是支持的数据周期类型
     * @param period 数据周期
     */
    boolean isSupportPeriod(Period period);
}
