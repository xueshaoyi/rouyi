package com.ruoyi.advertise.mapper;

import com.ruoyi.advertise.domain.AdvCompany;

import java.util.List;

public interface CompanyMapper {

    public List<AdvCompany> selectCompanyList(AdvCompany advCompany);

    public int insertCompany(AdvCompany advCompany);

    public int deleteCompanyById(long id);

    AdvCompany getCompanyById(Long id);

    int updateCompany(AdvCompany company);
}
