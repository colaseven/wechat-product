<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "com.abc.pay.client.ebus.*" %>
<%@ page import = "com.abc.pay.client.*" %>
<%@ page import = "java.util.*" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<%
	//参数刷新
	MerchantParaFactory factoryDB = new MerchantParaFromDB();
	factoryDB.refreshConfig();

%>