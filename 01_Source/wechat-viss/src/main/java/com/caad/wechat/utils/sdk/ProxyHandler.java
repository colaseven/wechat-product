package com.caad.wechat.utils.sdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private Class<?> interfactType;
    private IActionInvoker invoker;

    ProxyHandler(Class<?> type, IActionInvoker invoker) {
        this.interfactType = type;
        this.invoker = invoker;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里排除掉一些Object自带的方法
        String methodName = method.getName();
        if (methodName .equals("getClass") )
            return interfactType.getName();
        if (methodName .equals("toString") )
            return method.toString();
        //调用执行器执行方法
        return invoker.invoke(interfactType, method, args);
    }
}
