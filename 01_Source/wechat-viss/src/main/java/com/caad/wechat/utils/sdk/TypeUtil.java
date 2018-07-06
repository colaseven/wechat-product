package com.caad.wechat.utils.sdk;

import javassist.*;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 类型的帮助类
 */
public class TypeUtil {

    /**
     * 判断类型是否为简单类型
     *
     * @param param 类型
     */
    static boolean isSampleType(Class<?> param) {
        if (param == Integer.class || param == int.class
                || param == Double.class || param == double.class
                || param == Float.class || param == float.class
                || param == Long.class || param == long.class
                || param == Boolean.class || param == boolean.class
                || param == Date.class
                || param == String.class)
            return true;
        return false;
    }

    public static String[] getParameterNames(Method method) throws NotFoundException {
        ParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        return discoverer.getParameterNames(method);
    }
}
