package com.zmj.web.microservice.zaokuangContract.SysBase;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZOrgEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author hushixian
 * @date 2019-04-09 16:36
 */
@RequestMapping(value = "/SysBase/Org")
public interface OrgContract {

    /**
     * 新增组织结构
     * @param orgEntity 组织结构实体类
     * @return SysResult
     */
    @RequestMapping(value = "/addZZOrgEntity", method = RequestMethod.POST,consumes = "application/json")
    SysResult addZZOrgEntity(@RequestBody ZZOrgEntity orgEntity);

    /**
     * 根据orgId修改集团，公司的信息。
     * @param orgEntity 组织结构实体类
     * @return SysResult
     */
    @RequestMapping(value = "/updateZZOrgById", method = RequestMethod.POST,consumes = "application/json")
    SysResult updateZZOrgById(@RequestBody ZZOrgEntity orgEntity);

    /**
     * 删除组织结构
     * @param orgEntity 组织结构实体类
     * @return SysResult
     */
    @RequestMapping(value = "/delZZOrgEntityById", method = RequestMethod.POST,consumes = "application/json")
    SysResult delZZOrgEntityById(@RequestBody ZZOrgEntity orgEntity);

    /**
     *获得组织结构列表值右面的方法。，并加上分页判断
     * @param keyWord 关键字
     * @param pageNo 页码
     * @param pageSize 条数
     * @return SysResult
     */
    @RequestMapping(value = "/getOrgTreeData", method = RequestMethod.POST,consumes = "application/json")
    SysResult getOrgTreeData(@RequestParam(name = "keyword", required = false) String keyWord,
                             @RequestParam(name = "pageNo", required = false) Integer pageNo,
                             @RequestParam(name = "pageSize", required = false) Integer pageSize);

    /**
     * 获取组织结构树列表
     * @param response 响应
     * @param request 请求
     * @return List
     */
    @RequestMapping(value = "/getOrgTree", method = RequestMethod.POST,consumes = "application/json")
    List<ZZOrgEntity> getOrgTree(HttpServletResponse response, HttpServletRequest request);

}
