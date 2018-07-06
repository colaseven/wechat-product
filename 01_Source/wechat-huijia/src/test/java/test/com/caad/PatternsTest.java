package test.com.caad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 正则测试用例
 */
public class PatternsTest {

    // "^账户\\- .* .*"
    //"^账户\\s.*"

    @Test
    public void test() {
        String patterns = "^账户 .+ .+";
//		String patterns="^账户\\s{2}.*";
        String message = "账户 lilei123 123";
        Pattern pattern;
        Matcher matcher;
        boolean flg;
        pattern = Pattern.compile(patterns);
        matcher = pattern.matcher(message);
        flg = matcher.matches();
        if (!flg) {
            System.out.println("该正则无法匹配");
        } else {
            System.out.println("该正则匹配成功");
        }
    }
}
