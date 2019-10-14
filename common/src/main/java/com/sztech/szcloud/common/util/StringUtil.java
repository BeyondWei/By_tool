package com.sztech.szcloud.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class StringUtil {

    /**
     * 通过反射将对象中的空字符串设置成null
     *
     * @param object
     * @return
     */
    public static Object paramToNull(Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (StringUtils.isEmpty(value.toString())) {
                    field.set(object, null);
                }
            } catch (Exception e) {
            }
        }
        return object;
    }

    /**
     * 将null的字符更改成空
     */
    public static String paramToEmpty(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        return param;
    }

    /**
     * Object 转 String
     */
    public static String objectToString(Object o) {
        return o == null ? null : o.toString();
    }

}
