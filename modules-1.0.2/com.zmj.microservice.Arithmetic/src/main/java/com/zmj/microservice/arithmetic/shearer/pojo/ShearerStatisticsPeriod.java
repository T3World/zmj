package com.zmj.microservice.arithmetic.shearer.pojo;


import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.math.BigDecimal;
/**
 * @description:    一次统计周期,本class中所有时间域的单位为毫秒值
 * @author:         umr
 * @date:           2019/4/30
 */
public class ShearerStatisticsPeriod{
    private final long CONDITION1;

    /**
     * 结果集
     * */
    private ShearerRatio sr;
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
    private CommonVO preRecord;
    /**
     * 当前数据采样时刻
     */
    private long curTime;
    /**
     * 当前数据
     */
    private CommonVO curRecord;

    /**
     * 是否开机
     */
    private boolean isRunning;
    /**
     * 采样间隔
     */
    private long interval;
    /**
     * 从ancTime到当前时刻的正常通讯时长
     */
    private long commDur = 0L;

    /**
     * 从ancTime到当前时刻的开机时长
     */
    private long runDur = 0L;
    /**
     * 从ancTime到当前时刻的绝对时长
     */
//    private long samDur = 0L;
    /**
     * 通讯正常率
     */
    private double commRatio = 0L;
    /**
     * 开机率
     */
    private double runRatio = 0L;


    public ShearerStatisticsPeriod() {
        this.CONDITION1 = 5*60*1000L;
    }

    public ShearerStatisticsPeriod(long condition) {
        this.CONDITION1 = condition;
    }

    public boolean addRecord(CommonVO record, long time) {
        //判断参数是否合理
        if (time < this.preTime)
            return false;

        if(ancTime == 0L)
            ancTime = time;

        //相同时间,value不为0
        if (this.curRecord != null && this.curTime == time && !getCurrentRecord().getValue().equals(BigDecimal.valueOf(0)))
            return true;

        this.preTime = this.curTime;
        this.preRecord = this.curRecord;

        this.curTime = time;
        this.curRecord = record;
        //然后计算
        this.interval = this.curTime - getPreTime();
        this.isRunning = isRunning();
        //先判断时间是否大于5min
        if (interval>CONDITION1){
            //通信中断, do nothing
        }else {
            //通讯正常,累加
            commDur = commDur + interval;
            //判断是否开机
            if (isRunning){
                //是开机时间
                runDur = runDur + interval;
            }else{
                // do nothing
            }
        }
        return true;
    }

    public CommonVO getCurrentRecord() {
        return this.curRecord;
    }

    /**
     * 获取上条记录
     */
    public CommonVO getPreRecord() {
        if (null == this.preRecord)
            this.preRecord = this.curRecord;
        return this.preRecord;
    }

    /**
     * 获取上条记录时间
     */
    public long getPreTime(){
        if (0 == this.preTime)
            this.preTime = this.curTime;
        return this.preTime;
    }
    /**
     * 获取第一条记录的时间
     * */
    public long getAncTime(){
        return this.ancTime;
    }


    /**
     * 获取开机率
     * */
    public double getRunRatio(){
        if (commDur == 0)
            return 0;
        BigDecimal rd = BigDecimal.valueOf(runDur);
        BigDecimal cd = BigDecimal.valueOf(commDur);

        return Double.valueOf(rd.divide(cd, 3, 5).toString());
    }
    /**
     * 获取通讯正常率
     */
    public double getCommRatio(){
        if (getSamDur() == 0)
            return 0;
        BigDecimal cd = BigDecimal.valueOf(commDur);
        BigDecimal sd = BigDecimal.valueOf(getSamDur());
        return Double.valueOf(cd.divide(sd, 3, 5).toString());
    }

    /**
     * 获取通讯正常时间
     */
    public long getCommDur(){
        return this.commDur;
    }
    /**
     * 获取从ancTime到当前时刻的绝对时长
     */
    public long getSamDur(){
        return this.curTime - this.ancTime;
    }

    /**
     * 获取从ancTime到当前时刻的开机时长
     */
    public long getRunDur(){
        return this.runDur;
    }

    /**
     * 判断当期interval是否开机
     */
    private boolean isRunning(){
        BigDecimal i;
        Object value = getPreRecord().getValue();
        if (value instanceof BigDecimal){
             i =  (BigDecimal)value;
        }else {
            i = BigDecimal.valueOf((Double) value);
        }
        return !i.equals(BigDecimal.valueOf(0.0));
    }

    public ShearerRatio getShearerRatio() {
        return new ShearerRatio(getAncTime(),curTime,getCommDur(),getRunDur(),getSamDur(),getCommRatio(),getRunRatio());
    }
}
