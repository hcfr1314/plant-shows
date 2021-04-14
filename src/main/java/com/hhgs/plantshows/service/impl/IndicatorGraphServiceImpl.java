package com.hhgs.plantshows.service.impl;

import com.hhgs.plantshows.mapper.hh.InfluxDBDataObjectMapper;
import com.hhgs.plantshows.mapper.hh.PlantShowsMapper;
import com.hhgs.plantshows.mapper.hh.PointMapper;
import com.hhgs.plantshows.model.BO.HbaseResult;
import com.hhgs.plantshows.model.BO.Point;
import com.hhgs.plantshows.model.DO.InfluxDBDataObject;
import com.hhgs.plantshows.model.DO.PlantTable;
import com.hhgs.plantshows.service.IndicatorGraphService;
import com.hhgs.plantshows.service.ParametarShowsService;
import com.hhgs.plantshows.util.DateUtil;
import com.hhgs.plantshows.util.SelectDataFromInfluxdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndicatorGraphServiceImpl implements IndicatorGraphService {


    @Autowired
    private InfluxDBDataObjectMapper influxDBDataObjectMapper;

    @Autowired
    private PlantShowsMapper plantShowsMapper;

    @Autowired
    private PointMapper pointMapper;

    @Override
    public Map<String, HbaseResult> queryIndicatorGraphData(String currentData, int plantCode) {

        Map<String,HbaseResult> resultMap = new HashMap<>();
        List<PlantTable> plantList = plantShowsMapper.getAllPlantName();
        List<String> nameList = plantList.stream().filter(e -> "1".equals(e.getPlantType())).map(p -> p.getPlantName()).collect(Collectors.toList());
        List<InfluxDBDataObject> influxDBDataObjects = influxDBDataObjectMapper.queryObjectNameByStationName(nameList);

        String dataObjectName = influxDBDataObjects.stream().filter(e -> e.getPlantCode() == plantCode).collect(Collectors.toList()).get(0).getDataObjectName();
        List<Point> points = pointMapper.queryIndicatorGrapIndex(plantCode);

        String start = currentData + " 00:00:00";

        long startTime = DateUtil.getTime(start);
        long endTime = DateUtil.getCurrentTime();

        points.forEach( point ->
                {
                    long orgId = point.getOrgId();
                    HbaseResult dataFromInfluxdb = SelectDataFromInfluxdb.getDataFromInfluxdb(dataObjectName, startTime, endTime, orgId);
                    if(dataFromInfluxdb != null) {
                        resultMap.put(point.getDescription(),dataFromInfluxdb);
                    }
                }
                );


        return resultMap;
    }
}
