package com.caad.wechat.utils.sdk;

import java.lang.reflect.Method;

/**
 * 方法执行器
 */
public interface IActionInvoker {
    /**
     * 执行方法
     */
    Object invoke(Class proxyType, Method method, Object[] args) throws Exception;
}
