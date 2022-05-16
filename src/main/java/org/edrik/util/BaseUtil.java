package org.edrik.util;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/8/21 15:23
 */
public class BaseUtil {
    public static boolean isEmpty(String field){
        if(field == null || "".equals(field.replace(" ",""))){
            return true;
        }
        return false;
    }
}
