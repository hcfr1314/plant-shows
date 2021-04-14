package com.hhgs.plantshows.controller;

import com.hhgs.plantshows.model.DO.*;
import com.hhgs.plantshows.service.MultiLevelLinkageService;
import com.hhgs.plantshows.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("多级条件查询参数列表")
@RestController
@RequestMapping("/MultiLevel")
@CrossOrigin
public class MultiLevelLinkageController {



    private static Logger logger = LoggerFactory.getLogger(MultiLevelLinkageController.class);

    @Autowired
    private MultiLevelLinkageService multiLevelLinkageService;

    @ApiOperation(value = "获取大系统列表",notes = "获取大系统列表")
    @GetMapping("getBigsysList")
    public BaseResponse getBigsysList(@ApiParam(required = true,value = "plantId") @RequestParam(required = true) int plantId) {

        try {
            List<PlantBigsys> plantBigsysList = multiLevelLinkageService.queryBigsysList(plantId);
            return BaseResponse.initSuccessBaseResponse(plantBigsysList);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--getBigsysList--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "获取小系统列表",notes = "获取小系统列表")
    @GetMapping("getSmallsysList")
    public BaseResponse getSmallsysList(@ApiParam(required = true,value = "bigsysId") @RequestParam(required = true) int bigsysId) {

        try {
            List<BigsysSmallsys> smallsysList = multiLevelLinkageService.querySmallsysList(bigsysId);
            return BaseResponse.initSuccessBaseResponse(smallsysList);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--getSmallsysList--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "获取一级设备列表",notes = "获取一级设备列表")
    @GetMapping("getFirstsDeviceList")
    public BaseResponse getFirstsDeviceList(@ApiParam(required = true,value = "smallsysId") @RequestParam(required = true) int smallsysId) {

        try {
            List<SmallsysFirstsDevice> firstsDeviceList = multiLevelLinkageService.queryFirstsDeviceList(smallsysId);
            return BaseResponse.initSuccessBaseResponse(firstsDeviceList);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--getFirstsDeviceList--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "获取二级设备列表",notes = "获取二级设备列表")
    @GetMapping("getSecondsDeviceList")
    public BaseResponse getSecondsDeviceList(@ApiParam(required = true,value = "smallsysId") @RequestParam(required = true) int firstsDeviceId) {

        try {
            List<FirstsDeviceSecondsDevice> secondsDeviceList = multiLevelLinkageService.querySecondsDeviceList(firstsDeviceId);
            return BaseResponse.initSuccessBaseResponse(secondsDeviceList);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--getSecondsDeviceList--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "获取支架类型",notes = "获取支架类型")
    @GetMapping("getBracketType")
    public BaseResponse getBracketType(@ApiParam(required = true,value = "smallsysId") @RequestParam(required = true) int smallsysId) {

        try {
            String bracketType = multiLevelLinkageService.getBracketType(smallsysId);
            return BaseResponse.initSuccessBaseResponse(bracketType);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--getBracketType--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "获取组件类型",notes = "获取组件类型")
    @GetMapping("getComponentType")
    public BaseResponse getComponentType(@ApiParam(required = true,value = "smallsysId") @RequestParam(required = true) int smallsysId) {

        try {
            String componentType = multiLevelLinkageService.getComponentType(smallsysId);
            return BaseResponse.initSuccessBaseResponse(componentType);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--getComponentType--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "获取一级设备标识",notes = "获取一级设备标识")
    @GetMapping("queryFirstsDeviceSign")
    public BaseResponse queryFirstsDeviceSign(@ApiParam(required = true,value = "deviceId") @RequestParam(required = true) int deviceId) {

        try {
            String deviceSign = multiLevelLinkageService.queryDeviceSign(deviceId);
            return BaseResponse.initSuccessBaseResponse(deviceSign);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--queryFirstsDeviceSign--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "获取二级设备标识",notes = "获取二级设备标识")
    @GetMapping("querySecondsDeviceSign")
    public BaseResponse querySecondsDeviceSign(@ApiParam(required = true,value = "deviceId") @RequestParam(required = true) int deviceId) {

        try {
            String deviceSign = multiLevelLinkageService.queryDeviceSign(deviceId);
            return BaseResponse.initSuccessBaseResponse(deviceSign);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--querySecondsDeviceSign--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "查询某个大系统下的设备",notes = "查询某个大系统下的设备")
    @GetMapping("queryDeviceTypeByBigsysId")
    public BaseResponse queryDeviceTypeByBigsysId(@ApiParam(required = true,value = "bigSysId") @RequestParam(required = true) int bigSysId) {

        try {
            List<SmallsysFirstsDevice> deviceNameList = multiLevelLinkageService.queryDeviceTypeByBigsysId(bigSysId);
            return BaseResponse.initSuccessBaseResponse(deviceNameList);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--queryDeviceTypeByBigsysName--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "根据设备名称和大系统查询某类设备的设备标识",notes = "根据设备名称和大系统查询某类设备的设备标识")
    @GetMapping("queryDeviceSignByDeviceId")
    public BaseResponse queryDeviceSignByDeviceId(@ApiParam(required = true,value = "deviceId") @RequestParam(required = true) int deviceId) {
        try {
            List<String> deviceSignList = multiLevelLinkageService.queryDeviceSignByDeviceId(deviceId);
            return BaseResponse.initSuccessBaseResponse(deviceSignList);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--queryDeviceSignByDeviceName--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "根据设备标识查询设备模型",notes = "根据设备标识查询设备模型")
    @GetMapping("queryDeviceModeByDeviceSign")
    public BaseResponse queryDeviceModeByDeviceSign(@ApiParam(required = true,value = "deviceSignName") @RequestParam(required = true) String deviceSignName

    ) {

        try {
            List<String> enPointNameList = multiLevelLinkageService.queryDeviceModeByDeviceSign(deviceSignName);
            return BaseResponse.initSuccessBaseResponse(enPointNameList);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--queryDeviceModeByDeviceSign--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }


    @ApiOperation(value = "根据根据多个条件查询数据",notes = "根据根据多个条件查询数据")
    @GetMapping("queryDataByCondition")
    public BaseResponse queryDataByCondition(@ApiParam(required = true,value = "plantName") @RequestParam(required = true) String plantName,
                                               @ApiParam(required = true,value = "importData") ImportData importData

    ) {

        try {
            List<ImportData> deviceNameList = multiLevelLinkageService.queryDataByCondition(plantName,importData);
            return BaseResponse.initSuccessBaseResponse(deviceNameList);
        } catch (Exception e) {
            logger.error("MultiLevelLinkageController--queryDataByEnPointName--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }


}
