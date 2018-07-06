package com.caad.wechat.utils.sdk;

import com.caad.wechat.utils.viss.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.Properties;

public class ProxyFactory {

    private static Log log = LogFactory.getLog(ProxyFactory.class);
    private static Properties prop;

    static {
        load(ProxyFactory.class);
    }

    public static void load(Class clazz) {
        InputStream in;
        String key = clazz.getName();
        try {
            log.info(key + "获取配置文件开始：");
            in = FileUtils.openInputStreamInClasspath(clazz, "/config.properties");
            prop = new Properties();
            prop.load(in);
        } catch (Exception e) {
            log.error(key + "获取配置文件失败，未找到配置文件！");
            log.error("", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T resolveApiProxy(String className) throws ClassNotFoundException {
        Class<T> inter = (Class<T>) Class.forName(className);
        return (T) Proxy.newProxyInstance(inter.getClassLoader(),
                new Class[]{inter}, new ProxyHandler(inter, new RestActionInvoker(new ApiAuthProvider(), "api",
                        prop.get("app.api.baseurl").toString())));
    }

    @SuppressWarnings("unchecked")
    public static <T> T resolveCustomerApiProxy(String className) throws ClassNotFoundException {
        Class<T> inter = (Class<T>) Class.forName(className);
        return (T) Proxy.newProxyInstance(inter.getClassLoader(),
                new Class[]{inter}, new ProxyHandler(inter, new RestActionInvoker(new CustomApiAuthProvider(), "api",
                        prop.get("app.api.baseurl").toString())));
    }
}


