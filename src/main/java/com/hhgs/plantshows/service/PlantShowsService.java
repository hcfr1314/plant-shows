package com.hhgs.plantshows.service;

import com.github.pagehelper.PageInfo;
import com.hhgs.plantshows.model.DO.ImportData;
import com.hhgs.plantshows.model.DO.LcuEquipment;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PlantShowsService {

    HashMap<String, String> batchInsert(List<ImportData> result, String plantName, String plantType, HttpServletRequest request);

    PageInfo<ImportData> selectDataByCondition(String plantTable, ImportData importData, int pageSize, int pageNum);

    public PageInfo<ImportData> getAllDataWithPlantTableAndDescript(String plantTable, ImportData importData, int pageSize, int pageNum);

    List<LcuEquipment> getLcuAndEquipmentByPlantCode(int plantCode);
}
