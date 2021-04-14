package com.hhgs.plantshows.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhgs.plantshows.common.CacheConstant;
import com.hhgs.plantshows.mapper.hh.PlantShowsMapper;
import com.hhgs.plantshows.model.DO.ImportData;
import com.hhgs.plantshows.model.DO.LcuEquipment;
import com.hhgs.plantshows.service.PlantShowsService;
import com.hhgs.plantshows.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class PlantShowsServiceImpl implements PlantShowsService {

    @Autowired
    private PlantShowsMapper plantShowsMapper;

    @Override
    public HashMap<String, String> batchInsert(List<ImportData> result, String plantName, String plantType, HttpServletRequest request) {

        HashMap<String, String> resultMap = new HashMap<>();

        if (result == null || result.size() == 0) {
            return null;
        }

        //获取该场站对应的表
        String plantTable = plantShowsMapper.getPlantTableWithPlantName(plantName);

        //请空表中原来数据
        plantShowsMapper.deleteByTableName(plantTable);

        ListUtil<ImportData> listUtil = new ListUtil();
        //将大集合拆分成小集合批量导入数据
        List<List<ImportData>> lists = listUtil.bgListToSmList(result, 2000);
        //插入总记录数
        int total = 0;
        //集合总长度
        int totalCount = result.size();
        //sessionId
        String sessionId = request.getSession().getId();
        CacheConstant.session = request.getSession();
        CacheConstant.session.setMaxInactiveInterval(4 * 3600);

        //如果导入的数据是风电数据，需要判断数据是否连续。
        if (plantType.equals("2")) {
            long endId = Long.valueOf(result.get(result.size() - 1).getOriginalPoint());
            long startId = Long.valueOf(result.get(0).getOriginalPoint());
            Map<Boolean, String> judgeDataMap = judgeDataIsSeries(result);
            Set<Boolean> booleans = judgeDataMap.keySet();
            for (Boolean aBoolean : booleans) {

                //如果数据连续
                if (aBoolean) {
                    for (List<ImportData> importDataList : lists) {
                        total += plantShowsMapper.batchInsert(importDataList, plantTable);
                    }
                    resultMap.put("插入条数", total + "");
                } else {
                    resultMap.put("数据不连续", judgeDataMap.get(aBoolean));
                    total = -1;
                }

            }
        }

        if (plantType.equals("1")) {
            for (List<ImportData> importDataList : lists) {
                total += plantShowsMapper.batchInsert(importDataList, plantTable);

                //导入数据的进度信息
                double dPercent = (double) total / totalCount;
                int percent = (int) (dPercent * 100);
                request.getSession().setAttribute("totalCount", totalCount);
                request.getSession().setAttribute("curCount", total);
                request.getSession().setAttribute("percent", percent);
                request.getSession().setAttribute("percentText", percent + "%");

                if (percent == 100) {
                    try {
                        Thread.sleep(5000);
                        CacheConstant.session.invalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //更新层级生成状态为未生成层级
        plantShowsMapper.updateHierarchyStatus(0, plantName);

        resultMap.put("插入条数", total + "");
        return resultMap;
    }


    /**
     * 判断风电数据是否是连续的
     * List<ImportData> result
     *
     * @return
     */
    private Map<Boolean, String> judgeDataIsSeries(List<ImportData> result) {
        Map<Boolean, String> resultMap = new HashMap<>();
        long endId = Long.valueOf(result.get(result.size() - 1).getOriginalPoint());
        long startId = Long.valueOf(result.get(0).getOriginalPoint());
        if (result.size() != (endId - startId + 1)) {
            for (int i = 0; i < result.size(); i++) {
                if (i == result.size() - 1) {
                    continue;
                }
                //long currentId = Long.valueOf(result.get(i).getOriginalPoint());
                long plantNextId = Long.valueOf(result.get(i).getOriginalPoint()) + 1;
                long trueNextId = Long.valueOf(result.get(i + 1).getOriginalPoint());

                if (plantNextId - trueNextId > 999999999) {
                    resultMap.put(false, "不连续id: " + trueNextId);
                } else {
                    resultMap.put(true, "连续");
                }
            }
        } else {
            resultMap.put(true, "连续");
        }
        return resultMap;
    }


    @Override
    public PageInfo<ImportData> selectDataByCondition(String plantTable, ImportData importData, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<ImportData> importDataList = plantShowsMapper.getAllDataWithPlantTableAndCondition(plantTable, importData);

        PageInfo pageInfo = new PageInfo(importDataList);
        return pageInfo;
    }

    @Override
    public PageInfo<ImportData> getAllDataWithPlantTableAndDescript(String plantTable, ImportData importData, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<ImportData> importDataList = plantShowsMapper.getAllDataWithPlantTableAndDescript(plantTable, importData);

        PageInfo pageInfo = new PageInfo(importDataList);
        return pageInfo;
    }

    @Override
    public List<LcuEquipment> getLcuAndEquipmentByPlantCode(int plantCode) {
        List<LcuEquipment> lcuEquipments = plantShowsMapper.getLcuAndEquipmentByPlantCode(plantCode);
        return lcuEquipments;
    }
}
