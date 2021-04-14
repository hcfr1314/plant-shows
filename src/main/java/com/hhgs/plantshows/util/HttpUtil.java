package com.hhgs.plantshows.util;

import com.alibaba.fastjson.JSON;
import com.hhgs.plantshows.model.DO.QueryObjectData;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {
    public static String doPost(String url, String token, String metaObjectName, QueryObjectData queryObjectData) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(url + metaObjectName);

        //queryObjectData.setQueryExpression(stringIds);
        String jsonString = JSON.toJSONString(queryObjectData);
        StringEntity entity = new StringEntity(jsonString, "utf-8");

        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("authorization", token);

        //响应模型
        CloseableHttpResponse response = null;
        String result = null;

        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());

            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity);
                System.out.println("响应长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + result);
            }
        } catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

