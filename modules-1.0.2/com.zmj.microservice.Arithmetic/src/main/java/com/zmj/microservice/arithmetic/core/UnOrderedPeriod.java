package com.zmj.microservice.arithmetic.core;

import java.util.Collection;
import java.util.List;

/**
 * @description 无序数据周期,可以不按照record.time 添加数据,数据周期内部也不再对数据排序,但提供order方法
 * @author umr
 * @date 2019/6/17
 */
public class UnOrderedPeriod implements Period{


    private List<Record> records;

    private Record curRecord;

    private Record preRecord;

    private long curTime;

    private long preTime;


    //数据保留的周期,单位:ms
    private static final long ROLLING_DURATION = 15*60*1000;

    boolean add(Record record){
        if (!filtrate(record)||!records.add(record)){
            return false;
        }else {
            //更新cur pre
            preRecord = curRecord;
            curRecord = record;
            preTime = curTime;
            curTime = record.getTime();
            //滚动删除
            rolling();

            return true;
        }
    }

    void addAll(Collection<Record> collection){
        for(Record r:collection){
            add(r);
        }
    }

    void rolling(){
    }

    @Override
    public long getStartTime() {
        return 0;
    }

    @Override
    public long getEndTime() {
        return 0;
    }

    @Override
    public long getFirstTime() {
        return 0;
    }

    @Override
    public long getLastTime() {
        return 0;
    }

    @Override
    public Record getCurRecord() {
        return null;
    }

    @Override
    public Record getPreRecord() {
        return null;
    }

    @Override
    public boolean filtrate(Record record) {
        return true;
    }
}
