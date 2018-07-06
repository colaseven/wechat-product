package test.com.caad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import com.caadt.cln.common.util.DateUtil;

public class UUIDTest {

    /**
     * 随机字符串，不长于32位
     */
    @Test
    public void getUUIDTest() {
        for (int i = 0; i < 100; i++) {
            String id = UUID.randomUUID().toString().replace("-", ""); //UUIDHexGenerator.generate();
            System.out.println(id);
        }
    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String days = DateUtil.dateAddDays(format.format(new Date()), -2);
        System.out.println(days);
    }

}
