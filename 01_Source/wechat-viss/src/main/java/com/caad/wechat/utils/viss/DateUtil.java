package com.caad.wechat.utils.viss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期工具。
 * <p>
 * <pre>
 * 说明：
 * 认为 yyyy-mm-dd的日期格式为标准日期格式，并以此作为系统内部的默认日期格式。
 * 所以，外来日期数据，需要进行格式转换，以统一格式。
 * </pre>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: company</p>
 *
 * @author Henry.Yao
 * @version 1.0
 */
public class DateUtil {

    private static Log log = LogFactory.getLog(DateUtil.class);

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * The Constant TIME_MILLIS_OF_ONE_DAY.
     */
    public static final long TIME_MILLIS_OF_ONE_DAY = 1000 * 60 * 60 * 24;

    public static final long TIME_MILLIS_OF_18991230 = -2209190400000L;
    private static final Object[][] ARY_PARSE_DATE_FORMAT_PATTERN1 = {{"^\\d{1,2}-\\d{1,2}月-\\d{1,2}$", "d-M月-yy", null},// 01-2月-00,
            {"^\\d{1,2}-\\d{1,2}月\\s-\\d{1,2}$", "d-M月 -yy", null}, // 01-2月 -00
            {"^\\d{4}年\\d{1,2}月\\d{1,2}日$", "yyyy年M月d日", null},// 2001年2月01日
            {"^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-M-d", null}, // 2006-01-02
            {"^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/M/d", null}, //
            {"^\\d{2}-\\d{1,2}-\\d{1,2}", "yy-M-d", null}, //
            {"^\\d{4}-\\d{1,2}-\\d{1,2}\\s上午\\s\\d{1,2}:\\d{1,2}:\\d{1,2}$", "yyyy-M-d 上午 h:m:s", null},
            {"^\\d{4}-\\d{1,2}-\\d{1,2}\\s下午\\s\\d{1,2}:\\d{1,2}:\\d{1,2}$", "yyyy-M-d 下午 h:m:s", new Long(43200000)},
            {"^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}$", "yyyy-M-d HH:mm:ss.SSS", null}, // 2006-01-02 01:01:01.123
            {"^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}$", "yyyy-M-d HH:mm:ss", null}, // 2006-01-02 01:01:01
            {"^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}$", "yyyy-M-d HH:mm", null}, // 2006-01-02 01:01
            {"^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-M-d", null}, // 2006-01-02
            {"^\\d{4}\\.\\d{1,2}\\.\\d{1,2}$", "yyyy.M.d", null}, // 2006.1.2
            {"^\\d{4}-\\d{1,2}$", "yyyy-M", null}, // 2006-01
            {"\\d{1,2}\\-[A-Za-z]{3}\\-\\d{4}\\s\\d{1,2}\\:\\d{1,2}\\:\\d{1,2}", "dd-MMM-yyyy HH:mm:ss", null}, // "26-Aug-2012 21:13"
            {"\\d{1,2}\\-[A-Za-z]{3}\\-\\d{4}\\s\\d{1,2}\\:\\d{1,2}", "dd-MMM-yyyy HH:mm", null}, // "26-Aug-2012 21:13"
            {"\\d{1,2}\\-[A-Za-z]{3}\\-\\d{4}", "dd-MMM-yyyy", null}, // "26-Aug-2012"
            {"^\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}\\d{2}$", "yyyyMMddHHmmss", null}, // 20060102010101
            {"^\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}$", "yyyyMMddHHmm", null}, // 200601011230
            {"^\\d{4}\\d{2}\\d{2}$", "yyyyMMdd", null}, // 20060101
            {"^\\d{4}年$", "yyyy年", null}, // 2006
            {"^\\d{4}$", "yyyy", null}, // 2006
            {"^\\d{2}\\d{2}\\d{2}$", "yyMMdd", null}// 010203
    };

    private static final Map<String, DateParserByPattern> MAP_PARSE_DATE_FORMAT_PATTERN2 = new LinkedHashMap<>();

