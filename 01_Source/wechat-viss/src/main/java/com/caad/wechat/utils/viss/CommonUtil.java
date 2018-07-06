package com.caad.wechat.utils.viss;

import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.service.wechat.ICommonService;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.processors.PropertyNameProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component("commonUtil")
public class CommonUtil {


    @Autowired
    @Resource(name = "commonSerice")
    private ICommonService commonSerice;

    private static Log log = LogFactory.getLog(CommonUtil.class);


    /**
     * 获取微信公众号基本信息
     */
    public OrganizationWeChatSettingResult getOrganizationBySession(HttpServletRequest request, String appId) {
        OrganizationWeChatSettingResult organizationInfo = (OrganizationWeChatSettingResult) request.getSession().getAttribute(appId+"_organizationInfo");
        log.info("session中获取的公众号基本信息："+JSONObject.fromObject(organizationInfo));
        if (StringUtils.isEmptyOrNull(organizationInfo)) {
            organizationInfo = commonSerice.getOrganizationInfo(appId);
            log.info("重新请求后获取到的公众号基本信息："+ JSONObject.fromObject(organizationInfo));
            organizationInfo.setUserAccount(organizationInfo.getUserAccount());
            organizationInfo.setToken(organizationInfo.getToken());
            request.getSession().setAttribute(appId+"_organizationInfo", organizationInfo);
        }
        return organizationInfo;
    }


    /**
     * 处理JSONObject为null
     *
     * @param json json
     * @return JSONObject
     */
    public JSONObject convertFieldNameForJSONObject(JSONObject json) {
        JsonConfig beanConfig = new JsonConfig();
        beanConfig.registerJsonValueProcessor(JSONNull.class, new JsonValueProcessor() {
            @Override
            public Object processArrayValue(Object arg0, JsonConfig arg1) {
                return arg0;
            }

            @Override
            public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
                return "";
            }
        });
        JsonConfig jsonConfig = new JsonConfig();
        //将key的首字母转为小写
        PropertyNameProcessor propertyNameProcessor = new PropertyNameProcessor() {
            @Override
            public String processPropertyName(Class target, String fieldName) {
                return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
            }
        };
        Map<String, Class> map = new HashMap<>();
        return JSONObject.fromObject(json, beanConfig);
    }


    /**
     * 处理JSONArray为null
     *
     * @param data json
     * @return JSONArray
     */
    public JSONArray convertFieldNameForJSONArrray(String data) {
        JsonConfig beanConfig = new JsonConfig();
        beanConfig.registerJsonValueProcessor(JSONNull.class, new JsonValueProcessor() {
            @Override
            public Object processArrayValue(Object arg0, JsonConfig arg1) {
                return arg0;
            }

            @Override
            public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
                return "";
            }
        });
        return JSONArray.fromObject(data, beanConfig);
    }
}
