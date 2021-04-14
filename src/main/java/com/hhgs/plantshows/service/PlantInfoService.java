package com.hhgs.plantshows.service;

import com.github.pagehelper.PageInfo;
import com.hhgs.plantshows.model.DO.PlantInfo;

import java.util.List;

public interface PlantInfoService {

    PageInfo<PlantInfo> queryPlantInfoByPlantCode (Integer plantCode, int pageSize, int pageNum);

    int addData (PlantInfo plantInfo);

    int editData(PlantInfo plantInfo);

    int deleteById(int id);

}
