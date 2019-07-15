package com.zzmj.controller.SysBase;

import com.zzmj.pojo.entity.ZZDevice;
import com.zzmj.pojo.vo.DeviceWithAttribute;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/SysBase/Device")
public class DeviceController {

	public static final String AbsolutePath = "/upload";

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ZZDeviceService zzDeviceService;

	@RequestMapping(value = "/addZZDevice", method = RequestMethod.POST)
	public SysResult addZZDevice(HttpServletRequest request, ZZDevice zzDevice) {
		try {
			return this.zzDeviceService.addZZDevice(zzDevice);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/delZZDevice", method = RequestMethod.POST)
	public SysResult delZZDevice(ZZDevice zzDevice) {
		try {
			return this.zzDeviceService.delZZDevice(zzDevice);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/updateZZdevice", method = RequestMethod.POST)
	public SysResult updateZZdevice(ZZDevice zzDevice) {
		try {
			return this.zzDeviceService.updateZZdevice(zzDevice);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 注意 查询出的每个对象 isdel应为0
	 * 
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/selectById", method = RequestMethod.POST)
	public SysResult selectById(String deviceId) {
		try {
			return this.zzDeviceService.selectByid(deviceId);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 根据设备类型id 查询设备表集合 且查询出的对象的isDel属性为0
	 * 
	 * @param deviceTypeId
	 * @return
	 */
	@RequestMapping(value = "/deviceTypeId", method = RequestMethod.POST)
	public SysResult selectBydevicetypeId(String deviceTypeId) {
		try {
			return zzDeviceService.selectByDeviceTypeId(deviceTypeId);

		} catch (EmptyResultException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 对设备表进行分页
	 * 
	 * @param devicetypeId
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getDeviceData", method = RequestMethod.POST)
	public SysResult getDeviceData(@RequestParam(name = "deviceTypeId", required = false) String deviceTypeId,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		try {
			return this.zzDeviceService.listZZDevicePage(deviceTypeId, keyword, pageNo, pageSize);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 根据工作面id 设备类型id 查询设备表集合
	 * 
	 * @param deviceTypeId
	 * @param workfaceId
	 * @return
	 */
	@RequestMapping(value = "/selectDeviceByWorkfaceAndDeviceTypeId", method = RequestMethod.POST)
	public SysResult selectDeviceByWorkfaceAndDeviceTypeId(
			@RequestParam(name = "deviceTypeId", required = false) String deviceTypeId,
			@RequestParam(name = "workfaceId", required = false) String workfaceId) {
		try {
			return this.zzDeviceService.selectDeviceByWorkfaceAndDeviceTypeId(workfaceId, deviceTypeId);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/batDelZZDevice", method = RequestMethod.POST)
	public SysResult batDelZZDevice(@RequestParam(name = "ids", required = false) String ids) {
		try {
			return this.zzDeviceService.batchDel(ids);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

    @RequestMapping(value = "/listDeviceAttributeAllByDeviceId", method = RequestMethod.POST)
    public SysResult listDeviceAttributeAllByDeviceId(@RequestParam(name = "deviceId") String deviceId) {
        try {
            return this.zzDeviceService.listDeviceAttributeAllByDeviceId(deviceId);
        } catch (DoSqlFailedException e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/selectDeviceWithAttributeByDeviceId", method = RequestMethod.POST)
    public SysResult selectDeviceWithAttributeByDeviceId(@RequestParam(name = "deviceId") String deviceId) {
            try {
				return this.zzDeviceService.selectDeviceWithAttributeByDeviceId(deviceId);
			} catch (Exception e) {
				e.printStackTrace();
				 return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
			}
    }


    @RequestMapping(value = "/updateDeviceWithAttributeByDeviceId", method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    public SysResult selectDeviceWithAttributeByDeviceId(@RequestBody DeviceWithAttribute deviceWithAttribute) {
    	try {
			return zzDeviceService.updateDeviceWithAttributeByDeviceId(deviceWithAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }

    @RequestMapping(value = "/addDeviceWithAttribute", method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    public SysResult addDeviceWithAttribute(@RequestBody(required = false)  DeviceWithAttribute deviceWithAttribute) {
    	try {
			return zzDeviceService.addDeviceWithAttribute(deviceWithAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
    
    @RequestMapping(value = "/deleteDeviceWithAttributeByDeviceId", method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    public SysResult deleteDeviceWithAttributeByDeviceId(@RequestBody(required = false)  DeviceWithAttribute deviceWithAttribute) {
    	try {
			return zzDeviceService.deleteDeviceWithAttributeByDeviceId(deviceWithAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
    
    @RequestMapping(value = "/selectDeviceWithAttributeAll", method = RequestMethod.POST)
    public SysResult selectDeviceWithAttributeAll() {
    	try {
			return zzDeviceService.selectDeviceWithAttributeAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
    
    @RequestMapping(value = "/selectDeviceWithAttributePage", method = RequestMethod.POST)
    public SysResult selectDeviceWithAttributePage(@RequestParam(name="keyword",required=false) String keyword,
            @RequestParam(name="pageNo",required=false) Integer pageNo,
            @RequestParam(name="pageSize",required=false) Integer pageSize) {
    	try {
			return zzDeviceService.selectDeviceWithAttributePage(keyword, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
}
