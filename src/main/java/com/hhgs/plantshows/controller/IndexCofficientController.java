package com.hhgs.plantshows.controller;



import com.hhgs.plantshows.model.DO.IndexCofficient;
import com.hhgs.plantshows.service.IndexCofficientService;
import com.hhgs.plantshows.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Api("指标系数管理")
@RestController
@CrossOrigin
@RequestMapping("/indexManage")
public class IndexCofficientController extends  BaseController{

    private static Logger logger = LoggerFactory.getLogger(PlantController.class);

    @Autowired
    private IndexCofficientService indexCofficientService;

    @ApiOperation(notes = "查询指标系数信息", value = "查询指标系数信息")
    @GetMapping("queryIndexCofficient")
    public BaseResponse queryIndexCofficient() {
        try {
            IndexCofficient indexCofficient = indexCofficientService.queryIndexMessage();
            return BaseResponse.initSuccessBaseResponse(indexCofficient);
        } catch (Exception e) {
            logger.error("PlantController--queryIndexCofficient--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @PostMapping("/editIndexCoddicient")
    @ApiOperation("编辑指标系数数据")
    public BaseResponse editIndexCoddicient(@ApiParam(required = true, value = "indexCofficient") @RequestBody(required = true) IndexCofficient indexCofficient) {
        try {
            int i = indexCofficientService.editData(indexCofficient);
            return BaseResponse.initSuccessBaseResponse(i);
        } catch (Exception e) {
            logger.error("PlantController--editIndexCoddicient--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }

    }

}
