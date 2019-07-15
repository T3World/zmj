package com.zmj.constant;

public class DatabaseConstant {
    /*
     * 库名前缀
     */
    public static final String DATABASE_NAME_PREFIX = "zzmj_db";

    /**
     * 表名前缀
     */
    public static final String TABLE_NAME_PREFIX = "datavalues_";

    /**
     * 主题最低长度
     */
    public static final int THEME_MIN_LENGTH = 4;

    /**
     * 主题数据库开始下标
     */
    public static final int THEME_DATABASE_BEGIN_INDEX = 0;

    /**
     *主题数据库结束下标
     */
    public static final int THEME_DATABASE_END_INDEX = 2;

    /**
     *主题属性值开始下标
     */
    public static final int THEME_DATANAME_BEGIN_INDEX = 3;

    /**
     *支架压力主题属性值字段
     */
    public static final String THEME_DATANAME_SUPPORT_PRESSURE = "/Support/Scu/Pressure";

    /**
     *自动跟机字段名
     */
    public static  final String THEME_DATANAME_IS_AUTO_RUNNING = "/Support/IsAutoRunning";


    /**
     * 煤机的红外位置
     */
    public static final String THEME_DATANAME_INFRARED_SHEARER_POS_DATA="/Support/InfraredShearerPos";

    /**
     * 煤机轨迹历史多表查询中用到的单表查询子句
     */
    public static final String SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam where DataName = 'DataNameParam' and GenerateTime between StartTimeParam and EndTimeParam) Alias";

    /**
     *数据库集团/公司信息配置文件地址
     */
    public static final String FILE_ORGANIZATION = "organization.yml";

    /**
     *数据库中表名的时间精确度,即表示时间的字符长度
     */
    public static final int TIME_LENGTH = 8;
}
