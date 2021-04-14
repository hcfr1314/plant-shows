package com.hhgs.plantshows.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhgs.plantshows.mapper.hh.PlantInfoMapper;
import com.hhgs.plantshows.model.DO.PlantInfo;
import com.hhgs.plantshows.service.PlantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PlantInfoServiceImpl implements PlantInfoService {


    @Autowired
    private PlantInfoMapper plantInfoMapper;

    @Override
    public PageInfo<PlantInfo> queryPlantInfoByPlantCode(Integer plantCode,int pageSize,int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<PlantInfo> plantInfoList = plantInfoMapper.queryPlantInfoByPlantCode(plantCode);
        PageInfo pageInfo = new PageInfo(plantInfoList);
        return pageInfo;
    }

    @Override
    @Transactional
    public int addData(PlantInfo plantInfo) {
        int i = plantInfoMapper.addData(plantInfo);
        return i;
    }

    @Override
    @Transactional
    public int editData(PlantInfo plantInfo) {
        int i = plantInfoMapper.editData(plantInfo);
        return i;
    }

    @Override
    @Transactional
    public int deleteById(int id) {
        int i = plantInfoMapper.deleteById(id);
        return i;
    }
}
