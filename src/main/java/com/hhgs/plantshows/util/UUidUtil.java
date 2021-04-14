package com.hhgs.plantshows.util;

import java.util.UUID;

public class UUidUtil {

    /**
     * 系统生成uuid
     * @return 返回uuid
     */
    public static String generateUUid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


}
