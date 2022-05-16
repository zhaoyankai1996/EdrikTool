package org.edrik.tool.http;

import java.util.Map;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2022/5/16 10:03
 */
public class HttpConfig {
    private String url;
    private static Map<String,String> header;
    private ContentType contentType;
    private Map<String,Object> param;
    private static Integer connectionRequestTimeout = 35000;
    private static Integer connectTimeout = 35000;
    private static Integer socketTimeout = 60000;

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public static Integer getConnectTimeout() {
        return connectTimeout;
    }

    public static void setConnectTimeout(Integer connectTimeout) {
        HttpConfig.connectTimeout = connectTimeout;
    }

    public String getGetUrl() {
        if(this.param != null){
            String paramStr = "";
            for (String key : this.param.keySet()) {
                paramStr = paramStr + key + "=" + this.param.get(key) + "&";
            }
            return url + "?" + paramStr;
        }
        return url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "HttpConfig{" +
                "header=" + header +
                ", param=" + param +
                ", connectionRequestTimeout=" + connectionRequestTimeout +
                ", socketTimeout=" + socketTimeout +
                '}';
    }

    public static void main(String[] args) {
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setContentType(ContentType.Json);
        System.err.println(ContentType.XWwwFormUrlencoded == httpConfig.getContentType());
    }
}
