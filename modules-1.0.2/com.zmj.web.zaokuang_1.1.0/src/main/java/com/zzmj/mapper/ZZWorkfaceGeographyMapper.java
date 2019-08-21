package com.zzmj.mapper;

import com.zzmj.pojo.entity.ZZWorkfaceGeography;
import com.zzmj.pojo.entity.ZZWorkfaceGeographyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZZWorkfaceGeographyMapper {

    /**
     * 插入，空属性也会插入
     * @param record pojo对象
     * @return 删除的个数
     */
    int insert(ZZWorkfaceGeography record);

    /**
     * 插入，空属性不会插入，插入矿井公司地理信息【实用】
     * @param record pojo对象
     * @return 删除的个数
     */
    int insertSelective(ZZWorkfaceGeography record);

    /**
     * 插入矿井集团地理信息【实用】
     * @param id
     * @param groupId
     * @param orgLongitude
     * @param orgLatitude
     * @param sortCode
     * @param updateTime
     * @param isDel
     * @return
     */
    int insertSelective2(@Param("id") String id, @Param("groupId") String groupId,
                         @Param("orgLongitude") String orgLongitude, @Param("orgLatitude") String orgLatitude,
                         @Param("sortCode") String sortCode, @Param("updateTime") String updateTime, @Param("isDel") String isDel);

    /**
     * 根据主键删除
     * @param id 主键
     * @return 删除的个数
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * @param record 要修改成的值
     * @return 成功修改的个数
     */
    int updateByPrimaryKeySelective(ZZWorkfaceGeography record);

    /**
     * [简单查询表中所有内容](不是需求，先看看效果)
     * @return
     */
    List<ZZWorkfaceGeography> selectAll();

    /**
     * 根据集团Id查询该集团下的所有矿井地理信息
     * @return
     */
    List<ZZWorkfaceGeography> selectByGroupId(String orgPId);

    /**
     * 查询所有集团下的矿井地理信息
     * @return
     */
    List<ZZWorkfaceGeography> selectAllByGroup();

    /**
     * 查询地理信息总数
     * @return
     */
    int countGeography();

    /**
     * 根据groupId查找总数
     * @param groupId
     * @return
     */
    int countGeographyByGroupId(@Param("groupId") String groupId);

    /**
     * 根据关键字查询总数量
     * @param keyword
     * @return
     */
    int countGeographyByKeyword(@Param("keyword") String keyword);

    /**
     * 根据关键字和groupId查找数量
     * @param groupId
     * @param keyword
     * @return
     */
    int countGeographyByGroupIdAndByKeyword(@Param("groupId") String groupId,
                                            @Param("keyword") String keyword);

    /**
     * 分页查询
     * @param rowNo
     * @param pageSize
     * @return
     */
    List<ZZWorkfaceGeography> listGeography(@Param("rowNo") int rowNo,
                                            @Param("pageSize") int pageSize);

    /**
     * 根据keyword单条件分页查询
     * @param keyword
     * @param rowNo
     * @param pageSize
     * @return
     */
    List<ZZWorkfaceGeography> listGeographyByKeyword(@Param("keyword") String keyword,
                                                     @Param("rowNo") int rowNo,
                                                     @Param("pageSize") int pageSize);

    /**
     * 根据groupId单条件分页查询
     * @param groupId
     * @param rowNo
     * @param pageSize
     * @return
     */
    List<ZZWorkfaceGeography> listGeographyByGroupId(@Param("groupId") String groupId,
                                                     @Param("rowNo") int rowNo,
                                                     @Param("pageSize") int pageSize);

    /**
     * 根据groupId查询不分页
     * @param groupId
     * @return
     */
    List<ZZWorkfaceGeography> listGeographyByGroupIdNoPage(@Param("groupId") String groupId);


    /**
     * 双条件分页查询
     * @param groupId
     * @param keyword
     * @param rowNo
     * @param pageSize
     * @return
     */
    List<ZZWorkfaceGeography> listGeographyByGroupIdAndByKeyword(@Param("groupId") String groupId,
                                                                 @Param("keyword") String keyword,
                                                                 @Param("rowNo") int rowNo,
                                                                 @Param("pageSize") int pageSize);


    /**
     * 假删除，根据orgId更新IsDel的状态为1
     * @param orgId
     * @return
     */
    int updateIsDel(@Param("orgId") String orgId);

    /**
     * 编辑矿井公司地理信息【实用】
     * @param orgName
     * @param orgLongitude
     * @param orgLatitude
     * @param currentTime
     * @param sortCode
     * @return
     */
    int updateZZWorkfaceGeographies(@Param("orgName") String orgName,
                                    @Param("orgLongitude") String orgLongitude,
                                    @Param("orgLatitude") String orgLatitude,
                                    @Param("currentTime") String currentTime,
                                    @Param("sortCode") String sortCode);

    /**
     * 编辑矿井集团地理信息【实用】
     * @param groupId
     * @param orgLongitude
     * @param orgLatitude
     * @param currentTime
     * @param sortCode
     * @return
     */
    int updateZZGroupsGeographies(@Param("groupId") String groupId,
                                  @Param("orgLongitude") String orgLongitude,
                                  @Param("orgLatitude") String orgLatitude,
                                  @Param("currentTime") String currentTime,
                                  @Param("sortCode") String sortCode);

    /**
     * 根据orgId查询此条数据是否存在
     * @param orgId
     * @return
     */
    List<ZZWorkfaceGeography> listByOrgId(String orgId);
}