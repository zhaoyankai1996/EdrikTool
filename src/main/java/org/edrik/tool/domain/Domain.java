package org.edrik.tool.domain;

import org.edrik.annotation.process;
import org.edrik.util.BaseUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author mohuangNPC
 * @version 1.0
 * @date 2021/8/17 19:03
 */
public class Domain {
    private static Domain DomainInstance;

    /**
     * 获取Domain工具类实例
     *
     * @return Domain实例
     */
    public static Domain InitDomainTool() {
        if (DomainInstance == null) {
            DomainInstance = new Domain();
        }
        return DomainInstance;
    }

    /**
     * 常规初始化实体类方法
     *
     * @param map 实体类的字段名称以及字段值map
     * @param obj 实体类
     * @return 初始化好的实体类
     * @throws IllegalAccessException
     */
    public Object InitDomain(Map map, Object obj) throws IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Object o = map.get(declaredField.getName());
            if (o != null) {
                declaredField.setAccessible(true);
                declaredField.set(obj, o);
            }
        }
        return obj;
    }

    /**
     * 自定义入参初始化实体类的方法
     *
     * @param param        自定义入参,为Object类型
     * @param obj          实体类
     * @param domainConfig 配置器,必填,用于配置如何将字段对应值从自定义入参中取出
     * @return 实体类
     * @throws IllegalAccessException
     */
    public Object InitDomain(Object param, Object obj, DomainConfig domainConfig) throws IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        boolean annotationPresent = obj.getClass().isAnnotationPresent(process.class);
        for (Field declaredField : declaredFields) {
            String mappingName = domainConfig.fieldMapping(declaredField.getName());
            Object o = domainConfig.customGetField(BaseUtil.isEmpty(mappingName) ? declaredField.getName() : mappingName, param);
            if(annotationPresent || declaredField.isAnnotationPresent(process.class)){
                o = domainConfig.processFieldValue(declaredField.getName(),o);
            }
            if (o != null) {
                declaredField.setAccessible(true);
                declaredField.set(obj, o);
            }
        }
        return obj;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
//        Map map = new HashMap();
//        map.put("key1", "1");
//        map.put("key2", "2");
//        map.put("key3", "3");
//        Domain domain = Domain.InitDomainTool();
//        A a = (A) domain.InitDomain(map, new A(), new DomainConfig() {
//            public Object customGetField(String key, Object o) {
//                Map a = (Map) o;
//                return a.get(key);
//            }
//
//            @Override
//            public Object processFieldValue(String field,Object value) {
//                String a = (String) value;
//                return a+"处理后的";
//            }
//        });
//        System.err.println(a);
    }

}
