package com.caad.wechat.utils.viss;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhonePatternUtil {

    public static String phoneNumberPattern(String text) {
        text = replacePhoneNumber(text, "(?<!\\d)(?:(?:1[3587]\\d{9})|(?:861[358]\\d{9}))(?!\\d)");
        text = replacePhoneNumber(text, "(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)");
        return text;
    }

    private static String replacePhoneNumber(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            text = text.replace(matcher.group(), "<a class=\"external\" href=\"tel:" + matcher.group() + "\">" + matcher.group() + "</a>");
        }
        return text;
    }
}
