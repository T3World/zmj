package com.zmj.microservice.common.history.cenum;

/**
 * 历史查询所用的ENUM
 *
 * 向数据库查询的使用数据点关键字
 *
 * @author umr,hushixian
 * @date 2019-03-30 13:48:11
 */

public enum DataNameEnum {
    //进口压力0,1
    FSIN("PumpStation/FilterStation/InPressure"),
    //进口压力2
    FSIN2("PumpStation/FilterStation/InPressure2"),
    //出口压力0,1
    FSOUT("PumpStation/FilterStation/OutPressure"),
    //出口压力2
    FSOUT2("PumpStation/FilterStation/OutPressure2"),
    //采煤机速度
    SHEARER_TS("Shearer/TractionSpeed"),
    //采煤机左滚筒高度
    SHEARER_LDH("Shearer/LeftDrumHeight"),
    //采煤机右滚筒高度
    SHEARER_RDH("Shearer/RightDrumHeight"),
    //采煤机位置
    SHEARER_P("Shearer/Position"),
    //采煤机状态码
    SHEARER_RS("Shearer/RunningStateIDs"),
    //刮板机机头高速电机电流
    CONVEYOER_HHS("Conveyor/Head/HighSpeadMotor/Current"),
    //刮板机机头低速电机电流
    CONVEYOER_HLS("Conveyor/Head/LowSpeadMotor/Current"),
    //刮板机机尾高速电机电流
    CONVEYOER_THS("Conveyor/Tail/HighSpeadMotor/Current"),
    //刮板机机尾低速电机电流
    CONVEYOER_TLS("Conveyor/Tail/LowSpeadMotor/Current"),
    //转载机低速电机电流
    LOADER_LC("Loader/LowSpeadMotor/Current"),
    //转载机高速电机电流
    LOADER_HC("Loader/HighSpeadMotor/Current"),
    //破碎机高速电机电流
    CRUSHER_HC("Crusher/HighSpeadMotor/Current"),
    //破碎机低速电机电流
    CRUSHER_LC("Crusher/LowSpeadMotor/Current")
    ;



    private String value;

    DataNameEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
