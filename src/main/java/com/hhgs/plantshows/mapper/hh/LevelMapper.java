package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.BootsterStation;
import com.hhgs.plantshows.model.DO.CollectionStation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 升压站和汇集站层级mapper
 */
@Repository
public interface LevelMapper {

    List<BootsterStation> queryBoosterByName(@Param("plantName") String plantName);

    List<CollectionStation> queryCollectByName(@Param("plantName") String plantName);

}
