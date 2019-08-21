package com.zzmj.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZMineMessageMapper;
import com.zzmj.pojo.entity.MineMessage;
import com.zzmj.pojo.entity.ZZDevice;
import com.zzmj.pojo.entity.ZZMineMessage;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZMineMessageService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.FormatDateUtil;
import com.zzmj.util.exception.DoSqlFailedException;

@Service
public class ZZMineMessageServiceImpl implements ZZMineMessageService {

	
	private static Logger logger = Logger.getLogger(ZZDeviceTypeServiceImpl.class);
	
	@Autowired
	private ZZMineMessageMapper zzMineMessageMapper;
	
	/**
	 * 添加矿井通知通告数据表数据  发送通知
	 */
	@Override
	public SysResult postMineMessage(ZZMineMessage zzMineMessage) {
	
		try {
			zzMineMessage.setId(CodeUtil.createUuid36());
			zzMineMessage.setMessageId(CodeUtil.createUuid36());
			zzMineMessage.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
			zzMineMessage.setIsDel("0");
			int flag=zzMineMessageMapper.postMineMessage(zzMineMessage);
			if(flag<1)
				return new SysResult(ErrorUtil.CODE2001, "矿井通知通告数据添加失败..", null);
			return new SysResult(ErrorUtil.CODE2000, "矿井通知通告数据添加成功", zzMineMessage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("矿井通知通告数据添加失 败 !!，出现异常", e);
			throw new DoSqlFailedException("矿井通知通告数据添加失败!!!");
		}
		
	}

	/**
	 * 对矿井通知通告数据表进行分页查
	 */
	@Override
	public SysResult getMineMessageList(String date, String messageType,
			Integer pageNo, Integer pageSize) throws ParseException {
		
		try {
			// 判断参数 
			if (null == pageNo || pageNo == 0)
				pageNo = 1;
			if (null == pageSize || pageSize == 0)
				pageSize = 11;

			// 从第几条开始
			int rowNum = (pageNo - 1) * pageSize;
			

			/**
			 * 将传过来的String类型的日期先转换成Date类型的日期格式
			 * 在转换成String类型的日期格式到数据库中进行比较  (条件为当天内的数据)
			 */
			Date datee=FormatDateUtil.dataFormatt(date);
			String dateee=FormatDateUtil.dataFormat(datee); //sql语句大于的值
			String dateeee=FormatDateUtil.dataFormattt(datee);//sql语句小于的值
			
			List<ZZMineMessage> zzMineMessageList=getZZMineMessageByPage(dateee,dateeee,messageType,rowNum,pageSize);
			List<MineMessage> MineMessageList=new ArrayList();
			
				for(ZZMineMessage zzMineMessage:zzMineMessageList){
//					System.out.println(zzMineMessage.getUserId()+"   "+zzMineMessage.getOrgId()+"   "+zzMineMessage.getWorkfaceId());
					String userId=zzMineMessage.getUserId();
					String orgId=zzMineMessage.getOrgId();
					String workfaceId=zzMineMessage.getWorkfaceId();
					 MineMessageList=zzMineMessageMapper.getZZMineMessage
							(userId,orgId,workfaceId,dateee,dateeee,messageType,rowNum,pageSize);
				}
			
				int count=MineMessageList.size();
//				
//				System.out.println(MineMessageList.size()+"    MineMessageList.size();");
//				for(MineMessage mineMessage:MineMessageList){
//					System.out.println(mineMessage+"    mineMessage");
//				}
				
			return new SysResult(ErrorUtil.CODE2000, " 获取成功",
	                    new PageObject<MineMessage>(pageNo, pageSize, count, MineMessageList));
		} catch (Exception e) {
			e.printStackTrace();
		    logger.info("分页查询出现异常，请检查");
            throw new DoSqlFailedException("分页查询出现异常,请检查");
		}
	}
	/**
	 * 矿井通知通告数据表分页查 查询数量
	 */
	public int getCount(String dateee,String dateeee,String messageType){
		return zzMineMessageMapper.getCount(dateee,dateeee,messageType);
	}
	
	/**
	 * 矿井通知通告数据表分页查 查询数据  负责将每页的数据查询出来
	 */
	public List<ZZMineMessage> getZZMineMessageByPage
	(String dateee,String dateeee,String messageType,Integer rowNum,Integer pageSize){
		return zzMineMessageMapper.getZZMineMessageByPage(dateee,dateeee,messageType,rowNum,pageSize);
		
	}

	/**
	 * 删除单个通知通告
	 */
	@Override
	public SysResult deleteById(String id) {
		
		try {
			int flag=zzMineMessageMapper.deleteById(id);
			if(flag<1)
				return new SysResult(ErrorUtil.CODE2001, "删除通知通告失败",null);
			return new SysResult(ErrorUtil.CODE2000, " 删除通知通告成功", flag );
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("分页查询出现异常，请检查");
            throw new DoSqlFailedException("分页查询出现异常,请检查");
		}
	}

	/**
	 * 批量删除通知通告
	 */
	@Override
	public SysResult batchDel(String ids) {
		 	String[] id=ids.split(",");
	        List<String> idToList= Arrays.asList(id);

	        for(String idd:idToList){
	            SysResult sysResult= this.deleteById(idd);
	            if(sysResult.getCode().equals("2001"))
					return new SysResult(ErrorUtil.CODE2001, "批量删除通知通告！！！",null );
	        }
	        return new SysResult(ErrorUtil.CODE2000,"批量删除通知通告成功",idToList);
	}
}
