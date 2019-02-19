package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.pojo.HistoryDO;
import com.example.mybatisdemo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private MyService service;

    @RequestMapping("/search/{database}/{table}")
    public String select(@PathVariable("database") String database,
                         @PathVariable("table") String table){
        return service.searchAll(database,table);
    }
//
//    @RequestMapping("/select/tables/{database}")
//    public String[] showtables(@PathVariable("database") String database){
//        return service.selectTables(database);
//    }

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
