package com.zzmj.service.impl;

import com.zzmj.mapper.ZZDeviceAttributeRelationMapper;
import com.zzmj.mapper.ZZWorkfaceDeviceMapper;
import com.zzmj.mapper.ZZWorkfaceconfigMapper;
import com.zzmj.pojo.vo.AttributeInnerAttributeDevice;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.pojo.vo.WorkfaceDeviceInfo;
import com.zzmj.service.StatementService;
import com.zzmj.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatementServiceImpl implements StatementService {

    @Autowired
    private ZZWorkfaceconfigMapper zzWorkfaceconfigMapper;

    @Autowired
    private ZZWorkfaceDeviceMapper zzWorkfaceDeviceMapper;

    @Autowired
    private ZZDeviceAttributeRelationMapper zzDeviceAttributeRelationMapper;
    /**
     * 用于安全预警分析-工作面基本参数
     * @return 工作面长度,走向长度,煤层厚度,平均采高,支架型号,支护强度
     */
    @Override
    public SysResult getWorkfaceConfig(String workfaceId) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> configMap = zzWorkfaceconfigMapper.selectConfigByWorkfaceId(workfaceId);
        String workfaceLength = (String)configMap.get("Workface_Length");
        String workfaceAbleLength = (String) configMap.get("Workface_AbleLength");
        String workfaceAverageHeight = (String) configMap.get("Workface_AverageHeight");
        String workfaceCoalThickness = (String) configMap.get("Workface_CoalThickness");
        //支架型号
        String deviceModel = "";
        List<WorkfaceDeviceInfo> workfaceDeviceInfos = zzWorkfaceDeviceMapper.selectWorkfaceDeviceInfoByWorkfaceId(workfaceId);
        for (WorkfaceDeviceInfo info : workfaceDeviceInfos){
            deviceModel += info.getDeviceModel()+",";
            String deviceId = info.getDeviceId();
            List<AttributeInnerAttributeDevice> deviceAttribute = zzDeviceAttributeRelationMapper.listDeviceAttributeSimpleByDeviceId(deviceId);
            for (AttributeInnerAttributeDevice attribute:deviceAttribute){
                result.put(attribute.getAttributeAlias(), attribute.getAttributeValue() + " " + attribute.getAttributeUnit());
            }
        }
        if (workfaceDeviceInfos.size()>0)
            deviceModel = deviceModel.substring(0, deviceModel.length() - 2);

        result.put("deviceModel",deviceModel);
        result.put("workfaceAbleLength", workfaceAbleLength);
        result.put("workfaceLength", workfaceLength);
            result.put("workfaceAverageHeight", workfaceAverageHeight);
        result.put("workfaceCoalThickness", workfaceCoalThickness);
        return new SysResult(ErrorUtil.CODE2000,"ok",result);
    }
}
