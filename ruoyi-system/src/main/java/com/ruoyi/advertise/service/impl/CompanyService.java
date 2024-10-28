package com.ruoyi.advertise.service.impl;

import com.ruoyi.advertise.domain.AdvCompany;
import com.ruoyi.advertise.mapper.CompanyMapper;
import com.ruoyi.advertise.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<AdvCompany> selectCompanyByParams(AdvCompany advCompany) {
        return companyMapper.selectCompanyList(advCompany);
    }

    @Override
    public int insertCompany(AdvCompany company) {
        return companyMapper.insertCompany(company);
    }

    @Override
    public AdvCompany selectCompanyById(Long id) {
        return companyMapper.getCompanyById(id);
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyMapper.deleteCompanyById(id);
    }

    @Override
    public int updateCompany(AdvCompany company) {
        return companyMapper.updateCompany(company);
    }
}
