package test.com.caad;

import java.math.BigDecimal;
import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringTest {

    private static Log log = LogFactory.getLog(StringTest.class);

    @SuppressWarnings("unused")
    public static void main(String[] args) throws ParseException {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("回价失败,%s", "单据已过期"));
        log.info(sb.toString());
        log.info("********************");
        BigDecimal b = new BigDecimal("1.35");
        log.info(b);
        b = b.setScale(1, BigDecimal.ROUND_HALF_UP);
        log.info(b);
        String text = "({0})号业务回价已完成！感谢您的配合，回价奖励稍后会计入您的帐号。";
    }

}
