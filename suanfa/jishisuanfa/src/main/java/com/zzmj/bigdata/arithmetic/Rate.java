package com.zzmj.bigdata.arithmetic;

import java.util.Collection;

/**
 * @description T 单条数据类型;
 *              Y  计算结果类型;
 * @author umr
 * @date 2019/6/19
 */
public interface Rate<T,Y> {

    /**
     * 添加单条数据
     * */
    boolean add(T record);

    /**
     * 添加多条数据
     * */
    boolean addALL(Collection<? extends T> records);

    /**
     * 计算返回 各种率
     * */
    Y compute();
}
