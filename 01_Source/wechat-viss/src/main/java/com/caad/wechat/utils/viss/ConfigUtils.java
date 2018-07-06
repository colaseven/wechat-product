package com.caad.wechat.utils.viss;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ConfigUtils {
    private static Log log = LogFactory.getLog(ConfigUtils.class);
    private static Map<String, Properties> propMap = new HashMap<>();

    private ConfigUtils() {

    }

    public static void load(Class clazz, String path) {
        InputStream in = null;
        String key = clazz.getName();
        try {
            log.info(key + "获取配置文件开始：");
            in = FileUtils.openInputStreamInClasspath(clazz, path);
            Properties prop = new Properties();
            prop.load(in);

            if (propMap.containsKey(key)) {
                propMap.remove(key);
            }
            propMap.put(key, prop);
            log.info(key + "获取配置文件结束：");
            //
            //loadConfigPart(DEFAULT_PART);
        } catch (IOException e) {
            log.error(key + "获取配置文件失败，未找到配置文件！");
            log.error("", e);
        } finally {
            IoUtils.closeQuietly(in);
        }
    }

    public static String getValue(Class clazz, String key, String defaul) {
        if (StringUtils.isEmptyOrNull(key))
            return defaul;
        if (!propMap.containsKey(clazz.getName()))
            return defaul;
        if (!propMap.get(clazz.getName()).containsKey(key))
            return defaul;
        return propMap.get(clazz.getName()).getProperty(key);
    }

    public static String getValue(Class clazz, String key) {
        return getValue(clazz, key, "");
    }

    public static int getIntValue(Class clazz, String key, int defaul) {
        return Integer.parseInt(getValue(clazz, key, defaul + ""));
    }

    public static int getIntValue(Class clazz, String key) {
        return getIntValue(clazz, key, 0);
    }

    public static boolean getBooleanValue(Class clazz, String key, boolean defaul) {
        return Boolean.parseBoolean(getValue(clazz, key, defaul + ""));
    }

    public static boolean getBooleanValue(Class clazz, String key) {
        return getBooleanValue(clazz, key, true);
    }

    public static Properties getProp(Class clazz) {
        if (clazz == null || !propMap.containsKey(clazz.getName()))
            return null;
        return propMap.get(clazz.getName());
    }
}
