package com.zzmj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzmj.pojo.entity.MineMessage;
import com.zzmj.pojo.entity.ZZMineMessage;

public interface ZZMineMessageMapper {

	/**
	 * 发表通知接口  (添加通知)
	 */
	public int postMineMessage(ZZMineMessage zzMineMessage);
	
	/*
	 *分页查进行查询数量
	 */
	public int getCount(@Param("dateee") String dateee,
			@Param("dateeee") String dateeee,@Param("messageType") String messageType);
	
	/**
	 * 分页查 查询每页的数据
	 * @param dateee
	 * @param dateeee
	 * @param messageType
	 * @param rowNum
	 * @param pageSize
	 * @return
	 */
	public List<ZZMineMessage> getZZMineMessageByPage
	(@Param("dateee") String dateee,@Param("dateeee")String dateeee,
			@Param("messageType")String messageType,@Param("rowNum")Integer rowNum,
			@Param("pageSize")Integer pageSize);

	public List<MineMessage> getZZMineMessage(@Param("userId")String userId, @Param("orgId")String orgId,
			@Param("workfaceId")String workfaceId, @Param("dateee")String dateee,@Param("dateeee") String dateeee,
			@Param("messageType")String messageType, @Param("rowNum")int rowNum, @Param("pageSize")Integer pageSize);

	public int deleteById(String id);

	
}
