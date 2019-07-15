package com.zmj.microservice.common.history.constant;

/**
 *
 * 查询主题参数所
 * 不小心用到的常量,
 * 部分用来避免数组越界异常
 *
 * @author umr,hushixian
 * @date 2019-03-30
 */
public class DatabaseConstant {
    /**
     * 历史数据存储使用的数据库名前缀
     */
    public static final String DATABASE_NAME_PREFIX = "zzmj_db";

    /**
     * 历史数据存储使用的表名前缀
     * */
    public static final String TABLE_NAME_PREFIX = "datavalues_";

    /**
     * 主题最低长度
     */
    public static final int THEME_MIN_LENGTH = 3;

    /**
     * 主题数据库开始下标
     */
    public static final int THEME_DATABASE_BEGIN_INDEX = 0;

    /**
     * 主题数据库结束下标
     */
    public static final int THEME_DATABASE_END_INDEX = 2;

    /**
     * 主题属性值开始下标
     */
    public static final int THEME_DATANAME_BEGIN_INDEX = 0;

    /**
     * 数据库中表名的时间精确度,即表示时间的字符长度
     */
    public static final int TIME_LENGTH = 8;

}
