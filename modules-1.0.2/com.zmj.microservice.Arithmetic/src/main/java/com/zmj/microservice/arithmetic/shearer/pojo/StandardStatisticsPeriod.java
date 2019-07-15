package com.zmj.microservice.arithmetic.shearer.pojo;

import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.math.BigDecimal;

/**
 * @description:    一次统计周期,本class中所有时间域的单位为毫秒值
 * @author:         umr
 * @date:           2019/5/5
 */
public class StandardStatisticsPeriod {

    /**
     * 参数一:依据该参数判断是否通讯正常
     */
    private final long CONDITION1;

    /**
     * 第一条数据记录时刻
     */
    private long ancTime = 0L;
    /**
     * 上条数据采样时刻
     */
    private long preTime;
    /**
     *
     * 上调数据值
     */
    private double preValue = 0;
    /**
     * 当前数据采样时刻
     */
    private long curTime;
    /**
     * 当前数据值
     */
    private double curValue;

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
    private long samDur = 0L;
    /**
     * 通讯正常率
     */
    private double commRatio = 0L;
    /**
     * 开机率
     */
    private double runRatio = 0L;


    public StandardStatisticsPeriod() {
        this.CONDITION1 = 5*60*1000L;
    }

    public StandardStatisticsPeriod(long condition) {
        this.CONDITION1 = condition;
    }

    public boolean addRecord(double value, long time) {
        //判断参数是否合理
        if (time < this.preTime)
            return false;

        if(ancTime == 0L)
            ancTime = time;

        //先接受参数
        this.preTime = this.curTime;
        this.preValue = this.curValue;

        this.curTime = time;
        this.curValue = value;
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

    public double getCurrentValue() {
        return this.curValue;
    }

    /**
     * 获取上条记录
     */
    public double getPreValue() {
        if (0 == this.preValue)
            this.preValue = this.curValue;
        return this.preValue;
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

        return Double.valueOf(rd.divide(cd, 5, 5).toString());
    }
    /**
     * 获取通讯正常率
     */
    public double getCommRatio(){
        if (samDur == 0)
            return 0;
        BigDecimal cd = BigDecimal.valueOf(commDur);
        BigDecimal sd = BigDecimal.valueOf(samDur);
        return Double.valueOf(cd.divide(sd, 2, 5).toString());
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
        double i =  getPreValue();
        return i != 0.0;
    }

}
