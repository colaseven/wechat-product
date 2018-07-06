package test.com.caad;

import com.caadt.cln.common.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caad.wechat.util.GenNewId;
import com.caad.wechat.util.UserUtil;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {

    private static Log log = LogFactory.getLog(UserTest.class);

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private GenNewId genNewID;

    /**
     * 将用户数据写入文件
     */
    @Test
    public void test() {
        userUtil.saveUser("123", "wangwu");
        userUtil.saveUser("456", "lisi");
    }

    /**
     * 根据openid获取用户登录信息
     */
    @Test
    public void test2() {
        log.info("______________" + userUtil.getLoginName("123"));
    }

    /**
     * 根据用户登录信息获取openid
     */
    @Test
    public void test3() {
        log.info("______________" + userUtil.getOpenId("chang123"));
    }

    @Test
    public void testLogin0() {
        String username = "chang123";
        String password = "chang123";
        boolean bln = userUtil.checkLoginNamePswdViaSSO(username, password);
        log.info(bln);

    }

    @Test
    public void deleteUser() {
        userUtil.deleteUser("lisi");
    }

    @Test
    public void InsertGenNewID() {
        genNewID.genNewId();
    }


    @Test
    public void testTime(){
        String deadlinetime = "2017-12-06 11:23:04";
        Date date = DateUtil.parseToDate(deadlinetime);
        log.info( DateUtil.parseToDate(deadlinetime));
        boolean before = date.before(new Date());
        log.info(before+"()()()");

    }
}
