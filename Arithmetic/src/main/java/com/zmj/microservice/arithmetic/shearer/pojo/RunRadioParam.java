package com.zmj.microservice.arithmetic.shearer.pojo;

/**
* @description:    开机率计算所需的参数
* @author:         umr
* @date:           2019/4/30
*/
public class RunRadioParam {
    /**
     * 从ancTime到当前时刻的正常通讯时长
     */
    private long commDur;
    /**
     * 从ancTime到当前时刻的开机时长
     */
    private long runningDur;

    /**
     * 从ancTime到当前时刻的开机时长
     */
    private long runDur;
    /**
     * 从ancTime到当前时刻的绝对时长
     */
    private long samDur;


}
