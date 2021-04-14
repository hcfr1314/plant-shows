package com.hhgs.plantshows.mapper.hh;


import com.hhgs.plantshows.model.DO.IndexCofficient;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("hhTemplate")
@Repository
public interface IndexCofficientMapper {


    /**
     * 查询指标系数信息
     * @return
     */
    IndexCofficient queryIndexMessage();

    /**
     * 编辑指标系数
     * @param indexCofficient
     * @return
     */
    int editData (@Param("indexCofficient") IndexCofficient indexCofficient);
}
