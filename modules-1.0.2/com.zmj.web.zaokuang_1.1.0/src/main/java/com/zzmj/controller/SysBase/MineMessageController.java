package com.zzmj.controller.SysBase;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzmj.pojo.entity.ZZDeviceType;
import com.zzmj.pojo.entity.ZZMineMessage;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceTypeService;
import com.zzmj.service.ZZMineMessageService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@RestController
@RequestMapping("/SysBase/MineMessage")
public class MineMessageController {
	Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ZZMineMessageService zzMineMessageService;
	
	@RequestMapping(value = "/postMineMessage", method = RequestMethod.POST)
	public SysResult postMineMessage(ZZMineMessage zzMineMessage) {
		try {
			return zzMineMessageService.postMineMessage(zzMineMessage);
		} catch (Exception e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}
	
	@RequestMapping(value = "/getMineMessageList",method = RequestMethod.POST)
    public SysResult getMineMessageList(@RequestParam(name="date",required=false) String date,
    							@RequestParam(name="messageType",required=false) String messageType,
                                @RequestParam(name="pageNo",required=false) Integer pageNo,
                                @RequestParam(name="pageSize",required=false) Integer pageSize) {
        
			try {
				return zzMineMessageService.getMineMessageList(date,messageType,pageNo,pageSize);
			} catch (Exception e) {
				e.printStackTrace();
				return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
			}
	    
		}
	
	/**
	 * 对通知表进行单个删除
	 * @return
	 */
	@RequestMapping(value = "/remove",method = RequestMethod.POST)
	public SysResult deleteById(@RequestParam(name="id",required=false) String id){
	
		try {
			return zzMineMessageService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
		
	}
	
	/**
	 * 对通知通告表进行批量删除
	 * @return
	 */
	@RequestMapping(value = "/batDelZZminemessage", method = RequestMethod.POST)
	public SysResult batDelZZWorkfaceDevice(@RequestParam(name = "ids", required = false) String ids) {
		try {
			return zzMineMessageService.batchDel(ids);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}
	
}
