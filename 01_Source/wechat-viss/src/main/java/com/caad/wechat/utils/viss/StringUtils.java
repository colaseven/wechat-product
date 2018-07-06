package com.caad.wechat.utils.viss;

import java.util.Collection;


public class StringUtils {

    private StringUtils() {

    }

    /**
     * 为 null, ""
     *
     * @param str
     * @return boolean
     * <p>
     * 功能同：org.apache.commons.lang.StringUtils.isEmpty(String str)
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 为 null, "", "NULL", "null", "Null"
     * 功能同：isEmpty(str)，并加上"null", "NULL"的判断。
     *
     * @param str
     * @return
     */
    public static boolean isEmptyOrNull(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
    }

    /**
     * 非 null, ""
     *
     * @param str
     * @return boolean
     * <p>
     * 功能同：org.apache.commons.lang.StringUtils.isNotEmpty(String str)
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 非 null, "", "NULL", "null", "Null"
     *
     * @param str
     * @return
     */
    public static boolean isNotEmptyOrNull(String str) {
        return !isEmptyOrNull(str);
    }

    /**
     * @param str
     * @return
     * @description: Sting转换为int
     * @author： caad
     * @createTime：2015年10月21日 上午10:24:35
     */
    public static int parseInt(Object str) {
        return (str == null || "".equals(str)) ? 0 : Integer.parseInt(new java.text.DecimalFormat("0").format(parseDouble(str)));
    }

    /**
     * @param str
     * @return
     * @description: Sting转换为int
     * @author： caad
     * @createTime：2015年10月21日 上午10:24:35
     */
    public static int parseInt(String str, int def) {
        return (str == null || "".equals(str)) ? def : Integer.parseInt(str);
    }

    /**
     * @param str
     * @return
     * @description: 字符串转换为double类型
     * @author： caad
     * @createTime：2015年10月21日 上午10:31:57
     */
    public static double parseDouble(Object str) {
        try {
            if (str == null || "".equals(str)) {
                return 0;
            } else {
                return Double.parseDouble(str.toString());
            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Sting转换为long
     *
     * @param str
     * @return
     */
    public static long parseLong(Object str) {
        try {
            return (str == null || "".equals(str)) ? 0 : Long.parseLong(str.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * @param str
     * @return
     * @description: Sting转换为long
     * @author： caad
     * @createTime：2015年10月21日 上午10:24:35
     */
    public static long parseLong(String str, long def) {
        return (str == null || "".equals(str)) ? def : Long.parseLong(str);
    }

    /**
     * 功能同：org.apache.commons.lang.StringUtils.defaultString(String str)
     *
     * @param str
     * @return
     */
    public static String dealEmpty(String str) {
        return str == null ? "" : str;
    }


    /**
     * 判断boolean
     *
     * @param obj
     * @return
     */
    public static boolean isEmptyOrNull(Object obj) {
        return obj == null || obj.toString().length() == 0 || obj.toString().equalsIgnoreCase("null");
    }

    /**
     * 满足任何一个正则表达式。
     *
     * @param string
     * @param regexp
     * @return
     */
    public static boolean matchRegexp(String string, String regexp) {
        if (string == null) {
            return false;
        }
        return string.matches(regexp);
    }


    public static String join(Collection<?> collection, String separator) {
        return org.apache.commons.lang.StringUtils.join(collection, separator);
    }

}
