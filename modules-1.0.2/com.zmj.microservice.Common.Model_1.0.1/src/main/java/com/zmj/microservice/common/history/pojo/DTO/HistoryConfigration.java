package com.zmj.microservice.common.history.pojo.DTO;

import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;

import javax.validation.constraints.NotNull;

/**
* @description:    历史查询的配置类
* @author:         umr
* @date:           2019/4/26
*/
public class HistoryConfigration {

    /**
     * 查询的数据类型,默认为double
     * */
    private DataValueTypeEnum dvte = DataValueTypeEnum.DOUBLE;
    /**
     * 查询带编号的数据点时使用的参数,例如支架压力,默认没有
     */
    private Integer n = null;
    /**
     * 查询历史数据的基本参数
     */
    @NotNull
    private BaseUNDTO dto;
    /**
     * 数据点名称
     * */
    @NotNull
    private String dataNameEnum;

    /**
     * 子sql,默认就4这个
     */
    private String sqlFragment = SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT;

    /**
     * 数据库名
     */
    private String dbName;
    /**
     * 数据点名
     * */
    private String dataName;
    private String startTime;
    private String endTime;
    /**
     * 表名
     * */
    private String[] tables;

    /**
     * 自定义sql,用来查询特殊的表名
     * */
    private String consumerSql;


    public HistoryConfigration(@NotNull BaseUNDTO dto, @NotNull String dataNameEnum) {
        this.dto = dto;
        this.dataNameEnum = dataNameEnum;
        init();
    }

    private void init(){
    }
}
