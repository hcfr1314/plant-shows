package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.InfluxDBDataObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluxDBDataObjectMapper {

    List<InfluxDBDataObject> queryObjectNameByStationName(@Param("nameList") List<String> nameList);
}