    static {
        // 补充检测.NET传来的数据格式。如："2016-07-12T15:02:27.6723082+08:00"
        MAP_PARSE_DATE_FORMAT_PATTERN2.put("\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{7}\\+\\d{2}\\:\\d{2}", new DateParserByPattern() {

            @Override
            public Date parseDateFormat(String strdate) {
                int p = strdate.indexOf(".");
                strdate = strdate.substring(0, p).replaceAll("T", " ");
                return DateUtil.parseDateFormat(strdate);
            }
        });

        // 补充检测Excel的HSSF文件中的日期格式。42517，表示1900-01-01后的第42517天。
        MAP_PARSE_DATE_FORMAT_PATTERN2.put("\\d{5}", new DateParserByPattern() {

            @Override
            public Date parseDateFormat(String strdate) {
                long delta = Long.parseLong(strdate);
                return new Date(TIME_MILLIS_OF_18991230 + delta * TIME_MILLIS_OF_ONE_DAY);
            }
        });
        // 补充检测Excel的HSSF文件中的日期格式。42517.423726851855，表示1900-01-01后的第42517天，0.423726851855，表示过去了多少秒（1/24/60/60）。
        MAP_PARSE_DATE_FORMAT_PATTERN2.put("\\d{5}.\\d{12}", new DateParserByPattern() {

            @Override
            public Date parseDateFormat(String strdate) {
                int p = strdate.indexOf(".");
                long delta = Long.parseLong(strdate.substring(0, p));
                double delta2 = Double.parseDouble("0" + strdate.substring(p));
                return new Date(TIME_MILLIS_OF_18991230 + (long) ((delta + delta2) * TIME_MILLIS_OF_ONE_DAY));
            }
        });
    }

