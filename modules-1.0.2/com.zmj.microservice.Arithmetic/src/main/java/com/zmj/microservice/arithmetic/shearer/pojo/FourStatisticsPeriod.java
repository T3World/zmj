package com.zmj.microservice.arithmetic.shearer.pojo;

import com.zmj.microservice.arithmetic.core.Record;

import java.math.BigDecimal;

/**
 * @description 类描述
 * @author umr
 * @date 2019/7/12
 */
public class FourStatisticsPeriod {

    /**
     * 连接中断
     */
    private static final BigDecimal CONNECTION_INTERRUPT = BigDecimal.valueOf(-1);
    /**
     * 非开机
     */
    private static final BigDecimal NO_RUNNING = BigDecimal.valueOf(0.0);

    /**
     * 默认断开连接超时时间,用于判断通讯中断的条件之一
     */
    private final long TIME_OUT;

    /**
     * 第一条数据记录时刻
     */
    private long ancTime = 0L;
    /**
     * 上条数据采样时刻
     */
    private long preTime;

    /**
     * 上条数据
     */
    private BigDecimal preValue;
    /**
     * 当前数据采样时刻
     */
    private long curTime;
    /**
     * 当前数据
     */
    private BigDecimal curValue;

    /**
     * 采样间隔
     */
    private long interval;

    /**
     * 从ancTime到当前时刻的开机时长
     */
    private long runDur = 0L;
    /**
     * 开机率
     */
    private double runRatio = 0.0;
    /**
     * 从ancTime到当前时刻的停机时长
     */
    private long offDur = 0L;
    /**
     * 停机率
     */
    private double offRatio = 0.0;
    /**
     * 从ancTime到当前时刻的通讯中断时长
     */
    private long timeOutDur = 0L;
    /**
     * 从ancTime到当前时刻的通讯中断率
     */
    private double timeOutRatio = 0.0;
    /**
     * 从ancTime到当前时刻的绝对时长
     */
    private long samDur = 0L;

    /**
     * 无参构造,设置默认超时时间为31min
     */
    public FourStatisticsPeriod() {
        this.TIME_OUT = 31 * 60 * 1000L;
    }

    public FourStatisticsPeriod(long timeOut) {
        this.TIME_OUT = timeOut;
    }

    /**
     * 每当新增一条记录时,更新成员变量
     */
    private void update(Record record) {
        long time = record.getTime();

        //先更新起始时间
        if(ancTime == 0L){
            ancTime = time;
            curTime = time;
            curValue = (BigDecimal) record.getValue();
        }
        //更新pre
        preValue = curValue;
        preTime = curTime;
        //更新cur
        curValue = (BigDecimal) record.getValue();
        curTime = time;
    }


    /**
     * 新增一条记录
     */
    private boolean add(Record record) {
        //先判断是否满足条件
        if (!filtrate(record))
            return false;
        //滚动更新pre,cur
        update(record);
        //对新的间隔分类并累计
        accumulate();
        //添加完毕
        return true;
    }

    /**
     * 用于判断是否可以执行add
     */
    private boolean filtrate(Record record) {
        if (null == record||record.getTime()<curTime )
            return false;
        //相同时间,value 优先取非零
        if(record.getTime() == curTime && NO_RUNNING.equals(record.getValue()))
            return false;
        return true;
    }

    /**
     * 对新的间隔分类并累计
     */
    private void accumulate() {
        interval = curTime - preTime;
        samDur += interval;
        //时间间隔判断通讯中断
        if (interval < TIME_OUT) {
            if (0 < preValue.compareTo(NO_RUNNING)){
                runDur += interval;
            }else if (NO_RUNNING.equals(preValue)){
                offDur += interval;
            } else {
               timeOutDur += interval;
           }
        }else {
            timeOutDur += interval;
        }
    }

    public double getRunRatio() {
        if (samDur == 0)
            return 0;
        BigDecimal rd = BigDecimal.valueOf(runDur);
        BigDecimal cd = BigDecimal.valueOf(samDur);
        return Double.valueOf(rd.divide(cd, 3, 5).toString());
    }

    public double getOffRatio() {
        if (samDur == 0)
            return 0;
        BigDecimal rd = BigDecimal.valueOf(offDur);
        BigDecimal cd = BigDecimal.valueOf(samDur);
        return Double.valueOf(rd.divide(cd, 3, 5).toString());
    }

    public double getTimeOutRatio() {
        if (samDur == 0)
            return 0;
        BigDecimal rd = BigDecimal.valueOf(timeOutDur);
        BigDecimal cd = BigDecimal.valueOf(samDur);
        return Double.valueOf(rd.divide(cd, 3, 5).toString());
    }

    public long getAncTime() {
        return ancTime;
    }

    public long getSamDur() {
        return samDur;
    }

    public ShearerFourRatio getShearerFourRatio(){
        return new ShearerFourRatio(ancTime,curTime,samDur,runRatio,offRatio,timeOutRatio);
    }
}



















