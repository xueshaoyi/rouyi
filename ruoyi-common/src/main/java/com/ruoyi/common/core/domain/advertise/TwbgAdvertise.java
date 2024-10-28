package com.ruoyi.common.core.domain.advertise;

import java.util.List;

public class TwbgAdvertise {

    private static final long serialVersionUID = 1L;
    private String fyappid;
    private Integer loadModel;
    private Integer serialModel;
    private List<AdvPlatform> platform;

    public String getFyappid() {
        return fyappid;
    }

    public void setFyappid(String fyappid) {
        this.fyappid = fyappid;
    }

    public Integer getLoadModel() {
        return loadModel;
    }

    public void setLoadModel(Integer loadModel) {
        this.loadModel = loadModel;
    }

    public Integer getSerialModel() {
        return serialModel;
    }

    public void setSerialModel(Integer serialModel) {
        this.serialModel = serialModel;
    }

    public List<AdvPlatform> getPlatform() {
        return platform;
    }

    public void setPlatform(List<AdvPlatform> platform) {
        this.platform = platform;
    }
}
