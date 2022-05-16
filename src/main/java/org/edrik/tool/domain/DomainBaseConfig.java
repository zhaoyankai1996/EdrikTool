package org.edrik.tool.domain;

import java.util.Map;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/8/17 19:06
 */
public interface DomainBaseConfig {
    /**
     * 自定义获取字段
     * @param key 字段key
     * @param o 包含key的对象
     * @return key对应的值
     */
    Object customGetField(String key,Object o);

    /**
     * 字段映射
     * @param fieldName 实体类的字段
     * @return 可自定义实体类中字段对应自定义入参的字段
     */
    String fieldMapping(String fieldName);

    /**
     * 处理字段值
     * @param field 字段名称
     * @param value 字段值
     * @return 返回处理后的字段值
     */
    Object processFieldValue(String field,Object value);
}
