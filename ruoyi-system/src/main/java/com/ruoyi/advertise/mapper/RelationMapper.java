package com.ruoyi.advertise.mapper;

import com.ruoyi.advertise.domain.AdvSpace;

import java.util.List;

public interface SpaceMapper {

    public List<AdvSpace> selectSpaceList(AdvSpace advSpace);

    public int insertSpace(AdvSpace advSpace);

    public int deleteSpaceById(long id);

    AdvSpace getSpaceById(Long id);

    int updateSpace(AdvSpace space);
}
