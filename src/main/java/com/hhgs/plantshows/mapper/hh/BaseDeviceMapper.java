package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.UtilModel.BasicDevice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface BaseDeviceMapper {

    void batchInsert(List<BasicDevice> list);

}
