package com.zmj.microservice.arithmetic.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @description 顺序数据周期,周期内的数据集是,按record.time 排序的, 最新的数据最靠前
 *              同时,只能按照时间顺序添加记录,时间早于数据周期内curRecord的数据将不被添加
 * @author umr
 * @date 2019/6/17
 */
public class OrderedPeriod implements Period{

    /**
     * 统计周期的起始时间,早于该时间的值不会被add,默认0,如不需要不需设置
     * */
    long startTime = 0;

    /**
     * 统计周期的结束时间,晚于该事件的值不会被add,默认max,如不需要,不需设置
     * */
    long endTime = Long.MAX_VALUE;

    /**
     * 统计周期的数据集
     * */
    List<Record> records = new LinkedList<>();


    Record curRecord = null;

    Record preRecord = null;

    long curTime = 0;

    long preTime = 0;


    public OrderedPeriod() {
    }

    public OrderedPeriod(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean add(Record record){
        if (!filtrate(record)||!records.add(record)){
            return false;
        }else {
            //更新cur pre
            preRecord = curRecord;
            curRecord = record;
            preTime = preRecord.getTime();
            curTime = curRecord.getTime();
        }
        return true;
    }

    void addAll(Collection<Record> collection){
        for(Record r:collection){
            add(r);
        }
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getCurTime() {
        return curTime;
    }

    public long getPreTime() {
        return preTime;
    }

    private Record preRecord(){
        if (preRecord == null)
            return curRecord;
        return preRecord;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    @Override
    public long getFirstTime() {
        if (null == records||records.size() == 0)
            return -1;
        return records.get(0).getTime();
    }

    @Override
    public long getLastTime() {
        if (null == records||records.size() == 0)
            return -1;
        LinkedList<Record> records = (LinkedList<Record>) this.records;
        return records.getLast().getTime();
    }

    @Override
    public Record getCurRecord() {
        return curRecord;
    }

    @Override
    public Record getPreRecord() {
        return preRecord();
    }

    @Override
    public boolean filtrate(Record record) {
        long t = record.getTime();
        return t >= curTime && startTime <= t && t <= endTime;
    }
}
