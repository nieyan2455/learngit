package com.ny.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClient工具类
 *
 * @author nieyan
 * @create 2018-10-25 10:03
 **/
public class HttpClientUtil {
    public static String doPostForJson(String url,String jsonParams){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(60 * 1000).setConnectionRequestTimeout(60 * 1000)
                .setSocketTimeout(60 * 1000).setRedirectsEnabled(true).build();

        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json");  //
        try {
            httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", "utf-8")));
            System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(" code:"+response.getStatusLine().getStatusCode());
            System.out.println("doPostForJson response"+EntityUtils.toString(response.getEntity()));
            return String.valueOf(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            return "post failure :caused by-->" + e.getMessage().toString();
        }finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
