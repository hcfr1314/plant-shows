package com.hhgs.plantshows.util;

import com.hhgs.plantshows.common.ErrorCodeEnum;
import com.hhgs.plantshows.common.Pages;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseResponse implements Serializable {


    private static Map<ErrorCodeEnum,String> map=new HashMap<>();
    private String message;

    private Object data;

    private int code;

    private Pages pages;

    static {
        map.put(ErrorCodeEnum.success,"操作成功：");
        map.put(ErrorCodeEnum.fail,"操作失败：");
        map.put(ErrorCodeEnum.exception,"操作异常：");
        map.put(ErrorCodeEnum.partSuccess,"部分成功：");
    }

    public  static BaseResponse initSuccess(Object data,Pages pages) {
        BaseResponse base = new BaseResponse("success", data, 200,pages);
        return base;
    }

    /**
     * 成功返回
     * @param object 业务数据
     * @return CommonResponse
     */
    public static BaseResponse initSuccessBaseResponse(Object object) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorCodeEnum.success.ordinal());
        baseResponse.setMessage(map.get(ErrorCodeEnum.success));
        baseResponse.setData(object);
        return baseResponse;
    }



    public static BaseResponse initError(Object data,String message) {
        return new BaseResponse(message, data, 500, null);
    }

    public BaseResponse() {
    }

    public BaseResponse(String message, Object data, int code,Pages pages) {
        this.message = message;
        this.data = data;
        this.code = code;
        this.pages  =pages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }
}
