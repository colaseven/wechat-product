package com.caad.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信接入校验公共类(由微信官网提供)
 */
public class SignUtil {

    private static String token = "845C2550903CE6FA54CACDB82EAD4350";// 与接口配置信息中的Token要一致  For Product

    /**
     * 验证签名
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return boolean
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);// 将token、timestamp、nonce三个参数进行字典序排序
        StringBuilder content = new StringBuilder();
        for (String element : arr) {
            content.append(element);
        }
        MessageDigest md;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());// 将三个参数字符串拼接成一个字符串进行sha1加密
            tmpStr = byteToStr(digest);// 字节转字符串
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    private static String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (byte element : byteArray) {
            strDigest.append(byteToHexStr(element));
        }
        return strDigest.toString();
    }

    /**
     * 将字节转换为十六进制字符串
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[]{Digit[(mByte >>> 4) & 0X0F], Digit[mByte & 0X0F]};
        return new String(tempArr);
    }

}