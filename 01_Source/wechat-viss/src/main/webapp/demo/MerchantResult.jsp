<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "com.abc.pay.client.ebus.*" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-支付结果接收</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>支付结果<br>
<%
//1、取得MSG参数，并利用此参数值生成支付结果对象
String msg = request.getParameter("MSG");
msg="PE1TRz48TWVzc2FnZT48VHJ4UmVzcG9uc2U+PFJldHVybkNvZGU+MDAwMDwvUmV0dXJuQ29kZT48RXJyb3JNZXNzYWdlPr270tezybmmPC9FcnJvck1lc3NhZ2U+PEVDTWVyY2hhbnRUeXBlPkVCVVM8L0VDTWVyY2hhbnRUeXBlPjxNZXJjaGFudElEPjEwMzg4MTEwMjk5MDAzODwvTWVyY2hhbnRJRD48VHJ4VHlwZT5BQkNSZWdDYXJkUGF5PC9UcnhUeXBlPjxPcmRlck5vPjE0ODgwMDU3NDgzPC9PcmRlck5vPjxBbW91bnQ+MC4wMTwvQW1vdW50PjxCYXRjaE5vPjAwMDAwNTwvQmF0Y2hObz48Vm91Y2hlck5vPjAwMDAzMjwvVm91Y2hlck5vPjxIb3N0RGF0ZT4yMDE3LzAyLzI1PC9Ib3N0RGF0ZT48SG9zdFRpbWU+MTQ6NTc6MTE8L0hvc3RUaW1lPjxQYXlUeXBlPkVQMDAyPC9QYXlUeXBlPjxOb3RpZnlUeXBlPjE8L05vdGlmeVR5cGU+PGlSc3BSZWY+MlBFQ0VQMDExNDIxNTc0OTYwNjc8L2lSc3BSZWY+PC9UcnhSZXNwb25zZT48L01lc3NhZ2U+PFNpZ25hdHVyZS1BbGdvcml0aG0+U0hBMXdpdGhSU0E8L1NpZ25hdHVyZS1BbGdvcml0aG0+PFNpZ25hdHVyZT4vZmpmQndodFo1M29md2lMZTdFcDVVNmoxQVo0TmdGK01lWGZkcTg0QlJ2QmFGYWd3cEx5dzRqMTE1NjVJdEZUTnVLd2J1dlQyRVJoSUdvSnhnR3pXcGNiRXZKdGdHOUxsUG1vLzRwRXg3SjZRb0twMDRhd0NWdmhoMW1rQVUzN091b2hVdlpueHgycjFyekpzNHViTTZLOE9Db0tySDQwdlBwVW1sd0w0SU09PC9TaWduYXR1cmU+PC9NU0c+";
PaymentResult tResult = new PaymentResult(msg);

//2、判断支付结果状态，进行后续操作
if (tResult.isSuccess()) {
  //3、支付成功并且验签、解析成功
  out.println("TrxType         = [" + tResult.getValue("TrxType"        ) + "]<br>");
  out.println("OrderNo         = [" + tResult.getValue("OrderNo"        ) + "]<br>");
  out.println("Amount          = [" + tResult.getValue("Amount"         ) + "]<br>");
  out.println("BatchNo         = [" + tResult.getValue("BatchNo"        ) + "]<br>");
  out.println("VoucherNo       = [" + tResult.getValue("VoucherNo"      ) + "]<br>");
  out.println("HostDate        = [" + tResult.getValue("HostDate"       ) + "]<br>");
  out.println("HostTime        = [" + tResult.getValue("HostTime"       ) + "]<br>");
  out.println("MerchantRemarks = [" + tResult.getValue("MerchantRemarks") + "]<br>");
  out.println("PayType         = [" + tResult.getValue("PayType"        ) + "]<br>");
  out.println("NotifyType      = [" + tResult.getValue("NotifyType"     ) + "]<br>");
  out.println("TrnxNo          = [" + tResult.getValue("iRspRef"        ) + "]<br>");
}
else {
  //4、支付成功但是由于验签或者解析报文等操作失败
  out.println("ReturnCode   = [" + tResult.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tResult.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>