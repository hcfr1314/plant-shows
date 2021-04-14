package com.hhgs.plantshows.service.impl;
import com.hhgs.plantshows.mapper.hh.BasicDeviceModelMapper;
import com.hhgs.plantshows.model.BO.BaseDeviceModelAndAttr;
import com.hhgs.plantshows.model.DO.BaseDeviceModel;
import com.hhgs.plantshows.service.BasicDeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class BasicDeviceModelServiceImpl implements BasicDeviceModelService {

    @Autowired
    BasicDeviceModelMapper basicDeviceModelMapper;

    @Override
    public int addData(BaseDeviceModel baseDeviceModel) {

        String deviceName = baseDeviceModel.getDeviceName();

        if (deviceName == null) {
            return -1;
        }

        List<BaseDeviceModelAndAttr> queryList = basicDeviceModelMapper.queryByType(baseDeviceModel.getDeviceType(), deviceName);

        if (queryList != null && queryList.size() > 0) {
            return -1;
        }

        baseDeviceModel.setAttributeID(-1);

        return basicDeviceModelMapper.addData(baseDeviceModel);
    }

    @Override
    public int editData(BaseDeviceModel baseDeviceModel) {
        return basicDeviceModelMapper.editData(baseDeviceModel);
    }

    @Override
    public int delPoint(int deviceId, int attrId) {
        return basicDeviceModelMapper.delByDeviceIdAndAttrId(deviceId, attrId);
    }

    @Override
    public Map<String, Object> queryByType(int deviceType, String deviceName, int pageNum, int pageSize) {
        List<BaseDeviceModelAndAttr> list = basicDeviceModelMapper.queryByType(deviceType, deviceName);
        TreeMap<String, List<BaseDeviceModelAndAttr>> map = new TreeMap<>(list.stream().collect(Collectors.groupingBy(BaseDeviceModelAndAttr::getDeviceName)));

        //��ȡ����key
        List<String> deviceNames = new ArrayList<>();

        for (Map.Entry entry : map.entrySet()) {
            deviceNames.add(String.valueOf(entry.getKey()));
        }

        int pageStart = (pageNum - 1) * pageSize;

        int size = map.size();

        List<String> subs = deviceNames.stream().skip(pageStart).limit(pageSize).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>(pageSize + 3);
        for (String sub : subs) {
            result.put(sub, map.get(sub));
        }

        result.put("totalCount", size);
        result.put("pageSize", pageSize);
        result.put("pageNum", pageNum);

        return result;
    }

    @Override
    public int deleteDevcie(String deviceName, int type) {
        return basicDeviceModelMapper.deleteDevcie(deviceName, type);
    }
}