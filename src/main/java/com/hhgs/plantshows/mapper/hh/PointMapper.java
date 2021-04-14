package com.hhgs.plantshows.mapper.hh;

import com.hhgs.plantshows.model.BO.Point;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointMapper {
    List<Point> queryByType();

    List<Point> queryAllPoint();

    List<Point> queryIndicatorGrapIndex(@Param("plantCode") int plantCode);
}
