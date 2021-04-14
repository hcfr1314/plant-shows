package com.hhgs.plantshows.controller;

import com.hhgs.plantshows.service.SaveDataFromHbaseToCSV;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Api("下载hbase数据")
@RestController
@CrossOrigin
@RequestMapping("/downLoadHbaseData")
public class DownloadHbaseDataController {


    @Autowired
    private SaveDataFromHbaseToCSV saveDataFromHbaseToCSV;

    @Value("${file.storage.path.download}")
    private String file_storage_path_downLoad;


    @ApiOperation(value = "下载hbase数据",notes = "下载hbase数据")
    @GetMapping(value = "downloadData")
    public String download(
                           @ApiParam(name = "plantName",value = "plantName" ,required = true) @RequestParam String plantName,
                           @ApiParam(name = "orgIds",value = "orgIds" ,required = true)@RequestParam List<String> orgIds,
                           @ApiParam(name = "startTime",value = "开始时间",required = true) @RequestParam String startTime,
                           @ApiParam(name = "endTime",value = "结束时间",required = true) @RequestParam String endTime,
                           @ApiParam(required = false) HttpServletRequest request,
                           @ApiParam(required = false) HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }



        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        //String filePath = System.getProperty("user.dir") + File.separator + file_storage_path_downLoad + File.separator + plantName + File.separator + lcuId + File.separator;
        String downLoadPath = System.getProperty("user.dir") + File.separator + file_storage_path_downLoad + File.separator + plantName +".zip";
        saveDataFromHbaseToCSV.saveDataToCSV(plantName,orgIds,startTime,endTime);
        try {

            String newFileName = plantName+".zip";
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(newFileName,"UTF-8"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            OutputStream out = response.getOutputStream();
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {

                    e.printStackTrace();

                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
        }

        return null;
    }
}