    /**
     * 解析日期文本成日期对象。按照指定格式进行解析.
     *
     * @param strdate the strdate
     * @param format  the format
     * @return java.util.Date
     */
    public static Date parseToDate(String strdate, String format) {
        try {
            return new SimpleDateFormat(format).parse(strdate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param strdate
     * @param format
     * @return
     */
    public static Date parseDateFormat(String strdate, String format) {
        return parseToDate(strdate, format);
    }


    public static Date parseDateFormat(String strdate) {
        return parseToDate(strdate);
    }

    /**
     * 解析日期文本成日期对象.
     *
     * @param strdate the strdate
     * @return java.util.Date
     */
    public static Date parseToDate(String strdate) {
        if (strdate == null || strdate.equals("")) {
            return null;
        }

        try {
            // 预处理
            strdate = strdate.replaceAll(" am ", " 上午 ").replaceAll(" AM ", " 上午 ").replaceAll(" pm ", " 下午 ").replaceAll(" PM ", " 下午 ").replaceAll("/", "-").trim();
            // 第一套循环检测模式
            for (Object[] element : ARY_PARSE_DATE_FORMAT_PATTERN1) {
                String strregexp = (String) element[0];
                String strpattern = (String) element[1];
                Long addtime = (Long) element[2];
                Pattern pattern = Pattern.compile(strregexp);
                Matcher matcher = pattern.matcher(strdate);
                if (matcher.find()) {
                    SimpleDateFormat sdf = new SimpleDateFormat(strpattern, Locale.ENGLISH);
                    Date date = sdf.parse(strdate);
                    if (addtime != null) {
                        date.setTime(date.getTime() + addtime.longValue());
                    }
                    return date;
                }
            }
            // 第二套循环检测模式
            for (Map.Entry<String, DateParserByPattern> entry : MAP_PARSE_DATE_FORMAT_PATTERN2.entrySet()) {
                if (strdate.matches(entry.getKey())) {
                    return entry.getValue().parseDateFormat(strdate);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        log.info("DateUtil.formatDate('" + strdate + "')，无法匹配日期格式，请予以关注。");
        return null;
    }

    /**
     * 日期格式化处理。 传入各个格式的日期字符串，返回统一格式的日期字符串.
     *
     * @param strdate String
     * @return String
     */
    public static String convertDateFormat(String strdate) {
        return convertDateFormat(strdate, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期格式转换。
     * <p>
     * <pre>
     * 传入各个格式的日期字符串，返回统一格式的日期字符串。
     * </pre>
     * <p>
     * .
     *
     * @param strdate  the strdate
     * @param toformat the toformat
     * @return String
     */
    public static String convertDateFormat(String strdate, String toformat) {
        Date date = parseDateFormat(strdate);
        if (date == null) {
            return "";
        }
        if (toformat == null) {
            toformat = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat(toformat);
        return sdf2.format(date);
    }

    // --------------------------------------------------------------------------

    /**
     * 获取当前年的最大日期。
     *
     * @return String
     */
    public static String getMaxDateOfThisYear() {
        return getMaxDateOfThisYear(new Date());
    }

    /**
     * 获取当前年的最大日期.
     *
     * @param strdate the strdate
     * @return the max date of this year
     * @throws ParseException the parse exception
     */
    public static String getMaxDateOfThisYear(String strdate) {
        return getMaxDateOfThisYear(parseDateFormat(strdate));
    }

    /**
     * 获取当前年的最大日期.
     *
     * @param date the date
     * @return String
     */
    public static String getMaxDateOfThisYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取上一年的最大日期.
     *
     * @param strdate the date
     * @return String
     */
    public static String getMaxDateOfLastYear(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDateFormat(strdate).getTime());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取下一年的最大日期.
     *
     * @param strdate the date
     * @return String
     */
    public static String getMaxDateOfNextYear(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDateFormat(strdate).getTime());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取当前年的最小日期。
     *
     * @return the min date of this year
     */
    public static String getMinDateOfThisYear() {
        return getMinDateOfThisYear(new Date());
    }

    /**
     * 获取当前年的最小日期.
     *
     * @param strdate the strdate
     * @return String
     * @throws ParseException the parse exception
     */
    public static String getMinDateOfThisYear(String strdate) {
        return getMinDateOfThisYear(parseDateFormat(strdate));
    }

    /**
     * 获取当前年的最小日期.
     *
     * @param date the date
     * @return the min date of this year
     */
    public static String getMinDateOfThisYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取当前年的最小日期.
     *
     * @param strdate the date
     * @return the min date of this year
     */
    public static String getMinDateOfLastYear(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDateFormat(strdate).getTime());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取当前年的最小日期.
     *
     * @param strdate the date
     * @return the min date of this year
     */
    public static String getMinDateOfNextYear(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDateFormat(strdate).getTime());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获得当前月的最大日期.
     *
     * @return the max date of this month
     */
    public static String getMaxDateOfThisMonth() {
        return getMaxDateOfThisMonth(new Date());
    }

    /**
     * 获取上个月的最大日期.
     *
     * @param strdate the strdate
     * @return the max date of this month
     * @throws ParseException the parse exception
     */
    public static String getMaxDateOfLastMonth(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDateFormat(strdate).getTime());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取下个月的最大日期.
     *
     * @param strdate the strdate
     * @return the max date of this month
     */
    public static String getMaxDateOfNextMonth(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDateFormat(strdate).getTime());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取上个月的最小日期.
     *
     * @param strdate the strdate
     * @return the min date of this month
     * @throws ParseException the parse exception
     */
    public static String getMinDateOfLastMonth(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDateFormat(strdate).getTime());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取上个月的最小日期.
     *
     * @param strdate the strdate
     * @return the min date of this month
     */
    public static String getMinDateOfNextMonth(String strdate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseDateFormat(strdate).getTime());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取当前月的最大日期.
     *
     * @param strdate the strdate
     * @return the max date of this month
     * @throws ParseException the parse exception
     */
    public static String getMaxDateOfThisMonth(String strdate) {
        return getMaxDateOfThisMonth(parseDateFormat(strdate));
    }

    /**
     * 获取当前月的最大日期.
     *
     * @param date the date
     * @return String
     */
    public static String getMaxDateOfThisMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取当前月的最小日期。 by Henry.Yao
     *
     * @return String
     */
    public static String getMinDateOfThisMonth() {
        return getMinDateOfThisMonth(new Date());
    }

    /**
     * 获取当前月的最小日期。 by Henry.Yao
     *
     * @param strdate the strdate
     * @return String
     * @throws ParseException the parse exception
     */
    public static String getMinDateOfThisMonth(String strdate) {
        return getMinDateOfThisMonth(parseDateFormat(strdate));
    }

    /**
     * 获取当前月的最小日期。 by Henry.Yao
     *
     * @param date the date
     * @return the min date of this month
     */
    public static String getMinDateOfThisMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDate("yyyy-MM-dd", calendar.getTime());
    }

    /**
     * 获取当前周的最大日期.
     *
     * @return String
     */
    public static String getMaxDateOfThisWeek() {
        return getMaxDateOfThisWeek(new Date());
    }

    /**
     * 获取当前周的最大日期.
     *
     * @param date the date
     * @return Date
     */
    @SuppressWarnings("deprecation")
    public static String getMaxDateOfThisWeek(Date date) {
        int day = (date.getDay() + 6) % 7; // 0~6 一～日
        return getDate("yyyy-MM-dd", dateAddDays(date, 6 - day));
    }

    /**
     * 获取当前周的最大日期.
     *
     * @param date 6191 7077
     * @return the max date of this week
     * @throws ParseException the parse exception
     */
    public static String getMaxDateOfThisWeek(String date) {
        return getMaxDateOfThisWeek(parseDateFormat(date));
    }

    /**
     * 获取当前周的最小日期.
     *
     * @return String
     */
    public static String getMinDateOfThisWeek() {
        return getMinDateOfThisWeek(new Date());
    }

    /**
     * 获取当前周的最小日期.
     *
     * @param date the date
     * @return the min date of this week
     * @throws ParseException the parse exception
     */
    public static String getMinDateOfThisWeek(String date) {
        return getMinDateOfThisWeek(parseDateFormat(date));
    }

    /**
     * 获取当前周的最小日期.
     *
     * @param date the date
     * @return String
     */
    @SuppressWarnings("deprecation")
    public static String getMinDateOfThisWeek(Date date) {
        int day = (date.getDay() + 6) % 7; // 0~6 一～日
        return getDate("yyyy-MM-dd", dateAddDays(date, -day));
    }

    /**
     * 对指定日期加上几天，返回新的日期.
     *
     * @param strdate the strdate
     * @param days    the days
     * @return the string
     * @throws ParseException the parse exception
     */
    public static String dateAddDays(String strdate, int days) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(strdate);
        date = dateAddDays(date, days);
        return sdf.format(date);
    }

    /**
     * 对指定日期加上几天，返回新的日期.
     *
     * @param date the date
     * @param days the days
     * @return Date
     */
    public static Date dateAddDays(Date date, int days) {
        return new Date(date.getTime() + days * TIME_MILLIS_OF_ONE_DAY);
    }

    // ----------------------------------------------
    public static final String TIME_MIN = " 00:00:00";
    public static final String TIME_MAX = " 23:59:59";

    /**
     * 获取当前年的最大时间。
     *
     * @return String
     */
    public static String getMaxTimeOfThisYear() {
        return getMaxDateOfThisYear() + TIME_MAX;
    }

    /**
     * 获取当前年的最大时间.
     *
     * @param strtime the strtime
     * @return the max time of this year
     */
    public static String getMaxTimeOfThisYear(String strtime) {
        return getMaxDateOfThisYear() + TIME_MAX;

    }

    /**
     * 获取当前年的最大时间.
     *
     * @param time the time
     * @return String
     */
    public static String getMaxTimeOfThisYear(Date time) {
        return getMaxDateOfThisYear(time) + TIME_MAX;
    }

    /**
     * 获取上一年的最大时间.
     *
     * @param strdate the time
     * @return String
     */
    public static String getMaxTimeOfLastYear(String strdate) {
        return getMaxDateOfLastYear(strdate) + TIME_MAX;
    }

    /**
     * 获取下一年的最大时间.
     *
     * @param strdate the time
     * @return String
     */
    public static String getMaxTimeOfNextYear(String strdate) {
        return getMaxDateOfNextYear(strdate) + TIME_MAX;
    }

    /**
     * 获取当前年的最小时间。
     *
     * @return the min time of this year
     */
    public static String getMinTimeOfThisYear() {
        return getMinDateOfThisYear() + TIME_MIN;
    }

    /**
     * 获取当前年的最小时间.
     *
     * @param strdate the strtime
     * @return String
     * @throws ParseException the parse exception
     */
    public static String getMinTimeOfThisYear(String strdate) {
        return getMinDateOfThisYear(strdate) + TIME_MIN;
    }

    /**
     * 获取当前年的最小时间.
     *
     * @param date the date
     * @return the min time of this year
     */
    public static String getMinTimeOfThisYear(Date date) {
        return getMinDateOfThisYear(date) + TIME_MIN;
    }

    /**
     * 获取当前年的最小时间.
     *
     * @param strdate the date
     * @return the min time of this year
     */
    public static String getMinTimeOfLastYear(String strdate) {
        return getMinDateOfLastYear(strdate) + TIME_MIN;
    }

    /**
     * 获取当前年的最小时间.
     *
     * @param strdate the date
     * @return the min time of this year
     */
    public static String getMinTimeOfNextYear(String strdate) {
        return getMinDateOfNextYear(strdate) + TIME_MIN;
    }

    /**
     * 获得当前月的最大时间.
     *
     * @return the max time of this month
     */
    public static String getMaxTimeOfThisMonth() {
        return getMaxDateOfThisMonth() + TIME_MAX;
    }

    /**
     * 获取上个月的最大时间.
     *
     * @param strdate the date
     * @return the max time of this month
     */
    public static String getMaxTimeOfLastMonth(String strdate) {
        return getMaxDateOfLastMonth(strdate) + TIME_MAX;
    }

    /**
     * 获取下个月的最大时间.
     *
     * @param strdate the date
     * @return the max time of this month
     */
    public static String getMaxTimeOfNextMonth(String strdate) {
        return getMaxDateOfNextMonth(strdate) + TIME_MAX;
    }

    /**
     * 获取上个月的最小时间.
     *
     * @param strdate the date
     * @return the max time of this month
     */
    public static String getMinTimeOfLastMonth(String strdate) {
        return getMinDateOfLastMonth(strdate) + TIME_MIN;
    }

    /**
     * 获取上个月的最小时间.
     *
     * @param strdate the date
     * @return the max time of this month
     */
    public static String getMinTimeOfNextMonth(String strdate) {
        return getMinDateOfNextMonth(strdate) + TIME_MIN;
    }

    /**
     * 获取当前月的最大时间.
     *
     * @param strdate the date
     * @return the max time of this month
     */
    public static String getMaxTimeOfThisMonth(String strdate) {
        return getMaxDateOfThisMonth(strdate) + TIME_MAX;
    }

    /**
     * 获取当前月的最大时间.
     *
     * @param date the date
     * @return String
     */
    public static String getMaxTimeOfThisMonth(Date date) {
        return getMaxDateOfThisMonth(date) + TIME_MAX;
    }

    /**
     * 获取当前月的最小时间。 by Henry.Yao
     *
     * @return String
     */
    public static String getMinTimeOfThisMonth() {
        return getMinDateOfThisMonth() + TIME_MIN;
    }

    /**
     * 获取当前月的最小时间。 by Henry.Yao
     *
     * @param strdate the date
     * @return String
     */
    public static String getMinTimeOfThisMonth(String strdate) {
        return getMinDateOfThisMonth(strdate) + TIME_MIN;
    }

    /**
     * 获取当前月的最小时间。 by Henry.Yao
     *
     * @param date the date
     * @return the min time of this month
     */
    public static String getMinTimeOfThisMonth(Date date) {
        return getMinDateOfThisMonth(date) + TIME_MIN;
    }

    /**
     * 获取当前周的最大时间.
     *
     * @return String
     */
    public static String getMaxTimeOfThisWeek() {
        return getMaxDateOfThisWeek() + TIME_MAX;
    }

    /**
     * 获取当前周的最大时间.
     *
     * @param date the date
     * @return Date
     */
    public static String getMaxTimeOfThisWeek(Date date) {
        return getMaxDateOfThisWeek(date) + TIME_MAX;
    }

    /**
     * 获取当前周的最大时间.
     *
     * @param date the date
     * @return the max time of this week
     */
    public static String getMaxTimeOfThisWeek(String date) {
        return getMaxDateOfThisWeek(date) + TIME_MAX;
    }

    /**
     * 获取当前周的最小时间.
     *
     * @return String
     */
    public static String getMinTimeOfThisWeek() {
        return getMinDateOfThisWeek() + TIME_MIN;
    }

    /**
     * 获取当前周的最小时间.
     *
     * @param date the date
     * @return the min time of this week
     */
    public static String getMinTimeOfThisWeek(String date) {
        return getMinDateOfThisWeek(date) + TIME_MIN;
    }

    /**
     * 获取当前周的最小时间.
     *
     * @param date the date
     * @return String
     */
    public static String getMinTimeOfThisWeek(Date date) {
        return getMinDateOfThisWeek(date) + TIME_MIN;
    }

    // --------------------------------------------------------------------------

    /**
     * 获得当前日期. 以指定的字符串格式返回当前日期。 用法: DateUtil.getNow("yyyyMMddHHmmss")
     *
     * @param pattern the pattern
     * @return the now
     * @see SimpleDateFormat "", "HH:mm", "yyyy-MM-dd",
     * "yyyy-MM-dd HH:mm", "", "HH时mm分", "yyyy年MM月dd日",
     * "yyyy年MM月dd日 HH时mm分", "yyyy-MM-dd HH:mm:ss"
     */
    public static String getNow(String pattern) {
        return getDate(pattern);
    }

    /**
     * 获得当前日期.
     *
     * @return the date
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 以指定的字符串格式返回当前日期.
     *
     * @param pattern the pattern
     * @return String
     */
    public static String getDate(String pattern) {
        return getDate(pattern, new Date());
    }

    /**
     * 以指定的字符串格式返回指定日期.
     *
     * @param pattern the pattern
     * @param date    the date
     * @return the date
     */
    public static String format(String pattern, Date date) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @param pattern
     * @param date
     * @return
     */
    public static String getDate(String pattern, Date date) {
        return format(pattern, date);
    }

    /**
     * 计算出时段文本的起止时间。 日期范围起止时间："2012年" ==> ["2012-01-01 00:00:00",
     * "2012-01-31 23:59:59"].
     *
     * @param rqfw the rqfw
     * @return the datetime range
     */
    @SuppressWarnings("deprecation")
    public static String[] getDatetimeRange(String rqfw) {
        String[] ary = new String[]{"", ""};
        if (StringUtils.matchRegexp(rqfw, "^\\d{4}年\\d{1,2}月$")) { // “2013年1月”的场景
            Date date = DateUtil.parseDateFormat(rqfw, "yyyy年M月");
            ary[0] = DateUtil.getDate("yyyy-MM-dd HH:mm:ss", date);
            date.setMonth(date.getMonth() + 1);
            date.setSeconds(date.getSeconds() - 1);
            ary[1] = DateUtil.getDate("yyyy-MM-dd HH:mm:ss", date);
        } else if (StringUtils.matchRegexp(rqfw, "^\\d{4}年$")) { // “2013年”的场景
            Date date = DateUtil.parseDateFormat(rqfw, "yyyy年");
            ary[0] = DateUtil.getDate("yyyy-MM-dd HH:mm:ss", date);
            date.setYear(date.getYear() + 1);
            date.setSeconds(date.getSeconds() - 1);
            ary[1] = DateUtil.getDate("yyyy-MM-dd HH:mm:ss", date);
        } else if (StringUtils.matchRegexp(rqfw, "^\\d{4}年第[\\u4e00-\\u9fa5]季度$")) { // “2013年第一季度”的场景
            rqfw = rqfw.replaceAll("第一季度", "1月").replaceAll("第二季度", "4月").replaceAll("第三季度", "7月").replaceAll("第四季度", "10月");
            Date date = DateUtil.parseDateFormat(rqfw, "yyyy年M月");
            ary[0] = DateUtil.getDate("yyyy-MM-dd HH:mm:ss", date);
            date.setMonth(date.getMonth() + 3);
            date.setSeconds(date.getSeconds() - 1);
            ary[1] = DateUtil.getDate("yyyy-MM-dd HH:mm:ss", date);
        }
        return ary;
    }

    /**
     * 将（33592）秒的数据，显示为可读的“5小时59分52秒”。
     *
     * @param seconds
     * @return String
     */
    public static String timeToText(long seconds) {
        long minutes = seconds / 60;
        long val1 = seconds % 60;
        long val2 = minutes % 60;
        long val3 = minutes / 60;
        String str3 = val3 == 0 ? "" : (val3 + "小时");
        String str2 = minutes == 0 ? "" : (val2 + "分");
        String str1 = val1 + "秒";
        String string = str3 + str2 + str1;
        return string;
    }

    /**
     * 解析字符串成日期的函数接口。
     *
     * @author yao
     */
    interface DateParserByPattern {

        public Date parseDateFormat(String strdate);
    }

}
