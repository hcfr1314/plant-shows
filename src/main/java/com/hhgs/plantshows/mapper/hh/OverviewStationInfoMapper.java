package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.OverviewStationInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface OverviewStationInfoMapper {

    OverviewStationInfo queryOverview();

    void updateDays();
}
