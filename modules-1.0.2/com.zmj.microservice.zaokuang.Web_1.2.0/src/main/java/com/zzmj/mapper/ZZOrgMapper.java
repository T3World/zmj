package com.zzmj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zmj.web.microservice.zaokuangContract.entity.ZZOrgEntity;
import com.zzmj.pojo.entity.ZZOrgExample;

public interface ZZOrgMapper {
	/**
	 * 条件统计 参数:查询条件,null为整张表 返回:查询个数
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int countByExample(ZZOrgExample example);

	/**
	 * 批量条件删除 参数:删除条件,null为整张表 返回:删除个数
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int deleteByExample(ZZOrgExample example);

	/**
	 * 批量条件查询 参数:查询条件,null查整张表 返回:对象集合
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	List<ZZOrgEntity> selectByExample(ZZOrgExample example);

	/**
	 * 批量条件修改，空值条件不修改 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByExampleSelective(@Param("record") ZZOrgEntity record, @Param("example") ZZOrgExample example);

	/**
	 * 批量条件修改，空值条件会修改成null 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByExample(@Param("record") ZZOrgEntity record, @Param("example") ZZOrgExample example);

	/**
	 * 物理分页条件查询 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 从第2条开始显示，显示3条(从0开始编号) 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	List<ZZOrgEntity> selectByExampleAndPage(ZZOrgExample example, RowBounds rowBound);

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
	int insert(ZZOrgEntity record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int insertSelective(ZZOrgEntity record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	ZZOrgEntity selectByPrimaryKey(@Param("id") String id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByPrimaryKeySelective(ZZOrgEntity record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * 
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByPrimaryKey(ZZOrgEntity record);

	/**
	 * 根据orgId查找数量count
	 * 
	 * @param orgId
	 * @return
	 */
	int getCount(@Param("orgId") String orgId);

	/**
	 * 根据keyWord关键字，模糊查询，
	 * 
	 * @param keyWord  关键字
	 * @param pageNo   从那条记录开始
	 * @param pageSize 每页显示的条数
	 * @return
	 */
	List<ZZOrgEntity> getlikeKeyWord(@Param("keyWord") String keyWord, @Param("pageNo") Integer pageNo,
			@Param("pageSize") Integer pageSize);

	/**
	 * 查询总条数
	 * 
	 * @return
	 */
	int allCount();

	/**
	 * @Title: listWorkfaceInOrg
	 * @Description: 前端用,打包数据
	 * @param: @param orgId
	 * @param: @return
	 * @return: List<ZZOrgEntity>
	 */
	List<Map<String, Object>> listOrgByPId(@Param("orgId") String orgId);

	/**
	 * @Title: getOrgByOrgId
	 * @Description: 根据orgId查询org
	 * @param: @param orgId
	 * @param: @return
	 * @return: ZZOrgEntity
	 */
	Map<String, Object> getOrgByOrgId(@Param("orgId") String orgId);

	/**
	 * 根据orgId查找该对象
	 * 
	 * @param orgId
	 * @return
	 */
	ZZOrgEntity getOrgIdByPId(@Param("orgId") String orgId);

}