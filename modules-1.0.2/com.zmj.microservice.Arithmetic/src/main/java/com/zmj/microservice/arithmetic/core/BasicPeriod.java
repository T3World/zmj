package com.zmj.microservice.arithmetic.core;

import java.util.Collection;
import java.util.List;

/**
 * @description 类描述
 * @author umr
 * @date 2019/6/17
 */
public class BasicPeriod implements Period{

    private List<Record> records;

    private Record curRecord;

    private Record preRecord;

    private long curTime;

    private long preTime;

    private List<Filtrate> filtrates;

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
            return true;
        }
    }

    void addAll(Collection<Record> collection){
        for(Record r:collection){
            add(r);
        }
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
