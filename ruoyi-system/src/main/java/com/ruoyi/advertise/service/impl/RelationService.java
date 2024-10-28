package com.ruoyi.advertise.service.impl;

import com.ruoyi.advertise.domain.AdvRelation;
import com.ruoyi.advertise.mapper.RelationMapper;
import com.ruoyi.advertise.service.IRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationService implements IRelationService {

    private static final Logger log = LoggerFactory.getLogger(RelationService.class);
    @Autowired
    private RelationMapper relationMapper;

    @Override
    public List<AdvRelation> selectRelationByParams(AdvRelation advRelation) {
        return relationMapper.selectRelationList(advRelation);
    }

    @Override
    public int insertRelation(AdvRelation relation) {
        log.info("{}", relation);
        return relationMapper.insertRelation(relation);
    }

    @Override
    public AdvRelation selectRelationById(Long id) {
        return relationMapper.getRelationById(id);
    }

    @Override
    public void deleteRelationById(Long id) {
        relationMapper.deleteRelationById(id);
    }

    @Override
    public int updateRelation(AdvRelation relation) {
        return relationMapper.updateRelation(relation);
    }

    @Override
    public List<AdvRelation> selectRelationByCompanyId(Long companyId) {
        return List.of();
    }
}
