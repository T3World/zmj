package com.zzmj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZCoalMiningRateMapper;
import com.zzmj.mapper.ZZWorkfaceMapper;
import com.zzmj.mapper.ZZWorkfaceconfigMapper;
import com.zzmj.pojo.entity.Rate;
import com.zzmj.pojo.entity.WorkfaceAndConfig;
import com.zzmj.pojo.entity.ZZWorkfaceEntity;
import com.zzmj.pojo.entity.ZZWorkfaceconfigEntity;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZCoalMiningRateService;
import com.zzmj.service.ZZWorkfaceService;
import com.zzmj.service.ZZWorkfaceconfigService;
import com.zzmj.service.ZZOrgService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.DoubleToStringUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.FormatDateUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.IllegalParamException;

@Service("WorkfaceService")
public class ZZWorkfaceServiceImpl implements ZZWorkfaceService {

	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ZZWorkfaceMapper zzWorkfaceMapper; // 工做面mapper

	@Autowired
	private ZZWorkfaceconfigService zzWorkfaceconfigService;

	@Autowired
	private ZZWorkfaceconfigMapper configMapper;
	
	@Autowired
	private ZZCoalMiningRateMapper zzCoalMiningRateMapper;
	
	@Autowired
	private ZZCoalMiningRateService zzCoalMiningRateService;
	
