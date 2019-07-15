package com.zmj.web.microservice.zaokuangContract.SysBase;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZUserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hushixian
 * @date 2019-04-09 17:12
 */
@RequestMapping(value = "/SysBase/User")
public interface UserContract {

    /**
     * 新增方法
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/addUserEntity",method = RequestMethod.POST,consumes = "application/json")
    SysResult addUserEntity(@RequestBody  ZZUserEntity userEntity);

    /**
     * 修改用户信息的方法
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/updateUserEntity",method = RequestMethod.POST,consumes = "application/json")
    SysResult updateUserEntity(@RequestBody ZZUserEntity userEntity);

    /**
     * 删除用户，其实就是改变用户的删除状态
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delUserEntity",method = RequestMethod.POST,consumes = "application/json")
    SysResult delUserEntity(@RequestParam(name = "userId", required = false) String userId);

    /**
     * 根据用户id查找，用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserById",method = RequestMethod.POST,consumes = "application/json")
    SysResult getUserById(@RequestParam(name = "userId", required = false) String userId);

    /**
     * 给用户赋予角色权限
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping(value = "/addUserRole",method = RequestMethod.POST,consumes = "application/json")
    SysResult addUserRole(@RequestParam(name = "userId", required = false) String userId,
                          @RequestParam(name = "roleIds", required = false) String roleIds);

    /**
     *  获得组织结构列表值右面的方法。，并加上分页判断
     * @param orgId
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getUserListData",method = RequestMethod.POST,consumes = "application/json")
    SysResult getUserListData(@RequestParam(name = "orgId", required = false) String orgId,
                              @RequestParam(name = "keyword", required = false) String keyword,
                              @RequestParam(name = "pageNo", required = false) Integer pageNo,
                              @RequestParam(name = "pageSize", required = false) Integer pageSize);

    /**
     * 根据用户id获取该用户所对应的角色权限
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserRole",method = RequestMethod.POST,consumes = "application/json")
    SysResult getUserRole(@RequestParam(name = "userId", required = false) String userId);


    /**
     * 根据用户userId 获取用户的角色，所对应的 所有模块资源
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUserModule",method = RequestMethod.POST,consumes = "application/json")
    SysResult getUserModule(HttpServletRequest request);
}
