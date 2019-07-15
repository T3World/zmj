package com.zmj.web.microservice.zaokuangContract.API;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hushixian
 * @date 2019-04-09 17:44
 */
@RequestMapping("/API/Auth")
public interface AuthContract {

    /**
     * 退出/注销用户
     * @param userAccount
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST,consumes = "application/json")
    SysResult doLogin(@RequestParam(name="userAccount",required=true) String userAccount,
                      @RequestParam(name="userPassword",required=true) String userPassword);
}
