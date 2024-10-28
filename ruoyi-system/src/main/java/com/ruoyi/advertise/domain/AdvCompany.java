package com.ruoyi.advertise.domain;


import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class AdvCompany extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String companyName;
    private String className;
    private Integer loadModel;
    private Integer serialModel;
    private Integer isDelete;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
