package com.hhgs.plantshows.service;

import com.hhgs.plantshows.model.BO.HbaseResult;

import java.util.Map;

public interface IndicatorGraphService {

    Map<String, HbaseResult> queryIndicatorGraphData(String currentData,int plantCode);
}
