package com.hhgs.plantshows.service;

import com.hhgs.plantshows.model.DO.*;

import java.util.List;

public interface MultiLevelLinkageService {

    List<PlantBigsys> queryBigsysList(int plantId);

    List<BigsysSmallsys> querySmallsysList(int bigsysId);

    List<SmallsysFirstsDevice> queryFirstsDeviceList(int smallsysId);

    List<FirstsDeviceSecondsDevice> querySecondsDeviceList(int firstsDeviceId);

    String queryDeviceSign(int deviceId);

    String getBracketType(int smallsysId);

    String getComponentType(int smallsysId);

    List<SmallsysFirstsDevice>  queryDeviceTypeByBigsysId(int bigsysId);

    List<String> queryDeviceSignByDeviceId(int deviceId);

    List<String> queryDeviceModeByDeviceSign (String deviceSignName);

    List<ImportData> queryDataByCondition (String plantName,ImportData importData);
}
