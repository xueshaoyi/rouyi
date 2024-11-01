package com.ruoyi.web.controller.advertise;

import com.ruoyi.advertise.domain.AdvCompany;
import com.ruoyi.advertise.domain.AdvRelation;
import com.ruoyi.advertise.domain.AdvSpace;
import com.ruoyi.advertise.service.impl.CompanyService;
import com.ruoyi.advertise.service.impl.RelationService;
import com.ruoyi.advertise.service.impl.SpaceService;
import com.ruoyi.common.core.domain.advertise.AdvPlatform;
import com.ruoyi.common.core.domain.advertise.AdvPlatformIds;
import com.ruoyi.common.core.domain.advertise.TwbgAdvertise;
import com.ruoyi.common.core.page.Response;
import com.ruoyi.common.core.page.TwbgDataUtil;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/twbg/advertise/")
public class AppController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private SpaceService spaceService;

    @GetMapping("/data")
    public Response getAdvertise(String companyId, String platform, String className) {
        AdvCompany company = companyService.selectCompanyById(Long.valueOf(companyId));
        if (!company.getClassName().equals(className)) {
            return TwbgDataUtil.makeFail("包名不匹配～～～");
        }
        TwbgAdvertise advertise = new TwbgAdvertise();
        advertise.setFyappid(company.getId().toString());
        advertise.setLoadModel(company.getLoadModel());
        advertise.setSerialModel(company.getSerialModel());
        List<AdvRelation> relations = relationService.selectRelationByCompanyId(company.getId());
        List<AdvPlatform> platforms = new ArrayList<>();
        for (AdvRelation relation : relations) {
            AdvPlatform platform1 = new AdvPlatform();
            List<AdvSpace> advSpaces = spaceService.selectSpaceBySpaceId(relation.getSpaceId(), platform);
            if (Collections.isEmpty(advSpaces)) {
                continue;
            }
            platform1.setId(Long.valueOf(advSpaces.get(0).getAdvPlatform()));
            platform1.setAppid(advSpaces.get(0).getAppId());
            platform1.setAdids(spaceToPlatformIds(advSpaces));
            platforms.add(platform1);
        }
        advertise.setPlatform(platforms);
        return TwbgDataUtil.makeResponse(advertise);
    }

    private List<AdvPlatformIds> spaceToPlatformIds(List<AdvSpace> advSpaces) {
        List<AdvPlatformIds> platformIds = new ArrayList<>();
        advSpaces.stream().forEach(advSpace -> {
            AdvPlatformIds platformId = new AdvPlatformIds();
            platformId.setAdid(advSpace.getAdvId());
            platformId.setAdtype(advSpace.getAdvType());
            platformId.setDistributton(advSpace.getDistributton());
            platformIds.add(platformId);
        });
        return platformIds;
    }

}
