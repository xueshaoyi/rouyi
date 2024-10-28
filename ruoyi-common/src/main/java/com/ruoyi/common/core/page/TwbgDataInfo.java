package com.ruoyi.common.core.page;

import org.apache.poi.ss.formula.functions.T;

public class TwbgDataInfo {
    private static final long serialVersionUID = 1L;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;

    private T data;


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
}
