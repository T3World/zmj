package com.zzmj.service;

import com.zzmj.pojo.vo.AppUpdate;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @description 手机APP相关业务
 * @author umr
 * @date 2019/7/22
 */
public interface MobileService {

    AppUpdate updateAPP(String appid,String version) throws IOException;
}
