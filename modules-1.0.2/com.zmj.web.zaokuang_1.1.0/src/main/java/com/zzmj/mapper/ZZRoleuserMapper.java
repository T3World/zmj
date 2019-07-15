package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZRoleuserEntity;
import com.zzmj.pojo.entity.ZZRoleuserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ZZRoleuserMapper {

    /**
     * @Title: selectUserIdsByRoleId
     * @Description:
     * @param roleId
     * @return: String[]
     */
    String[] selectUserIdsByRoleId(@Param("roleId") String roleId);

    /**
     * @Title: selectRoleIdsByUserId
     * @Description:
     * @param: @param userId
     * @param: @return
     * @return: roleId
     */
    String[] selectRoleIdsByUserId(@Param("userId") String userId);

    /**
     * 条件统计 参数:查询条件,null为整张表 返回:查询个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int countByExample(ZZRoleuserExample example);

    /**
     * 批量条件删除 参数:删除条件,null为整张表 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int deleteByExample(ZZRoleuserExample example);

    /**
     * 批量条件查询 参数:查询条件,null查整张表 返回:对象集合
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    List<ZZRoleuserEntity> selectByExample(ZZRoleuserExample example);

    /**
     * 批量条件修改，空值条件不修改 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByExampleSelective(@Param("record") ZZRoleuserEntity record, @Param("example") ZZRoleuserExample example);

    /**
     * 批量条件修改，空值条件会修改成null 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByExample(@Param("record") ZZRoleuserEntity record, @Param("example") ZZRoleuserExample example);

    /**
     * 物理分页条件查询 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    List<ZZRoleuserEntity> selectByExampleAndPage(ZZRoleuserExample example, RowBounds rowBound);

    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int insert(ZZRoleuserEntity record);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int insertSelective(ZZRoleuserEntity record);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    ZZRoleuserEntity selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByPrimaryKeySelective(ZZRoleuserEntity record);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByPrimaryKey(ZZRoleuserEntity record);

    /**
     * @Title: selectRoleValueByUserId
     * @Description: 根据userId查询是否具有管理员权限
     * @param: @param userId
     * @param: @return
     * @return: int
     */
    int selectRoleValueByUserId(@Param("userId") String userId);
}