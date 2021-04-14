package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.BO.BaseDeviceModelAndAttr;
import com.hhgs.plantshows.model.DO.BaseDeviceModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface BasicDeviceModelMapper {


    //添加一条数据到设备模型表
    int addData(@Param("baseDeviceModel") BaseDeviceModel baseDeviceModel);

    //修改数据
    int editData(@Param("baseDeviceModel") BaseDeviceModel baseDeviceModel);

    int delByDeviceIdAndAttrId(@Param("deviceId") int deviceId, @Param("attrId") int attrId);

    List<BaseDeviceModelAndAttr> queryByType(@Param("deviceType") int deviceType,@Param("deviceName") String deviceName);

    int deleteDevcie(@Param("deviceName") String deviceName,@Param("type") int type);
}
