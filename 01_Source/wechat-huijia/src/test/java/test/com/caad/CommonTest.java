package test.com.caad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonTest {

    private static Log log = LogFactory.getLog(CommonTest.class);

    public static void main(String[] args) {

        String result = "{\"Data\":{\"ErrorCode\":21327,\"Error\":\"AppToken过期\"},\"State\":1,\"Message\":\"fail\"}";
        try {
            JSONObject json = new JSONObject(result);
            log.info(json.getJSONObject("Data").getString("ErrorCode") + "|" + json.getJSONObject("Data").getString("Error"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
