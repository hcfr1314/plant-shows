package com.hhgs.plantshows.controller;


import com.github.pagehelper.PageInfo;
import com.hhgs.plantshows.common.Pages;
import com.hhgs.plantshows.model.BO.HbaseResult;
import com.hhgs.plantshows.model.BO.IndexDataAndCompute;
import com.hhgs.plantshows.model.DO.ComputeIndex;
import com.hhgs.plantshows.model.DO.IndexCofficient;
import com.hhgs.plantshows.model.DO.IndexData;
import com.hhgs.plantshows.model.DO.PlantInfo;
import com.hhgs.plantshows.service.IndicatorGraphService;
import com.hhgs.plantshows.service.ParametarShowsService;
import com.hhgs.plantshows.service.PlantInfoService;
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

@Api("peometar指标展示")
@CrossOrigin
@RestController
@RequestMapping("/prometarShows")
public class PrometarShowsController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PlantController.class);

    @Autowired
    private ParametarShowsService parametarShowsService;

    @Autowired
    private IndicatorGraphService indicatorGraphService;

    @Autowired
    private PlantInfoService plantInfoService;

    @ApiOperation(notes = "查询指标计算结果信息", value = "查询指标计算结果信息")
    @GetMapping("queryDataByPlantCode")
    public BaseResponse queryDataByPlantCode(@ApiParam(required = true, value = "plantCode") @RequestParam(required = true) int plantCode) {
        try {
            IndexDataAndCompute indexDataAndCompute = parametarShowsService.queryDataByPlantCode(plantCode);
            return BaseResponse.initSuccessBaseResponse(indexDataAndCompute);
        } catch (Exception e) {
            logger.error("PlantController--queryDataByPlantCode--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation(notes = "查询场站基本信息", value = "查询场站基本信息")
    @GetMapping("queryPlantInfoByPlantCode")
    public BaseResponse queryPlantInfoByPlantCode(@ApiParam(required = false, value = "plantCode") @RequestParam(required = false) Integer plantCode,
                                                  @ApiParam(required = true, value = "pageSize") @RequestParam(required = true, defaultValue = "10") int pageSize,
                                                  @ApiParam(required = true, value = "pageNum") @RequestParam(required = true, defaultValue = "1") int pageNum) {

        try {
            PageInfo<PlantInfo> pageInfo = plantInfoService.queryPlantInfoByPlantCode(plantCode,pageSize,pageNum);
            if (pageInfo.getList() == null) {
                return BaseResponse.initError(null, "查寻结果为空");
            } else {
                Pages pages = new Pages();
                pages.setPageCount(pageInfo.getPages());
                pages.setDataCount((int) pageInfo.getTotal());
                pages.setPageNum(pageInfo.getPageNum());
                pages.setPageSize(pageInfo.getPageSize());
                pages.setDataList(pageInfo.getList());
                return BaseResponse.initSuccessBaseResponse(pages);
            }
        } catch (Exception e) {
            logger.error("PlantController--queryPlantInfoByPlantCode--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation(notes = "编辑场站基本信息", value = "编辑场站基本信息")
    @PostMapping("editData")
    public BaseResponse editData(@ApiParam(required = true, value = "plantInfo") @RequestBody(required = true) PlantInfo plantInfo) {
        try {
            int i = plantInfoService.editData(plantInfo);
            return BaseResponse.initSuccessBaseResponse(i);
        } catch (Exception e) {
            logger.error("PlantController--editData--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation(notes = "新增场站基本信息", value = "新增场站基本信息")
    @PostMapping("addData")
    public BaseResponse addData(@ApiParam(required = true, value = "plantInfo") @RequestBody(required = true) PlantInfo plantInfo) {
        try {
            int i = plantInfoService.addData(plantInfo);
            return BaseResponse.initSuccessBaseResponse(i);
        } catch (Exception e) {
            logger.error("PlantController--addData--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation(notes = "删除场站基本信息", value = "删除场站基本信息")
    @DeleteMapping("deleteById")
    public BaseResponse deleteById(@ApiParam(required = true, value = "id") @RequestParam(required = true) int id) {
        try {
            int i = plantInfoService.deleteById(id);
            return BaseResponse.initSuccessBaseResponse(i);
        } catch (Exception e) {
            logger.error("PlantController--deleteById--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @ApiOperation(notes = "查询指标图形数据", value = "查询指标图形数据")
    @GetMapping("queryIndicatorGraphData")
    public BaseResponse queryIndicatorGraphData(@ApiParam(required = true, value = "currenetData") @RequestParam(required = true) String currenetData,
                                                @ApiParam(required = true, value = "plantCode") @RequestParam(required = true) int plantCode) {
        try {
            Map<String, HbaseResult> stringHbaseResultMap = indicatorGraphService.queryIndicatorGraphData(currenetData, plantCode);
            return BaseResponse.initSuccessBaseResponse(stringHbaseResultMap);
        } catch (Exception e) {
            logger.error("PlantController--queryIndicatorGraphData--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation(notes = "查询指标图形数据(当日/月累计上网电量)", value = "查询指标图形数据(当日/月累计上网电量)")
    @GetMapping("queryMonthAndCurrentAccOnGridEnergy")
    public BaseResponse queryMonthAndCurrentAccOnGridEnergy(@ApiParam(required = true, value = "data") @RequestParam(required = true) String data,
                                                @ApiParam(required = true, value = "plantCode") @RequestParam(required = true) int plantCode) {
        try {
            List<IndexData> indexDataList = parametarShowsService.queryMonthAccOnGridEnergy(plantCode, data);
            return BaseResponse.initSuccessBaseResponse(indexDataList);
        } catch (Exception e) {
            logger.error("PlantController--queryMonthAndCurrentAccOnGridEnergy--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @ApiOperation(notes = "查询指标图形数据（当日/月收益）", value = "查询指标图形数据（当日/月收益）")
    @GetMapping("queryComputeIndex")
    public BaseResponse queryComputeIndex(@ApiParam(required = true, value = "data") @RequestParam(required = true) String data,
                                                  @ApiParam(required = true, value = "plantCode") @RequestParam(required = true) int plantCode) {
        try {
            List<ComputeIndex> computeIndexList = parametarShowsService.queryComputeIndex(plantCode, data);
            return BaseResponse.initSuccessBaseResponse(computeIndexList);
        } catch (Exception e) {
            logger.error("PlantController--queryComputeIndex--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

}
