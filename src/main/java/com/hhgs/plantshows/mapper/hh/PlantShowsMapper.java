package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.ImportData;
import com.hhgs.plantshows.model.DO.LcuEquipment;
import com.hhgs.plantshows.model.DO.PlantTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface PlantShowsMapper {


    //根据表名和条件查询场站数据
    List<ImportData> getAllDataWithPlantTableAndCondition(@Param("plantTable") String plantTable, @Param("importData") ImportData importData);

    List<ImportData> getAllDataWithPlantTableAndDescript(@Param("plantTable") String plantTable, @Param("importData") ImportData importData);

    //根据场站名称查询场站所属表名
    String getPlantTableWithPlantName(String plantName);

    //根据场站名称查询场站所属hbase表名
    String getHbaseTableWithPlantName(String plantName);

    //根据plantCode获取lcu信息
    List<LcuEquipment> getLcuAndEquipmentByPlantCode(@Param("plantCode") int plantCode);

    /**
     * 根据场站名称查询plantId
     * @param plantName
     * @return
     */
    int getPlantIdWithPlantName (String plantName);

    /**
     * 更新层级生成状态
     * @param status
     * @param plantName
     */
    void updateHierarchyStatus(@Param("status") int status,@Param("plantName") String plantName);

    int batchInsert( @Param("result") List<ImportData> result,@Param("plantTable") String plantTable);

    /**
     * 获取所有场站名称列表
     * @return
     */
    List<PlantTable> getAllPlantName();

    /**
     * 清空表
     * @param tableName
     */
    void deleteByTableName (@Param("tableName") String tableName);
}
