package com.hhgs.plantshows.service;

import com.hhgs.plantshows.model.DO.BasicAttribute;
import com.hhgs.plantshows.model.UtilModel.BasicAttributeModel;

import java.util.List;

public interface BaseAttrOfPointService {

    List<BasicAttributeModel> queryByType(int i);

    int addAttrToDevice(String deviceName, int deviceType, BasicAttribute attr);
}
