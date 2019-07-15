package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZWorkfaceEntity;
import com.zzmj.pojo.entity.ZZWorkfaceExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface ZZWorkfaceMapper {

    /**
     * @Title: countWorkfaceAll
     * @Description: 查询工作面总数
     * @param: 无
     * @return: int 工作面总数
     */
    int countWorkfaceAll();

    /**
     * @Title: countWorkfaceyOrgId
     * @Description: orgId查询工作面总数
     * @param: @param orgId
     * @return: int
     */
    int countWorkfaceByOrgId(@Param("orgId") String orgId);

    /**
     * @Title: countWorkfaceByKeyword
     * @Description: 关键字查询工作面总数
     * @param: keyword
     * @param: 关键字
     * @return: int 符合条件的工作面总数
     */
    int countWorkfaceByKeyword(@Param("keyword") String keyword);

    int countWorkfaceByOrgIdAndByKeyword(@Param("orgId") String orgId, @Param("keyword") String keyword);

    /**
     * @Title: listWorkfaceAll
     * @Description: 分页查询工作面情况
     * @param: rowNo 指定第一个返回记录行的偏移量
     * @param: pageSize 指定返回记录行的最大数目
     * @return: List<ZZWorkfaceEntity>
     */
    List<ZZWorkfaceEntity> listWorkfaceAll(@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);

    /**
     * @Title: listWorkfaceByOrgIdAndKeyword
     * @Description: 双条件分页查询
     * @param: @param orgId
     * @param: @param keyword
     * @param: @param rowNo
     * @param: @param pageSize
     * @param: @return
     * @return: List<ZZWorkfaceEntity>
     */
    List<ZZWorkfaceEntity> listWorkfaceByOrgIdAndKeyword(@Param("orgId") String orgId, @Param("keyword") String keyword, @Param("rowNo") int rowNo, @Param("pageSize") int pageSize);

    /**
     * @Title: listWorkfaceByOrgId
     * @Description: 根据组织Id分页查询工作面
     * @param: orgId
     * @param: rowNo
     * @param: pageSize
     * @return: List<ZZWorkfaceEntity>
     */
    List<ZZWorkfaceEntity> listWorkfaceByOrgId(@Param("orgId") String orgId, @Param("rowNo") int rowNo, @Param("pageSize") int pageSize);

    /**
     * @Title: listWorkfaceByKeyword
     * @Description: 根据关键字分页查询工作面
     * @param: keyword
     * @param: rowNo
     * @param: pageSize
     * @return: List<ZZWorkfaceEntity>
     */
    List<ZZWorkfaceEntity> listWorkfaceByKeyword(@Param("keyword") String keyword, @Param("rowNo") int rowNo, @Param("pageSize") int pageSize);

    /**
     * @param workfaceId
     * @Title: delWorkfaceByWorkfaceId
     * @Description: 逻辑删除工作面信息
     * @param: @return
     * @return: int
     */
    int delWorkfaceByWorkfaceId(@Param("workfaceId") String workfaceId);

    /**
     * 条件统计 参数:查询条件,null为整张表 返回:查询个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int countByExample(ZZWorkfaceExample example);

    /**
     * 批量条件删除 参数:删除条件,null为整张表 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int deleteByExample(ZZWorkfaceExample example);

    /**
     * 批量条件查询 参数:查询条件,null查整张表 返回:对象集合
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    List<ZZWorkfaceEntity> selectByExample(ZZWorkfaceExample example);

    /**
     * 批量条件修改，空值条件不修改 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByExampleSelective(@Param("record") ZZWorkfaceEntity record, @Param("example") ZZWorkfaceExample example);

    /**
     * 批量条件修改，空值条件会修改成null 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByExample(@Param("record") ZZWorkfaceEntity record, @Param("example") ZZWorkfaceExample example);

    /**
     * 物理分页条件查询 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数 <<<<<<< .mine
     * 
     * @ibatorgenerated 2018-12-06 16:51:09 ||||||| .r41 =======
     * @ibatorgenerated 2018-12-08 14:48:37 >>>>>>> .r56
     */
    List<ZZWorkfaceEntity> selectByExampleAndPage(ZZWorkfaceExample example, RowBounds rowBound);

    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入 参数: pojo对象 返回: 新增个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int insert(ZZWorkfaceEntity record);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int insertSelective(ZZWorkfaceEntity record);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    ZZWorkfaceEntity selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByPrimaryKeySelective(ZZWorkfaceEntity record);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByPrimaryKey(ZZWorkfaceEntity record);

    int stopWorkface(@Param("workfaceId") String workfaceId, @Param("state") Integer state);

    List<Map<String, Object>> listWorkfaceNoPage(@Param("orgId") String orgId);
    
    String selectId(String workfaceId);
}