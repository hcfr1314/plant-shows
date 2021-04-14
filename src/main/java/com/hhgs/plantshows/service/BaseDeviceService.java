package com.hhgs.plantshows.service;

import com.hhgs.plantshows.model.UtilModel.BasicDevice;

import java.util.List;

public interface BaseDeviceService {

    void batchInsert(List<BasicDevice> list);


    void batchInsertData(String plantName);
}
