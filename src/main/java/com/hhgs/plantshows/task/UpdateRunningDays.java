package com.hhgs.plantshows.task;

import com.hhgs.plantshows.mapper.hh.OverviewStationInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 安全运行天数更新
 */
@Component
public class UpdateRunningDays {

    @Autowired
    private OverviewStationInfoMapper overviewStationInfoMapper;

    @Scheduled(cron = "0 3 0 */1 * ?")
    public void updateDays(){
        overviewStationInfoMapper.updateDays();
    }
}
