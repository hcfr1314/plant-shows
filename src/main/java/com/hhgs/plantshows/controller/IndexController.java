package com.hhgs.plantshows.controller;

import com.hhgs.plantshows.model.DO.IndexData;
import com.hhgs.plantshows.model.DO.NetAndPlanPower;
import com.hhgs.plantshows.model.DO.OverviewStationInfo;
import com.hhgs.plantshows.service.FixedService;
import com.hhgs.plantshows.service.ParametarShowsService;
import com.hhgs.plantshows.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
@Api("首页数据")
@CrossOrigin
public class IndexController {

    @Autowired
    private FixedService fixedService;

    @Autowired
    private ParametarShowsService parametarShowsService;

    @GetMapping("/fixed")
    @ApiOperation("首页固定数据查询")
    public BaseResponse getFixed() {
        try {
            OverviewStationInfo overviewStationInfo = fixedService.queryOverview();
            return BaseResponse.initSuccessBaseResponse(overviewStationInfo);
        } catch (Exception e) {
            return BaseResponse.initError(null, "查询数据出错");
        }
    }

    @GetMapping("/power")
    @ApiOperation("获取集团月累计上网电量，月累计发电量，日发电量，日上网电量")
    public BaseResponse getPower() {
        try {
            IndexData result = parametarShowsService.getTotalPower();
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, "查询数据出错");
        }
    }

    @GetMapping("/realdata")
    @ApiOperation("首页实时数据")
    public BaseResponse getRealTime() {
        try {
            Map<String, Object> result = parametarShowsService.getRealTime();
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @GetMapping("/monthPw")
    @ApiOperation("本月发电量")
    public BaseResponse monthPw() {
        try {
            List<IndexData> result = parametarShowsService.monthPw();
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, "查询数据出错");
        }
    }

    @GetMapping("/envir")
    @ApiOperation("环境保护指标,运营指标计算")
    public BaseResponse getEnvirAndOption(){
        try {
            Map<String,Object> result = parametarShowsService.getEnvirAndOption();
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, "查询数据出错"+e.getMessage());
        }
    }

    @GetMapping("/monthNetAndPlanPower")
    @ApiOperation("当年黄河公司月计划发电量和月上网电量")
    public BaseResponse monthNetAndPlanPower(){
        try {
            List<NetAndPlanPower> result = parametarShowsService.monthNetAndPlanPower();
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, "查询数据出错");
        }
    }

}
