package com.hhgs.plantshows.service.impl;

import com.hhgs.plantshows.mapper.avatar.BcsMapper;
import com.hhgs.plantshows.mapper.hh.PlantShowsMapper;
import com.hhgs.plantshows.model.DO.Bcs;
import com.hhgs.plantshows.service.SaveDataFromHbaseToCSV;
import com.hhgs.plantshows.util.DateUtil;
import com.hhgs.plantshows.util.FileUtil;
import com.hhgs.plantshows.util.HbaseUtil;
import com.hhgs.plantshows.util.ZIPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class SaveDataFromHbaseToCSVImpl implements SaveDataFromHbaseToCSV {


    @Autowired
    private PlantShowsMapper plantShowsMapper;

    @Autowired
    private BcsMapper bcsMapper;

    @Value("${file.storage.path.download}")
    private String file_storage_path_downLoad;

    @Override
    public void saveDataToCSV(String plantName, List<String> orgIds, String startTime, String endTime) {
        long startDate = DateUtil.getTime(startTime);
        long endDate = DateUtil.getTime(endTime);

        String hTableName = plantShowsMapper.getHbaseTableWithPlantName(plantName);

        List<Bcs> bcsList = bcsMapper.queryBcsByOrgId(orgIds);

        String dataFilePath = System.getProperty("user.dir") + File.separator + file_storage_path_downLoad + File.separator + plantName + File.separator;
        File freFile = new File(dataFilePath);
        if(freFile.exists()) {
            FileUtil.deleteDirectory(freFile);
        }

        File file = FileUtil.createCsv(dataFilePath);



        bcsList.forEach(bcs -> {

            //文件存放路径
            //String dataFilePath = System.getProperty("user.dir") + File.separator + file_storage_path_downLoad + File.separator + plantName + File.separator + bcs.getOrgId() + File.separator;

            //删除之前的文件
            //FileUtil.deleteDirectory(new File(dataFilePath));

            //HbaseUtil.scanReportDataByRowkeyword(dataFilePath, hTableName, bcs, startDate, endDate);

            File returnFile = HbaseUtil.scanReportDataByRowkeyword(file, hTableName,bcs, startDate, endDate);
            File newFile = file;

            //判断文件大小
            if (returnFile.length() / 1024f / 1024f > 50) {
                newFile = FileUtil.createCsv(dataFilePath);
            }

        });

        //源文件路径
        String sourceFilePath = System.getProperty("user.dir") + File.separator + file_storage_path_downLoad + File.separator + plantName;


        //压缩路径
        String finalFilePath = System.getProperty("user.dir") + File.separator + file_storage_path_downLoad + File.separator + plantName + ".zip";
        //压缩文件
        ZIPUtil.compress(sourceFilePath, finalFilePath);
        //删除文件
        File sourceFile = new File(sourceFilePath);
        FileUtil.deleteDirectory(sourceFile);

    }
}
