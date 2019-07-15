package com.zmj.web.microservice.zaokuangContract.SysBase;

import com.zmj.web.microservice.zaokuangContract.Vo.SysResult;
import com.zmj.web.microservice.zaokuangContract.entity.WorkfaceAndConfig;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hushixian
 * @date 2019-04-09 17:22
 */
@RequestMapping("/SysBase/workface")
public interface WorkfaceContract {

    /**
     * 新增工作面
     * @param wac
     * @return
     */
    @RequestMapping(value = "/addZZWorkface", method = RequestMethod.POST,consumes = "application/json")
    SysResult addZZWorkface(@RequestBody WorkfaceAndConfig wac);

    /**
     * 根据参数，查询工作面的列表，分页查询
     * @param orgId
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/listWorkface", method = RequestMethod.POST,consumes = "application/json")
    SysResult listWorkface(@RequestParam(name="orgId",required=false) String orgId,
                           @RequestParam(name="keyword",required=false) String keyword,
                           @RequestParam(name="pageNo",required=false) Integer pageNo,
                           @RequestParam(name="pageSize",required=false) Integer pageSize);


    /**
     * 修改工作面
     * @param wac
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST,consumes = "application/json")
    SysResult updateWorkface(@RequestBody WorkfaceAndConfig wac);

    /**
     * 删除工作面
     * @param workfaceId
     * @return
     */
    @RequestMapping(value = "/deleteWorkface", method = RequestMethod.POST,consumes = "application/json")
    SysResult deleteWorkface(@RequestParam(name="workfaceId",required=true)String workfaceId);

    /**
     * 启停工作面，改变状态
     * @param workfaceId
     * @param workfaceState
     * @return
     */
    @RequestMapping(value = "/stopWorkface", method = RequestMethod.POST,consumes = "application/json")
    SysResult stopWorkface(@RequestParam(name="workfaceId",required=true) String workfaceId,
                           @RequestParam(name="workfaceState",required=true) Integer workfaceState);

}
