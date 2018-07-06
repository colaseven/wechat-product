package test.com.caad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caad.wechat.util.GenNewId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class GenNewIdTest {
    @Autowired
    private GenNewId genNewId;

    @Test
    public void TestNewId() {
        int id = genNewId.genNewId();
        System.out.println(id);
    }
}
