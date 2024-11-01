package com.ruoyi.advertise.mapper;

import com.ruoyi.advertise.domain.AdvRelation;

import java.util.List;

public interface RelationMapper {

    public List<AdvRelation> selectRelationList(AdvRelation advRelation);

    public int insertRelation(AdvRelation advRelation);

    public int deleteRelationById(long id);

    AdvRelation getRelationById(Long id);

    int updateRelation(AdvRelation relation);

    List<AdvRelation> selectRelationByCompanyId(Long companyId);
}
