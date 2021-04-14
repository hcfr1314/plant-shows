package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface MultiLevelMapper {

    /**
     * 查询区域列表
     * @param plantId
     * @return
     */
    List<PlantBigsys> queryBigsysList(@Param("plantId") int plantId);


    /**
     * 查询方阵列表
     * @param bigsysId
     * @returns
     */
    List<BigsysSmallsys> querySmallsyslist(@Param("bigsysId") int bigsysId);

    /**
     * 查询一级设备列表
     * @param smallsysId
     * @return
     */
    List<SmallsysFirstsDevice> queryFirstsDeviceList(@Param("smallsysId") int smallsysId);

    /**
     * 查询二级设备列表
     * @param firstsDeviceId
     * @return
     */
    List<FirstsDeviceSecondsDevice> querySecondsDeviceList(@Param("firstsDeviceId") int firstsDeviceId);

    /**
     * 查询二级设备的设备标识
     * @param deviceId
     * @return
     */
    String querySecondsDeviceSign(@Param("deviceId") int deviceId);

    /**
     * 查询一级设备的设备标识
     * @param deviceId
     * @return
     */
    String queryFirstsDeviceSign (@Param("deviceId") int deviceId);

    /**
     * 查询支架类型
     * @param smallsysId
     * @return
     */
    String queryBracketType(@Param("smallsysId") int smallsysId);

    /**
     * 查询组件类型
     * @param smallsysId
     * @return
     */
    String queryComponentType(@Param("smallsysId") int smallsysId);

    /**
     * 根据大系统id查询
     * @return
     */
    List<SmallsysFirstsDevice> queryDeviceTypeByBigsysId(@Param("bigSysId") int bigSysId);

    /**
     * 根据大系统设备名称查询某类设备的设备标识
     * @return
     */
    List<String> queryDeviceSignByDeviceId(@Param("deviceId") int deviceId);

    /**
     * 根据设备标识查询设备模型
     * @param deviceSignName
     * @return
     */
    List<String> queryDeviceModeByDeviceSign (@Param("deviceSignName") String deviceSignName);

    /**
     * 根据测点英文名称查询数据
     * @param plantTable
     * @param importData
     * @return
     */
    List<ImportData> queryDataByCondition (@Param("plantTable") String plantTable, @Param("plantName") String  plantName, @Param("importData") ImportData importData);
}
