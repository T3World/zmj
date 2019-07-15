package com.zzmj.controller.SysBase;

import com.zzmj.pojo.dto.WorkfaceDeviceAccumulative;
import com.zzmj.pojo.entity.ZZWorkfaceDevice;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceDeviceService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SysBase/WorkfaceDevice")
public class WorkfaceDeviceController {

    Logger logger = Logger.getLogger(this.getClass().getName());


    @Autowired
    private ZZWorkfaceDeviceService zzWorkfaceDeviceService;

    @RequestMapping(value = "/addZZWorkfaceDevice", method = RequestMethod.POST)
    public SysResult addZZWorkfaceDevice(ZZWorkfaceDevice zzWorkfaceDevice) {
        try {
            return this.zzWorkfaceDeviceService.addZZWorkfaceDevice(zzWorkfaceDevice);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }

    }

    @RequestMapping(value = "/delZZWorkfaceDevice", method = RequestMethod.POST)
    public SysResult delZZWorkfaceDevice(ZZWorkfaceDevice zzWorkfaceDevice) {
        try {
            return this.zzWorkfaceDeviceService.delZZWorkfaceDevice(zzWorkfaceDevice.getId());
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }

    }

    @RequestMapping(value = "/updateZZWorkfaceDevice", method = RequestMethod.POST)
    public SysResult updateZZWorkfaceDevice(ZZWorkfaceDevice zzWorkfaceDevice) {
        try {
            return this.zzWorkfaceDeviceService.updateZZWorkfaceDevice(zzWorkfaceDevice);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/selectById", method = RequestMethod.POST)
    public SysResult selectById(String workfaceId) {
        try {
            return this.zzWorkfaceDeviceService.selectById(workfaceId);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    /**
     * 根据工作面的设备表的workfaceId查询设备表集合
     * 先根据workfaceId查询多个deviceId
     * 在查询集合   注意查询的集合中isdel为0
     * @param workfaceId
     * @return
     */
    @RequestMapping(value = "/workfaceId", method = RequestMethod.POST)
    public SysResult selectByWorkfaceId(String workfaceId) {
        try {
            return this.zzWorkfaceDeviceService.selectByWorkfaceId(workfaceId);
        } catch (Exception e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    @RequestMapping("/listWorkfaceDeviceByPage")
    public SysResult listWorkfaceDeviceByPage(
            @RequestParam("workfaceId") String workfaceId,
            @RequestParam("deviceTypeId") String deviceTypeId,
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("pageSize") Integer pageSize){
        SysResult result = null;
        try {
            result = zzWorkfaceDeviceService.listWorkfaceDeviceByPage(workfaceId,deviceTypeId,pageNo,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            result = new SysResult(ErrorUtil.CODE5000,e.getMessage(),null);
        }
        return result;
    }

    /**
     * 依据工作面Id,设备编码查询设备详情
     * */
    @RequestMapping("/selectWorkfaceDeviceInfo")
    public SysResult selectWorkfaceDeviceInfo(
            @RequestParam("workfaceId") String workfaceId,
            @RequestParam("deviceTypeId") String deviceTypeId,
            @RequestParam("code") String code){
        SysResult result = null;
        try {
            result  = zzWorkfaceDeviceService.selectWorkfaceDeviceInfo(workfaceId,deviceTypeId, code);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            result = new SysResult(ErrorUtil.CODE5000,e.getMessage(),null);
        }
        return result;
    }

    /**
     * 依据工作面Id,设备编码查询设备详情 前端专用简化版
     * */
    @RequestMapping("/selectWorkfaceDeviceInfoSimple")
    public SysResult selectWorkfaceDeviceInfoSimple(
            @RequestParam("workfaceId") String workfaceId,
            @RequestParam("deviceTypeId") String deviceTypeId,
            @RequestParam("code") String code){
        SysResult result = null;
        try {
            result  = zzWorkfaceDeviceService.selectWorkfaceDeviceInfoSimple(workfaceId,deviceTypeId, code);
        }catch (Exception e){
            e.printStackTrace();
            result = new SysResult(ErrorUtil.CODE5000,e.getMessage(),null);
        }
        return result;
    }

    /**
     * 依据工作面Id,设备编码,更新设备累计量
     * */
    @RequestMapping("/updateDeviceAccumulativeByWorkfaceId")
    public SysResult updateDeviceAccumulativeByWorkfaceId(WorkfaceDeviceAccumulative wda){
        SysResult result = null;
        try {
            result  = zzWorkfaceDeviceService.updateDeviceAccumulative(wda);
        }catch (Exception e){
            e.printStackTrace();
            result = new SysResult(ErrorUtil.CODE5000,e.getMessage(),null);
        }
        return result;
    }

    
    @RequestMapping(value = "/batDelZZWorkfaceDevice", method = RequestMethod.POST)
	public SysResult batDelZZWorkfaceDevice(@RequestParam(name = "ids", required = false) String ids) {
		try {
			return this.zzWorkfaceDeviceService.batchDel(ids);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

}
