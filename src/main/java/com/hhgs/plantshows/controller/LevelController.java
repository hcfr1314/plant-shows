package com.hhgs.plantshows.controller;

import com.hhgs.plantshows.model.DO.BootsterStation;
import com.hhgs.plantshows.model.DO.CollectionStation;
import com.hhgs.plantshows.service.LevelService;
import com.hhgs.plantshows.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/level")
@Api("汇集站和升压站controller")
public class LevelController {

    private static Logger logger = LoggerFactory.getLogger(LevelController.class);

    @Autowired
    private LevelService levelService;

    @ApiOperation("查找场站名称升压站信息")
    @GetMapping("/main/system")
    public BaseResponse getMainSystem(@ApiParam(required = true) BootsterStation entity) {
        try {
            List<BootsterStation> result = levelService.queryMainSystemByStationName(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation("查找场站名称查询升压站开关柜信息")
    @GetMapping("/main/switch")
    public BaseResponse getMainSwitch(@ApiParam(required = true) BootsterStation entity){
        try {
            List<BootsterStation> result = levelService.getMainSwitch(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation("查找升压站母线信息")
    @GetMapping("/main/bus")
    public BaseResponse getBus(@ApiParam(required = true) BootsterStation entity){
        try {
            List<BootsterStation> result = levelService.getBus(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @ApiOperation("查找升压站线路开关柜信息")
    @GetMapping("/main/line/switch")
    public BaseResponse getLineSwitch(@ApiParam(required = true) BootsterStation entity){
        try {
            List<BootsterStation> result = levelService.getLineSwitch(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation("查找升压站下级设备信息")
    @GetMapping("/main/lower")
    public BaseResponse getLower(@ApiParam(required = true) BootsterStation entity){
        try {
            List<BootsterStation> result = levelService.getLower(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @ApiOperation("根据场站名称查找汇集站")
    @GetMapping("/collect/station")
    public BaseResponse getCollectionStation(@ApiParam(required = true) CollectionStation entity){
        try {
            List<CollectionStation> result = levelService.getCollectionStation(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation("根据汇集站找母线")
    @GetMapping("/collect/bus")
    public BaseResponse getCollectionBus(@ApiParam(required = true) CollectionStation entity){
        try {
            List<CollectionStation> result = levelService.getCollectionBus(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation("查找汇集站线路开关柜")
    @GetMapping("/collect/line")
    public BaseResponse getCollectionLineSwitch(@ApiParam(required = true) CollectionStation entity){
        try {
            List<CollectionStation> result = levelService.getCollectionLineSwitch(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation("查找汇集站连接设备")
    @GetMapping("/collect/connect")
    public BaseResponse getCollectionConnect(@ApiParam(required = true) CollectionStation entity){
        try {
            List<CollectionStation> result = levelService.getCollectionConnect(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage());
            return BaseResponse.initError(null, e.getMessage());
        }
    }








}
