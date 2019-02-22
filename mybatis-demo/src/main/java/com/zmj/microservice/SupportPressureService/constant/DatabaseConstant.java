package com.zmj.microservice.SupportPressureService.constant;

public class DatabaseConstant {
    //库名前缀
    public static final String DATABASE_NAME_PREFIX = "zzmj_db";

    //表名前缀
    public static final String TABLE_NAME_PREFIX = "datavalues_";

    //主题最低长度
    public static final int THEME_MIN_LENGTH = 4;

    //主题数据库开始下标
    public static final int THEME_DATABASE_BEGIN_INDEX = 0;

    //主题数据库结束下标
    public static final int THEME_DATABASE_END_INDEX = 2;

    //主题属性值开始下标
    public static final int THEME_DATANAME_BEGIN_INDEX = 3;

    //主题属性值字段
    public static final String THEME_DATANAME_SUPPORT_PRESSURE = "/Support/Scu/Pressure";

    //煤机轨迹历史多表查询中用到的单表查询子句
    public static final String SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT = "Select DataName,DataValue,ValueType,GenerateTime,StoreTime From (" +
            "select DataName,DataValue,ValueType,GenerateTime,StoreTime from TableNameParam where DataName = DataNameParam and GenerateTime between StartTimeParam and EndTimeParam) Alias";

}
