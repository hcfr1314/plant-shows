package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface UitlMapper {

    //批量插入数据
    void insertSmallsys(@Param("list") List<BigsysSmallsys> list) ;

    void insertFirstsDevice(@Param("list") List<SmallsysFirstsDevice> list) ;

    void insertFirstsDeviceSign(@Param("firstsDeviceSignList") List<FirstsDeviceSign> firstsDeviceSignList);

    void insertBracket(@Param("list") List<SmallsysBracket> list);

    void insertComponent(@Param("list") List<SmallsysComponent> list);

    void insertFirstsDeviceSecondsDevice(@Param("list")List<FirstsDeviceSecondsDevice> firstsDeviceSecondsDeviceList);

    void insertSecondsDeviceSign(@Param("list") List<SecondsDeviceSign> secondsDeviceSignList);

    /**
     * 给大系统表中插入数据
     *
     * @param plantId
     * @param plantName
     * @param plantTable
     */
    void insertPlantBigSys(@Param("plantId") int plantId, @Param("plantName") String plantName, @Param("plantTable") String plantTable);

    /**
     * 删除原来的层级数据
     */
    void deleteOriginalData(@Param("plantName") String plantName);

    /**
     * 更新场站数据
     * @param plantTable
     */
    void updateData(@Param("plantTable")String plantTable);

}
