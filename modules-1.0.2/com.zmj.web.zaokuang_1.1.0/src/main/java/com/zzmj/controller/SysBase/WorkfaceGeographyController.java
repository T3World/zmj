package com.zzmj.controller.SysBase;

import com.zzmj.pojo.entity.ZZWorkfaceGeography;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceGeographyService;
import com.zzmj.util.ErrorUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysBase/WorkfaceGeography")
public class WorkfaceGeographyController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private ZZWorkfaceGeographyService zzWorkfaceGeographyService;

    /**
     * 添加工作面地理信息(只测试)
     * @param zzWorkfaceGeography
     * @return
     */
    @RequestMapping(value = "/addZZWorkfaceGeography", method = RequestMethod.POST)
    public SysResult addZZWorkfaceGeography(ZZWorkfaceGeography zzWorkfaceGeography) {
        try {
            return this.zzWorkfaceGeographyService.addZZWorkfaceGeography(zzWorkfaceGeography);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 删除工作面地理信息(只测试)
     * @param zzWorkfaceGeography
     * @return
     */
    @RequestMapping(value = "/delZZWorkfaceGeography", method = RequestMethod.POST)
    public SysResult delZZWorkfaceGeography(ZZWorkfaceGeography zzWorkfaceGeography) {
        try {
            return this.zzWorkfaceGeographyService.delZZWorkfaceGeography(zzWorkfaceGeography.getId());
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 更新工作面地理信息
     * @param zzWorkfaceGeography
     * @return
     */
    @RequestMapping(value = "/updateZZWorkfaceGeography", method = RequestMethod.POST)
    public SysResult updateZZWorkfaceGeography(ZZWorkfaceGeography zzWorkfaceGeography) {
        try {
            return this.zzWorkfaceGeographyService.updateZZWorkfaceGeography(zzWorkfaceGeography);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 编辑矿井公司地理信息【实用】
     * @param orgName
     * @param orgLongitude
     * @param orgLatitude
     * @param currentTime
     * @param sortCode
     * @return
     */
    @RequestMapping(value = "/updateZZWorkfaceGeographies", method = RequestMethod.POST)
    public SysResult updateZZWorkfaceGeographies(
            @RequestParam(name = "orgName", required = false) String orgName,
            @RequestParam(name = "orgLongitude", required = false) String orgLongitude,
            @RequestParam(name = "orgLatitude", required = false) String orgLatitude,
            @RequestParam(name = "currentTime", required = false) String currentTime,
            @RequestParam(name = "sortCode", required = false) String sortCode) {
        return this.zzWorkfaceGeographyService.updateZZWorkfaceGeographies(orgName, orgLongitude, orgLatitude, currentTime, sortCode);
    }

    /**
     * 编辑矿井集团地理信息【实用】
     * @param groupId
     * @param orgLongitude
     * @param orgLatitude
     * @param currentTime
     * @param sortCode
     * @return
     */
    @RequestMapping(value = "/updateZZGroupsGeographies", method = RequestMethod.POST)
    public SysResult updateZZGroupsGeographies(
            @RequestParam(name = "groupId", required = false) String groupId,
            @RequestParam(name = "orgLongitude", required = false) String orgLongitude,
            @RequestParam(name = "orgLatitude", required = false) String orgLatitude,
            @RequestParam(name = "currentTime", required = false) String currentTime,
            @RequestParam(name = "sortCode", required = false) String sortCode) {
        return this.zzWorkfaceGeographyService.updateZZGroupsGeographies(groupId, orgLongitude, orgLatitude, currentTime, sortCode);
    }

    /**
     * 获得分页数据【实用】
     * @param groupId
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getGeographyPage", method = RequestMethod.POST)
    public SysResult getGeographyPage(
            @RequestParam(name = "groupId", required = false) String groupId,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "pageNo", required = false) Integer pageNo,
            @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        try {
            return this.zzWorkfaceGeographyService.listGeography(groupId, keyword, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 查询指定集团下所有矿井公司的地理信息，不分页【实用】
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/getGeographyNoPage", method = RequestMethod.POST)
    public SysResult getGeographyNoPage(@RequestParam(name = "groupId", required = false) String groupId) {
        try {
            return this.zzWorkfaceGeographyService.listGeographyNoPage(groupId);
        } catch (Exception e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 假删除，根据orgId更新IsDel的状态为1【实用】
     * @param orgId
     * @return
     */
    @RequestMapping(value = "/delGeography", method = RequestMethod.POST)
    public SysResult delGeography(@RequestParam(name = "orgId", required = false) String orgId) {
        try {
            return this.zzWorkfaceGeographyService.updateIsDel(orgId);
        } catch (Exception e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 批量删除【实用】
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batDelGeography", method = RequestMethod.POST)
    public SysResult batDelGeography(@RequestParam(name = "ids", required = false) String ids) {
        try {
            return this.zzWorkfaceGeographyService.batDelGeography(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 添加矿井公司地理信息【实用】
     * @param zzWorkfaceGeography
     * @return
     */
    @RequestMapping(value = "/addZZWorkfaceGeographies", method = RequestMethod.POST)
    public SysResult addZZWorkfaceGeographies(ZZWorkfaceGeography zzWorkfaceGeography) {
        try {
            return this.zzWorkfaceGeographyService.addZZWorkfaceGeography(zzWorkfaceGeography);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 添加矿井集团地理信息【实用】
     * @param groupId
     * @param orgLongitude
     * @param orgLatitude
     * @param sortCode
     * @return
     */
    @RequestMapping(value = "/addZZGroupsGeographies", method = RequestMethod.POST)
    public SysResult addZZGroupsGeographies(
            @RequestParam(name = "groupId", required = false) String groupId,
            @RequestParam(name = "orgLongitude", required = false) String orgLongitude,
            @RequestParam(name = "orgLatitude", required = false) String orgLatitude,
            @RequestParam(name = "sortCode", required = false) String sortCode) {
        try {
            return this.zzWorkfaceGeographyService.addZZGroupsGeography(groupId, orgLongitude, orgLatitude, sortCode);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

}