package com.hhgs.plantshows.util;

import com.alibaba.fastjson.JSONObject;
import com.hhgs.plantshows.model.BO.HbaseResult;
import com.hhgs.plantshows.model.BO.LoginCheckResult;
import com.hhgs.plantshows.model.DO.QueryObjectData;

public class SelectDataFromInfluxdb {


    private static final String URL = "http://172.28.8.25/avatar-datamanage-service/data/queryMetaObjData?metaObjectName=";

    private static final String SUCCESS_CODE = "0";

    public static HbaseResult getDataFromInfluxdb(String dataObjectName, long startTime, long endTime, long id) {

        String p = "{'starttime': '" + startTime + "ms','id':'" + id + "','fillPoint':'" + 0 + "','sampled':'" + 1800 + "','endtime': '" + endTime + "ms'}";

        QueryObjectData param = new QueryObjectData(p, null);

        //调用阿凡达接口，首先需要获取token
        LoginCheckResult result = null;
        try {
            result = LoginCheckUtils.LoginCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!SUCCESS_CODE.equals(result.getStatusCode())) {
            return null;
        }

        String token = result.getData();

        //调用阿凡达接口
        String json = HttpUtils.getInstance().doPostByToken(URL, token, dataObjectName, param);

        HbaseResult hbaseResult = JSONObject.parseObject(json, HbaseResult.class);

        if (SUCCESS_CODE.equals(hbaseResult.getStatusCode())) {
            return hbaseResult;
        }

        return null;
    }
}
