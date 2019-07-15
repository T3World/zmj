package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZUserEntity;
import com.zzmj.pojo.entity.ZZUserExample;
import com.zzmj.pojo.vo.LoginResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ZZUserMapper {
    /**
     * 条件统计 参数:查询条件,null为整张表 返回:查询个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int countByExample(ZZUserExample example);

    /**
     * 批量条件删除 参数:删除条件,null为整张表 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int deleteByExample(ZZUserExample example);

    /**
     * 批量条件查询 参数:查询条件,null查整张表 返回:对象集合
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    List<ZZUserEntity> selectByExample(ZZUserExample example);

    /**
     * 批量条件修改，空值条件不修改 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByExampleSelective(@Param("record") ZZUserEntity record, @Param("example") ZZUserExample example);

    /**
     * 批量条件修改，空值条件会修改成null 参数:1.要修改成的值，2.要修改条件 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByExample(@Param("record") ZZUserEntity record, @Param("example") ZZUserExample example);

    /**
     * 物理分页条件查询 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    List<ZZUserEntity> selectByExampleAndPage(ZZUserExample example, RowBounds rowBound);

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
    int insert(ZZUserEntity record);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int insertSelective(ZZUserEntity record);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    ZZUserEntity selectByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByPrimaryKeySelective(ZZUserEntity record);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2018-12-08 14:48:37
     */
    int updateByPrimaryKey(ZZUserEntity record);

    /**
     * 根据userId查找用户的信息
     * 
     * @param userId 用户id
     * @return
     */
    ZZUserEntity selectByUserId(@Param("userId") String userId);

    /**
     * 根据keyWord关键字，模糊查询，
     * 
     * @param keyWord 关键字
     * @param pageNo 从那条记录开始
     * @param pageSize 每页显示的条数
     * @return
     */
    List<ZZUserEntity> getlikeKeyWord(@Param("keyWord") String keyWord, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * @Title: listWorkfaceAll
     * @Description: 分页查询用户
     * @param: rowNo 指定第一个返回记录行的偏移量
     * @param: pageSize 指定返回记录行的最大数目
     * @return: List<ZZUserEntity>
     */
    List<ZZUserEntity> listUserAll(@Param("rowNo") int rowNo, @Param("pageSize") int pageSize);
	/**
	 * 查询总条数
	 * 
	 * @return
	 */
	int allCount();

	/**
	 * 根据组织机构id和关键字查询，该组织机构下所拥有的所有用户信息
	 * 
	 * @param orgId
	 * @param keyword
	 * @return
	 */
	int getCountByOrgIdANDKeyword(@Param("orgId") String orgId, @Param("keyword") String keyword);

    /**
     * @Title: listUserByOrgId
     * @Description: 根据组织Id分页查询工作面
     * @param: orgId
     * @param: rowNo
     * @param: pageSize
     * @return: List<ZZUserEntity>
     */
    List<ZZUserEntity> listUserByOrgId(@Param("orgId") String orgId, @Param("rowNo") int rowNo, @Param("pageSize") int pageSize);

    LoginResult selectForLogin(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword);

    /**
     * @Title: selectOrgIdByUserId
     * @Description: 根据userId查询orgId
     * @param: @param userId
     * @param: @return
     * @return: String
     */
    String selectOrgIdByUserId(@Param("userId") String userId);

    /**
     * // 根据用户userId 获取用户的角色，所对应的 所有模块资源
     * 
     * @param userId 用户Id
     * @return
     */
    List<ZZUserEntity> getUserMoudel(@Param("userId") String userId);

}