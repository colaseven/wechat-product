package com.caad.wechat;


import com.caad.wechat.utils.viss.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {


    public static void main(String[] args) {
        //
        String text = "如需快捷回价请联系我们电话：0598-88997967地址：厦门市   联系电话：15221312712  请拨打 17602121916";
        text = replacePhoneNumber(text,"(?<!\\d)(?:(?:1[3587]\\d{9})|(?:861[358]\\d{9}))(?!\\d)");
        text = replacePhoneNumber(text,"(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)");
        System.out.println(text);

//        StringBuffer bf = new StringBuffer(64);
//        while (matcher.find()) {
//            bf.append(matcher.group()).append(",");
//        }
//        int len = bf.length();
//        if (len > 0) {
//            bf.deleteCharAt(len - 1);
//        }
//        System.out.println(bf.toString());
//
//        StringBuffer bf1 = new StringBuffer(64);
//        while (matcher1.find()) {
//            bf1.append(matcher1.group()).append(",");
//        }
//        int len1 = bf1.length();
//        if (len1 > 0) {
//            bf1.deleteCharAt(len1 - 1);
//        }
//        System.out.println(bf1.toString());

    }

    private static String replacePhoneNumber(String text,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            text = text.replace(matcher.group(),"<a>" + matcher.group() + "</a>");
        }
        return  text;
    }

}
