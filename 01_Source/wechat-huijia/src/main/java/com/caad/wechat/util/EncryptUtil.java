package com.caad.wechat.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.caadt.cln.common.util.DateUtil;
import com.caadt.cln.common.util.EncryptAES;

import net.sf.json.JSONObject;

public class EncryptUtil {
    private static Log log = LogFactory.getLog(EncryptUtil.class);

    private final static String DECRYPT_KEY = "DEB6F2B5FFF344DE922FC377742CA90D";

    /**
     * 对数据进行加密
     */
    public static String encrypt(JSONObject object) throws Exception {
        object.put("sendtime", DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
        log.debug("加密后的url数据：" + object);
        return EncryptAES.encrypt(object.toString(), DECRYPT_KEY);
    }

    /**
     * 对传递过来的数据进行解密
     */
    public static JSONObject decrypt(String str) throws Exception {
        String decryptJson = EncryptAES.decrypt(str, DECRYPT_KEY);
        log.info("[x50-60,解密]对发送红包、查询订单数据进行解密：" + decryptJson);
        JSONObject json = JSONObject.fromObject(decryptJson);
        String time = json.getString("SendTime");
        if (time == null) {
            throw new Exception();
        }
        Date timeFormat = DateUtil.parseToDate(time);
        if (System.currentTimeMillis() - timeFormat.getTime() > 20 * 60 * 1000) {
            throw new Exception();
        }
        return json;
    }
}
