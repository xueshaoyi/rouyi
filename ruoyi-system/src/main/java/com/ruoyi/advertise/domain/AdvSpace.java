package com.ruoyi.advertise.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class AdvSpace extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String advId;
    private String advName;
    private String appId;
    private String advPlatform;
    private Integer advType;
    private Integer distributton;
    private String projectPlatform;
    private Integer isDelete;
    private String createUser;
    private String updateUser;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getDistributton() {
        return distributton;
    }

    public void setDistributton(Integer distributton) {
        this.distributton = distributton;
    }

    public String getProjectPlatform() {
        return projectPlatform;
    }

    public void setProjectPlatform(String projectPlatform) {
        this.projectPlatform = projectPlatform;
    }

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public String getAdvPlatform() {
        return advPlatform;
    }

    public void setAdvPlatform(String advPlatform) {
        this.advPlatform = advPlatform;
    }

    public Integer getAdvType() {
        return advType;
    }

    public void setAdvType(Integer advType) {
        this.advType = advType;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
