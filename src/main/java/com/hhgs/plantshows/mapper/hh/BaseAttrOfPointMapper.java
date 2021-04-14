package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.DO.BasicAttribute;
import com.hhgs.plantshows.model.UtilModel.BasicAttributeModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("hhTemplate")
@Repository
public interface BaseAttrOfPointMapper {

    List<BasicAttributeModel> queryByType(@Param("type") int i);

    int insertAttr(BasicAttribute attr);
}
