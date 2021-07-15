package cn.edu.nju.FindIt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.nju.FindIt.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"获取物品及用户统计数据接口"})
@RequestMapping("/api/data")
@RestController
public class DataController {
    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService){
        this.dataService = dataService;
    }

    @ApiOperation(value = "获取当日数据", notes = "")
    @GetMapping("")
    public ResponseEntity<List<Map<String, String>>> todayData(){
        return dataService.todayData();
    }
}
