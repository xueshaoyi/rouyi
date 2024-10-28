package com.ruoyi.advertise.service;

import com.ruoyi.advertise.domain.AdvCompany;

import java.util.List;

public interface ICompanyService {

    List<AdvCompany> selectCompanyByParams(AdvCompany advCompany);

    int insertCompany(AdvCompany company);

    AdvCompany selectCompanyById(Long id);

    void deleteCompanyById(Long id);

    int updateCompany(AdvCompany company);
}
