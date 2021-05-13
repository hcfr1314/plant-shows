package com.hhgs.plantshows.mapper.mysqlAvatar4;

import com.hhgs.plantshows.model.DO.Bcs;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Qualifier("mysqlAvatar4Template")
public interface AvatarBcsMapper {

    List<Bcs> queryBcsByOrgId(@Param("orgIds") List<String> orgIds);
}
