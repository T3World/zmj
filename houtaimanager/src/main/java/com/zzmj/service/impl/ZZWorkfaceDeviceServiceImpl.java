package com.zzmj.service.impl;

import com.zzmj.mapper.ZZDeviceAttributeRelationMapper;
import com.zzmj.mapper.ZZDeviceMapper;
import com.zzmj.mapper.ZZWorkfaceDeviceMapper;
import com.zzmj.pojo.dto.WorkfaceDeviceAccumulative;
import com.zzmj.pojo.entity.*;
import com.zzmj.pojo.vo.AttributeInnerAttributeDevice;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.pojo.vo.WorkfaceDeviceInfo;
import com.zzmj.service.ZZDeviceAccumulativeService;
import com.zzmj.service.ZZWorkfaceDeviceService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.FormatDateUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;
import com.zzmj.util.exception.IllegalParamException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ZZWorkfaceDeviceServiceImpl implements ZZWorkfaceDeviceService {


    private static Logger logger = Logger.getLogger(ZZGlobalServiceImpl.class);
    @Autowired
    private ZZWorkfaceDeviceMapper zzWorkfaceDeviceMapper;
    @Autowired
    private ZZDeviceMapper zzDeviceMapper;
    @Autowired
    private ZZDeviceAttributeRelationMapper zzDeviceAttributeRelationMapper;
    @Autowired
    private ZZDeviceAccumulativeService zzDeviceAccumulativeService;
    @Value("${zmj.supportTypeId}")
    private String supportTypeId;
    /**
     * 添加工作面设备 设置isDel初始值为0 
     */
    @Override
    public SysResult addZZWorkfaceDevice(ZZWorkfaceDevice zzWorkfaceDevice) {
    	try {
			zzWorkfaceDevice.setId(CodeUtil.createUuid36());
			zzWorkfaceDevice.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
			zzWorkfaceDevice.setIsDel("0");
			
			int flag=zzWorkfaceDeviceMapper.insertSelective(zzWorkfaceDevice);
			if(flag>0){
				return new SysResult(ErrorUtil.CODE2000,"添加工作面设备成功",zzWorkfaceDevice);
			}else{
				return new SysResult(ErrorUtil.CODE2001,"添加工作面设备失败",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("工作面设备添加失败，出现异常", e);
			throw new DoSqlFailedException("工作面设备添加失败");

    	}
    }

    /**
     * 删除工作面设备(假删除) 根据Workface_Id更改isDel状态为1 
     */
    @Override
    public SysResult delZZWorkfaceDevice(String id) {
    	try {
			int flag=zzWorkfaceDeviceMapper.update(id);
			if(flag<1)
				return new SysResult(ErrorUtil.CODE2001,"删除工作面设备失败",null);
			return new SysResult(ErrorUtil.CODE2000,"添加工作面设备成功",null);
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("删除工作面设备失败，出现异常", e);
			throw new DoSqlFailedException("删除工作面设备失败");
		}
    }

    @Override
    public SysResult updateZZWorkfaceDevice(ZZWorkfaceDevice zzWorkfaceDevice) {
    
    	try {
			String id=zzWorkfaceDevice.getId();
			zzWorkfaceDevice.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
			
			ZZWorkfaceDeviceExample example=new ZZWorkfaceDeviceExample();
			example.createCriteria().andIdEqualTo(id);
			int flag=zzWorkfaceDeviceMapper.updateByExampleSelective(zzWorkfaceDevice, example);
			if(flag>0){
				return new SysResult(ErrorUtil.CODE2000,"更改工作面设备成功",zzWorkfaceDevice);
			}else{
				return new SysResult(ErrorUtil.CODE2001,"更改工作面设备失败 失败",zzWorkfaceDevice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("工作面设备更改失败，出现异常", e);
			throw new DoSqlFailedException("工作满设备更改失败");
		}		
    }

    @Override
    public SysResult selectById(String workfaceDeviceId) {
    	
    	try {
			ZZWorkfaceDeviceExample example=new ZZWorkfaceDeviceExample();
			example.createCriteria().andWorkfaceIdEqualTo(workfaceDeviceId);
			List<ZZWorkfaceDevice> list=zzWorkfaceDeviceMapper.selectByExample(example);
			if(list!=null){
				  return new SysResult(ErrorUtil.CODE2000, "查询成功ok", list);
			}else{
				  return new SysResult(ErrorUtil.CODE2001, "查询失败", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("工作面设备查询失败，出现异常", e);
			throw new DoSqlFailedException("工作满设备查询失败");
		}
    }

    @Override
    public SysResult selectByWorkfaceId(String workfaceId) {

        List<ZZDevice> ZZDeviceList=new ArrayList<>();
        //验证参数合法性
        if(null==workfaceId||workfaceId.equals("")){
            return new SysResult(ErrorUtil.CODE2001, "参数不能为空!", null);
        }
        List<String> list=zzWorkfaceDeviceMapper.selectByWorkfaceId(workfaceId);

        if (list.size() == 0) {
            throw new EmptyResultException("数据库为空!");
        }

        for(int i=0;i<list.size();i++){
            ZZDeviceExample exmple=new ZZDeviceExample();
            exmple.createCriteria().andDeviceIdEqualTo(list.get(i));
            List<ZZDevice> listt=zzDeviceMapper.selectByExampleWithBLOBs(exmple);
            ZZDeviceList.addAll(listt);
        }

        return new SysResult(ErrorUtil.CODE2000, "查询成功ok",ZZDeviceList);

    }

    @Override
    public SysResult listWorkfaceDeviceByPage(String workfaceId, String deviceTypeId, Integer pageNo, Integer pageSize) {
        List<WorkfaceDeviceInfo> list = null;
        int count;
        // 为pageNo和pageSize赋默认值
        if (null == pageNo || pageNo == 0)
            pageNo = 1;
        if (null == pageSize || pageSize == 0)
            pageSize = 11;
        if ("".equals(deviceTypeId))
            deviceTypeId = null;
        if ("".equals(workfaceId))
            workfaceId = null;
        // 封装到分页查询包装类中
        Integer rowNo = (pageNo - 1) * pageSize;
        try{
            count = this.countWorkfaceDevice(workfaceId,deviceTypeId);
            list = zzWorkfaceDeviceMapper.listWorkfaceDeviceByPage(workfaceId,deviceTypeId,rowNo, pageSize);
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            throw new DoSqlFailedException("工作面设备表查询失败", e);
        }
        return new SysResult(ErrorUtil.CODE2000, "ok",
                new PageObject<WorkfaceDeviceInfo>(pageNo, pageSize, count, list));
    }

    @Override
    public SysResult selectWorkfaceDeviceInfo(String workfaceId,String deviceTypeId, String code) {
        List<WorkfaceDeviceInfo> workfaceDeviceInfos = zzWorkfaceDeviceMapper.selectWorkfaceDeviceInfo(workfaceId,deviceTypeId, code);
        for (WorkfaceDeviceInfo info: workfaceDeviceInfos){
            String deviceId = info.getDeviceId();
            List<AttributeInnerAttributeDevice> attrs = zzDeviceAttributeRelationMapper.listDeviceAttributeAllByDeviceId(deviceId);
            info.setDeviceAttribute(attrs);
        }
        return new SysResult(ErrorUtil.CODE2000,"ok",workfaceDeviceInfos);
    }

    @Override
    public SysResult selectWorkfaceDeviceInfoSimple(String workfaceId,String deviceTypeId, String code) {
        if (workfaceId == null || "".equals(workfaceId)||code == null || "".equals(code)||"".equals(deviceTypeId)||deviceTypeId == null)
            throw new IllegalParamException("参数不能为空!");
        List<WorkfaceDeviceInfo> workfaceDeviceInfos = zzWorkfaceDeviceMapper.selectWorkfaceDeviceInfoSimple(workfaceId,deviceTypeId, code);
        for (WorkfaceDeviceInfo info: workfaceDeviceInfos){
            String deviceId = info.getDeviceId();
            List<AttributeInnerAttributeDevice> attrs = zzDeviceAttributeRelationMapper.listDeviceAttributeSimpleByDeviceId(deviceId);
            info.setDeviceAttribute(attrs);
        }
        return new SysResult(ErrorUtil.CODE2000,"ok",workfaceDeviceInfos);
    }

    @Override
    public SysResult updateDeviceAccumulative(WorkfaceDeviceAccumulative wda) {
        String workfaceId = wda.getWorkfaceId();
        String code = wda.getCode();
        int num = 0;
        if (workfaceId==null )
            throw new IllegalParamException("workfaceId 不能为空!");
        if (null==code){
            throw new IllegalParamException("code 不能为空");
        }else {
            String[] split = code.split(",");
            for (String c:split){
                List<WorkfaceDeviceInfo> deviceInfo = zzWorkfaceDeviceMapper.selectWorkfaceDeviceInfoSimple(workfaceId, supportTypeId, c);
                int size = deviceInfo.size();
                if (size<1){
                    //新建
                    return new SysResult(ErrorUtil.CODE2001,"目标设备不存在",null);
                }else {
                    String deviceId = deviceInfo.get(0).getDeviceId();
                    //更新
                    ZZDeviceAccumulative pojo = new ZZDeviceAccumulative(wda);
                    pojo.setDeviceId(deviceId);
                    SysResult update = zzDeviceAccumulativeService.update(pojo);
                    if ("2000".equals(update.getCode()))
                        num++;
                }
            }
        }
        return new SysResult(ErrorUtil.CODE2000,"ok",num);
    }

    public int countWorkfaceDevice(String workfaceId,String deviceTypeId){
        return zzWorkfaceDeviceMapper.countWorkfaceDevice(workfaceId,deviceTypeId);
    }
    
    /**
	 * 对工作面设备表进行批量删除
	 * 循环调用单个删除工作面设备方法
	 */
	@Override
	public SysResult batchDel(String ids) {
			String[] id=ids.split(",");			
			List<String> idToList= Arrays.asList(id);
			
			for(String idd:idToList){
				this.delZZWorkfaceDevice(idd);
			}
			return new SysResult(ErrorUtil.CODE2000,"批量删除工作面设备成功",idToList);	
	}

    public String getSupportTypeId() {
        return supportTypeId;
    }

    public void setSupportTypeId(String supportTypeId) {
        this.supportTypeId = supportTypeId;
    }
}
