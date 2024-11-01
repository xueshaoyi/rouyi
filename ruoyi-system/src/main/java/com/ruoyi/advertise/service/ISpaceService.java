package com.ruoyi.advertise.service;

import com.ruoyi.advertise.domain.AdvSpace;

import java.util.List;

public interface ISpaceService {
    List<AdvSpace> selectSpaceByParams(AdvSpace advSpace);

    int insertSpace(AdvSpace space);

    AdvSpace selectSpaceById(Long id);

    void deleteSpaceById(Long id);

    int updateSpace(AdvSpace space);

    List<AdvSpace> selectSpaceBySpaceId(Long spaceId, String platform);
}
