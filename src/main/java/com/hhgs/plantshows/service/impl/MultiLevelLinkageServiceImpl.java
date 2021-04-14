package com.hhgs.plantshows.service.impl;

import com.hhgs.plantshows.mapper.hh.MultiLevelMapper;
import com.hhgs.plantshows.mapper.hh.PlantShowsMapper;
import com.hhgs.plantshows.model.DO.*;
import com.hhgs.plantshows.service.MultiLevelLinkageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class MultiLevelLinkageServiceImpl implements MultiLevelLinkageService {

    @Autowired
    MultiLevelMapper multiLevelMapper;

    @Autowired
    PlantShowsMapper plantShowsMapper;

    @Override
    public List<PlantBigsys> queryBigsysList(int plantId) {
        List<PlantBigsys> plantBigsysList = multiLevelMapper.queryBigsysList(plantId);
        List<PlantBigsys> collectList = plantBigsysList.stream().filter(a -> {
            if (a.getBigsysName() != null && a.getBigsysName().contains("区") && !a.getBigsysName().contains("期")) {
                return true;
            } else if (a.getBigsysName() != null && a.getBigsysName().contains("#") && !a.getBigsysName().contains("期")) {
                return true;
            }
            return false;
        }).
                sorted(Comparator.comparing(ele -> {
                    if (ele.getBigsysName().contains("区")) {
                        if (ele.getBigsysName().contains("#")) {
                            return Integer.valueOf(ele.getBigsysName().split("#")[0]);
                        }
                        return Integer.valueOf(ele.getBigsysName().split("区")[0]);
                    } else {
                        return Integer.valueOf(ele.getBigsysName().split("#")[0]);
                    }

                })).collect(Collectors.toList());

        List<PlantBigsys> collectList1 = plantBigsysList.stream().filter(a -> {
            if (a.getBigsysName() != null && a.getBigsysName().contains("期") && a.getBigsysName().contains("#")) {
                return true;
            }
            return false;
        }).sorted(Comparator.comparing(ele ->
                Integer.valueOf(ele.getBigsysName().split("期")[1].split("#")[0])
        )).
                filter(ele -> ele.getBigsysName().contains("期") && ele.getBigsysName().contains("#")).sorted(Comparator.comparing(ele ->
                Integer.valueOf(ele.getBigsysName().split("期")[0])
        )).
                collect(Collectors.toList());

        collectList.addAll(
                collectList1
        );

        collectList.addAll(plantBigsysList.stream().filter(a -> {
                    if (!(a.getBigsysName() != null && (a.getBigsysName().contains("区") || a.getBigsysName().contains("#") || a.getBigsysName().contains("期")))) {
                        return true;
                    }
                    return false;
                }
        ).collect(Collectors.toList()));
        return collectList;
    }

    @Override
    public List<BigsysSmallsys> querySmallsysList(int bigsysId) {
        List<BigsysSmallsys> bigsysSmallsysList = multiLevelMapper.querySmallsyslist(bigsysId);
        List<BigsysSmallsys> collectList = bigsysSmallsysList.stream().filter(a -> {
                    if (a.getSmallsysName() != null && a.getSmallsysName().contains("#") && !a.getSmallsysName().contains(" ") && !a.getSmallsysName().contains("区") && !a.getSmallsysName().contains("、")) {
                        return true;
                    }
                    return false;
                }
        ).
                sorted(Comparator.comparingInt(ele -> Integer.valueOf(ele.getSmallsysName().split("#")[0]))).collect(Collectors.toList());


        List<BigsysSmallsys> collectList2 = bigsysSmallsysList.stream().filter(a -> {
                    if (a.getSmallsysName() != null && !a.getSmallsysName().contains("#") && !a.getSmallsysName().contains(" ") && a.getSmallsysName().contains("区") && !a.getSmallsysName().contains("、")) {
                        return true;
                    }
                    return false;
                }
        ).
                sorted(Comparator.comparingInt(ele -> Integer.valueOf(ele.getSmallsysName().split("区")[0]))).collect(Collectors.toList());

        List<BigsysSmallsys> collectList1 = bigsysSmallsysList.stream().filter(a -> {
            if (a.getSmallsysName() != null && a.getSmallsysName().contains("#") && a.getSmallsysName().contains("区")) {
                return true;
            }
            return false;
        }).sorted(Comparator.comparing(ele ->
                Integer.valueOf(ele.getSmallsysName().split("\\-")[1].split("区")[0])
        )).
                filter(ele -> ele.getSmallsysName().contains("#") && ele.getSmallsysName().contains("区")).sorted(Comparator.comparing(ele ->
                Integer.valueOf(ele.getSmallsysName().split("#")[0])
        )).
                collect(Collectors.toList());
        collectList.addAll(collectList2);

        collectList.addAll(
                collectList1
        );

        collectList.addAll(bigsysSmallsysList.stream().filter(a ->
                !(a.getSmallsysName() != null && a.getSmallsysName().contains("#") && !a.getSmallsysName().contains(" ") || a.getSmallsysName().contains("区") && !a.getSmallsysName().contains("、"))
        ).collect(Collectors.toList()));
        return collectList;
    }

    @Override
    public List<SmallsysFirstsDevice> queryFirstsDeviceList(int smallsysId) {
        List<SmallsysFirstsDevice> smallsysFirstsDeviceList = multiLevelMapper.queryFirstsDeviceList(smallsysId);

        List<SmallsysFirstsDevice> collectList = smallsysFirstsDeviceList.stream().filter(a -> {
            if (a.getDeviceName() != null && a.getDeviceName().contains("#") && !a.getDeviceName().contains(" ") && !a.getDeviceName().contains("段") && !a.getDeviceName().contains("、") && !a.getDeviceName().contains("（")) {
                return true;
            } else {
                return false;
            }
        }).sorted(Comparator.comparingInt(ele -> Integer.valueOf(ele.getDeviceName().split("\\#")[0]))).collect(Collectors.toList());

        collectList.addAll(smallsysFirstsDeviceList.stream().filter(a ->
                !(a.getDeviceName() != null && a.getDeviceName().contains("#") && !a.getDeviceName().contains(" ") && !a.getDeviceName().contains("段") && !a.getDeviceName().contains("、") && !a.getDeviceName().contains("（"))
        ).collect(Collectors.toList()));

        return collectList;
    }

    @Override
    public List<FirstsDeviceSecondsDevice> querySecondsDeviceList(int firstsDeviceId) {
        List<FirstsDeviceSecondsDevice> firstsDeviceSecondsDeviceList = multiLevelMapper.querySecondsDeviceList(firstsDeviceId);
        return firstsDeviceSecondsDeviceList;
    }

    @Override
    public String queryDeviceSign(int deviceId) {
        String deviceSign = multiLevelMapper.queryFirstsDeviceSign(deviceId);
        return deviceSign;
    }

    @Override
    public String getBracketType(int smallsysId) {
        String bracketType = multiLevelMapper.queryBracketType(smallsysId);
        return bracketType;
    }

    @Override
    public String getComponentType(int smallsysId) {
        String componentType = multiLevelMapper.queryComponentType(smallsysId);
        return componentType;
    }

    @Override
    public List<SmallsysFirstsDevice> queryDeviceTypeByBigsysId(int bigsysId) {

        List<SmallsysFirstsDevice> tempList = new ArrayList<>();
        List<SmallsysFirstsDevice> deviceTypeList = multiLevelMapper.queryDeviceTypeByBigsysId(bigsysId);

        deviceTypeList.forEach(ele ->
        {
            String deviceName = ele.getDeviceName();
            String deviceId = ele.getDeviceId();
            SmallsysFirstsDevice tmp = new SmallsysFirstsDevice();
            if (deviceName.contains("#")) {
                tmp.setDeviceName(deviceName.split("#")[1]);
            } else {
                tmp.setDeviceName(deviceName);
            }
            tmp.setDeviceId(deviceId);
            tempList.add(tmp);
        });

        return tempList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()-> new TreeSet<>(Comparator.comparing(o -> o.getDeviceName()))),ArrayList::new));
    }

    @Override
    public List<String> queryDeviceSignByDeviceId(int deviceId) {
        List<String> deviceSignList = multiLevelMapper.queryDeviceSignByDeviceId(deviceId);
        return deviceSignList;
    }

    @Override
    public List<String> queryDeviceModeByDeviceSign(String deviceSignName) {
        List<String> enPointNameList = multiLevelMapper.queryDeviceModeByDeviceSign(deviceSignName);
        return enPointNameList;
    }

    @Override
    public List<ImportData> queryDataByCondition(String plantName, ImportData importData) {
        //获取该场站对应的表
        String plantTable = plantShowsMapper.getPlantTableWithPlantName(plantName);
        List<ImportData> dataList = multiLevelMapper.queryDataByCondition(plantTable, plantName,importData);
        return dataList;
    }


}
