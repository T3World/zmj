package com.zzmj.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zmj.web.microservice.zaokuangContract.entity.ZZModuleEntity;
import com.zzmj.pojo.entity.ZZModuleExample;

public interface ZZModuleMapper {

	int delByIsDel(@Param("mId") String mId);

	int updateByMId(ZZModuleEntity entity);

	int countModuleAll();

	int countModuleByKeyword(@Param("keyword") String keyword);

	List<ZZModuleEntity> listModuleAll(@Param("rowNo") Integer rowNo, @Param("pageSize") Integer pageSize);

	List<ZZModuleEntity> listModuleByKeyword(@Param("keyword") String keyword, @Param("rowNo") Integer rowNo,
			@Param("pageSize") Integer pageSize);

	/**
	 * 条件统计 参数:查询条件,null为整张表 返回:查询个数
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int countByExample(ZZModuleExample example);

	/**
	 * 批量条件删除 参数:删除条件,null为整张表 返回:删除个数
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int deleteByExample(ZZModuleExample example);

	/**
	 * 批量条件查询 参数:查询条件,null查整张表 返回:对象集合
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	List<ZZModuleEntity> selectByExample(ZZModuleExample example);

	/**
	 * 批量条件修改，空值条件不修改 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByExampleSelective(@Param("record") ZZModuleEntity record, @Param("example") ZZModuleExample example);

	/**
	 * 批量条件修改，空值条件会修改成null 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByExample(@Param("record") ZZModuleEntity record, @Param("example") ZZModuleExample example);

	/**
	 * 物理分页条件查询 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 从第2条开始显示，显示3条(从0开始编号) 返回:成功修改个数
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	List<ZZModuleEntity> selectByExampleAndPage(ZZModuleExample example, RowBounds rowBound);

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
	int insert(ZZModuleEntity record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int insertSelective(ZZModuleEntity record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	ZZModuleEntity selectByPrimaryKey(@Param("id") String id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByPrimaryKeySelective(ZZModuleEntity record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 *
	 * @ibatorgenerated 2018-12-08 14:48:37
	 */
	int updateByPrimaryKey(ZZModuleEntity record);

	/**
	 * // 根据用户userId 获取用户的角色，所对应的 所有模块资源
	 *
	 * @param userId 用户Id
	 * @return
	 */
	List<HashMap<String, Object>> getUserMoudel(@Param("userId") String userId);

	/**
	 * 查询mid有关联的子模块数量
	 */
	int countSonModuleByMid(@Param("mid") String mid);

}