package com.zzmj.service;

import com.zzmj.pojo.entity.ZZWorkfaceAccumulative;
import com.zzmj.pojo.vo.SysResult;

public interface ZZWorkfaceAccumulativeService {

	/**
	 * a 新增工作累计信息表
	 * 
	 * @param zzWofkfaceAccumulative
	 * @return
	 */
	SysResult addZZWorkfaceAccumulative(ZZWorkfaceAccumulative zzWorkfaceAccumulative);

	/**
	 * d 删除工作累计信息表
	 * 
	 * @param zzWofkfaceAccumulative
	 * @return
	 */
	SysResult delZZWorkfaceAccumulative(ZZWorkfaceAccumulative zzWorkfaceAccumulative);

	/**
	 * u 修改工作累计信息表
	 * 
	 * @param zzWofkfaceAccumulative
	 * @return
	 */
	SysResult updateZZWorkfaceAccumulative(ZZWorkfaceAccumulative zzWorkfaceAccumulative);

	/**
	 * s 查找工作累计信息表
	 * 
	 * @param wofkfaceAccumulativeId
	 * @return
	 */
	SysResult selectById(String workfaceId);
}
