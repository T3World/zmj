package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZGlobalEntity;
import com.zzmj.pojo.entity.ZZGlobalExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ZZGlobalMapper {
    /**
     * 条件统计 参数:查询条件,null为整张表 返回:查询个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int countByExample(ZZGlobalExample example);

    /**
     * 批量条件删除 参数:删除条件,null为整张表 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int deleteByExample(ZZGlobalExample example);

    /**
     * 批量条件查询 参数:查询条件,null查整张表 返回:对象集合
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    List<ZZGlobalEntity> selectByExample(ZZGlobalExample example);

    /**
     * 批量条件修改，空值条件不修改 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByExampleSelective(@Param("record") ZZGlobalEntity record, @Param("example") ZZGlobalExample example);

    /**
     * 批量条件修改，空值条件会修改成null 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByExample(@Param("record") ZZGlobalEntity record, @Param("example") ZZGlobalExample example);

    /**
     * 物理分页条件查询 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    List<ZZGlobalEntity> selectByExampleAndPage(ZZGlobalExample example, RowBounds rowBound);

    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int insert(ZZGlobalEntity record);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int insertSelective(ZZGlobalEntity record);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    ZZGlobalEntity selectByPrimaryKey(@Param("id") String id);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByPrimaryKeySelective(ZZGlobalEntity record);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByPrimaryKey(ZZGlobalEntity record);

    /**
     * @Title: selectByGlobalId
     * @Description: 根据globalID查询表单
     * @param: @param globalId
     * @param: @return
     * @return: int
     */
    List<ZZGlobalEntity> selectByGlobalId(@Param("globalId") String globalId);

    /**
     * @Title: countByGlobalId
     * @Description: 依据globalId查询配置是否存在,用于
     * @param: @param globalId
     * @param: @return
     * @return: int
     */
    int countByGlobalId(@Param("globalId") String globalId);

    ZZGlobalEntity selectTheFirst();

    int countAll();

}