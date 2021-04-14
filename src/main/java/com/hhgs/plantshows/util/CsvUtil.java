package com.hhgs.plantshows.util;

import com.hhgs.plantshows.model.DO.ImportData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    /**
     * 读取csv并处理数据
     * @param file
     * @throws Exception
     */
    public static List<ImportData> readCSV(File file) throws Exception {
        List<ImportData> importDataList  = new ArrayList<>();
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
            String line = "";
            //忽略第一行标题
            for (int i = 0; i < 1; i++) {
                line = bReader.readLine();
            }
            while((line = bReader.readLine()) != null){
                if (line.trim() != "") {
                    //分割开来的即是对应的每个单元格，注意空的情况
                    String[] result = line.split(",");
                    ImportData importData = new ImportData();

                    importData.setBigsysName(result[0]);
                    importData.setSmallsysName(result[1] = result[1].equals("NULL") ? null : result[1]);
                    importData.setFirstsDeviceName(result[2] = result[2].equals("NULL") ? null : result[2]);
                    importData.setSecondsDeviceName(result[3] = result[3].equals("NULL") ? null : result[3]);
                    importData.setPartsName(result[4] = result[4].equals("NULL") ? null : result[4]);
                    importData.setGlobalNumber(result[5] = result[5].equals("NULL") ? null : result[5]);
                    importData.setPointName(result[6] = result[6].equals("NULL") ? null : result[6]);
                    importData.setPointDescript(result[7] = result[7].equals("NULL") ? null : result[7]);
                    importData.setFirstsDeviceSign(result[8] = result[8].equals("NULL") ? null : result[8]);
                    importData.setSecondsDeviceSign(result[9] = result[9].equals("NULL") ? null : result[9]);
                    importData.setBracketType(result[10] = result[10].equals("NULL") ? null : result[10]);
                    importData.setComponentType(result[11] = result[11].equals("NULL") ? null : result[11]);
                    importData.setOriginalPoint(result[12] = result[12].equals("NULL") ? null : result[12]);

                    importDataList.add(importData);
                }
            }

            return importDataList;
        }
    finally {
        if (bReader != null) {
            bReader.close();
        }
    }
}

}
