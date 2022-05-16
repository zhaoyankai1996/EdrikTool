package org.edrik.tool.http;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2022/5/16 16:08
 */
public enum ContentType {
    XWwwFormUrlencoded("application/x-www-form-urlencoded;charset=utf-8"),
    MultipartFormData("multipart/form-data"),
    Json("application/json;charset=utf-8"),
    TextPlain("text/plain");
    private String type;
    ContentType(String s) {
        this.type = s;
    }
    public String getType() {
        return type;
    }
}
