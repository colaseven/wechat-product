package com.caad.wechat.vissapi_sdkdemo;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.caadt.vissapi.sdk.ApiClient;
import com.caadt.vissapi.sdk.ApiException;
import com.caadt.vissapi.sdk.ApiMessage;
import com.caadt.vissapi.sdk.beans.login.LoginBean;

import junit.framework.TestCase;

/**
 * weixin系统的每个登录用户的对象（包括：username, password, token）
 *
 * @author yao
 *
 */
public class WechatApiAuth0 extends TestCase {

	private static Log log = LogFactory.getLog(WechatApiDemo1.class);

	ApiClient apiclient;

	@Override
	protected void setUp() throws ApiException {
		this.apiclient = new ApiClient(Constant.HTTP_URL, Constant.USERNAME, Constant.PASSWORD);
	}

	@Test
	public void test1() throws IOException {
		ApiMessage<LoginBean> apimsg = this.apiclient.login();
		log.info(apimsg.getMessage());
		log.info(apimsg.getData().getUserModel().getName());
	}
}
