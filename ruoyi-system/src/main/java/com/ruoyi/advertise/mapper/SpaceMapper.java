package com.ruoyi.advertise.mapper;

import com.ruoyi.advertise.domain.AdvSpace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpaceMapper {

    public List<AdvSpace> selectSpaceList(AdvSpace advSpace);

    public int insertSpace(AdvSpace advSpace);

    public int deleteSpaceById(long id);

    AdvSpace getSpaceById(Long id);

    int updateSpace(AdvSpace space);

    List<AdvSpace> selectSpaceByIdAndPlatform(@Param("id") long id, @Param("projectPlatform") String projectPlatform);
}
