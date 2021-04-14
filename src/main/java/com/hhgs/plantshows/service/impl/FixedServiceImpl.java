package com.hhgs.plantshows.service.impl;

import com.hhgs.plantshows.mapper.hh.OverviewStationInfoMapper;
import com.hhgs.plantshows.mapper.hh.ParametarShowsMapper;
import com.hhgs.plantshows.model.DO.OverviewStationInfo;
import com.hhgs.plantshows.model.DO.PlantInfo;
import com.hhgs.plantshows.service.FixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedServiceImpl implements FixedService {

    @Autowired
    private OverviewStationInfoMapper overviewStationInfoMapper;

    @Autowired
    private ParametarShowsMapper parametarShowsMapper;

    @Override
    public OverviewStationInfo queryOverview() {
        OverviewStationInfo overviewStationInfo = overviewStationInfoMapper.queryOverview();
        //查询装机容量
        List<PlantInfo> plantInfos = parametarShowsMapper.queryPlantInfo();
        double sum = plantInfos.stream().mapToDouble(PlantInfo::getInstallCapacity).sum();
        overviewStationInfo.setTotalInstallCap(sum);
        return overviewStationInfo;
    }
}
