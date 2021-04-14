package com.hhgs.plantshows.service;

import com.hhgs.plantshows.model.BO.IndexDataAndCompute;
import com.hhgs.plantshows.model.DO.ComputeIndex;
import com.hhgs.plantshows.model.DO.NetAndPlanPower;
import com.hhgs.plantshows.model.DO.PlantInfo;
import com.hhgs.plantshows.model.DO.IndexData;
import java.util.List;
import java.util.Map;

public interface ParametarShowsService {

    IndexDataAndCompute queryDataByPlantCode(int plantCode);


    Map<Integer, List<PlantInfo>> queryPlantinfo();

    /**
     * 查询所有光伏场站的月累计上网电量，月累计发电量，日上网电量，日累计发电量
     * @return
     */
    IndexData getTotalPower();

    /**
     * 获取首页实时数据接口
     * @return
     */
    Map<String, Object> getRealTime();

    /**
     * 本月发电量和上网电量
     * @return
     */
    List<IndexData> monthPw();

    /**
     * 环境保护指标和运营指标计算
     *
     * @return
     */
    Map<String, Object> getEnvirAndOption();

    List<IndexData> queryMonthAccOnGridEnergy(int plantCode,String date);

    List<ComputeIndex> queryComputeIndex(int plantCode,String date);

    /**
     * 当前年每个月累计上网电量
     * @return
     */
    List<NetAndPlanPower> monthNetAndPlanPower();
}
