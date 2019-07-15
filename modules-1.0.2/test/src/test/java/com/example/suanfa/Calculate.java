package com.example.suanfa;

import java.util.List;

public class Calculate {
    //每天第一个数据记录时刻
    private long ancTime;
    //上个数据采样时刻
    private long preTime;
    //当前数据采样时刻
    private long curTime;
    //采样间隔
    private long interval;
    //时间中的日期信息
    private long ancTimeDAY;
    //时间中的日期信息
    private long curTimeDAY;
    //从ancTime到当前时刻的开机时长
    private long runPeriod;
    //从ancTime到当前时刻的绝对时长
    private long samPeriod;

    //左牵引电流
    private double lTracA;
    //右牵引电流
    private double rTracA;
    //左截割电流
    private double lCutA;
    //右截割电流
    private double rCutA;


    //开机率
    private double runRatio;
    //显示时间设置
    private String Disp_Interval;
    //开机率链表
    private List<Double> Run_Ratio_list;

    public void runRatio(){

    }
}
