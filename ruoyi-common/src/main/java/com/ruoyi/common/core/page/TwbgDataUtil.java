package com.ruoyi.common.core.page;

import com.ruoyi.common.core.domain.advertise.TwbgAdvertise;
import org.apache.poi.ss.formula.functions.T;

public class TwbgDataUtil {
    public static <T> Response<T> makeResponse(T obj) {
        Response<T> result = new Response<>();
        result.setCode(0);
        result.setMsg("success");
        result.setData(obj);
        return result;
    }

    public static Response makeFail(String s) {
        Response<T> result = new Response<>();
        result.setCode(-1);
        result.setMsg(s);
        result.setData(null);
        return result;
    }
}
