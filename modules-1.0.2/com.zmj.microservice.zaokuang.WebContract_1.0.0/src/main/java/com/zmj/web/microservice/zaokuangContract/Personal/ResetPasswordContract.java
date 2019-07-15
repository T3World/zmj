package com.zmj.web.microservice.zaokuangContract.Personal;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hushixian
 * @date 2019-04-09 17:39
 */
@RequestMapping("/Personal/Reset")
public interface ResetPasswordContract {

    /**
     * 修改面膜
     * @param userId 用户id
     * @param oldPassword 旧密码
     * @param userPassword 新密码
     * @return SysResult
     */
    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST,consumes = "application/json")
    SysResult resetPassword(@RequestParam("userId") String userId,
                            @RequestParam("oldPassword") String oldPassword,
                            @RequestParam("userPassword") String userPassword);


}
