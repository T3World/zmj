package com.zmj.microservice.arithmetic.core;

import java.util.Iterator;

/**
 * @description 该数据周期只保留最近时长RollingTime的数据,会滚动删除过期数据
 * @author umr
 * @date 2019/6/17
 */
public class RollingPeriod extends OrderedPeriod{

    public static final long ROLLING_TIME = 15*60*1000;

    @Override
    public boolean add(Record record) {
        //TODO
        boolean add = super.add(record);
        rolling();
        return true;
    }

    private void rolling() {
        //TODO
        Iterator<Record> iterator = records.iterator();
    }

    @Override
    public boolean filtrate(Record record) {
        //TODO
        return super.filtrate(record) ;
    }
}
