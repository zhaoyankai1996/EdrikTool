package org.edrik.tool.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2022/5/16 9:56
 */
public class HttpTool {
    private static CloseableHttpClient aDefault;
    private static HttpTool httpInstance;
    public static HttpTool InitHttpTool(){
        if(httpInstance != null){
            return httpInstance;
        }
        httpInstance = new HttpTool();
        aDefault = HttpClients.createDefault();
        return httpInstance;
    }
    public String doGet(HttpConfig httpConfig){
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(httpConfig.getGetUrl());
            if(httpConfig != null){
                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpConfig.getConnectTimeout())// 连接主机服务超时时间
                        .setConnectionRequestTimeout(httpConfig.getConnectionRequestTimeout())
                        .setSocketTimeout(httpConfig.getSocketTimeout())
                        .build();
                httpGet.setConfig(requestConfig);
                if(httpConfig.getHeader() != null){
                    for (String key : httpConfig.getHeader().keySet()) {
                        httpGet.addHeader(key,httpConfig.getHeader().get(key));
                    }
                }
                if(httpConfig.getContentType() != null){
                    httpGet.setHeader("Content-Type",httpConfig.getContentType().getType());
                }
            }
            response = aDefault.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public String doPost(HttpConfig httpConfig){
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(httpConfig.getUrl());
            if(httpConfig != null){
                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpConfig.getConnectTimeout())// 连接主机服务超时时间
                        .setConnectionRequestTimeout(httpConfig.getConnectionRequestTimeout())
                        .setSocketTimeout(httpConfig.getSocketTimeout())
                        .build();
                httpPost.setConfig(requestConfig);
                if(httpConfig.getHeader() != null){
                    for (String key : httpConfig.getHeader().keySet()) {
                        httpPost.addHeader(key,httpConfig.getHeader().get(key));
                    }
                }
                if(httpConfig.getContentType() != null){
                    httpPost.setHeader("Content-Type",httpConfig.getContentType().getType());
                    if(httpConfig.getParam() != null){
                        if(ContentType.XWwwFormUrlencoded == httpConfig.getContentType()){
                            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                            for (String key : httpConfig.getParam().keySet()) {
                                nvps.add(new BasicNameValuePair(key, httpConfig.getParam().get(key).toString()));
                            }
                            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                        }else if(ContentType.Json == httpConfig.getContentType()){
                            String jsonString = JSON.toJSONString(httpConfig.getParam());
                            StringEntity entity = new StringEntity(jsonString, "utf-8");
                            httpPost.setEntity(entity);
                        }
                    }
                }else{
                    if(httpConfig.getParam() != null){
                        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        for (String key : httpConfig.getParam().keySet()) {
                            nvps.add(new BasicNameValuePair(key, httpConfig.getParam().get(key).toString()));
                        }
                        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                    }
                }
            }
            response = aDefault.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
