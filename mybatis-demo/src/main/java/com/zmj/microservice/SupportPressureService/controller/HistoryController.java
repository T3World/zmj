package com.zmj.microservice.SupportPressureService.controller;

import com.zmj.microservice.SupportPressureService.pojo.DO.HistoryDO;
import com.zmj.microservice.SupportPressureService.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HistoryController {

    @Autowired
    private HistoryService service;

//    @RequestMapping(value = "/getSupportPressureData",method = RequestMethod.POST)
//    public SysResult getSupportPressureData(@RequestBody SupportPressureDTO sp){
//        service.getSupportPressureData(sp);
//    }

    @RequestMapping("/search/{database}/{table}")
    public String select(@PathVariable("database") String database,
                         @PathVariable("table") String table){
        return service.searchAll(database,table);
    }

    @RequestMapping("/test/set/{key}/{value}")
    public String testSet(@PathVariable("key") String key,@PathVariable("value") String value){
        HistoryDO historyDO = new HistoryDO();
        historyDO.setDataName(value);
        historyDO.setDataValue("value");
        return service.testSet(key,historyDO);
    }

    @RequestMapping("/test/get/{key}")
    public String test(@PathVariable("key") String key){
        return service.testGet(key);
    }
    @RequestMapping("/test/hget/{key}")
    public String testhget(@PathVariable("key") String key){
        return service.testGet(key);
    }


    @RequestMapping(value = "/select/coalCutterTrack",method = RequestMethod.GET)
    public List<HistoryDO> selectCoalCutterTrackHistory(@RequestParam("theme") String theme,
                                                 @RequestParam("startTime") String startTime,
                                                 @RequestParam("endTime") String endTime,
                                                 @RequestParam(value = "index",required = false) Integer n){
        List<HistoryDO> result;
        try {
            result = service.selectCoalCutterTrackHistory(theme, startTime, endTime, n);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return result;
    }

}
