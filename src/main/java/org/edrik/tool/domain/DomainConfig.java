package org.edrik.tool.domain;

import java.util.Map;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/8/21 15:38
 */
public abstract class DomainConfig implements DomainBaseConfig {
    /**
     * 默认实现
     * @param key 字段key
     * @param o 包含key的对象
     * @return
     */
    public Object customGetField(String key, Object o) {
        Map a = (Map) o;
        return a.get(key);
    }

    /**
     * 默认实现
     * @param fieldName 实体类的字段
     * @return
     */
    public String fieldMapping(String fieldName) {
        return fieldName;
    }

    /**
     * 默认实现
     * @param field 字段名称
     * @param value 字段值
     * @return 返回处理后的字段值
     */
    public Object processFieldValue(String field,Object value) {
        return value;
    }
}
