package com.zmj.microservice.arithmetic.core;

/**
 * @description 数据条数
 * @author umr
 * @date 2019/6/12
 */
public interface Record<T> {

   T getValue();

   long getTime();
}
