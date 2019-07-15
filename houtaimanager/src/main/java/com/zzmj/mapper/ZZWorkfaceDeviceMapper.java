package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZWorkfaceDevice;
import com.zzmj.pojo.entity.ZZWorkfaceDeviceExample;
import com.zzmj.pojo.vo.WorkfaceDeviceInfo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ZZWorkfaceDeviceMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int countByExample(ZZWorkfaceDeviceExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int deleteByExample(ZZWorkfaceDeviceExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    List<ZZWorkfaceDevice> selectByExample(ZZWorkfaceDeviceExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByExampleSelective(@Param("record") ZZWorkfaceDevice record, @Param("example") ZZWorkfaceDeviceExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByExample(@Param("record") ZZWorkfaceDevice record, @Param("example") ZZWorkfaceDeviceExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
     从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    List<ZZWorkfaceDevice> selectByExampleAndPage(ZZWorkfaceDeviceExample example, RowBounds rowBound);

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
    int insert(ZZWorkfaceDevice record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int insertSelective(ZZWorkfaceDevice record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    ZZWorkfaceDevice selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByPrimaryKeySelective(ZZWorkfaceDevice record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2019-06-22 15:39:21
     */
    int updateByPrimaryKey(ZZWorkfaceDevice record);


    /**
     * 根据设备表的workfaceId查询设备表集合
     * 现根据workfaceId查询多个deviceId
     */
    List<String> selectByWorkfaceId(String workfaceId);

    List<WorkfaceDeviceInfo> listWorkfaceDeviceByPage(@Param("workfaceId") String workfaceId,@Param("deviceTypeId") String deviceTypeId ,@Param("rowNo") Integer rowNo,@Param("pageSize") Integer pageSize);

    int countWorkfaceDevice(@Param("workfaceId")String workfaceId,@Param("deviceTypeId") String deviceTypeId);

    List<WorkfaceDeviceInfo> selectWorkfaceDeviceInfo(@Param("workfaceId") String workfaceId,@Param("deviceTypeId") String deviceTypeId,@Param("code") String code);

    List<WorkfaceDeviceInfo> selectWorkfaceDeviceInfoSimple(@Param("workfaceId")String workfaceId,@Param("deviceTypeId") String deviceTypeId,@Param("code") String code);
	
	/**
	 * 先根据deviceId判断关联关系的表(工作面设备)是否有isDel为0的数据
	 * @param deviceId
	 * @return
	 */
	int selectWorkfaceDeviceByDeviceId(String deviceId);
	
	/**
	 * 根据id查找工作面设备
	 * @param id
	 * @return
	 */
	ZZWorkfaceDevice selectWorkfaceDeviceById(String id);
	

    /**
	 * 批量删除工作面设备  假删除  更改isDel属性为1
	 * @param list
	 * @return 删除成功的个数
	 */
	int batchDel(@Param("List")List<String> list);	//此方法废弃

	/**
	 * 删除工作面设备  假删除  修改isDel状态
	 * @param id
	 * @return
	 */
	int update(String id);
	
}