package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.IndexData;
import com.hhgs.plantshows.model.DO.NetAndPlanPower;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetAndPlanPowerMapper {

    List<NetAndPlanPower> queryThisYearData();

    List<NetAndPlanPower> judgeData();

    void updateNetPower(@Param("netPower") double netPower);

    void save(@Param("totalPower") IndexData totalPower);
}
