package com.hhgs.plantshows.mapper.hh;


import com.hhgs.plantshows.model.DO.PlantBigsys;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface DataCardingMapper {

    /**
     * 查询所有大系统
     * @param plantName
     * @return
     */
    List<PlantBigsys> queryBigsys(@Param("plantName") String plantName);

    /**
     * 根据大系统名称查询该大系统下的小系统
     *
     * @param tableName
     * @param bigsysName
     * @return
     */
    List<String> querySmallsysByBigsys(@Param("tableName") String tableName, @Param("bigsysName") String bigsysName);


    /**
     * 根据小系统名称查询该小系统的id
     * @param smallsysName
     * @return
     */
    Integer querySmallsysId(@Param("smallsysName") String smallsysName,@Param("bigsysId")int bigsysId);

    /**
     * 根据大系统和小系统查询该小系统下的设备
     *
     * @param tableName
     * @param bigsysName
     * @param smallsysName
     * @return
     */
    List<String> queryDeviceByBigsysAndSmallsys(@Param("tableName") String tableName, @Param("bigsysName") String bigsysName, @Param("smallsysName") String smallsysName);

    /**
     * 根据大系统和小系统查询该小系统下的支架
     * @param tableName
     * @param bigsysName
     * @param smallsysName
     * @return
     */
    List<String> queryBracketByBigsysAndSmallsys(@Param("tableName") String tableName, @Param("bigsysName") String bigsysName, @Param("smallsysName") String smallsysName);

    /**
     * 根据大系统和小系统查询该小系统下的组件
     * @param tableName
     * @param bigsysName
     * @param smallsysName
     * @return
     */
    List<String> queryComponentByBigsysAndSmallsys(@Param("tableName") String tableName, @Param("bigsysName") String bigsysName, @Param("smallsysName") String smallsysName);

    /**
     * 根据大系统和小系统以及设备名称查询该设备所属标识
     *
     * @param tableName
     * @param bigsysName
     * @param smallsysName
     * @param firstsDeviceName
     * @return
     */
    String queryDeviceSignByBigsysAndSmallsysAndFirstsDeviceName(@Param("tableName") String tableName, @Param("bigsysName") String bigsysName, @Param("smallsysName") String smallsysName, @Param("firstsDeviceName") String firstsDeviceName);

    /**
     * 获取一级设备id
     * @param deviceName
     * @param smallsysId
     * @return
     */
    Integer queryFirstsDeviceId(@Param("firstsDeviceName") String deviceName,@Param("smallsysId")int smallsysId);

    /**
     * 获取一级设备下的二级设备集合
     * @param tableName
     * @param bigsysName
     * @param smallsysName
     * @param firstsDeviceName
     * @return
     */
    List<String> querySecondsDeviceByBigsysAndSmallsysAndFirstsDeviceName(@Param("tableName")String tableName,@Param("bigsysName") String bigsysName, @Param("smallsysName") String smallsysName,@Param("firstsDeviceName") String firstsDeviceName);

    /**
     * 获取二级设备id
     * @param secondsDeviceName
     * @param firstsDeviceId
     * @return
     */
    Integer querySecondsDeviceId(@Param("secondsDeviceName") String secondsDeviceName, @Param("firstsDeviceId") Integer firstsDeviceId);

    /**
     * 获取二级设备标识集合
     * @param tableName
     * @param bigsysName
     * @param smallsysName
     * @param firstsDeviceName
     * @param secondsDeviceName
     * @return
     */
    List<String> querySecondsDeviceSign(@Param("tableName") String tableName, @Param("bigsysName") String bigsysName, @Param("smallsysName") String smallsysName, @Param("firstsDeviceName") String firstsDeviceName,@Param("secondsDeviceName") String secondsDeviceName);
}
