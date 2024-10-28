package com.ruoyi.common.core.domain.advertise;

import java.util.List;

public class AdvPlatform {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String appid;
    private Integer distributton;
    private List<AdvPlatformIds> adids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Integer getDistributton() {
        return distributton;
    }

    public void setDistributton(Integer distributton) {
        this.distributton = distributton;
    }

    public List<AdvPlatformIds> getAdids() {
        return adids;
    }

    public void setAdids(List<AdvPlatformIds> adids) {
        this.adids = adids;
    }
}
