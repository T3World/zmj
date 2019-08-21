package com.zzmj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zzmj.pojo.entity.ZZDeviceAttribute;
import com.zzmj.pojo.entity.ZZDeviceAttributeExample;

public interface ZZDeviceAttributeMapper {
	
	/**
	 * 批量删除设备属性表中数据  假删除 更改isDel属性为1
	 * 返回批量删除的个数
	 * @param idToList
	 */
	int remove(@Param("List")List<String> idToList);

	/**
	 * 查询设备属性的总数
	 * 
	 * @return
	 */
	int countDeviceAttribute();

	/**
	 * 根据deviceTypeId查找总数
	 * 查询设备属性表中和设备类型表相关联且isDel为0的数量
	 * @return
	 */
	int countDeviceAttributeByDeviceTypeId(@Param("deviceTypeId") String deviceTypeId);

	/**
	 * 
	 * @param keyword 根据关键字查询总数量
	 * @return
	 */
	int countDeviceAttributeByKeyword(@Param("keyword") String keyword);

	/**
	 * g 根据关键字和设备类型id查找数量
	 * 
	 * @param deviceTypeId
	 * @param keyword
	 * @return
	 */
	int countDeviceAttributeByDeviceTypeIddAndByKeyword(@Param("deviceTypeId") String deviceTypeId,
			@Param("keyword") String keyword);

	/**
	 * @Description: 分页查询
	 * @param rowNo
	 * @param pageSize
	 * @return
	 */
	List<ZZDeviceAttribute> listDeviceAttribute(@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);

	/**
	 * @Description:根据 keyword 单条件分页查询
	 * @param keyword
	 * @param rowNo
	 * @param pageSize
	 * @return
	 */
	List<ZZDeviceAttribute> listDeviceAttributeByKeyword(@Param("keyword") String keyword, @Param("rowNo") int rowNo,
			@Param("pageSize") int pageSize);

	/**
	 * @Description: 根据 deviceTypeId 单条件分页查询
	 * @param deviceTypeId
	 * @param rowNo
	 * @param pageSize
	 * @return
	 */
	List<ZZDeviceAttribute> listDeviceByDeviceTypeId(@Param("deviceTypeId") String deviceTypeId,
			@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);

	/**
	 * @Description: 双条件分页查询
	 * @param deviceTypeId
	 * @param keyword
	 * @param rowNo
	 * @param pageSize
	 * @return
	 */
	List<ZZDeviceAttribute> listDeviceAttributeByDeviceTypeIdAndKeyword(@Param("deviceTypeId") String deviceTypeId,
			@Param("keyword") String keyword, @Param("rowNo") int rowNo, @Param("pageSize") int pageSize);

	/**
	 * 条件统计 参数:查询条件,null为整张表 返回:查询个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int countByExample(ZZDeviceAttributeExample example);

	/**
	 * 批量条件删除 参数:删除条件,null为整张表 返回:删除个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int deleteByExample(ZZDeviceAttributeExample example);

	/**
	 * 批量条件查询 参数:查询条件,null查整张表 返回:对象集合
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	List<ZZDeviceAttribute> selectByExample(ZZDeviceAttributeExample example);

	/**
	 * 批量条件修改，空值条件不修改 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int updateByExampleSelective(@Param("record") ZZDeviceAttribute record,
			@Param("example") ZZDeviceAttributeExample example);

	/**
	 * 批量条件修改，空值条件会修改成null 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int updateByExample(@Param("record") ZZDeviceAttribute record, @Param("example") ZZDeviceAttributeExample example);

	/**
	 * 物理分页条件查询 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 从第2条开始显示，显示3条(从0开始编号) 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	List<ZZDeviceAttribute> selectByExampleAndPage(ZZDeviceAttributeExample example, RowBounds rowBound);

	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int insert(ZZDeviceAttribute record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int insertSelective(ZZDeviceAttribute record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	ZZDeviceAttribute selectByPrimaryKey(String id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int updateByPrimaryKeySelective(ZZDeviceAttribute record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2019-06-22 15:39:21
	 */
	int updateByPrimaryKey(ZZDeviceAttribute record);
	
	/**
	 * 根据attributeId查询ZZDeviceAttribute对象
	 * @param attribute
	 * @return
	 */
	ZZDeviceAttribute selectDeviceAttribute(String attributeId);
}