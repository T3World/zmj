package com.zmj.microservice.common.history.constant;

/**
 * @author umr,hushixian
 * @date 2019-03-30 13:58:13
 */
public final class SqlFragmentConstant {
    /**
     * 煤机轨迹历史多表查询中用到的单表查询子句,也通用
     */
    public static final String SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where DataName = 'DataNameParam' and GenerateTime between StartTimeParam and EndTimeParam) Alias";
    /**
     * 右边like语句
     */
    public static final String SQL_SELECT_HISTORY_FRAGMENT_LIKE_RIGHT_SIDE = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where DataName like 'DataNameParam%' and GenerateTime between StartTimeParam and EndTimeParam) Alias";
    /**
     * 查询进出口压力 (in2)/(out2)合并查询的方法
     */
    public static final String SQL_SELECT_HISTORY_FRAGMENT_EXCEPT_ZERO = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where (DataName = 'DataNameParam') and (DataValue!= '0') and GenerateTime between StartTimeParam and EndTimeParam) Alias";

    /**
     * 查询进出口压力 (in/in1)/(out/out1)合并查询的方法
     */
    public static final String SQL_SELECT_HISTORY_FRAGMENT_INOUT_ZERO_ONE = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where (DataName = 'DataNameParam' or DataName = 'DataNameParam1') and (DataValue!= '0') and GenerateTime between StartTimeParam and EndTimeParam) Alias";

    /**
     * 煤机速度取绝对值sql
     */
    public static final String SQL_SELECT_COAL_CUTTER_SPREED_ABS = "Select  DataValue ,GenerateTime From (" +
            "select ABS(DataValue) as DataValue ,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where DataName = 'DataNameParam' and GenerateTime between StartTimeParam and EndTimeParam) Alias";

    /**
     * 特殊表名,查询采煤机编码器位置
     */
    public static final String SQL_SELECT_HISTORY_CONSUMER_TABLENAME = "Select DataValue,GenerateTime From (" +
            "select Position as DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where GenerateTime between StartTimeParam and EndTimeParam) Alias";
    /**
     * select the first one
     */
    public static final String SQL_SELECT_THE_FIRST_ONE = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where DataName = 'DataNameParam' and GenerateTime between StartTimeParam and EndTimeParam ORDER BY GenerateTime DESC limit 0,1) Alias";
    /**
     * select the last one
     */
    public static final String SQL_SELECT_THE_LAST_ONE = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where DataName = 'DataNameParam' and GenerateTime between StartTimeParam and EndTimeParam ORDER BY GenerateTime ASC limit 0,1) Alias";
    /**
     * select the first one like
     */
    public static final String SQL_SELECT_THE_FIRST_ONE_LIKE = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where DataName like 'DataNameParam' and GenerateTime between StartTimeParam and EndTimeParam ORDER BY GenerateTime DESC limit 0,1) Alias";
    /**
     * select the last one like
     */
    public static final String SQL_SELECT_THE_LAST_ONE_LIKE = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where DataName like 'DataNameParam' and GenerateTime between StartTimeParam and EndTimeParam ORDER BY GenerateTime ASC limit 0,1) Alias";


}
