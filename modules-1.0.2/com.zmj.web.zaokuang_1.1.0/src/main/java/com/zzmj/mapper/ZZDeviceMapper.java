package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZDevice;
import com.zzmj.pojo.entity.ZZDeviceExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ZZDeviceMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int countByExample(ZZDeviceExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int deleteByExample(ZZDeviceExample example);

    /**
     * 批量条件查询,支持大字段类型
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    List<ZZDevice> selectByExampleWithBLOBs(ZZDeviceExample example);
    List<ZZDevice> selectByExampleWithBLOBss(ZZDeviceExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    List<ZZDevice> selectByExample(ZZDeviceExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByExampleSelective(@Param("record") ZZDevice record, @Param("example") ZZDeviceExample example);

    /**
     * 批量条件修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByExampleWithBLOBs(@Param("record") ZZDevice record, @Param("example") ZZDeviceExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByExample(@Param("record") ZZDevice record, @Param("example") ZZDeviceExample example);

    /**
     * 物理分页条件查询,支持大字段
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    List<ZZDevice> selectByExampleWithBLOBsAndPage(ZZDeviceExample example, RowBounds rowBound);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    List<ZZDevice> selectByExampleAndPage(ZZDeviceExample example, RowBounds rowBound);

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
    int insert(ZZDevice record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int insertSelective(ZZDevice record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    ZZDevice selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByPrimaryKeySelective(ZZDevice record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByPrimaryKeyWithBLOBs(ZZDevice record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByPrimaryKey(ZZDevice record);
    
    
    /**
     * 根据设备类型id查询设备表集合
     */
    List<ZZDevice> selectByDeviceTypeId(@Param("deviceTypeId") String deviceTypeId);
    
    //以下四个方法查询总条数
    /**
     * 查询devicetypeId  keyword都为空时的总条数
     */
    int getAllCount();
    
    /**
     * 询devicetypeId为空  keyword不为空时的总条数
     * @param keyword
     * @return
     */
    int countDeviceByKeyword(String keyword);
    
    /**
     * 查询devicetypeId不为空  关键字为空时的总条数
     * @param deviceTypeId
     * @return
     */
    int getCountBydeviceTypeId(String deviceTypeId);
    
    
    /**
     * 
     * @param deviceTypeId
     * @param keyword
     * @return
     */
    int countDeviceByDeviceTypeIdAndByKeyword(@Param("deviceTypeId") String deviceTypeId,
			@Param("keyword") String keyword);
    
    
    /**
     * 以下三个方法查询数据
     */
    List<ZZDevice> listZZDeviceAll(@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);
    List<ZZDevice> listZZDeviceBydeviceTypeId(@Param("deviceTypeId")String deviceTypeId,@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);
    List<ZZDevice> listDeviceByKeyword(@Param("keyword")String keyword,@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);
    List<ZZDevice> getlikeKeyWordAndDevicetypeId(@Param("deviceTypeId")String deviceTypeId,@Param("keyword")String keyword,@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);
 
    List<ZZDevice> selectDeviceByWorkfaceAndDeviceTypeId(@Param("workfaceId")String workfaceId,@Param("deviceTypeId")String deviceTypeId);

    /**
     * 查询所有的设备集合
     * @return
     */
    List<ZZDevice> selectAllDevice();
    
    /**
     * 根据deviceId查询ZZDevice对象
     * @param deviceId
     * @return
     */
    ZZDevice selectByDeviceId(String deviceId);
    
}