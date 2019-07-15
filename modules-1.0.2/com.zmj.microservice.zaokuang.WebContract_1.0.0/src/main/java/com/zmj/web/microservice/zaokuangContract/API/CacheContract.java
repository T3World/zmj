package com.zmj.web.microservice.zaokuangContract.API;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hushixian
 * @date 2019-04-10 13:25
 */
@RequestMapping("/API/Cache")
public interface CacheContract {

    /**
     * 获取缓存数据
     * @param request
     * @param httpResponse
     * @return
     */
    @RequestMapping(value = "/getMQTTCacheData",method = RequestMethod.POST,consumes = "application/json")
    SysResult getMQTTCacheData(HttpServletRequest request, HttpServletResponse httpResponse);

    /**
     * 根据主题，获取煤机轨迹缓存
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getShearerPosCacheData",method = RequestMethod.POST,consumes = "application/json")
    SysResult getShearerPosCacheData(HttpServletRequest request, HttpServletResponse response);

    /**
     * 支架压力历史查询接口 throws CustomerException
     * @param request
     * @param httpResponse
     * @return
     */
    @RequestMapping(value = "/GetSingleSupportPressure",method = RequestMethod.POST,consumes = "application/json")
    SysResult GetSingleSupportPressure(HttpServletRequest request, HttpServletResponse httpResponse) ;

    /**
     * 煤机轨迹历史查询接口
     * @param request
     * @param httpResponse
     * @return
     */
    @RequestMapping(value = "/getCoalHistoryData",method = RequestMethod.POST,consumes = "application/json")
    SysResult getCoalHistoryData(HttpServletRequest request, HttpServletResponse httpResponse);



}
