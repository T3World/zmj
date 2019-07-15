package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZDeviceType;
import com.zzmj.pojo.entity.ZZDeviceTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ZZDeviceTypeMapper {
   
	/**
	 * 批量删除设备类型   假删除  更改isDel属性为1
	 * @param list
	 * @return 删除成功的个数
	 */
	int batchDel(@Param("List")List<String> list);
	/**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int countByExample(ZZDeviceTypeExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int deleteByExample(ZZDeviceTypeExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    List<ZZDeviceType> selectByExample(ZZDeviceTypeExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByExampleSelective(@Param("record") ZZDeviceType record, @Param("example") ZZDeviceTypeExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByExample(@Param("record") ZZDeviceType record, @Param("example") ZZDeviceTypeExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    List<ZZDeviceType> selectByExampleAndPage(ZZDeviceTypeExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int insert(ZZDeviceType record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int insertSelective(ZZDeviceType record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    ZZDeviceType selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByPrimaryKeySelective(ZZDeviceType record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByPrimaryKey(ZZDeviceType record);

    
    /**
     * 假删除  返回删除(修改)个数
     * @return
     */
    int delByIsDel(String devicetypeId);
    
    
    /**
     * 根据DeviceType_Id查找对象
     */
    ZZDeviceType getZZDeviceType(String devicetypeId);
    
    
    /**
     * 以下对设备类型表进行分页 所需要的方法
     * @return
     */
    int getAllCount();
    int getCountByKeyWord(@Param("keyword")String keyword);
    List<ZZDeviceType> selectZZDeviceTypeList(@Param("rowNo")Integer rowNum,@Param("pageSize")Integer pageSize);
    List<ZZDeviceType> selectZZDeviceTypeListByKeyword(@Param("keyword")String keyword,@Param("rowNo")Integer rowNum,@Param("pageSize")Integer pageSize);

    /**
     * 查询所有设备种类集合
     */
    List<ZZDeviceType> selectAll();
    
    /**
     * 根据deviceTypeId查询ZZDeviceType对象
     * @param deviceTypeId
     * @return
     */
    ZZDeviceType selectByDeviceTypeId(String deviceTypeId);

}