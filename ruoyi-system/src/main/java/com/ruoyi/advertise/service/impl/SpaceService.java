package com.ruoyi.advertise.service.impl;

import com.ruoyi.advertise.domain.AdvSpace;
import com.ruoyi.advertise.mapper.SpaceMapper;
import com.ruoyi.advertise.service.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceService implements ISpaceService {

    @Autowired
    private SpaceMapper spaceMapper;

    @Override
    public List<AdvSpace> selectSpaceByParams(AdvSpace advSpace) {
        return spaceMapper.selectSpaceList(advSpace);
    }

    @Override
    public int insertSpace(AdvSpace space) {
        return spaceMapper.insertSpace(space);
    }

    @Override
    public AdvSpace selectSpaceById(Long id) {
        return spaceMapper.getSpaceById(id);
    }

    @Override
    public void deleteSpaceById(Long id) {
        spaceMapper.deleteSpaceById(id);
    }

    @Override
    public int updateSpace(AdvSpace space) {
        return spaceMapper.updateSpace(space);
    }
}
