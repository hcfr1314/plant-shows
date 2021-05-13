package com.hhgs.plantshows.util;

import com.alibaba.fastjson.JSONObject;
import com.hhgs.plantshows.model.BO.LoginCheckResult;

public class LoginCheckUtils {
    public static LoginCheckResult LoginCheck() throws Exception {
        HttpUtils instance = HttpUtils.getInstance();
        String s = instance.executePostWithJson("http://172.28.8.25/avatar-system-service/sys/userrole/loginCheck?formal=false", "{\n" +
                "  \"userId\": \"admin\",\n" +
                "  \"userName\": \"admin\",\n" +
                "\"passWord\":\"1qaz2WSX#\"\n" +
                "}");
        LoginCheckResult result = JSONObject.parseObject(s, LoginCheckResult.class);
        return result;
    }

    public static void main(String[] args) throws Exception {
        LoginCheckResult loginCheckResult = LoginCheck();
        System.out.println(loginCheckResult.getData());
    }
}
