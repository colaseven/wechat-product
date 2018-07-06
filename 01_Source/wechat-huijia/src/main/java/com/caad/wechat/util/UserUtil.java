package com.caad.wechat.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.caad.wechat.dao.BindUserInfoDao;
import com.caad.wechat.entity.UserInfo;
import com.caadt.cln.common.util.EncryptAES;
import com.caadt.cln.common.util.HttpUtil;
import com.caadt.cln.common.util.QueryListUtil;
import com.caadt.cln.common.util.StringUtils;

@Component("userUtil")
public class UserUtil {

    private static Log log = LogFactory.getLog(UserUtil.class);

    private static final String ENCRYPT_KEY = "4CB85BBA862A4234B226897A1ED92E70";

    @Autowired
    private HttpUtil httpClientTemplateToDataSso;// 账户登录确认接口
    @Autowired
    private SpringDataJpaBase springDataJpaBase;
    @Autowired
    private BindUserInfoDao bindUserInfoDao;

    /**
     * 获取openid
     */
    public String getOpenId(String loginname) {
        List<UserInfo> userInfo = queryOne(loginname, null);
        for (UserInfo list : userInfo) {
            log.info("[d01,收到]根据loginname从数据库获取openId" + list.getOpenid());
            return list.getOpenid();
        }
        return null;
    }

    /**
     * 获取VISS用户
     */
    public String getLoginName(String openId) {
        List<UserInfo> userInfo = queryOne(null, openId);
        for (UserInfo list : userInfo) {
            if (list.getOpenid().equals(openId)) {
                log.info("[d02,收到]根据openId从数据库获取loginname" + list.getLoginname());
                return list.getLoginname();
            }
        }
        return null;
    }

    /**
     * 保存数据到文件。
     */
    public boolean saveUser(String openId, String loginname) {
        log.info("[d03,收到]用户绑定的信息：" + loginname + "," + openId);
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid(openId);
        userInfo.setLoginname(formatLoginName(loginname));
        userInfo.setBindtime(new Date());
        this.bindUserInfoDao.save(userInfo);
        return true;
    }

    /**
     * 取消关联用户。
     */

    @Transactional
    public String deleteUser(String loginname) {
        this.bindUserInfoDao.deleteUser(loginname);
        return loginname;
    }

    @Transactional
    public String deleteOpenId(String openid) {
        this.bindUserInfoDao.deleteOpenid(openid);
        return openid;
    }

    /**
     * 调用登陆接口校验账户密码
     */
    public boolean checkLoginNamePswdViaSSO(String loginname, String password) {
        boolean bln = false;
        try {
            String Md5Password = MD5Util.format(password);
            String passwordEncrypted = EncryptAES.encrypt(Md5Password, ENCRYPT_KEY);
            Map<String, String> params = new HashMap<>();
            params.put("username", loginname);
            params.put("pwd", passwordEncrypted);
            String result = this.httpClientTemplateToDataSso.invoke(params);
            log.info(String.format("返回结果：“%s”。", result));
            bln = result.contains("登录成功"); // 验证成功
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bln;
    }

    /**
     * 将字符串以字节码形式按charset格式转换为新的字符串
     */
    public String fromBASE64(String str, String charset) {
        if (StringUtils.isEmptyOrNull(str)) {
            return "";
        }
        try {
            return new String(Base64.encodeBase64(str.getBytes("UTF-8")));

        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 格式化LoginName，统一成大写，因为业务系统平台不区分用户名大小写。
     */
    public String formatLoginName(String loginname) {
        return loginname.toUpperCase();
    }

    /**
     * 查询条件
     */
    private List<UserInfo> queryOne(String loginname, String openid) {
        Map<String, String> params = new HashMap<>();
        if (StringUtils.isNotEmptyOrNull(loginname)) {
            params.put("q|loginname", loginname);
        }
        if (StringUtils.isNotEmptyOrNull(openid)) {
            params.put("q|openid", openid);
        }
        Specification<UserInfo> spec = QueryListUtil.buildSpecification(params, UserInfo.class);
        return this.springDataJpaBase.findAll(UserInfo.class, spec);
    }
}
