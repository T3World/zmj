package com.zzmj.controller.SysBase;

import com.zzmj.pojo.entity.ZZWorkfaceGeography;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceGeographyService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 此WorkfaceGeography2Controller类只用于数据访问的测试(只测试)
 */
@RestController
@RequestMapping("/SysBase/WorkfaceGeographyTest")
public class WorkfaceGeography2Controller {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private ZZWorkfaceGeographyService zzWorkfaceGeographyService;

    /**
     * 添加功能(测试)
     * @return
     */
    @RequestMapping(value = "/addZZWorkfaceGeography", method = RequestMethod.POST)
    public SysResult addZZWorkfaceGeography() {
        try {
            ZZWorkfaceGeography zzWorkfaceGeography = new ZZWorkfaceGeography();
            zzWorkfaceGeography.setOrgId("1");
            zzWorkfaceGeography.setOrgLatitude("111.11");
            zzWorkfaceGeography.setOrgLongitude("111.11");
            zzWorkfaceGeography.setSortCode("00005");
            return this.zzWorkfaceGeographyService.addZZWorkfaceGeography(zzWorkfaceGeography);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 根据Id删除功能(测试)
     * @return
     */
    @RequestMapping(value = "/delZZWorkfaceGeography", method = RequestMethod.POST)
    public SysResult delZZWorkfaceGeography() {
        ZZWorkfaceGeography zzWorkfaceGeography = new ZZWorkfaceGeography();
        zzWorkfaceGeography.setId("f5b6e59b-7416-42fa-ad88-ef3eb701f56f");
        try {
            return this.zzWorkfaceGeographyService.delZZWorkfaceGeography(zzWorkfaceGeography.getId());
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 修改功能(测试)
     * @return
     */
    @RequestMapping(value = "/updateZZWorkfaceGeography", method = RequestMethod.POST)
    public SysResult updateZZWorkfaceGeography() {
        ZZWorkfaceGeography zzWorkfaceGeography = new ZZWorkfaceGeography();
        zzWorkfaceGeography.setId("11b3e0be-0f7e-410c-827a-4613cb4c4782");
        zzWorkfaceGeography.setOrgId("456");
        zzWorkfaceGeography.setOrgLatitude("999.99");
        zzWorkfaceGeography.setOrgLongitude("888.88");
        zzWorkfaceGeography.setSortCode("002");
        try {
            return this.zzWorkfaceGeographyService.updateZZWorkfaceGeography(zzWorkfaceGeography);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * [单表查询所有信息，这个不是需求](测试)
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.POST)
    public List<ZZWorkfaceGeography> selectAll() {
        try {
            return this.zzWorkfaceGeographyService.selectAll();
        } catch (Exception e) {
            throw new DoSqlFailedException("查询所有信息失败");
        }
    }

    /**
     * 根据集团id查询该集团下的所有矿井地理信息(测试)
     * @return
     */
    @RequestMapping(value = "/selectByGroupId", method = RequestMethod.POST)
    public List<ZZWorkfaceGeography> selectByGroupId() {
        try {
            //输入要查询的集团id
            String testId = "6c41ff40-4064-4356-bb02-7ecf0517dbd3";
            return this.zzWorkfaceGeographyService.selectByGroupId(testId);
        } catch (Exception e) {
            throw new DoSqlFailedException("查询集团下所有矿井地理信息失败");
        }
    }

    /**
     * 查询所有集团下的矿井地理信息(测试)
     * @return
     */
    @RequestMapping(value = "/selectAllByGroup", method = RequestMethod.POST)
    public List<ZZWorkfaceGeography> selectAllByGroup() {
        try {
            return this.zzWorkfaceGeographyService.selectAllByGroup();
        } catch (Exception e) {
            throw new DoSqlFailedException("查询所有集团下的矿井地理信息失败");
        }
    }

}
