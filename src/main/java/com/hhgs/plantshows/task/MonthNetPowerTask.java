package com.hhgs.plantshows.task;

import com.hhgs.plantshows.mapper.hh.NetAndPlanPowerMapper;
import com.hhgs.plantshows.model.DO.IndexData;
import com.hhgs.plantshows.model.DO.NetAndPlanPower;
import com.hhgs.plantshows.service.ParametarShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MonthNetPowerTask {

    @Autowired
    private ParametarShowsService parametarShowsService;

    @Autowired
    private NetAndPlanPowerMapper netAndPlanPowerMapper;

    /**
     * 每天凌晨5:40执行一次
     */
    @Scheduled(cron = "0 40 5 * * ? ")
    //@Scheduled(fixedDelay = 3600000)
    public void updateMonthNetPower() {
        IndexData totalPower = parametarShowsService.getTotalPower();
        //这里为了判断当月第一天库中是否有数据，如果没有则新增一条数据
        List<NetAndPlanPower> netAndPlanPowers = netAndPlanPowerMapper.judgeData();
        if(netAndPlanPowers.size() == 0) {
            netAndPlanPowerMapper.save(totalPower);
        }

        if (totalPower != null) {
            netAndPlanPowerMapper.updateNetPower(totalPower.getMonthAccOnGridEnergy());
            return;
        }
    }
}
