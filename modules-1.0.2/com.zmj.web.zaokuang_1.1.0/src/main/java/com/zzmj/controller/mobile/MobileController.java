package com.zzmj.controller.mobile;

import com.zzmj.pojo.vo.AppUpdate;
import com.zzmj.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @description 手机app相关业务
 * @author umr
 * @date 2019/7/22
 */
@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @RequestMapping("/update")
    public AppUpdate updateAPP(@RequestParam("appid") String appid,
                               @RequestParam("version")String version){
        try {
            return mobileService.updateAPP(appid, version);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
