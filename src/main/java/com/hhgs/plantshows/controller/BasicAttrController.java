package com.hhgs.plantshows.controller;

import com.hhgs.plantshows.model.DO.BasicAttribute;
import com.hhgs.plantshows.model.UtilModel.BasicAttributeModel;
import com.hhgs.plantshows.service.BaseAttrOfPointService;
import com.hhgs.plantshows.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(" 基础属性")
@RestController
@CrossOrigin
@RequestMapping("/basicAttr")
public class BasicAttrController {

    @Autowired
    private BaseAttrOfPointService baseAttrOfPointService;

    @GetMapping("/all")
    @ApiOperation(notes = "根据类型查询所有属性", value = "1，光伏，2，风电")
    private BaseResponse queryAllByType(@ApiParam(required = true, value = "deviceType") @RequestParam(required = true) int deviceType) {
        try {
            List<BasicAttributeModel> attrs = baseAttrOfPointService.queryByType(deviceType);
            return BaseResponse.initSuccessBaseResponse(attrs);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @PostMapping("/addAttr")
    @ApiOperation("给某类设备挂点")
    public BaseResponse addAttrToDevice(@ApiParam(required = true, value = "设备名称") @RequestParam String deviceName,
                                        @ApiParam(required = true, value = "设备类型 1，光伏 2，风电") @RequestParam int deviceType,
                                        @ApiParam(required = true, value = "属性对象") @RequestBody BasicAttribute attr) {
        try {
            int i = baseAttrOfPointService.addAttrToDevice(deviceName, deviceType, attr);
            if (i == 1) {
                return BaseResponse.initSuccessBaseResponse(i);
            } else if (i == -1) {
                return BaseResponse.initError(-1, "没有该设备，添加设备");
            } else if (i == -2) {
                return BaseResponse.initError(-2, "属性中文名称或者英文名称重复");
            }else{
                return BaseResponse.initError(-3, "未知错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }

    }


}
