package com.zmj.microservice.arithmetic.core;

/**
 * @description 希望能成为所有算法计算器的baba
 * @author umr
 * @date 2019/6/12
 */
public interface ArithmeticCalculator{

    /**
     * 向算法计算器中添加单条记录,
     * 即向数据周期中添加记录
     * 如果没有数据周期,应新建
     * @param record 向算法计算器中添加的数据,单条
     * @return 是否添加成功
     * */
    boolean addRecord(Record record);


    /**
     * 配置数据周期,会替换原有的数据周期
     * @param period 数据周期,数据周期是一个数据记录的集合
     * */
    void setPeriod(Period period);

    /**
     * 调用传入的算法对Period进行计算,
     * <>A</> 为算法规定的返回类型
     * @param algorithmic 计算方法
     * @return 利用传入算法得出的结果
     * */
    <A> A calculate(Arithmetic<A> algorithmic);

    /**
     * 使用都是自个儿的东西,去计算
     * @param algorithmic 算法
     * @param period 数据周期
     * @return 传入算法和计算周期的计算结果
     * */
    <A> A calculate(Arithmetic<A> algorithmic,Period period);


}
