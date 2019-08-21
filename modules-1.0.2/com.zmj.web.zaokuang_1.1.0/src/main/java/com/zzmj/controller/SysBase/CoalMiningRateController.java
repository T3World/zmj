package com.zzmj.controller.SysBase;

import java.util.List;

import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZCoalMiningRateService;
import com.zzmj.service.ZZDeviceService;
import com.zzmj.service.ZZOrgService;
import com.zzmj.service.ZZWorkfaceService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.ListToStringUtil;
import com.zzmj.util.exception.DoSqlFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 综采
 * @author umr
 * @date 2019/7/26
 */
@RestController
@RequestMapping("/SysBase/CoalMiningRate")
public class CoalMiningRateController {

	@Autowired
	private ZZCoalMiningRateService zzCoalMiningRateService;
	
	@Autowired
	private ZZWorkfaceService zzWorkfaceService;
	
	@Autowired
	private ZZOrgService zzOrgService;
	
    /**
     * 根据工作面ID,sourceTime,rateType查询率
     * */
    @RequestMapping("/getRateByDayByWorkfaceId")
    public SysResult getRateByDayByWorkfaceId(
    		@RequestParam(name="workfaceId",required=false) String workfaceId,
    		@RequestParam(name="sourceTime",required=false) String sourceTime,
    		@RequestParam(name="rateType",required=false) String rateType){                                           
    	
    	try {
			return zzCoalMiningRateService.selectRateValue(workfaceId,sourceTime,rateType);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }

    /**
	 * 查询某个组织机构下(多个工作面的)综采率平均率 
     * 根据矿井公司查询综采率
     *  说明: 现根据orgId查询下属工作面,得到下属工作面Id,
     *      再调用getRateByDayByWorkfaceId()方法,得到下属所有工作面的综采率,求平均数
     */
    @RequestMapping("/getRateByDayByOrgId")
    public SysResult getRateByDayByOrgId(@RequestParam("orgId")String orgId,
                                         @RequestParam("sourceTime") String sourceTime,
                                         @RequestParam("rateType")String rateType){
        
    	//现根据orgId到zz_workface表中 查询多个workfaceId
//    	System.out.println(zzWorkfaceService.workfaceIdListByOrgId(orgId,sourceTime,rateType));
    	
    	try {
			return zzWorkfaceService.workfaceIdListByOrgId(orgId,sourceTime,rateType);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
    
    
    /**
     * 自动跟机率模块  查询平均值
     * orgPid ==>传入的是集团的Org_Id
     * startTime 分析的数据起始时间
     * endTime 分析数据的结束时间
     * 
     */
    @RequestMapping("/getMineAutoRunningRate")
    public SysResult getMineAutoRunningRate(@RequestParam("orgPid")String orgPid,
                                         @RequestParam("startTime")long startTime,
    									 @RequestParam("endTime")long endTime){
    	
    	try {
			List<String> orgIdList=(List<String>) zzOrgService.selectOrgIdsByOrgId(orgPid).getData();
			String orgIds=ListToStringUtil.listToString(orgIdList, ',');
			return zzWorkfaceService.selectReteValueSecond(orgIds,startTime,endTime,"1");
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(),null);
		}
    }
    /**
     * 记忆截割率模块查询平均值
     * orgPid ==>传入的是集团的Org_Id
     * startTime 分析的数据起始时间
     * endTime 分析数据的结束时间
     */
    @RequestMapping("/getMineMemoryCuttingRate")
    public SysResult getMineMemoryCuttingRate(@RequestParam("orgPid")String orgPid,                                      
                                         @RequestParam("startTime")long startTime,
                                         @RequestParam("endTime")long endTime){
    	
    	try {
			List<String> orgIdList=(List<String>) zzOrgService.selectOrgIdsByOrgId(orgPid).getData();
			String orgIds=ListToStringUtil.listToString(orgIdList, ',');
			return zzWorkfaceService.selectReteValueSecond(orgIds,startTime,endTime,"2");
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult
	    			(ErrorUtil.CODE5000, e.getMessage(),null);
		}
    }
    
    /**
     *  乳化液自动配比合格率模块查询平均值
     * orgPid ==>传入的是集团的Org_Id
     * startTime 分析的数据起始时间
     * endTime 分析数据的结束时间
     */
    @RequestMapping("/getEmulsionConcentrationCuttingRate")
    public SysResult getEmulsionConcentrationCuttingRate(@RequestParam("orgPid")String orgPid,
                                         @RequestParam("startTime")long startTime,
                                         @RequestParam("endTime")long endTime){
    	try {
			List<String> orgIdList=(List<String>) zzOrgService.selectOrgIdsByOrgId(orgPid).getData();
			String orgIds=ListToStringUtil.listToString(orgIdList, ',');
			return zzWorkfaceService.selectReteValueSecond(orgIds,startTime,endTime,"3");
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult
	    			(ErrorUtil.CODE5000, e.getMessage(),null);
		}
    }
    /**
     * 故障率模块查询平均值
     * orgPid ==>传入的是集团的Org_Id
     * startTime 分析的数据起始时间
     * endTime 分析数据的结束时间
     */
    @RequestMapping("/getDeviceFaultRate")
    public SysResult getDeviceFaultRate(@RequestParam("orgPid")String orgPid,
                                         @RequestParam("startTime")long startTime,
                                         @RequestParam("endTime")long endTime){
    	try {
			List<String> orgIdList=(List<String>) zzOrgService.selectOrgIdsByOrgId(orgPid).getData();
			String orgIds=ListToStringUtil.listToString(orgIdList, ',');
			return zzWorkfaceService.selectReteValueSecond(orgIds,startTime,endTime,"4");
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult
	    			(ErrorUtil.CODE5000, e.getMessage(),null);
		}
    }
    
    
    /**
     * orgPid ==>传入的是集团的Org_Id
     * 自动跟机率模块  查询平均值
     * startTime 分析的数据时间
     */
    @RequestMapping("/getMineAutoRunningRateSecond")
    public SysResult getMineAutoRunningRateSecond(@RequestParam("orgPid")String orgPid,
                                         @RequestParam("cycle") String cycle,
                                         @RequestParam("startTime")long startTime){
    	
    	try {
    		List<String> orgIdList=(List<String>) zzOrgService.selectOrgIdsByOrgId(orgPid).getData();
    		String orgIds=ListToStringUtil.listToString(orgIdList, ',');
    		System.out.println(orgIds+"     orgIds");
    		
    		return zzWorkfaceService.selectReteValue(orgIds,cycle,startTime,"1");
//			return zzWorkfaceService.select(orgIds,cycle,startTime,"1");
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(),null);
		}
    }
    
    /**
     * 记忆截割率模块查询平均值
     * @param orgIds
     * @param cycle
     * @param startTime
     * @return
     */
    @RequestMapping("/getMineMemoryCuttingRateSecond")
    public SysResult getMineMemoryCuttingRateSecond(@RequestParam("orgPid")String orgPid,
                                         @RequestParam("cycle") String cycle,
                                         @RequestParam("startTime")long startTime){
    	
    	try {
    		List<String> orgIdList=(List<String>) zzOrgService.selectOrgIdsByOrgId(orgPid).getData();
    		for(String ss:orgIdList){
    			System.out.println(ss+"      ss");
    		}
    		String orgIds=ListToStringUtil.listToString(orgIdList, ',');
    		return zzWorkfaceService.selectReteValue(orgIds,cycle,startTime,"2");
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult
	    			(ErrorUtil.CODE5000, e.getMessage(),null);
		}
    }
    
    /**
     *  乳化液自动配比合格率模块查询平均值
     * @param orgIds
     * @param cycle
     * @param startTime
     * @return
     */
    @RequestMapping("/getEmulsionConcentrationCuttingRateSecond")
    public SysResult getEmulsionConcentrationCuttingRateSecond(@RequestParam("orgPid")String orgPid,
                                         @RequestParam("cycle") String cycle,
                                         @RequestParam("startTime")long startTime){
    	
    	try {
    		List<String> orgIdList=(List<String>) zzOrgService.selectOrgIdsByOrgId(orgPid).getData();
    		String orgIds=ListToStringUtil.listToString(orgIdList, ',');
    		return zzWorkfaceService.selectReteValue(orgIds,cycle,startTime,"3");
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult
	    			(ErrorUtil.CODE5000, e.getMessage(),null);
		}
    }
    
    /**
     * 故障率模块查询平均值
     * @param orgIds
     * @param cycle
     * @param startTime
     * @return
     */
    @RequestMapping("/getDeviceFaultRateSecond")
    public SysResult getDeviceFaultRateSecond(@RequestParam("orgPid")String orgPid,
                                         @RequestParam("cycle") String cycle,
                                         @RequestParam("startTime")long startTime){
    	
    	try {
    		List<String> orgIdList=(List<String>) zzOrgService.selectOrgIdsByOrgId(orgPid).getData();
    		String orgIds=ListToStringUtil.listToString(orgIdList, ',');
    		return zzWorkfaceService.selectReteValue(orgIds,cycle,startTime,"4");
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult
	    			(ErrorUtil.CODE5000, e.getMessage(),null);
		}
    }
    //测试用
    @RequestMapping("/selectAll")
    public SysResult selectAll(){
    	return new SysResult
    			(ErrorUtil.CODE5000, null,zzCoalMiningRateService.selectAll());
    }
}
