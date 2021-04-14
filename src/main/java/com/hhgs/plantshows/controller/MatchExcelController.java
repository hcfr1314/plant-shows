package com.hhgs.plantshows.controller;

import com.hhgs.plantshows.model.UtilModel.BasicAttributeModel;
import com.hhgs.plantshows.model.UtilModel.BasicDevice;
import com.hhgs.plantshows.service.BaseAttrOfPointService;
import com.hhgs.plantshows.service.BaseDeviceService;
import com.hhgs.plantshows.util.BaseResponse;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/excel")
public class MatchExcelController extends BaseController {

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    @Autowired
    private BaseAttrOfPointService baseAttrService;


    @Autowired
    private BaseDeviceService baseDeviceService;

    @PostMapping("/parse")
    public BaseResponse parse(@ApiParam(required = true, value = "plantType") @RequestParam(required = true) int plantType,
                      @RequestParam("file") MultipartFile file,
                      HttpServletRequest request) {
        try {
            String fileName = file.getOriginalFilename();
            String timestamp = File.separator + new Date().getTime() + File.separator;
            String base = System.getProperty("user.dir") + file_storage_path_upload + timestamp;
            File test = new File(base);
            if (!test.exists()) {
                test.mkdirs();
            }
            File dest = new File(base + fileName);

            file.transferTo(dest);

            //File file = new File(System.getProperty("user.dir") + "/upload/光伏设备参数1022.xlsx");
            //int type=1;

            Workbook workbook = getWorkBook(dest);

            if (workbook != null) {

                List<BasicAttributeModel> attres = baseAttrService.queryByType(plantType);

                int sheetSize = workbook.getNumberOfSheets();
                for (int i = 0; i < 1; i++) {

                    List<BasicDevice> result = new ArrayList<>();

                    Sheet sheet = workbook.getSheetAt(i);

                    //获取每行
                    int rows = sheet.getLastRowNum();
                    if (rows == 0) {
                        return BaseResponse.initError(null,"没有数据");
                    }

                    for (int j = 1; j <= rows; j++) {

                        Row row = sheet.getRow(j);

                        //获取设备名称
                        String deviceName = row.getCell(0).getStringCellValue();
                        String eigenValue = row.getCell(3).getStringCellValue();
                        String lowerLimit = row.getCell(4).getStringCellValue();
                        String upperLimit = row.getCell(5).getStringCellValue();
                        String pointType = row.getCell(8).getStringCellValue();

                        //获取每行第三个单元格（属性英文名称）
                        String val = row.getCell(2).getStringCellValue();

                        System.out.println(val);

                        //查询然后匹配
                        List<BasicAttributeModel> models = attres.stream().filter(ele -> val.equals(ele.getAttributeNameEN())).collect(Collectors.toList());

                        if (models == null && models.size() <= 0) {
                            System.out.println(deviceName + "的" + val + " 属性匹配失败");
                            continue;
                        }

                        if (models != null && models.size() > 0) {

                            System.out.println(models.size() + "***************************************************************************************");

                            BasicAttributeModel attr = models.get(0);

                            if (attr == null) {
                                System.out.println(deviceName + "的" + val + " 属性匹配失败");
                                continue;
                            }
                            BasicDevice device = new BasicDevice();
                            device.setDeviceName(deviceName);
                            device.setDeviceType(plantType);
                            device.setEffective(1);
                            device.setEigenValue(eigenValue);
                            device.setLowerLimit(lowerLimit);
                            device.setUpperLimit(upperLimit);
                            device.setAttr_id(attr.getId());
                            device.setPointType(pointType);
                            result.add(device);
                        }
                    }
                    baseDeviceService.batchInsert(result);
                }
            }
            return BaseResponse.initSuccessBaseResponse("设备模型导入成功");
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }

    }


    @GetMapping("/parseInfo")
    public void parseInfo(String[] args) {
        File file = new File(System.getProperty("user.dir") + "/upload/光伏设备参数1022.xlsx");
        int type = 1;

        Workbook workbook = getWorkBook(file);

        if (workbook != null) {

            List<BasicAttributeModel> attres = baseAttrService.queryByType(type);

            int sheetSize = workbook.getNumberOfSheets();
            for (int i = 0; i < 1; i++) {

                List<BasicDevice> result = new ArrayList<>();

                Sheet sheet = workbook.getSheetAt(i);

                //获取每行
                int rows = sheet.getLastRowNum();
                if (rows == 0) {
                    return;
                }

                for (int j = 1; j <= rows; j++) {

                    Row row = sheet.getRow(j);

                    //获取设备名称
                    String deviceName = row.getCell(0).getStringCellValue();
                    String eigenValue = row.getCell(3).getStringCellValue();
                    String lowerLimit = row.getCell(4).getStringCellValue();
                    String upperLimit = row.getCell(5).getStringCellValue();
                    String pointType = row.getCell(8).getStringCellValue();

                    //获取每行第三个单元格（属性英文名称）
                    String val = row.getCell(2).getStringCellValue();

                    System.out.println(val);

                    //查询然后匹配
                    List<BasicAttributeModel> models = attres.stream().filter(ele -> val.equals(ele.getAttributeNameEN())).collect(Collectors.toList());

                    if (models == null && models.size() <= 0) {
                        System.out.println(deviceName + "的" + val + " 属性匹配失败");
                        continue;
                    }

                    if (models != null && models.size() > 0) {

                        System.out.println(models.size() + "***************************************************************************************");

                        BasicAttributeModel attr = models.get(0);

                        if (attr == null) {
                            System.out.println(deviceName + "的" + val + " 属性匹配失败");
                            continue;
                        }
                        BasicDevice device = new BasicDevice();
                        device.setDeviceName(deviceName);
                        device.setDeviceType(type);
                        device.setEffective(1);
                        device.setEigenValue(eigenValue);
                        device.setLowerLimit(lowerLimit);
                        device.setUpperLimit(upperLimit);
                        device.setAttr_id(attr.getId());
                        device.setPointType(pointType);
                        result.add(device);
                    }
                }
                baseDeviceService.batchInsert(result);
            }
        }

    }

    private Workbook getWorkBook(File file) {

        String fileName = file.getName();
        Workbook workbook = null;
        try (InputStream is = new FileInputStream(file)) {
            if (fileName.endsWith(XLS)) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(XLSX)) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    @GetMapping("/batchInsertData")
    BaseResponse batchData(@ApiParam(required = true, value = "plantName") @RequestParam(required = true) String plantName) {

        try {
            baseDeviceService.batchInsertData(plantName);
            return BaseResponse.initSuccessBaseResponse("");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }
}
