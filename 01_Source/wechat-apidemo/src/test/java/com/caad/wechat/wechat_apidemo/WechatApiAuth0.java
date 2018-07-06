package com.caad.wechat.wechat_apidemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.caadt.cln.common.util.FileUtil;
import com.caadt.cln.common.util.HttpClientUtil;

import net.sf.json.JSONObject;

/**
 * weixin系统的每个登录用户的对象（包括：username, password, token）
 *
 * @author yao
 *
 */
public class WechatApiAuth0 {

	private static Log log = LogFactory.getLog(WechatApiAuth0.class);

	String username = "wechat"; // 账户//username = "shiguolong";
	String password = "wechat123"; // 口令

	private String token; // 登录校验后获得的token

	/**
	 * 预先认证用户信息，获取token以供后续操作时多次使用，在header中使用到token等信息。
	 *
	 * @return
	 * @throws IOException
	 */
	public void genUserToken() throws IOException {
		this.token = getUserToken(Constant.USERNAME, Constant.PASSWORD);
	}

	/**
	 * 验证获取用户信息。
	 *
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	private String getUserToken(String username, String password) throws IOException {
		String md5 = DigestUtils.md5Hex(password);
		String base64 = new String(Base64.encodeBase64(md5.getBytes("UTF-8")));

		String url = Constant.HTTP_URL + "/api/Portal/Passport/Login";
		Map<String, String> data = new HashMap<>();
		data.put("UserName", username);
		data.put("PassWord", base64);
		String postcontent = JSONObject.fromObject(data).toString();

		// 设置HTTP请求头信息
		Map<String, String> headerparams = new HashMap<>();
		headerparams.put("connection", "Keep-Alive");
		headerparams.put("Charset", "UTF-8");
		headerparams.put("Content-Type", "application/json;charset=UTF-8");
		headerparams.put("DeviceId", "wechat-" + Constant.DEVICEID);
		headerparams.put("version", "1.0");

		String result = HttpClientUtil.callMethodResponseBodyAsString(null, null, 0, url, null, "POSTCONTENT", headerparams, postcontent);
		FileUtil.setContentString("D:/data0.json", result, "UTF-8");
		log.info("result:\t" + result + "");
		// {"ErrorCode":10013,"Data":null,"State":1,"Message":"不合法的VISS用户"}
		//

		if (result.contains("Error")) { return ""; // 登录验证失败
		}
		String tag0 = "\"AppToken\":\"";
		String tag1 = "\"";
		int pos0 = result.lastIndexOf(tag0) + tag0.length();
		int pos1 = result.indexOf(tag1, pos0);
        String token = result.substring(pos0, pos1);
		log.info(token);
		return token;
	}

	/**
	 * 设置HTTP请求头信息，以供后续接口调用使用。
	 *
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public Map<String, String> getHeaderParams() throws UnsupportedEncodingException, IOException {
		// 设置HTTP请求头信息
		Map<String, String> headerparams = new HashMap<>();
		headerparams.put("connection", "Keep-Alive");
		headerparams.put("Charset", "UTF-8");
		headerparams.put("Content-Type", "application/json;charset=UTF-8");
		headerparams.put("DeviceId", Constant.DEVICEID);
		headerparams.put("version", "1.0");
		headerparams.put("Authorization", "Basic " + new String(Base64.encodeBase64((Constant.USERNAME + ":" + this.token).getBytes("UTF-8"))));
		return headerparams;
	}
}
