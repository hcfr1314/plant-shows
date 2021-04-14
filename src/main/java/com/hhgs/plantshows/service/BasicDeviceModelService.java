package com.hhgs.plantshows.service;

import com.hhgs.plantshows.model.BO.BaseDeviceModelAndAttr;
import com.hhgs.plantshows.model.DO.BaseDeviceModel;

import java.util.List;
import java.util.Map;

public interface BasicDeviceModelService {
    int addData(BaseDeviceModel baseDeviceModel);

    int editData(BaseDeviceModel baseDeviceModel);

    int delPoint(int deviceId, int attrId);

    Map<String,Object> queryByType(int deviceType, String deviceName, int pageNum, int pageSize);

    int deleteDevcie(String deviceName, int type);
}
