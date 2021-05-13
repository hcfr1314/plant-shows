package com.hhgs.plantshows.task;


import com.hhgs.plantshows.mapper.hh.ComputeIndexMapper;
import com.hhgs.plantshows.mapper.hh.IndexCofficientMapper;
import com.hhgs.plantshows.mapper.hh.ParametarShowsMapper;
import com.hhgs.plantshows.mapper.hh.PlantShowsMapper;
import com.hhgs.plantshows.model.DO.*;
import com.hhgs.plantshows.service.ParametarShowsService;
import com.hhgs.plantshows.util.NumberalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class PrometarFirstPageTask {

    @Autowired
    private ParametarShowsMapper indexDataMapper;

    private Map<Integer, List<PlantInfo>> plantInfoMap;


    @Autowired
    private ParametarShowsMapper parametarShowsMapper;

    @Autowired
    private ParametarShowsService parametarShowsService;

    @Autowired
    private IndexCofficientMapper indexCofficientMapper;

    @Autowired
    private ComputeIndexMapper computeIndexMapper;


    @Autowired
    private PlantShowsMapper plantShowsMapper;


    @PostConstruct
    public void init() {
        plantInfoMap = parametarShowsService.queryPlantinfo();
    }


    @Transactional
    //每天凌晨6点执行一次
    @Scheduled(cron = "0 0 6 * * ? ")
    //@Scheduled(fixedDelay = 3600000)
    void saveIndexData() {

        List<ComputeIndex> computeIndexList = new ArrayList<>();
        IndexCofficient indexCofficient = indexCofficientMapper.queryIndexMessage();
        List<PlantTable> allPlantName = plantShowsMapper.getAllPlantName();
        allPlantName.forEach(plant ->
                {
                    ComputeIndex computeIndex = new ComputeIndex();
                    int plantCode = plant.getPlantCode();
                    IndexData indexData = parametarShowsMapper.queryIndexValue(plantCode);
                    IrradiationIndex irradiationIndex = parametarShowsMapper.queryMonthAccIrradiation(plantCode);

                    if (indexData != null && irradiationIndex != null && plantInfoMap.size() > 0) {

                        //获取该场站当日上网电量
                        double currentOnGridEnergy = indexData.getCurrentOnGridEnergy();

                        //获取该场站当月累计上网电量
                        double monthAccOnGridEnergy = indexData.getMonthAccOnGridEnergy();

                        //当日发电量
                        double currentGeneration = indexData.getCurrentGeneration();

                        //当月发电量
                        double monthGeneration = indexData.getMonthGeneration();

                        //获取该场站当月累计辐照量
                        double monthAccIrradition = irradiationIndex.getMonthAccIrradition();

                        //获取该场站的装机容量
                        double installCapacity = plantInfoMap.get(plantCode).get(0).getInstallCapacity();

                        //获取该场站当月日照小时
                        double sunlightTimeMonth = irradiationIndex.getTotalSunShineOfMonth();

                        double sunlightTimeDay = irradiationIndex.getTotalSunShineOfDay();

                        //co2
                        float co2 = (float) (indexCofficient.getCo2Cofficient() * currentOnGridEnergy * 10);
                        float conCo2 = NumberalUtil.conversionTwoDecimical(co2);


                        //标准煤
                        float strandardCoal = (float) (indexCofficient.getCoalCofficient() * currentOnGridEnergy * 10);
                        float conTrandardCoal = NumberalUtil.conversionTwoDecimical(strandardCoal);


                        //森林砍伐
                        float deforestation = (float) (currentOnGridEnergy / indexCofficient.getDeforstation());
                        float conDeforestation = NumberalUtil.conversionTwoDecimical(deforestation);


                        //当日收益
                        float currentEarnings = (float) (indexCofficient.getIncomeCofficient() * currentOnGridEnergy);
                        float conCurrentEarnings = NumberalUtil.conversionTwoDecimical(currentEarnings);


                        //当月收益
                        float monthEarnings = (float) (indexCofficient.getIncomeCofficient() * monthAccOnGridEnergy);
                        float conMonthEarnings = NumberalUtil.conversionTwoDecimical(monthEarnings);


                        //等效利用小时(日)
                        float dayEquUtilizationHours = (float) (currentGeneration / installCapacity);
                        float conDayEquUtilizationHours = NumberalUtil.conversionTwoDecimical(dayEquUtilizationHours);


                        //等效利用小时(月)
                        float monthEquUtilizationHours = (float) (monthGeneration / installCapacity);
                        float conMonthEquUtilizationHours = NumberalUtil.conversionTwoDecimical(monthEquUtilizationHours);


                        //发电效率
                        float powerEficiency = (float) (monthGeneration / (installCapacity * sunlightTimeMonth));
                        float conPowerEficiency = NumberalUtil.conversionTwoDecimical(powerEficiency);


                        //单MW功率
                        float conSinglMWEficiency = 0;
                        if (sunlightTimeDay != 0) {
                            float singlMWEficiency = (float) (currentGeneration / installCapacity / sunlightTimeDay);
                            conSinglMWEficiency = NumberalUtil.conversionTwoDecimical(singlMWEficiency);
                        }

                        computeIndex.setCo2(conCo2);
                        computeIndex.setStrandardCoal(conTrandardCoal);
                        computeIndex.setDeforestation(conDeforestation);
                        computeIndex.setCurrentEarnings(conCurrentEarnings);
                        computeIndex.setMonthEarnings(conMonthEarnings);
                        computeIndex.setDayEquUtilizationHours(conDayEquUtilizationHours);
                        computeIndex.setMonthEquUtilizationHours(conMonthEquUtilizationHours);
                        computeIndex.setPowerEficiency(conPowerEficiency);
                        computeIndex.setSinglMWEficiency(conSinglMWEficiency);
                        computeIndex.setCreateTime(new Date());
                        computeIndex.setPlantCode(plantCode);

                        computeIndexList.add(computeIndex);
                    }
                }
        );

        if (computeIndexList.size() > 0) {
            computeIndexMapper.batchInsertIndexData(computeIndexList);

        }
    }

}
