package com.caad.wechat.utils.sdk;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;

public class DataConvert {

    /**
     *  把net.sf.JSON对象转换成Bean。
     */
    @SuppressWarnings("unchecked")
    private static <T> T convertBean(Object object, Class<T> clazz) throws Exception {
        try {
            if (object == null) {
                return null;
            }
            if (clazz == null) {
                return (T) object;
            }
            if (Object.class.equals(clazz)) {
                return (T) object;
            }
            //
            if (JSONObject.class.equals(clazz)) {
                return (T) object;
            }
            if (JSONArray.class.equals(clazz)) {
                return (T) object;
            }
            if (org.json.JSONObject.class.equals(clazz)) {
                return (T) new org.json.JSONObject(object.toString());
            }
            if (org.json.JSONArray.class.equals(clazz)) {
                return (T) new org.json.JSONArray(object.toString());
            }
            //
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(object.toString(), clazz);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 把返回net.sf.JSON中的Message对象转成MessageAPI对象。不包括Data。
     *
     */
    @SuppressWarnings("unchecked")
    static <T> ApiMessage<T> convertApiMessage(JSONObject jsonobject, Class<T> clazz) throws Exception {
        ApiMessage<T> apiMessage = convertBean(jsonobject, ApiMessage.class);
        apiMessage.setData(convertBean(jsonobject.get("Data"), clazz));
        return apiMessage;
    }

}
