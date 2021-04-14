package com.hhgs.plantshows.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hhgs.plantshows.common.CacheConstant;
import com.hhgs.plantshows.common.MessageConstant;
import com.hhgs.plantshows.common.Pages;
import com.hhgs.plantshows.mapper.hh.PlantShowsMapper;
import com.hhgs.plantshows.mapper.hh.UitlMapper;
import com.hhgs.plantshows.model.DO.ImportData;
import com.hhgs.plantshows.model.DO.LcuEquipment;
import com.hhgs.plantshows.model.DO.PlantTable;
import com.hhgs.plantshows.service.BaseDeviceService;
import com.hhgs.plantshows.service.PlantShowsService;
import com.hhgs.plantshows.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Api("场站信息展示")
@RestController
@RequestMapping("/plant")
@CrossOrigin
public class PlantController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PlantController.class);

    @Autowired
    private PlantShowsMapper plantShowsMapper;

    @Autowired
    private PlantShowsService plantShowsService;

    @ApiOperation(notes = "根据场站名称查询场站数据", value = "根据场站名称查询场站数据")
    @PostMapping("getPlantData")
    public BaseResponse getPlantData(@ApiParam(required = true, value = "pageSize") @RequestParam(required = true, defaultValue = "10") int pageSize,
                                     @ApiParam(required = true, value = "pageNum") @RequestParam(required = true, defaultValue = "1") int pageNum,
                                     @ApiParam(required = true, value = "plantName") @RequestParam(required = true) String plantName,
                                     @ApiParam(required = true, value = "importData") @RequestBody(required = true) ImportData importData
    ) {
        try {
            String plantTable = plantShowsMapper.getPlantTableWithPlantName(plantName);
            //List<ImportData> allData = plantShowsMapper.getAllDataWithPlantTableAndCondition(plantTable,importData);
            PageInfo<ImportData> pageInfo = plantShowsService.selectDataByCondition(plantTable, importData, pageSize, pageNum);
            //Map<String, List<ImportData>> listMap = allData.stream().collect(Collectors.groupingBy(ImportData::getAreaName));
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
            logger.error("PlantController--getPlantData--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @ApiOperation(notes = "根据场测点描述称查询场站数据", value = "根据测点描述查询场站数据")
    @PostMapping("getPlantDataWithPointDescript")
    public BaseResponse getPlantDataWithPointDescript(@ApiParam(required = true, value = "pageSize") @RequestParam(required = true, defaultValue = "10") int pageSize,
                                                      @ApiParam(required = true, value = "pageNum") @RequestParam(required = true, defaultValue = "1") int pageNum,
                                                      @ApiParam(required = true, value = "plantName") @RequestParam(required = true) String plantName,
                                                      @ApiParam(required = true, value = "importData") @RequestBody(required = true) ImportData importData
    ) {
        try {
            String plantTable = plantShowsMapper.getPlantTableWithPlantName(plantName);
            //List<ImportData> allData = plantShowsMapper.getAllDataWithPlantTableAndCondition(plantTable,importData);
            PageInfo<ImportData> pageInfo = plantShowsService.getAllDataWithPlantTableAndDescript(plantTable, importData, pageSize, pageNum);
            //Map<String, List<ImportData>> listMap = allData.stream().collect(Collectors.groupingBy(ImportData::getAreaName));
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
            logger.error("PlantController--getPlantDataWithPonitDescript--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    /**
     * 经本机测试：一万五千数据导入时间为6.34s
     *
     * @param file 文件
     * @return CommonResponse
     */
    @CrossOrigin
    @ApiOperation(notes = "导入场站数据(Excel)", value = "导入场站数据(Excel)")
    @PostMapping(value = "importFileExcel")
    public BaseResponse importFile(@ApiParam(required = true, value = "plantName") @RequestParam(required = true) String plantName,
                                   @ApiParam(required = true, value = "plantType") @RequestParam(required = true) String plantType,
                                   @RequestParam("file") MultipartFile file,
                                   HttpServletRequest request) {

        String fileName = file.getOriginalFilename();
        String timestamp = File.separator + new Date().getTime() + File.separator;
        String base = System.getProperty("user.dir") + file_storage_path_upload + timestamp;
        try {

            File test = new File(base);
            if (!test.exists()) {
                test.mkdirs();
            }
            File dest = new File(base + fileName);
            file.transferTo(dest);

            /*ExcelUtil.setSheetSicze(dest);
            int size = ExcelUtil.size();
            System.out.println(size + "+++++++++++++++++++++++++++++++++++++++++++");
            int num = size % 2000 == 0 ? size / 2000 : (int) Math.floor(size / 2000 + 1);

            int batchNum = 0;


            for (int i = 0; i < num; i++) {
                int startIndex = i * 2000;
                int maxIndex = (i + 1) * 2000;
                int endIndex = maxIndex > size ? size : maxIndex;

                List<String[]> data = ExcelUtil.readStandardExcel(dest, 0, startIndex, endIndex);
                List<ImportData> result = new ArrayList<>();
                Date date = DateUtil.getCurrentDate();
                for (int j = 0; j < data.size(); j++) {
                    if (StringUtil.testStrIsNUll(data.get(j)[0])) {
                        continue;
                    }
                    result.add(createKksDicData(data.get(j), date));
                }

                batchNum += plantShowsService.batchInsert(result, plantName, plantType);

                result.clear();
            }*/

            List<String[]> data = ExcelUtil.readStandardExcel(dest, 0, 1, 0, 0, 20);
            List<ImportData> result = new ArrayList<>();
            Date date = DateUtil.getCurrentDate();
            for (int i = 0; i < data.size(); i++) {
                if (StringUtil.testStrIsNUll(data.get(i)[0])) {
                    continue;
                }
                result.add(createKksDicData(data.get(i), date));
            }

            HashMap<String, String> resultMap = plantShowsService.batchInsert(result, plantName, plantType, request);

            //插入完成，删除文件
            if (test.isDirectory()) {
                File[] files = test.listFiles();
                if (files.length > 0) {
                    for (File tmp : files) {
                        tmp.delete();
                    }
                }
            }
            //删除文件夹
            test.delete();


            return BaseResponse.initSuccessBaseResponse(resultMap);

        } catch (Exception e) {
            logger.error("PlantController--importFile--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    /**
     * @param file 文件
     * @return CommonResponse
     */
    @CrossOrigin
    @ApiOperation(notes = "导入场站数据(CSV)", value = "导入场站数据(CSV)")
    @PostMapping(value = "importCsvFile")
    public BaseResponse importCsvFile(@ApiParam(required = true, value = "plantName") @RequestParam(required = true) String plantName,
                                      @ApiParam(required = true, value = "plantType") @RequestParam(required = true) String plantType,
                                      @RequestParam("file") MultipartFile file,
                                      HttpServletRequest request) {

        String fileName = file.getOriginalFilename();
        String timestamp = File.separator + new Date().getTime() + File.separator;
        String base = System.getProperty("user.dir") + file_storage_path_upload + timestamp;
        try {

            File test = new File(base);
            if (!test.exists()) {
                test.mkdirs();
            }
            File dest = new File(base + fileName);
            //上传文件到服务器
            file.transferTo(dest);

            //读取csv文件
            List<ImportData> importDataList = CsvUtil.readCSV(dest);

            //批量插入数据
            HashMap<String, String> resultMap = plantShowsService.batchInsert(importDataList, plantName, plantType, request);

            //插入完成，删除文件
            if (test.isDirectory()) {
                File[] files = test.listFiles();
                if (files.length > 0) {
                    for (File tmp : files) {
                        tmp.delete();
                    }
                }
            }
            //删除文件夹
            test.delete();

            return BaseResponse.initSuccessBaseResponse(resultMap);

        } catch (Exception e) {
            logger.error("PlantController--importCsvFile--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    /**
     * 将excel文件中的内容封装到数组中
     *
     * @param array
     * @param date
     * @return
     * @throws Exception
     */
    private ImportData createKksDicData(String[] array, Date date) throws Exception {
        if (array.length < 7) {
            throw new Exception(MessageConstant.ARRAY_LENGTH_ANOMALY);
        }
        ImportData importData = new ImportData();

        importData.setBigsysName(array[0]);
        importData.setSmallsysName(array[1] = array[1].equals("NULL") ? null : array[1]);
        importData.setFirstsDeviceName(array[2] = array[2].equals("NULL") ? null : array[2]);
        importData.setSecondsDeviceName(array[3] = array[3].equals("NULL") ? null : array[3]);
        importData.setPartsName(array[4] = array[4].equals("NULL") ? null : array[4]);
        importData.setGlobalNumber(array[5] = array[5].equals("NULL") ? null : array[5]);
        importData.setPointName(array[6] = array[6].equals("NULL") ? null : array[6]);
        importData.setPointDescript(array[7] = array[7].equals("NULL") ? null : array[7]);
        importData.setFirstsDeviceSign(array[8] = array[8].equals("NULL") ? null : array[8]);
        importData.setSecondsDeviceSign(array[9] = array[9].equals("NULL") ? null : array[9]);
        importData.setBracketType(array[10] = array[10].equals("NULL") ? null : array[10]);
        importData.setComponentType(array[11] = array[11].equals("NULL") ? null : array[11]);
        importData.setOriginalPoint(array[12] = array[12].equals("NULL") ? null : array[12]);
        return importData;
    }

    @CrossOrigin
    @ApiOperation(notes = "获取场站列表", value = "获取场站列表")
    @GetMapping("/getAllPlantName")
    public BaseResponse getAllPlantName() {
        try {
            List<PlantTable> plantNames = plantShowsMapper.getAllPlantName();
            return BaseResponse.initSuccessBaseResponse(plantNames);
        } catch (Exception e) {
            logger.error("PlantController--getAllPlantName--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }



    @CrossOrigin
    @ApiOperation(notes = "根据场站编号获取lcu信息", value = "根据场站编号获取lcu信息")
    @GetMapping("/getLcuEquipmentByPlantCode")
    public BaseResponse getLcuEquipmentByPlantCode(@ApiParam(required = true, value = "plantCode") @RequestParam(required = true) int plantCode) {
        try {
            //获取场站表名称
            List<LcuEquipment> lcuEquipments = plantShowsService.getLcuAndEquipmentByPlantCode(plantCode);
            return BaseResponse.initSuccessBaseResponse(lcuEquipments);
        } catch (Exception e) {
            logger.error("PlantController--getLcuEquipmentByPlantCode--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @Autowired
    BaseDeviceService baseDeviceService;

    @Autowired
    UitlMapper uitlMapper;


    @CrossOrigin
    @ApiOperation(notes = "生成层级", value = "生成层级")
    @GetMapping("/generationHierarchyForData")
    public BaseResponse generationHierarchyForData(@ApiParam(required = true, value = "plantName") @RequestParam(required = true) String plantName) {
        try {

            //获取场站表名称
            String tableName = plantShowsMapper.getPlantTableWithPlantName(plantName);
            //获取场站id
            int plantId = plantShowsMapper.getPlantIdWithPlantName(plantName);

            //删除数据
            uitlMapper.deleteOriginalData(plantName);

            //给大系统表中导入数据
            uitlMapper.insertPlantBigSys(plantId, plantName, tableName);

            //分别给各个层级表批量插入数据
            baseDeviceService.batchInsertData(plantName);

            //更新数据
            uitlMapper.updateData(tableName);

            return BaseResponse.initSuccessBaseResponse("success");
        } catch (Exception e) {
            logger.error("PlantController--getAllPlantName--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @CrossOrigin
    @ApiOperation(notes = "测试", value = "测试")
    @GetMapping("/test")
    public BaseResponse test(@ApiParam(required = true, value = "plantName") @RequestParam(required = true) String plantName) {
        try {
            //获取场站表名称
            String tableName = plantShowsMapper.getPlantTableWithPlantName(plantName);
            uitlMapper.updateData(tableName);
            return BaseResponse.initSuccessBaseResponse("成功");
        } catch (Exception e) {
            logger.error("PlantController--getAllPlantName--e " + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @CrossOrigin
    @ApiOperation(notes = "进度条刷新", value = "进度条刷新")
    @GetMapping("/flishProgress")
    public String flishProgress() {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

       // HttpSession session = request.getSession().getSessionContext().getSession(sessionId);

        HttpSession session= CacheConstant.session;

        if(session == null) {
            resultMap.put("percentText",-1);
            return  JSON.toJSONString(resultMap);
        }
        try {

            resultMap.put("totalCount",session.getAttribute("totalCount"));
            resultMap.put("curCount", session.getAttribute("curCount"));
            resultMap.put("percent", session.getAttribute("percent"));
            //进度条百分比
            resultMap.put("percentText",session.getAttribute("percentText"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(resultMap);
    }
}
