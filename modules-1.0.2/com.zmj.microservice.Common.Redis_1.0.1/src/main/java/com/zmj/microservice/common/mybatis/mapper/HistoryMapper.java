package com.zmj.microservice.common.mybatis.mapper;

import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface HistoryMapper {
    /**
     * 查询历史通用sql
     * */
    String SELECT_HISTORY = "<script>" +
            "SELECT DataValue,GenerateTime" +
            " From (\n" +
            " <foreach collection=\"tableList\" separator=\" Union \" item=\"item\">\n" +
            " ${item}\n" +
            " </foreach>\n" +
            " ) as T\n" +
            " Order by T.GenerateTime" +
            "</script>";
    /**
     * 查询表
     * @return 该数据库所有表
     * */
    @Select("show tables")
    String[] selectTables();
    /**
     * 用于判断数据库中是否含有表
     * @param tableName like条件
     * @return 符合条件的表名
     * */
    @Select("SHOW TABLES LIKE #{tableName}")
    String[] showTableLike(@Param("tableName") String tableName);

    @Select(SELECT_HISTORY)
    @Results(id = "BooleanHistory",
            value = {@Result(property = "value",column = "DataValue",javaType = Boolean.class),
                     @Result(column = "GenerateTime",property = "time",javaType = String.class)})
    List<CommonVO> selectBooleanHistory(@Param("tableList") List tableList);

    @Select(SELECT_HISTORY)
    @Results(id = "DoubleHistory",
            value = {@Result(property = "value",column = "DataValue",javaType = Double.class),
                    @Result(column = "GenerateTime",property = "time",javaType = String.class)})
    List<CommonVO> selectDoubleHistory(@Param("tableList") List tableList);
    @Select(SELECT_HISTORY)
    @Results(id = "StringHistory",
            value = {@Result(property = "value",column = "DataValue",javaType = String.class),
                    @Result(column = "GenerateTime",property = "time",javaType = String.class)})
    List<CommonVO> selectStringHistory(@Param("tableList") List tableList);

}
