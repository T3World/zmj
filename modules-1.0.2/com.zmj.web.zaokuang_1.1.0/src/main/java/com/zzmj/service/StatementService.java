package com.zzmj.service;

import com.zzmj.pojo.vo.SysResult;

/**
 * @description 处理报表相关业务
 * @author umr
 * @date 2019/7/16
 */
public interface StatementService {

    /**
     * 根据工作面ID获取工作面配置及工作面设备信息
     * */
   SysResult getWorkfaceConfig(String workfaceId);
}
