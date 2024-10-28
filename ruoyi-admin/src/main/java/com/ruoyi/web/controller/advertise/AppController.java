package com.ruoyi.web.controller.advertise;

import com.ruoyi.advertise.domain.AdvCompany;
import com.ruoyi.advertise.service.impl.CompanyService;
import com.ruoyi.advertise.service.impl.RelationService;
import com.ruoyi.advertise.service.impl.SpaceService;
import com.ruoyi.common.core.page.TwbgDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twbg/advertise/")
public class AppController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private SpaceService spaceService;

    @GetMapping
    public TwbgDataInfo getAdvertise(String companyId, String platform, String className) {
        TwbgDataInfo result = new TwbgDataInfo();
        AdvCompany company = companyService.selectCompanyById(Long.valueOf(companyId));
        if (!company.getClassName().equals(className)) {
            result.setCode(-1);
            result.setMsg("包名不匹配～～～");
            return result;
        }

        return result;
    }

}
