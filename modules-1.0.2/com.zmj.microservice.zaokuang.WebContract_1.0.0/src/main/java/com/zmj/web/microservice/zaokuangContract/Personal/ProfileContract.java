package com.zmj.web.microservice.zaokuangContract.Personal;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.ZZUserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author hushixian
 * @date 2019-04-09 17:35
 */
@RequestMapping("/Personal/Profile")
public interface ProfileContract {

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST,consumes = "application/json")
    SysResult getUserInfo(String userId);

    @RequestMapping(value = "/setUserInfo",method = RequestMethod.POST,consumes = "application/json")
    SysResult setUserInfo(@RequestBody ZZUserEntity entity);

}
