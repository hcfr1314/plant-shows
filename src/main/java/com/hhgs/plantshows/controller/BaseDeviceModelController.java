package com.hhgs.plantshows.controller;


import com.github.pagehelper.PageHelper;
import com.hhgs.plantshows.model.BO.BaseDeviceModelAndAttr;
import com.hhgs.plantshows.model.DO.BaseDeviceModel;
import com.hhgs.plantshows.service.BasicDeviceModelService;
import com.hhgs.plantshows.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("设备模型")
@RequestMapping("/deviceModel")
@RestController
@CrossOrigin
public class BaseDeviceModelController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PlantController.class);

    @Autowired
    private BasicDeviceModelService basicDeviceModelService;

    @ApiOperation(notes = "添加设备", value = "添加设备")
    @PostMapping("/addDevice")
    //设备名称，场站类型
    public BaseResponse addAttrToDevice(@ApiParam(required = true, value = "baseDeviceModel") @RequestBody(required = true) BaseDeviceModel baseDeviceModel) {
        try {
            int i = basicDeviceModelService.addData(baseDeviceModel);

            if (i > 0) {
                return BaseResponse.initSuccessBaseResponse(i);
            }

            return BaseResponse.initError(i, baseDeviceModel.getDeviceName() + "的设备已经存在或者设备名称为空");

        } catch (Exception e) {
            logger.error("BaseDeviceModelController--addAttrToDevice--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @ApiOperation(notes = "编辑设备模型数据", value = "编辑设备模型数据")
    @PutMapping("/editData")
    public BaseResponse editData(@ApiParam(required = true, value = "baseDeviceModel") @RequestBody(required = true) BaseDeviceModel baseDeviceModel) {
        try {
            int i = basicDeviceModelService.editData(baseDeviceModel);
            return BaseResponse.initSuccessBaseResponse(baseDeviceModel);
        } catch (Exception e) {
            logger.error("BaseDeviceModelController--addAttrToDevice--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation(notes = "删除测点", value = "删除测点")
    @DeleteMapping("/del")
    public BaseResponse del(@ApiParam(required = true) @RequestParam(required = true) int deviceId,
                            @ApiParam(required = true) @RequestParam(required = true) int attrId) {
        BaseResponse response = new BaseResponse();
        try {
            int i = basicDeviceModelService.delPoint(deviceId, attrId);
            response.setMessage("删除了" + i + "条数据");
            response.setCode(200);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setCode(-1);
        }
        return response;
    }

    @ApiOperation(notes = "通过设备类型查询设备", value = "通过设备类型查询设备")
    @GetMapping("/queryByType")
    public BaseResponse queryByType(@ApiParam(required = true, value = "deviceType") @RequestParam int deviceType,
                                    @ApiParam("deviceName") @RequestParam(required = false) String deviceName,
                                    @ApiParam("pageNum") @RequestParam(defaultValue = "1") int pageNum,
                                    @ApiParam("pageSize") @RequestParam(defaultValue = "6") int pageSize) {
        BaseResponse response = new BaseResponse();
        try {
            Map<String, Object> result = basicDeviceModelService.queryByType(deviceType, deviceName, pageNum, pageSize);
            response.setCode(200);
            response.setData(result);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setCode(-1);
        }

        return response;

    }


    @ApiOperation(notes = "通过设备类型和名称删除设备", value = "通过设备类型和名称删除设备")
    @DeleteMapping("/device")
    public BaseResponse deleteDevcie(@ApiParam(required = true, value = "deviceName") @RequestParam String deviceName,
                                     @ApiParam(required = true, value = "type") @RequestParam int type) {
        try {
            int result = basicDeviceModelService.deleteDevcie(deviceName, type);
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }

    }


}
