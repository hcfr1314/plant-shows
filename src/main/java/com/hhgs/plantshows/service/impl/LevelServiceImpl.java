package com.hhgs.plantshows.service.impl;

import com.hhgs.plantshows.mapper.hh.LevelMapper;
import com.hhgs.plantshows.model.DO.BootsterStation;
import com.hhgs.plantshows.model.DO.CollectionStation;
import com.hhgs.plantshows.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelMapper levelMapper;

    @Override
    public List<BootsterStation> queryMainSystemByStationName(BootsterStation entity) {
        List<BootsterStation> bootsterStations = levelMapper.queryBoosterByName(entity.getPlantName());
        List<BootsterStation> result = bootsterStations.stream().filter(ele -> ele.getDeviceName() != null).collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(BootsterStation::getDeviceName))
        ), ArrayList::new));
        return result;
    }

    @Override
    public List<BootsterStation> getMainSwitch(BootsterStation entity) {
        List<BootsterStation> bootsterStations = levelMapper.queryBoosterByName(entity.getPlantName());
        List<BootsterStation> filterList = bootsterStations.stream().filter(ele -> entity.getDeviceName().equals(ele.getDeviceName())).filter(ele -> ele.getMainSwitchName() != null).collect(Collectors.toList());
        List<BootsterStation> result = filterList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(BootsterStation::getMainSwitchName))
        ), ArrayList::new));
        return result;
    }

    @Override
    public List<BootsterStation> getBus(BootsterStation entity) {
        List<BootsterStation> bootsterStations = levelMapper.queryBoosterByName(entity.getPlantName());
        List<BootsterStation> filterList = bootsterStations.stream().filter(ele -> entity.getDeviceName().equals(ele.getDeviceName()))
                .filter(ele -> entity.getMainSwitchName().equals(ele.getMainSwitchName()))
                .filter(ele -> ele.getBusName() != null)
                .collect(Collectors.toList());

        List<BootsterStation> result = filterList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(BootsterStation::getBusName))
        ), ArrayList::new));

        return result;
    }

    @Override
    public List<BootsterStation> getLineSwitch(BootsterStation entity) {
        List<BootsterStation> bootsterStations = levelMapper.queryBoosterByName(entity.getPlantName());
        List<BootsterStation> filterList = bootsterStations.stream().filter(ele -> entity.getDeviceName().equals(ele.getDeviceName()))
                .filter(ele -> entity.getMainSwitchName().equals(ele.getMainSwitchName()))
                .filter(ele -> entity.getBusName().equals(ele.getBusName()))
                .filter(ele -> ele.getLineSwitchName() != null)
                .collect(Collectors.toList());

        return filterList;
    }

    @Override
    public List<BootsterStation> getLower(BootsterStation entity) {
        List<BootsterStation> bootsterStations = levelMapper.queryBoosterByName(entity.getPlantName());
        List<BootsterStation> filterList = bootsterStations.stream().filter(ele -> entity.getDeviceName().equals(ele.getDeviceName()))
                .filter(ele -> entity.getMainSwitchName().equals(ele.getMainSwitchName()))
                .filter(ele -> entity.getBusName().equals(ele.getBusName()))
                .filter(ele -> entity.getLineSwitchName().equals(ele.getLineSwitchName()))
                .filter(ele -> ele.getLowerDeviceName() != null)
                .collect(Collectors.toList());
        return filterList;
    }

    @Override
    public List<CollectionStation> getCollectionStation(CollectionStation entity) {
        List<CollectionStation> collectionStationsn = levelMapper.queryCollectByName(entity.getPlantName());
        List<CollectionStation> result = collectionStationsn.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(CollectionStation::getSystemName))
        ), ArrayList::new));
        return result;
    }

    @Override
    public List<CollectionStation> getCollectionBus(CollectionStation entity) {
        List<CollectionStation> collectionStationsn = levelMapper.queryCollectByName(entity.getPlantName());
        List<CollectionStation> collect = collectionStationsn.stream().filter(ele -> entity.getSystemName().equals(ele.getSystemName())).collect(Collectors.toList());

        List<CollectionStation> result = collect.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(CollectionStation::getBusName))
        ), ArrayList::new));
        return result;
    }

    @Override
    public List<CollectionStation> getCollectionLineSwitch(CollectionStation entity) {
        List<CollectionStation> collectionStationsn = levelMapper.queryCollectByName(entity.getPlantName());
        List<CollectionStation> collect = collectionStationsn.stream().filter(ele -> entity.getSystemName().equals(ele.getSystemName())).filter(ele -> ele.getBusName().equals(entity.getBusName())).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<CollectionStation> getCollectionConnect(CollectionStation entity) {
        List<CollectionStation> collectionStationsn = levelMapper.queryCollectByName(entity.getPlantName());
        List<CollectionStation> collect = collectionStationsn.stream().filter(ele -> entity.getSystemName().equals(ele.getSystemName())).filter(ele -> ele.getBusName().equals(entity.getBusName())).filter(ele -> ele.getLineSwitchName().equals(entity.getLineSwitchName())).filter(ele -> ele.getConectDeviceName() != null).
                collect(Collectors.toList());
        return collect;
    }

}
