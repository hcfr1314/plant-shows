package com.hhgs.plantshows.mapper.hh;


import com.hhgs.plantshows.model.DO.ComputeIndex;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface ComputeIndexMapper {

    /**
     * 批量插入指标计算结果
     * @param computeIndexList
     */
    void batchInsertIndexData(@Param("computeIndexList") List<ComputeIndex> computeIndexList);


    /**
     * 根据场站编码查询指标数据
     * @param plantCode
     * @return
     */
    ComputeIndex queryIndexByPlantCode(@Param("plantCode") int plantCode);

    /**
     * 根据场站编号和时间查询数据
     * @param plantCodes
     * @return
     */
    List<ComputeIndex> queryIndexByCodeAndDate(@Param("codes") List<Integer> plantCodes);

    List<ComputeIndex> queryComputeIndex(@Param("plantCode") int plantCode,@Param("date") String date);
}
