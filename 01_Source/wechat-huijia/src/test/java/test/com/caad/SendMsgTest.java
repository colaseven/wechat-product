package test.com.caad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caad.wechat.service.impl.SendMsgService;

import net.sf.json.JSONObject;

/**
 * 利用微信功用模板推送消息测试用例
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SendMsgTest {

    private static Log log = LogFactory.getLog(SendMsgTest.class);

    @Autowired
    private SendMsgService sendMsg;

    // 接口1测试用例
    @Test
    public void test() throws JSONException {
        String data = "{\n" +
                "\t\"Id\": 1114703659360000200,\n" +
                "\t\"Number\": \"K03261608002496\",\n" +
                "\t\"Province\": 10558,\n" +
                "\t\"ProvinceName\": \"台湾省\",\n" +
                "\t\"City\": 20234,\n" +
                "\t\"CityName\": \"新北市\",\n" +
                "\t\"County\": 20240,\n" +
                "\t\"CountyName\": \"汐止区\",\n" +
                "\t\"DetailAddress\": \"赏金测试211111\",\n" +
                "\t\"FloorAcreage\": 100,\n" +
                "\t\"CreateTime\": \"2016-08-05T10:58:54.396506+08:00\",\n" +
                "\t\"LoginName\": \"cschangyanke\",\n" +
                "\t\"Url\": \"http://gj.visscaa.com/Portal/Home/QuickAdvisor?id=1114703659360000200&resultType=2\",\n" +
                "\t\"CompanyId\": 614659763810000248,\n" +
                "\t\"CompanyName\": \"平安信保\",\n" +
                "\t\"LandAcreage\": 0,\n" +
                "\t\"DispProductName\": \"平安系统询价\",\n" +
                "\t\"CaseId\": \"2016080510585024bbe3a3b416b645bdafe2ff1f42282141,DY201507290000231212312333\",\n" +
                "\t\"DeadLine\": 30,\n" +
                "\t\"DeadLineTime\": \"\",\n" +
                "\t\"curid\": \"4806\",\n" +
                "\t\"AssessTotalPrice\": \"91.0\",\n" +
                "\t\"AssessSinglePrice\": \"9098\"\n" +
                "}";
        JSONObject json = JSONObject.fromObject(data);
        sendMsg.sendTemplateMessage(json, "wangwu");
    }

    // 接口3测试用例
    @Test
    public void test1() {
        // sendMsg.sendTemplateMessage("123", "oq8FEw79L2YeMF9G6pObe67Uy3dE",
        // "event", "CAAD2016","subscribe");
    }

    // 接口2测试用例
    @Test
    public void test2() {
        log.info(sendMsg.onReceiveTextMsg("100-5858", "张三"));
    }

    @Test
    public void test3() {
        // sendMsg.getUserOpenId("123");
    }

    @Test
    public void test4() {
        sendMsg.toTextMsgXml("好好好", "oZaIpxNHCp-_Tbs3mA88PB6oRRn8", "text", "CAAD2016");
    }

    @Test
    public void test5() {
        sendMsg.sendPendingMsg("1234556", "您有一条地址为123;建筑面积为123.00的物业需要估价,业务号:X01261611000014", "oZaIpxNHCp-_Tbs3mA88PB6oRRn8");
    }
}
