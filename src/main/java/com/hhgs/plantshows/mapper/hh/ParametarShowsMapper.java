package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.IndexData;
import com.hhgs.plantshows.model.DO.IrradiationIndex;
import com.hhgs.plantshows.model.DO.PlantInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface ParametarShowsMapper {

    /**
     * 根据场站查询当日指标数据
     * @param plantCode
     * @return
     */
    IndexData queryIndexValue(@Param("plantCode") int plantCode);

    /**
     * 查询当月累计辐照量
     * @param plantCode
     * @return
     */
    IrradiationIndex queryMonthAccIrradiation (@Param("plantCode") int plantCode);

    /**
     * 查询场站装机容量
     * @return
     */
    List<PlantInfo> queryPlantInfo();


    List<IndexData> queryByPlantCodeAndDate(@Param("list") List<Integer> plantCodes);

    List<IndexData> queryAllStationThisMontData(@Param("list") List<Integer> plantCodes);

    List<IndexData> queryMonthAccOnGridEnergy(@Param("plantCode") int plantCode,@Param("date") String date);

    /**
     * 查询所有场站昨天月累计日照利用小时
     * @return
     */
    List<IrradiationIndex> queryTotalSunlightByMonth();
}
