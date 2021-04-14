package com.hhgs.plantshows.common;

public enum ErrorCodeEnum {
    success(0),
    fail(1),
    exception(2),
    partSuccess(3);



    private ErrorCodeEnum(){

    }

    private ErrorCodeEnum(Integer param){

    }
}
