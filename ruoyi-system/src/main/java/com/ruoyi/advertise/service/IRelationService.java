package com.ruoyi.advertise.service;

import com.ruoyi.advertise.domain.AdvRelation;

import java.util.List;

public interface IRelationService {
    List<AdvRelation> selectRelationByParams(AdvRelation advRelation);

    int insertRelation(AdvRelation relation);

    AdvRelation selectRelationById(Long id);

    void deleteRelationById(Long id);

    int updateRelation(AdvRelation relation);

    List<AdvRelation> selectRelationByCompanyId(Long companyId);
}
