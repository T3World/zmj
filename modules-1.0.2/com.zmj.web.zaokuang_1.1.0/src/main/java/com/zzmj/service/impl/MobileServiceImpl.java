package com.zzmj.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzmj.pojo.entity.Update;
import com.zzmj.pojo.vo.AppUpdate;
import com.zzmj.service.MobileService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @description 手机APP相关业务
 * @author umr
 * @date 2019/7/22
 */
@Service
public class MobileServiceImpl implements MobileService {

    private static final String UPDATE_ROOT = "D:\\ZMJ_BigDataPlatform\\app\\WEB\\Monting1.3.0\\downloads";

    private static Logger LOGGER = Logger.getLogger(MobileServiceImpl.class);

    @Value("${zmj.updateRoot}")
    private String updateRoot;

    @Override
    public AppUpdate updateAPP(String appid, String version) throws IOException {
        LOGGER.info("appid: "+appid+" version: "+version);
        LOGGER.info("updateRoot : "+updateRoot);
        File file = new File(UPDATE_ROOT+"/update.json");
        if (file.exists()){
            String json = FileUtils.readFileToString(file, "utf-8");
            Update  update = JSON.parseObject(json, Update.class);
            if (!update.getVersion().equals(version)){
                String url = updateRoot +"/" +update.getFileName();
                return new AppUpdate(true,200,update.getNote(),url);
            }
            return new AppUpdate(false,200,"",updateRoot);
        }else {
            return new AppUpdate(false,500,"update文件不存在",updateRoot);
        }
    }

}
