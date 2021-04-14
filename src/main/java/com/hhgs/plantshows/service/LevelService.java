package com.hhgs.plantshows.service;

import com.hhgs.plantshows.model.DO.BootsterStation;
import com.hhgs.plantshows.model.DO.CollectionStation;

import java.util.List;

/**
 * 升压站和汇集站层级service
 */
public interface LevelService {

    List<BootsterStation> queryMainSystemByStationName(BootsterStation entity);

    List<BootsterStation> getMainSwitch(BootsterStation entity);

    List<BootsterStation> getBus(BootsterStation entity);

    List<BootsterStation> getLineSwitch(BootsterStation entity);

    List<BootsterStation> getLower(BootsterStation entity);

    List<CollectionStation> getCollectionStation(CollectionStation entity);

    List<CollectionStation> getCollectionBus(CollectionStation entity);

    List<CollectionStation> getCollectionLineSwitch(CollectionStation entity);

    List<CollectionStation> getCollectionConnect(CollectionStation entity);
}
