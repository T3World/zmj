package com.zzmj.service;

import com.zzmj.pojo.entity.ZZWorkfaceGeography;
import com.zzmj.pojo.vo.SysResult;

import java.util.List;

public interface ZZWorkfaceGeographyService {

    /**
     * 添加矿井公司地理信息【实用】
     * @param zzWorkfaceGeography
     * @return
     */
    SysResult addZZWorkfaceGeography(ZZWorkfaceGeography zzWorkfaceGeography);

    /**
     * 添加矿井集团地理信息【实用】
     * @param groupId
     * @param orgLongitude
     * @param orgLatitude
     * @param sortCode
     * @return
     */
    SysResult addZZGroupsGeography(String groupId, String orgLongitude, String orgLatitude, String sortCode);

    /**
     * 根据id删除工作面地理信息(测试)
     * @param id
     * @return
     */
    SysResult delZZWorkfaceGeography(String id);

    /**
     * 修改工作面地理信息【实用】
     * @param zzWorkfaceGeography
     * @return
     */
    SysResult updateZZWorkfaceGeography(ZZWorkfaceGeography zzWorkfaceGeography);

    /**
     * [单表查询所有信息](只是测试)
     * @return
     */
    List<ZZWorkfaceGeography> selectAll();

    /**
     * 根据集团id查询该集团下的所有矿井信息(测试)
     * @return
     */
    List<ZZWorkfaceGeography> selectByGroupId(String orgPId);

    /**
     * 查询所有集团下的矿井地理信息(测试)
     * @return
     */
    List<ZZWorkfaceGeography> selectAllByGroup();

    /**
     * 分页查询【实用】
     * @param groupId   集团id
     * @param keyword   关键字
     * @param pageNo    页数
     * @param pageSize  每页显示的数量
     * @return
     */
    SysResult listGeography(String groupId, String keyword, Integer pageNo, Integer pageSize);

    /**
     * 查询，不分页
     * @param groupId
     * @return
     */
    SysResult listGeographyNoPage(String groupId);

    //listGeographyByGroupIdNoPage

    /**
     * 假删除，根据orgId更新IsDel的状态为1【实用】
     * @param orgId
     * @return
     */
    SysResult updateIsDel(String orgId);

    /**
     * 批量删除【实用】
     * @param ids
     * @return
     */
    SysResult batDelGeography(String ids);

    /**
     * 编辑矿井公司地理信息【实用】
     * @param orgName
     * @param orgLongitude
     * @param orgLatitude
     * @return
     */
    SysResult updateZZWorkfaceGeographies(String orgName, String orgLongitude, String orgLatitude, String currentTime, String sortCode);

    /**
     * 编辑矿井集团地理信息【实用】
     * @param groupId
     * @param orgLongitude
     * @param orgLatitude
     * @param currentTime
     * @param sortCode
     * @return
     */
    SysResult updateZZGroupsGeographies(String groupId, String orgLongitude, String orgLatitude, String currentTime, String sortCode);

}
