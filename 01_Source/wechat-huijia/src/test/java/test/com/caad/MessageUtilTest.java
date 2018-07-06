package test.com.caad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.caad.wechat.util.GenNewId;

public class MessageUtilTest {

    private static Log log = LogFactory.getLog(MessageUtilTest.class);

    @Test
    public void test() {
        GenNewId genNewId = new GenNewId();
        for (int i = 0; i < 60; i++) {
            log.info(genNewId.genNewId());
        }
    }
}
