package com.caad.wechat.wechat_apidemo;

import java.util.UUID;

public class Constant {

	public static String HTTP_URL = "http://vissapi.visscaa.com";

	public static String DEVICEID = "wechat" + "-" + "HBJZ-APP0" + "-" + UUID.randomUUID();// 在正式使用时，请使用不同的值，如：HBJZ，确保每个请求系统是一个不一样的值。

	public static String USERNAME = "wechat"; // 账户//username = "shiguolong";
	public static String PASSWORD = "wechat123"; // 口令

	static {
		USERNAME = "wechat";
		PASSWORD = "wechat123";
	}
}
