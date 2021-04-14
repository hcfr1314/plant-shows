package com.hhgs.plantshows.service.impl;

import com.hhgs.plantshows.mapper.hh.ComputeIndexMapper;
import com.hhgs.plantshows.mapper.hh.ParametarShowsMapper;
import com.hhgs.plantshows.model.BO.IndexDataAndCompute;
import com.hhgs.plantshows.model.DO.ComputeIndex;
import com.hhgs.plantshows.model.DO.IndexData;
import com.hhgs.plantshows.model.DO.PlantInfo;
import com.alibaba.fastjson.JSONObject;
import com.hhgs.plantshows.mapper.hh.*;
import com.hhgs.plantshows.model.BO.*;
import com.hhgs.plantshows.model.DO.*;
import com.hhgs.plantshows.service.ParametarShowsService;
import com.hhgs.plantshows.util.DateUtil;
import com.hhgs.plantshows.util.HttpUtils;
import com.hhgs.plantshows.util.InfluxDBConnection;
import com.hhgs.plantshows.util.LoginCheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.influxdb.dto.QueryResult;


import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParametarShowsServiceImpl implements ParametarShowsService {

    private static final String URL = "http://172.28.8.25/avatar-datamanage-service/data/queryMetaObjData?metaObjectName=";

    private static final String SUCCESS_CODE = "0";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");

    private static final DateFormat DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int START_HOUR = 7;

    @Autowired
    private PlantShowsMapper plantShowsMapper;

    @Autowired
    private ParametarShowsMapper parametarShowsMapper;

    @Autowired
    private ComputeIndexMapper computeIndexMapper;

    @Autowired
    private InfluxDBDataObjectMapper influxDBDataObjectMapper;

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private NetAndPlanPowerMapper netAndPlanPowerMapper;

    @Autowired
    private IndexCofficientMapper indexCofficientMapper;

    @Override
    public IndexDataAndCompute queryDataByPlantCode(int plantCode) {

        IndexDataAndCompute indexDataAndCompute = new IndexDataAndCompute();

        IndexData indexData = parametarShowsMapper.queryIndexValue(plantCode);
        ComputeIndex computeIndex = computeIndexMapper.queryIndexByPlantCode(plantCode);
        IrradiationIndex irradiationIndex = parametarShowsMapper.queryMonthAccIrradiation(plantCode);

        if (indexData != null && computeIndex != null && irradiationIndex != null) {
            indexDataAndCompute.setCurrentGeneration(indexData.getCurrentGeneration());
            indexDataAndCompute.setCurrentOnGridEnergy(indexData.getCurrentOnGridEnergy());
            indexDataAndCompute.setMonthAccOnGridEnergy(indexData.getMonthAccOnGridEnergy());
            indexDataAndCompute.setYearAccOnGridEnergy(indexData.getYearAccOnGridEnergy());

            indexDataAndCompute.setCo2Value(computeIndex.getCo2());
            indexDataAndCompute.setDeforestation(computeIndex.getDeforestation());
            indexDataAndCompute.setCurrentEarnings(computeIndex.getCurrentEarnings());
            indexDataAndCompute.setMonthEarnings(computeIndex.getMonthEarnings());
            indexDataAndCompute.setStrandCoalValue(computeIndex.getStrandardCoal());
            indexDataAndCompute.setMonthEquUtilizationHours(computeIndex.getMonthEquUtilizationHours());
            indexDataAndCompute.setDayEquUtilizationHours(computeIndex.getDayEquUtilizationHours());
            indexDataAndCompute.setCurrentIrradition(irradiationIndex.getCurrentIrradition());
        }
        Map<String, String[]> realTimeDataMap = new HashMap<>();
        List<PlantTable> plantList = plantShowsMapper.getAllPlantName();
        List<String> nameList = plantList.stream().filter(e -> "1".equals(e.getPlantType())).map(p -> p.getPlantName()).collect(Collectors.toList());
        List<InfluxDBDataObject> influxDBDataObjects = influxDBDataObjectMapper.queryObjectNameByStationName(nameList);

        //获取该场站的数据对象
        String dataObjectName = influxDBDataObjects.stream().filter(e -> e.getPlantCode() == plantCode).collect(Collectors.toList()).get(0).getDataObjectName();
        List<Point> points = pointMapper.queryAllPoint();
        //该场站下的所有点
        List<Point> pointList = points.stream().filter(e -> e.getPlantCode() == plantCode).collect(Collectors.toList());

        long startTime = DateUtil.getCurrentTime() - 3600000;
        long endTime = DateUtil.getCurrentTime();

        //请求获取数据
        pointList.forEach(point -> {
            long orgId = point.getOrgId();

            System.out.println(point);
            HbaseResult hbaseResult = getNewDataFromInfluxdb(dataObjectName, startTime, endTime, orgId);
            if (hbaseResult != null) {
                List<String[]> dataRows = hbaseResult.getData().getDataRows();
                if (dataRows.size() > 0) {

                    realTimeDataMap.put(point.getDescription(), dataRows.get(0));
                }
            }
        });

        System.out.println(realTimeDataMap);
        indexDataAndCompute.setRealTimeDataMap(realTimeDataMap);
        return indexDataAndCompute;
    }


    @Override
    public Map<Integer, List<PlantInfo>> queryPlantinfo() {
        List<PlantInfo> plantInfoList = parametarShowsMapper.queryPlantInfo();
        Map<Integer, List<PlantInfo>> collect = plantInfoList.stream().collect(Collectors.groupingBy(PlantInfo::getPlantCode));
        return collect;
    }

    @Override
    public IndexData getTotalPower() {

        //查询所有光伏场站的信息（1，光伏，2，风电）
        List<PlantTable> allPlantName = plantShowsMapper.getAllPlantName();
        List<Integer> plantCodes = allPlantName.stream().filter(e -> "1".equals(e.getPlantType()) && (e.getPlantCode() == 121010)).map(p -> p.getPlantCode()).collect(Collectors.toList());

        //根据plantCode查询所有光伏场站的信息
        List<IndexData> dataList = parametarShowsMapper.queryByPlantCodeAndDate(plantCodes);

        if (dataList != null && dataList.size() > 0) {
            //计算total
            IndexData data = new IndexData();
            double currentGeneration = dataList.stream().mapToDouble(IndexData::getCurrentGeneration).sum();
            double currentOnGridEnergy = dataList.stream().mapToDouble(IndexData::getCurrentOnGridEnergy).sum();
            double monthAccOnGridEnergy = dataList.stream().mapToDouble(IndexData::getMonthAccOnGridEnergy).sum();
            double monthGeneration = dataList.stream().mapToDouble(IndexData::getMonthGeneration).sum();
            double yearGeneration = dataList.stream().mapToDouble(IndexData::getYearGeneration).sum();
            double yearAccOnGridEnergy = dataList.stream().mapToDouble(IndexData::getYearAccOnGridEnergy).sum();
            data.setCurrentGeneration(currentGeneration);
            data.setCurrentOnGridEnergy(currentOnGridEnergy);
            data.setMonthAccOnGridEnergy(monthAccOnGridEnergy);
            data.setMonthGeneration(monthGeneration);
            data.setYearAccOnGridEnergy(yearAccOnGridEnergy);
            data.setYearGeneration(yearGeneration);
            return data;
        }

        return null;
    }

    @Override
    public Map<String, Object> getRealTime() {
        Map<Integer, List<String[]>> map = new HashMap<>();
        List<PlantTable> allPlantName = plantShowsMapper.getAllPlantName();
        List<String> nameList = allPlantName.stream().filter(e -> "1".equals(e.getPlantType())).map(p -> p.getPlantName()).collect(Collectors.toList());
        List<InfluxDBDataObject> influxDBDataObjects = influxDBDataObjectMapper.queryObjectNameByStationName(nameList);
        List<Point> points = pointMapper.queryByType().stream().filter(e->e.getPlantCode()==121010).collect(Collectors.toList());

        //请求获取数据
        for (Point point : points) {
            String dataObjectName = influxDBDataObjects.stream().filter(e -> e.getStationName().equals(point.getPlantName())).collect(Collectors.toList()).get(0).getDataObjectName();
            List<String[]> data = getData(dataObjectName, point);
            if (data != null) {
                map.put(point.getPlantCode(), data);
            }
        }

        int index = findIndex();
        List<Object[]> result = new ArrayList<>(index);
        for (int i = 0; i < index; i++) {
            double total = 0;
            String dateStr = null;
            for (Map.Entry<Integer, List<String[]>> entity : map.entrySet()) {
                total += Double.valueOf(entity.getValue().get(i)[2]);
                dateStr = entity.getValue().get(i)[1].split("\\.")[0];
            }
            result.add(new Object[]{dateStr, total});
        }

        Map<String, Object> finalResult = new HashMap();

        finalResult.put("arr", result);
        finalResult.put("value", result.get(index - 1)[1]);

        return finalResult;
    }

    @Override
    public List<IndexData> monthPw() {
        List<IndexData> result = new ArrayList<>();
        List<PlantTable> allPlantName = plantShowsMapper.getAllPlantName();
        List<Integer> plantCodes = allPlantName.stream().filter(e -> "1".equals(e.getPlantType()) && (e.getPlantCode() == 121010)).map(p -> p.getPlantCode()).collect(Collectors.toList());
        List<IndexData> data = parametarShowsMapper.queryAllStationThisMontData(plantCodes);
        Map<Date, List<IndexData>> timeGroupMap = data.stream().collect(Collectors.groupingBy(IndexData::getCreateTime));
        timeGroupMap.forEach((createTime, list) -> {
            //DATE_FORMAT1.format(createTime).equals("2020-08-30")
            IndexData ele = new IndexData();
            //上网电量
            double net = list.stream().mapToDouble(IndexData::getCurrentOnGridEnergy).sum();

            //发电量
            double pw = list.stream().mapToDouble(IndexData::getCurrentGeneration).sum();

            ele.setCurrentGeneration(pw);
            ele.setCurrentOnGridEnergy(net);
            ele.setCreateTime(createTime);
            result.add(ele);
        });

        return result.stream().sorted(Comparator.comparing(IndexData::getCreateTime)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getEnvirAndOption() {
        List<PlantInfo> plantInfos = parametarShowsMapper.queryPlantInfo();
        List<Integer> plantCodes = plantInfos.stream().filter(e -> "1".equals(e.getPlantType()) && (e.getPlantCode() == 121010)).map(p -> p.getPlantCode()).collect(Collectors.toList());

        //根据时间和plantCode查询本月的数据
        List<IndexData> dataList = parametarShowsMapper.queryByPlantCodeAndDate(plantCodes);

        if (dataList == null || dataList.size() == 0) {
            return null;
        }

        //获取装机容量
        Map<Integer, List<PlantInfo>> integerListMap = queryPlantinfo();

        //获取每个场站的月累计日照小时数
        List<IrradiationIndex> indexDataList = parametarShowsMapper.queryTotalSunlightByMonth();

        List<ComputeIndex> stationList = new ArrayList<>();


        //首先算某个场站当月的数据
        Optional.ofNullable(dataList).ifPresent(e -> {
            Map<Integer, List<IndexData>> collect = e.stream().collect(Collectors.groupingBy(IndexData::getPlantCode));

            collect.forEach((key, list) -> {
                List<PlantInfo> plantTables = plantInfos.stream().filter(ele -> ele.getPlantCode() == key).collect(Collectors.toList());

                double installCap = plantTables.get(0).getInstallCapacity();

                ComputeIndex computeIndex = new ComputeIndex();

                //月累计发电量
                double monthGeneration = list.get(0).getMonthGeneration();

                //获取日照小时数
                double totalSunShine = indexDataList.stream().filter(ele -> ele.getPlantCode() == key).collect(Collectors.toList())
                        .get(0).getTotalSunShineOfMonth();

                //单个场站发电效率(上网电量/（装机容量*日照小时数）)
                double powerEfic = (monthGeneration / (installCap * totalSunShine)) * 100;

                //单个场站利用小时数
                double equHours = monthGeneration / installCap;

                //单mv功率
                double singleMW = monthGeneration / installCap / totalSunShine;

                computeIndex.setPowerEficiency(powerEfic);
                computeIndex.setMonthEquUtilizationHours(equHours);
                computeIndex.setSinglMWEficiency(singleMW);
                computeIndex.setPlantCode(key);
                computeIndex.setPlantName(plantTables.get(0).getPlantName());
                stationList.add(computeIndex);
            });
        });


        List<ComputeIndex> powerList = stationList.stream().sorted(Comparator.comparing(ComputeIndex::getPowerEficiency)).limit(5).collect(Collectors.toList());
        List<ComputeIndex> eqHourList = stationList.stream().sorted(Comparator.comparing(ComputeIndex::getMonthEquUtilizationHours)).limit(5).collect(Collectors.toList());
        List<ComputeIndex> singleMwList = stationList.stream().sorted(Comparator.comparing(ComputeIndex::getSinglMWEficiency)).limit(5).collect(Collectors.toList());

        //然后计算黄河公司当年的数据
        IndexCofficient indexCofficient = indexCofficientMapper.queryIndexMessage();
        double yearOfNet = dataList.stream().mapToDouble(IndexData::getYearAccOnGridEnergy).sum();

        //根据上网电量计算co，标准煤，森林砍伐指标


        double co2Sum = yearOfNet * indexCofficient.getCo2Cofficient() / 1000;
        double strandardCoal = yearOfNet * indexCofficient.getCoalCofficient() / 1000;
        double deforestation = yearOfNet / indexCofficient.getDeforstation();


        Map<String, Object> result = new HashMap<>();
        result.put("powerList", powerList);
        result.put("eqHourList", eqHourList);
        result.put("singleMwList", singleMwList);
        result.put("co2Sum", co2Sum);
        result.put("strandardCoal", strandardCoal);
        result.put("deforestation", deforestation);
        return result;
    }

    @Override
    public List<IndexData> queryMonthAccOnGridEnergy(int plantCode, String date) {
        List<IndexData> indexDataList = parametarShowsMapper.queryMonthAccOnGridEnergy(plantCode, date);
        return indexDataList;
    }

    @Override
    public List<ComputeIndex> queryComputeIndex(int plantCode, String date) {

        List<ComputeIndex> computeIndexList = computeIndexMapper.queryComputeIndex(plantCode, date);
        return computeIndexList;
    }

    public List<NetAndPlanPower> monthNetAndPlanPower() {
        List<NetAndPlanPower> netAndPlanPowers = netAndPlanPowerMapper.queryThisYearData();
        return netAndPlanPowers.stream().sorted(Comparator.comparing(NetAndPlanPower::getCreateDate)).collect(Collectors.toList());

    }

    private List<String[]> getData(String objectName, Point point) {
        //构造起始时间和结束时间
        long[] times = getDayStartMs();

        long endTime = times[1];
        long startTime = times[0];
        HbaseResult dataFromHbase = getDataFromInfluxdb(objectName, String.valueOf(startTime), String.valueOf(endTime), point.getOrgId());
        Data data = dataFromHbase.getData();
        if (data == null) {
            return null;
        }

        List<String[]> dataRows = data.getDataRows();
        if (data != null && dataRows != null && dataRows.size() > 0) {
            return data.getDataRows();
        }
        return null;
    }


    /**
     * 调用阿凡达接口，从influxdb获取数据
     *
     * @param dataObjectName
     * @param startTime
     * @param endTime
     * @return
     */
    private HbaseResult getDataFromInfluxdb(String dataObjectName, String startTime, String endTime, long id) {

        String p = "{'starttime': '" + startTime + "ms','id':'" + id + "','fillPoint':'" + 0 + "','sampled':'" + 1800 + "','endtime': '" + endTime + "ms'}";

        String queryExpression = "id='4462874646'";
        QueryObjectData param = new QueryObjectData(p, null);

        //调用阿凡达接口，首先需要获取token
        LoginCheckResult result = null;
        try {
            result = LoginCheckUtils.LoginCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!SUCCESS_CODE.equals(result.getStatusCode())) {
            return null;
        }

        String token = result.getData();

        //调用阿凡达接口
        String json = HttpUtils.getInstance().doPostByToken(URL, token, dataObjectName, param);

        HbaseResult hbaseResult = JSONObject.parseObject(json, HbaseResult.class);

        if (SUCCESS_CODE.equals(hbaseResult.getStatusCode())) {
            return hbaseResult;
        }

        return null;
    }

    /**
     * 获取当天7点和21点的毫秒数
     *
     * @return
     */
    private static long[] getDayStartMs() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long start = calendar.getTime().getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 0);
        long end = calendar.getTime().getTime();
        return new long[]{start, end};
    }


    /**
     * 根据时间获取数组下标
     *
     * @return
     */
    private static int findIndex() {
        Date date = new Date();
        String[] split = DATE_FORMAT.format(date).split(":");
        int index = (Integer.valueOf(split[0]) - START_HOUR) * 2 + 1;
        if (Integer.valueOf(split[1]) >= 30) {
            index = index + 1;
        }
        if (index > 29) {
            index = 29;
        }
        return index;
    }


    private HbaseResult getNewDataFromInfluxdb(String dataObjectName, long startTime, long endTime, long id) {

        String p = "{'id':'" + id + "','starttime':'" + startTime + "ms','endtime':'" + endTime + "ms','alignoffset':'" + 30000 + "','alignpoint':'" + 60000 + "','fillPoint':'" + 30000 + "'}";


        //{'id':'120110.TotIrr','starttime':'2019-10-04T00:00:00.000Z','endtime':'2019-10-05T00:00:00.000Z','alignoffset':'30000','alignpoint':'60000','fillpoint':'30000'}
        String queryExpression = "id='4462874646'";
        QueryObjectData param = new QueryObjectData(p, null);

        //调用阿凡达接口，首先需要获取token
        LoginCheckResult result = null;
        try {
            result = LoginCheckUtils.LoginCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!SUCCESS_CODE.equals(result.getStatusCode())) {
            return null;
        }

        String token = result.getData();

        //调用阿凡达接口
        String json = HttpUtils.getInstance().doPostByToken(URL, token, dataObjectName, param);

        HbaseResult hbaseResult = JSONObject.parseObject(json, HbaseResult.class);

        if (SUCCESS_CODE.equals(hbaseResult.getStatusCode())) {
            return hbaseResult;
        }

        return null;
    }

}
