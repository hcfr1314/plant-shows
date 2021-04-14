package com.hhgs.plantshows.mapper.hh;


import com.hhgs.plantshows.model.DO.PlantInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface PlantInfoMapper {

    List<PlantInfo> queryPlantInfoByPlantCode (@Param("plantCode") Integer plantCode);

    int addData (@Param("plantInfo") PlantInfo plantInfo);

    int editData(@Param("plantInfo") PlantInfo plantInfo);

    int deleteById(@Param("id") int id);
}
