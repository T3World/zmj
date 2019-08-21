package com.zzmj.service;

import java.text.ParseException;

import com.zzmj.pojo.entity.ZZMineMessage;
import com.zzmj.pojo.vo.SysResult;

public interface ZZMineMessageService {

	/**
	 * 发表通知接口  (添加通知)
	 */
	public SysResult postMineMessage(ZZMineMessage zzMineMessage);

	/**
	 * 对矿井通知通告数据表进行分页查
	 * @param date
	 * @param messageType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws ParseException 
	 */
	public SysResult getMineMessageList(String date, String messageType,
			Integer pageNo, Integer pageSize) throws ParseException;

	/**
	 * 对通知表进行单个删除
	 * @param id
	 */
	public SysResult deleteById(String id);

	public SysResult batchDel(String ids);
}
