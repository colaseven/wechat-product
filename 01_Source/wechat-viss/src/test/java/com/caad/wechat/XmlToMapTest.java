package com.caad.wechat;


import com.caad.wechat.utils.wechat.WechatPayUtil;

import java.util.Map;

public class XmlToMapTest {
    public static void main(String[] args) {
        try {
            String xmlStr = "<TrxResponse><ReturnCode>0000</ReturnCode><ErrorMessage>交易成功</ErrorMessage><ECMerchantType>EBUS</ECMerchantType><MerchantID>103884099990480</MerchantID><TrxType>RecvQRPayResult</TrxType><OrderNo>15222018744502606110314</OrderNo><Amount>0.01</Amount><BatchNo>000003</BatchNo><VoucherNo>000362</VoucherNo><PayType>EP139</PayType><NotifyType>1</NotifyType><iRspRef>3SECEP01093341684091</iRspRef></TrxResponse>\n";
            WechatPayUtil wechatPayUtil = new WechatPayUtil();
            Map<String, String> stringStringMap = wechatPayUtil.xmlToMap(xmlStr);
            System.out.print(stringStringMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
