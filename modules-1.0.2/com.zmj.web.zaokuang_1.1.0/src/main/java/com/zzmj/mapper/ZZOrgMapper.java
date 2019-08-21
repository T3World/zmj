package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZOrgEntity;
import com.zzmj.pojo.entity.ZZOrgExample;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface ZZOrgMapper {
	/**
	 * 条件统计 参数:查询条件,null为整张表 返回:查询个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int countByExample(ZZOrgExample example);

	/**
	 * 批量条件删除 参数:删除条件,null为整张表 返回:删除个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int deleteByExample(ZZOrgExample example);

	/**
	 * 批量条件查询 参数:查询条件,null查整张表 返回:对象集合
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	List<ZZOrgEntity> selectByExample(ZZOrgExample example);

	/**
	 * 批量条件修改，空值条件不修改 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByExampleSelective(@Param("record") ZZOrgEntity record, @Param("example") ZZOrgExample example);

	/**
	 * 批量条件修改，空值条件会修改成null 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByExample(@Param("record") ZZOrgEntity record, @Param("example") ZZOrgExample example);

	/**
	 * 物理分页条件查询 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 从第2条开始显示，显示3条(从0开始编号) 返回:成功修改个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	List<ZZOrgEntity> selectByExampleAndPage(ZZOrgExample example, RowBounds rowBound);

	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int deleteByPrimaryKey(@Param("id") String id);

	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int insert(ZZOrgEntity record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int insertSelective(ZZOrgEntity record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	ZZOrgEntity selectByPrimaryKey(@Param("id") String id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByPrimaryKeySelective(ZZOrgEntity record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByPrimaryKey(ZZOrgEntity record);

	/**
	 * 根据orgId查找数量count
	 * @param orgId
	 * @return
	 */
	int getCount(@Param("orgId") String orgId);

	/**
	 * 根据keyWord关键字，模糊查询，
	 * @param keyWord  关键字
	 * @param pageNo   从那条记录开始
	 * @param pageSize 每页显示的条数
	 * @return
	 */
	List<ZZOrgEntity> getlikeKeyWord(@Param("keyWord") String keyWord, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 查询总条数
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
	 *查询所有集团 
	 */
	List<Map<String, Object>> selectByPid();
	
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
	 * @param orgId
	 * @return
	 */
	ZZOrgEntity getOrgIdByPId(@Param("orgId") String orgId);

	/**
	 * 从组织机构中获取所有的集团
	 * @return
	 */
	List<ZZOrgEntity> listAllGroup();

	/**
	 * 分页查询
	 * @param rowNo
	 * @param pageSize
	 * @return
	 */
	List<ZZOrgEntity> listOrgs(@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);


	/**
	 * 对组织机构(集团)进行分页
	 * @return
	 */
	int getAllCount();
	int getCountByKeyword(@Param("keyword") String keyword);
	List<ZZOrgEntity> selectOrgList(@Param("rowNo") Integer rowNum, @Param("pageSize") Integer pageSize);
	List<ZZOrgEntity> selectOrgListByKeyword(@Param("keyword") String keyword, @Param("rowNo") Integer rowNum, @Param("pageSize") Integer pageSize);
	int getCountByGroupId(@Param("groupId") String groupId);
	int getCountByGroupIdAndByKeyword(@Param("groupId") String groupId, @Param("keyword") String keyword);

	int getAllCount2();
	int getCountByKeyword2(@Param("keyword") String keyword);
	List<ZZOrgEntity> selectOrgList2(@Param("rowNo") Integer rowNum, @Param("pageSize") Integer pageSize);
	List<ZZOrgEntity> selectOrgListByKeyword2(@Param("keyword") String keyword, @Param("rowNo") Integer rowNum, @Param("pageSize") Integer pageSize);

	List<ZZOrgEntity> listOrgsByGroupIdAndByKeyword(@Param("groupId") String groupId, @Param("keyword") String keyword, @Param("rowNo") Integer rowNum, @Param("pageSize") Integer pageSize);

	/**
	 * 根据groupId查询该集团下所有矿井公司
	 * @param groupId
	 * @return
	 */
	List<ZZOrgEntity> listOrgsByGroupId(@Param("groupId") String groupId);
	List<ZZOrgEntity> listOrgsByGroupId2(@Param("groupId") String groupId, @Param("rowNo") Integer rowNum, @Param("pageSize") Integer pageSize);

	/**
	 * <!-- 根据矿井公司的org_id 查询该矿井公司的name -->
	 * @param orgId
	 * @return
	 */
	String selectByOrgId(String orgId);

	/**
	 * <!-- 根据页面传过来的集团的Id 查询集团下所有的矿井公司的Id -->
	 * @param orgPid
	 * @return
	 */
	List<String> selectOrgIdsByOrgId(String orgPid);

	/**
	 * 查询ZZ_Org表中所有数据  循环递归调用该sql
	 * @param pid
	 * @return
	 */
	List<ZZOrgEntity> selectAll(String pid);
	
}