	@Autowired
	private ZZOrgService zzOrgService;
	
	
	/**
	 * @see com.zzmj.service.ZZWorkfaceService#countWorkface(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public int countWorkface(String orgId, String keyword) {
		if (null == orgId || orgId.equals("")) {
			if (null == keyword || keyword.equals("")) {
				return zzWorkfaceMapper.countWorkfaceAll();
			}
			return zzWorkfaceMapper.countWorkfaceByKeyword(keyword);
		}
		if (null == keyword || keyword.equals("")) {
			return zzWorkfaceMapper.countWorkfaceByOrgId(orgId);
		}
		return zzWorkfaceMapper.countWorkfaceByOrgIdAndByKeyword(orgId, keyword);
	}

	@Override
	public SysResult listWorkface(String orgId, String keyword, Integer pageNo, Integer pageSize) {
		try {
			// 分页查询先查询结果总数
			int count = this.countWorkface(orgId, keyword);
			// 为pageNo和pageSize赋默认值
			if (null == pageNo || pageNo == 0)
				pageNo = 1;
			if (null == pageSize || pageSize == 0)
				pageSize = 11;
			// 封装到分页查询包装类中
			Integer rowNo = (pageNo - 1) * pageSize;
			List<ZZWorkfaceEntity> list = listWorkfaceByPage(orgId, keyword, rowNo, pageSize);
			return new SysResult(ErrorUtil.CODE2000, "ok",
					new PageObject<ZZWorkfaceEntity>(pageNo, pageSize, count, list));
		} catch (RuntimeException e) {
			logger.error("工作面列表查询失败!", e);
			throw new DoSqlFailedException("工作面列表查询失败!", e);
		}
	}

	private List<ZZWorkfaceEntity> listWorkfaceByPage(String orgId, String keyword, Integer rowNo, Integer pageSize) {
		if (null == orgId || orgId.equals("")) {
			if (null == keyword || keyword.equals("")) {
				List<ZZWorkfaceEntity> list = zzWorkfaceMapper.listWorkfaceAll(rowNo, pageSize);
				logger.info(String.valueOf(list.size()));
				return list;
			}
			return zzWorkfaceMapper.listWorkfaceByKeyword(keyword, rowNo, pageSize);
		} else {
			if (null == keyword || keyword.equals("")) {
				List<ZZWorkfaceEntity> list = zzWorkfaceMapper.listWorkfaceByOrgId(orgId, rowNo, pageSize);
				logger.info(String.valueOf(list.size()));
				return list;
			}
			return zzWorkfaceMapper.listWorkfaceByOrgIdAndKeyword(orgId, keyword, rowNo, pageSize);
		}
	}

	@Override
	public SysResult addWorkface(WorkfaceAndConfig wac) throws IllegalParamException, DoSqlFailedException {
		if (null == wac)
			return new SysResult(ErrorUtil.CODE2001, "传入参数不能为空!新增失败!", null);
		String id = CodeUtil.createUuid36();
		String wid = CodeUtil.createUuid36();
		ZZWorkfaceEntity workface = getset(wac);
		workface.setId(id);
		workface.setWorkfaceId(wid);
		try {
			ZZWorkfaceconfigEntity zzWorkfaceconfigEntity = workface.getZzWorkfaceconfigEntity();
			zzWorkfaceconfigEntity.setId(CodeUtil.createUuid36());
			zzWorkfaceconfigEntity.setWorkfaceId(wid);
			zzWorkfaceconfigEntity.setWorkfaceLength(wac.getWorkfaceLength());
			zzWorkfaceconfigEntity.setWorkfaceAbleLength(wac.getWorkfaceAbleLength());
			zzWorkfaceconfigEntity.setWorkfaceCoalThickness(wac.getWorkfaceCoalThickness());
			zzWorkfaceconfigEntity.setWorkfaceInclination(wac.getWorkfaceInclination());
			zzWorkfaceconfigEntity.setWorkfaceAverageHeight(wac.getWorkfaceAverageHeight());
			zzWorkfaceconfigEntity.setWorkfaceStartTime(wac.getWorkfaceStartTime());
			zzWorkfaceconfigEntity.setMorningShift(wac.getMorningShift());
			zzWorkfaceconfigEntity.setAfternoonShift(wac.getAfternoonShift());
			zzWorkfaceconfigEntity.setNightShift(wac.getNightShift());
			int flag2 = this.zzWorkfaceconfigService.addZZWorkfaceconfigEntity(zzWorkfaceconfigEntity);
			int flag1 = zzWorkfaceMapper.insert(workface);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag1 + flag2);
		} catch (RuntimeException e) {
			logger.error("工作面新增失败!", e);
			throw new DoSqlFailedException("工作面新增失败!");
		}
	}

	@Override
	public SysResult updateWorkface(WorkfaceAndConfig wac) {
	
		try {
			ZZWorkfaceEntity zzWorkfaceEntity = this.getset(wac);
			
			ZZWorkfaceconfigEntity zzWorkfaceconfigEntity = zzWorkfaceEntity.getZzWorkfaceconfigEntity();
			
			String id=zzWorkfaceMapper.selectId(zzWorkfaceconfigEntity.getWorkfaceId());
			zzWorkfaceconfigEntity.setId(id);
			zzWorkfaceconfigEntity.setWorkfaceLength(wac.getWorkfaceLength());
			zzWorkfaceconfigEntity.setWorkfaceAbleLength(wac.getWorkfaceAbleLength());
			zzWorkfaceconfigEntity.setWorkfaceCoalThickness(wac.getWorkfaceCoalThickness());
			zzWorkfaceconfigEntity.setWorkfaceInclination(wac.getWorkfaceInclination());
			zzWorkfaceconfigEntity.setWorkfaceAverageHeight(wac.getWorkfaceAverageHeight());
			zzWorkfaceconfigEntity.setWorkfaceStartTime(wac.getWorkfaceStartTime());
			zzWorkfaceconfigEntity.setMorningShift(wac.getMorningShift());
			zzWorkfaceconfigEntity.setAfternoonShift(wac.getAfternoonShift());
			zzWorkfaceconfigEntity.setNightShift(wac.getNightShift());
		
		
			int flag1 = zzWorkfaceconfigService.updateZZWorkfaceconfig(zzWorkfaceconfigEntity);
			int flag2 = zzWorkfaceMapper.updateByPrimaryKeySelective(zzWorkfaceEntity);
			if (flag1 < 1 && flag2 < 1)
				throw new DoSqlFailedException("工作面配置更新失败!");
			return new SysResult(ErrorUtil.CODE2000, "ok", flag1 + flag2);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new DoSqlFailedException(e.getMessage(), e);
		}
	}

	@Override
	public SysResult delWorkface(String workfaceId) {
		// 验证参数合法性
		if (null == workfaceId || workfaceId.equals("")) {
			return new SysResult(ErrorUtil.CODE2001, "参数为空,删除失败!", null);
		}
		try {
			int flag = this.zzWorkfaceMapper.delWorkfaceByWorkfaceId(workfaceId);
			if (flag < 1)
				return new SysResult(ErrorUtil.CODE2001, "删除失败,没有符合条件的工作面!", flag);
			return new SysResult(ErrorUtil.CODE2000, "ok", flag);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("工作面删除失败!", e);
			throw new DoSqlFailedException("工作面删除失败!");
		}
	}

	@Override
	public SysResult stopWorkface(String workfaceId, Integer workfaceState) {
		// 参数合法性校验
		if (null == workfaceId || null == workfaceState)
			return new SysResult("2001", "工作面状态更新失败", null);
		if (workfaceState == 1) {
			workfaceState = 0;
		} else {
			workfaceState = 1;
		}
		try {
			return new SysResult(ErrorUtil.CODE2000, "ok", zzWorkfaceMapper.stopWorkface(workfaceId, workfaceState));
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("工作面启停操作失败!", e);
			throw new DoSqlFailedException("工作面启停操作失败!", e);
		}
	}

	private ZZWorkfaceEntity getset(WorkfaceAndConfig wac) {
		ZZWorkfaceEntity workface = new ZZWorkfaceEntity();
		ZZWorkfaceconfigEntity wc = new ZZWorkfaceconfigEntity();
		workface.setWorkfaceId(wac.getWorkfaceId());
		workface.setOrgId(wac.getOrgId());
		workface.setWorkfaceName(wac.getWorkfaceName());
		workface.setWorkfaceAlias(wac.getWorkfaceAlias());
		workface.setWorkfaceState(1);
		workface.setWorkfaceType(wac.getWorkfaceType());
		workface.setIsdel(0);
		workface.setSortcode(wac.getSortcode());
		wc.setWorkfaceId(wac.getWorkfaceId());
		wc.setSupportCount(wac.getSupportCount());
		wc.setSupportDir(wac.getSupportDir());
		wc.setBeltType(wac.getBeltType());
		wc.setFontMaxpressure(wac.getFontMaxpressure());
		wc.setFontMinpressure(wac.getFontMinpressure());
		wc.setBackMaxpressure(wac.getBackMaxpressure());
		wc.setBackMinpressure(wac.getBackMinpressure());
		wc.setConveyorDir(wac.getConveyorDir());
		wc.setPressureCharttype(wac.getPressureCharttype());
		wc.setShearerposCachetime(wac.getShearerposCachetime());
		workface.setZzWorkfaceconfigEntity(wc);
		return workface;
	}

	@Override
	public List<Map<String, Object>> listWorkfaceNoPage(String orgId) {
		List<Map<String, Object>> wlist = this.zzWorkfaceMapper.listWorkfaceNoPage(orgId);
		if (null == wlist || wlist.isEmpty())
			return null;
		Iterator<Map<String, Object>> it = wlist.iterator();
		while (it.hasNext()) {
			Map<String, Object> w = it.next();
			String wid = (String) w.get("Workface_Id");
			Map<String, Object> config = this.configMapper.selectConfigByWorkfaceId(wid);
			w.put("Workface_Config", config);
		}
		return wlist;
	}

	/**
	 * 先根据orgId 到zz_workerface表中查询多个wokerfaceId
	 */
	@Override
	public SysResult workfaceIdListByOrgId(String orgId,String sourceTime,String rateType) {
		
		List<Double> rateValueList=new ArrayList<>();
		double total=0.0;
		
		if(("").equals(orgId) || null==orgId)
			return new SysResult(ErrorUtil.CODE2001, "请输入orgId",null);
		
		try {
			List<String> workerfaceIdList=zzWorkfaceMapper.selectworkfaceIdByOrgId(orgId);
			if(workerfaceIdList.size()!=0 && null!=workerfaceIdList){
				for(String workfaceId:workerfaceIdList){
					//输出得到的workfaceId
					System.out.println(workfaceId);
					//求出综采率
//					String rateVlue=zzCoalMiningRateMapper.selectWorkfaceRatee(workfaceId, sourceTime, rateType);
//					zzCoalMiningRateService.selectRateValue(workfaceId,sourceTime,rateType);

					rateValueList.add(Double.parseDouble((String)(zzCoalMiningRateService.selectRateValue(workfaceId,sourceTime,rateType).getData())));
				}
				for(Double rateValue:rateValueList){
					//输出得到的综采率
					System.out.println(rateValue+"++++");
					total+=rateValue;
				}
				double average=total/rateValueList.size();
				return new SysResult(ErrorUtil.CODE2000, "计算平均率成功",average);
			}else{
				return new SysResult(ErrorUtil.CODE2001, "查询综采率失败 数据库没有综采率",null);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询，出现异常！！", e);
			throw new DoSqlFailedException("查询，出现异常失败！");
		}
		
	}


	/**
	 * 返回Rate对象  controller调用该方法进行查询率  
	 * @param orgIds
	 * @param cycle
	 * @param startTime
	 * @param rateType
	 * @return
	 */
	public SysResult selectReteValue(String orgIds,String cycle,long startTime,String rateType){
		
		List<Rate> rateList=new ArrayList();
		
		String orgId[]=orgIds.split(",");
		
		for(String ss:orgId){
			rateList.add(this.select(ss,cycle,startTime,rateType));
		}
		
		return new SysResult(ErrorUtil.CODE2000, "计算平均率成功",rateList);
	}
	
	
	
	/**
	 * 查询率的平均值
	 * @param orgIds
	 * startTime //sql语句大于的值
	 */
	@Override
	public Rate select(String orgId,String cycle,long startTime,String rateType) {
		String rate;
		
		//存放rateValue的集合
		List<Double> rateValueList=new ArrayList<>();
		double total=0.0;
		
		//存放workfaceId的集合
		List<String> workerfaceIdList=new ArrayList<>();
		
		
		try{
			
		
		//将long转换成Date
		if(cycle.equals("1")){
			
//			long time=1*24*60*60*1000;
//			long sourceTimee=startTime+time;
//			Long sourceTimeee=new Long(sourceTimee);
//						
//			String sourceTimeeee=FormatDateUtil.stampToTime(sourceTimeee);	
			
			//在一天内  between和and 两个值相同
			String end=FormatDateUtil.stampToTime(startTime);	//sql小于的值
			String start=FormatDateUtil.stampToTime(startTime);	//sql大于的值
			
			System.out.println(end+"   end+++");
			System.out.println(start+"   start+++");	
			
			//根据一个orgId查询多个workfaceId
			workerfaceIdList = zzWorkfaceMapper.selectworkfaceIdByOrgId(orgId);
				
			//对workfaceIdList进行遍历 
			for(String workfaceId:workerfaceIdList){
				
				//遍历得到的rateValue
				List<String> rateValueListt=(List<String>) zzCoalMiningRateService.selectRateValueSecond(workfaceId,rateType,end,start).getData();
				for(String rateValue:rateValueListt){
					rateValueList.add(Double.parseDouble(rateValue));					
				}				
			}
			
			//求rateValue总和
			for(Double rateValue:rateValueList){
				//输出得到的综采率
				System.out.println(rateValue+"++++");
				total+=rateValue;
			}
			System.out.println(total+"   total");
			double average=total/(rateValueList.size());
			System.out.println(rateValueList.size()+"   总共多少个率");
			System.out.println(workerfaceIdList.size()+"   总共多少个工作面");
			
			System.out.println(average+"   average");
			//对平均值进行判断是否为NaN
			if(Double.isNaN(average)){
				rate="0";
			}else{
			    rate=DoubleToStringUtil.doubleToString(average);
			}
			System.out.println(Double.isNaN(average));
			System.out.println(rate+"   rate");
			
			String orgName=(String) zzOrgService.selectByOrgId(orgId).getData();
//			String rate=DoubleToStringUtil.doubleToString(average);
			int cyclee=Integer.parseInt(cycle);
			long startTimee=FormatDateUtil.timeToStamp(end);
			Rate ratee=new Rate(orgName,rate,cyclee,startTimee,startTimee);
			
			return ratee;
//			return new SysResult(ErrorUtil.CODE2000, "计算平均率成功",ratee);
		}else if(cycle.equals("2")){
			long time=6*24*60*60*1000;
			long sourceTimee=startTime+time;
			Long sourceTimeee=new Long(sourceTimee);
						
			String end=FormatDateUtil.stampToTime(sourceTimeee);	//sql小于的值
			String start=FormatDateUtil.stampToTime(startTime);	//sql大于的值
			System.out.println(end+"   end+++");
			System.out.println(start+"   start+++");			
			
			//根据orgId查询多个workfaceId
			workerfaceIdList = zzWorkfaceMapper.selectworkfaceIdByOrgId(orgId);
			
			//对workfaceIdList进行遍历 
			for(String workfaceId:workerfaceIdList){	
				//遍历得到的rateVllue
				List<String> rateValueListt=(List<String>) zzCoalMiningRateService.selectRateValueSecond(workfaceId,rateType,end,start).getData();
				for(String rateValue:rateValueListt){
					rateValueList.add(Double.parseDouble(rateValue));					
				}				
			}
			//求rateValue总和
			for(Double rateValue:rateValueList){
				//输出得到的综采率
				System.out.println(rateValue+"++++");
				total+=rateValue;
			}
			System.out.println(total+"   total");
			double average=total/(rateValueList.size());
			System.out.println(rateValueList.size()+"   rateValueList.size()");
			System.out.println(workerfaceIdList.size()+"   workerfaceIdList.size()");
			
			if(Double.isNaN(average)){
				rate="0";
			}else{
			    rate=DoubleToStringUtil.doubleToString(average);
			}
			String orgName=(String) zzOrgService.selectByOrgId(orgId).getData();
			int cyclee=Integer.parseInt(cycle);
			long startTimee=FormatDateUtil.timeToStamp(start);
			long endTime=FormatDateUtil.timeToStamp(end);
			Rate ratee=new Rate(orgName,rate,cyclee,startTimee,endTime);
			
			return ratee;
			
		}else if(cycle.equals("3")){
			
			String start=FormatDateUtil.stampToTime(startTime);	//sql大于的值
			String end=FormatDateUtil.month(start);	//sql小于的值
			System.out.println(end+"   end+++");
			System.out.println(start+"   start+++");
			
			//根据orgId查询多个workfaceId
			workerfaceIdList = zzWorkfaceMapper.selectworkfaceIdByOrgId(orgId);
			
			//对workfaceIdList进行遍历 
			for(String workfaceId:workerfaceIdList){
				
				//遍历得到的rateVllue
				List<String> rateValueListt=(List<String>) zzCoalMiningRateService.selectRateValueSecond(workfaceId,rateType,end,start).getData();
				for(String rateValue:rateValueListt){
					rateValueList.add(Double.parseDouble(rateValue));					
				}				
			}
			
			//求rateValue总和
			for(Double rateValue:rateValueList){
				//输出得到的综采率
				System.out.println(rateValue+"++++");
				total+=rateValue;
			}
			System.out.println(total+"   total");
			double average=total/(rateValueList.size());
			if(Double.isNaN(average)){
				rate="0";
			}else{
			    rate=DoubleToStringUtil.doubleToString(average);
			}
			
			String orgName=(String) zzOrgService.selectByOrgId(orgId).getData();
			int cyclee=Integer.parseInt(cycle);
			long startTimee=FormatDateUtil.timeToStamp(start);
			long endTime=FormatDateUtil.timeToStamp(end);
			Rate ratee=new Rate(orgName,rate,cyclee,startTimee,endTime);
			
			return ratee;
		}else if(cycle.equals("4")){
		
			String start=FormatDateUtil.stampToTime(startTime);	//sql大于的值
			String end=FormatDateUtil.year(start);	//sql小于的值
			System.out.println(end+"   end+++");
			System.out.println(start+"   start+++");
				
			//根据orgId查询多个workfaceId
			workerfaceIdList = zzWorkfaceMapper.selectworkfaceIdByOrgId(orgId);
			
			//对workfaceIdList进行遍历 
			for(String workfaceId:workerfaceIdList){
				
				//遍历得到的rateVllue
				List<String> rateValueListt=(List<String>) zzCoalMiningRateService.selectRateValueSecond(workfaceId,rateType,end,start).getData();
				for(String rateValue:rateValueListt){
					rateValueList.add(Double.parseDouble(rateValue));					
				}				
			}
			
			//求rateValue总和
			for(Double rateValue:rateValueList){
				//输出得到的综采率
				System.out.println(rateValue+"++++");
				total+=rateValue;
			}
			System.out.println(total+"   total");
			double average=total/(rateValueList.size());
			if(Double.isNaN(average)){
				rate="0";
			}else{
			    rate=DoubleToStringUtil.doubleToString(average);
			}
			
			String orgName=(String) zzOrgService.selectByOrgId(orgId).getData();
			int cyclee=Integer.parseInt(cycle);
			long startTimee=FormatDateUtil.timeToStamp(start);
			long endTime=FormatDateUtil.timeToStamp(end);
			Rate ratee=new Rate(orgName,rate,cyclee,startTimee,endTime);
			
			return ratee;	
		}else{
			return null;
		}
	}catch(Exception e){
		e.printStackTrace();
		logger.error("查询失败Rate失败", e);
		throw new DoSqlFailedException("查询失败Rate失败！！！");
	}	
}

	
	
	/**
	 * 返回Rate对象的集合
	 */
	@Override
	public SysResult selectReteValueSecond(String orgIds, long startTime,
			long endTime, String rateType) {
		
		List<Rate> rateList=new ArrayList();
		String orgId[]=orgIds.split(",");
		
		for(String ss:orgId){
			rateList.add(this.selectSecond(ss,startTime,endTime,rateType));
		}
		
		return new SysResult(ErrorUtil.CODE2000, "计算平均率成功",rateList);
	}

	/**
	 * 计算率放入Rate对象中 并且返回
	 * @param ss
	 * @param startTime
	 * @param endTime
	 * @param rateType
	 * @return
	 */
	private Rate selectSecond(String orgId, long startTime, long endTime,String rateType) {

		//定义平均率
		String rate;
		
		//存放rateValue的集合
		List<Double> rateValueList=new ArrayList<>();
		double total=0.0;
		
		//存放workfaceId的集合
		List<String> workerfaceIdList=new ArrayList<>();
		
		try{
			
			//将long时间戳转换为String类型日期
			String start=FormatDateUtil.stampToTime(startTime);	//sql小于的值
			String end=FormatDateUtil.stampToTime(endTime);	//sql大于的值
			
			System.out.println(start+"   start+++");	
			System.out.println(end+"   end+++");
			
			//根据一个orgId查询多个workfaceId
			workerfaceIdList = zzWorkfaceMapper.selectworkfaceIdByOrgId(orgId);
				
			List<String> rateValueListSecond=new ArrayList<>();
			//对workfaceIdList进行遍历 
			for(String workfaceId:workerfaceIdList){
				
				//遍历得到的rateValue
				//List<String> rateValueListSecond=(List<String>) zzCoalMiningRateService.selectRateValueSecond(workfaceId,rateType,end,start).getData();
//				List<String> rateValueListSecond=new ArrayList<>();
				rateValueListSecond.addAll((List<String>) zzCoalMiningRateService.selectRateValueSecond(workfaceId,rateType,end,start).getData());
				
				/*for(String rateValue:rateValueListSecond){
					rateValueList.add(Double.parseDouble(rateValue));					
				}*/				
			}
			for(String rateValue:rateValueListSecond){
				rateValueList.add(Double.parseDouble(rateValue));					
			}
			
			//求rateValue总和
			for(Double rateValue:rateValueList){
				//输出得到的综采率
				System.out.println(rateValue+"++++");
				total+=rateValue;
			}
			System.out.println(total+"   total");
			double average=total/(rateValueList.size());
			System.out.println(rateValueList.size()+"   总共多少个率");
			System.out.println(workerfaceIdList.size()+"   总共多少个工作面");
			
			System.out.println(average+"   average");
			//对平均值进行判断是否为NaN
			if(Double.isNaN(average)){
				rate="0";
			}else{
			    rate=DoubleToStringUtil.doubleToString(average);
			}
			System.out.println(Double.isNaN(average));
			System.out.println(rate+"   rate");
			
			String orgName=(String) zzOrgService.selectByOrgId(orgId).getData();
			long endTimeSecond=FormatDateUtil.timeToStamp(end);	//结束时间
			Rate rateSecond=new Rate(orgName,rate,startTime,endTimeSecond);	
			
			return rateSecond;	//返回Rate对象
		}catch(Exception e){
			e.printStackTrace();
			logger.error("查询失败Rate失败", e);
			throw new DoSqlFailedException("查询失败Rate失败！！！");
		}		
	}
}
