package test.com.caad;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caad.wechat.entity.RedPackQueryRespItem;
import com.caad.wechat.entity.RedPackSendReq;
import com.caad.wechat.entity.RedPackSendRespItem;
import com.caad.wechat.service.impl.RedPackService;
import com.caad.wechat.util.EncryptUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RedPackTest {

    private static Log log = LogFactory.getLog(RedPackTest.class);
    @Autowired
    private RedPackService redPackService;

    /**
     * 发送红包
     */
    @Test
    public void senRedPackTest() throws Exception {
        RedPackSendReq redPackSendReq = new RedPackSendReq();
        List<RedPackSendRespItem> redPack = redPackService.sendRedPack("oZaIpxNHCp-_Tbs3mA88PB6oRRn8", "1.3", redPackSendReq);
        JSONArray jsonArray = JSONArray.fromObject(redPack);
        log.info(jsonArray);
    }

    /**
     * 查询订单
     */
    @Test
    public void queryOrderTest() throws Exception {
        RedPackQueryRespItem orderQuery = redPackService.queryRedPackInfo("1356018702201606217241207718", null);
        log.info(JSONObject.fromObject(orderQuery));
        ;
    }

    @Test
    public void chaifen() {
        @SuppressWarnings("unused")
        int aa = 0;
        int total = 1500;
        while (total > 0) {
            int num = 0;
            if (total > 200) {
                // 发一次200
                num = 200;
                total -= 200;
            } else if (total > 0) {
                // 发一次total
                num = total;
                total = 0;
            }
            // 开始发，发num元红包
            aa += num;
            log.info("-------------" + num);
        }
        // log.info(aa);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void encryptUrl() {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("username", "chang123");
            String encrypt = EncryptUtil.encrypt(JSONObject.fromObject(map));
            String vissUrl = "" + URLEncoder.encode(encrypt);
            log.info(vissUrl + "------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
