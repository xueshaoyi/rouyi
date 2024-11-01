package com.ruoyi.common.core.page;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1998307887673028548L;

    private int code;
    private String msg;
    private T data;

    public Response() {

    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Response)) {
            return false;
        }
        return this.getCode() == ((Response) obj).getCode();
    }

    @Override
    public int hashCode() {
        return this.code;
    }

    public boolean ok() {
        return this.code == 0;
    }

    public boolean notOk() {
        return this.code != 0;
    }
}
