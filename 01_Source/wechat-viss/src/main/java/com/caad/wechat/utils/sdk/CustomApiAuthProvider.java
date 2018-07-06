package com.caad.wechat.utils.sdk;

import com.caad.wechat.model.viss.common.OrganizationWeChatSettingResult;
import com.caad.wechat.utils.viss.StringUtils;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CustomApiAuthProvider implements IAuthProvider {


    private static Log log = LogFactory.getLog(CustomApiAuthProvider.class);


    @Override
    public Map<String, Object> GetAuthHeader() throws Exception {
        Map<String, Object> headers = new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionAppId=(String)request.getSession().getAttribute("appId");
        log.debug("请求头中拿到了request中的appId为："+sessionAppId);
        if (StringUtils.isEmptyOrNull(request.getSession().getAttribute(sessionAppId+"_organizationInfo"))) {
            headers.put("Content-Type", "application/json;charset=UTF-8");
            headers.put("Version", "1.0");
            headers.put("DeviceId", "wechat-viss");
            headers.put("Authorization", "Basic ODRlZThhNTBlNDE1NDA5OGI2ZTlmMjc4MmVhYTkwNjU6OTY3YmJmNDAwY2E2NGQwMzgzY2E5YzI1MDNmZmFmMmM=");
        } else {
            OrganizationWeChatSettingResult organizationInfo = (OrganizationWeChatSettingResult) request.getSession().getAttribute(sessionAppId+"_organizationInfo");
            String userAccount = organizationInfo.getUserAccount();
            String token = organizationInfo.getToken();
            String authorization = userAccount + ":" + token;
            headers.put("Content-Type", "application/json;charset=UTF-8");
            headers.put("Version", "1.0");
            headers.put("DeviceId", "wechat-viss");
            headers.put("Authorization", "Basic " + new String(Base64.encodeBase64(authorization.getBytes("UTF-8"))));
        }
        log.debug("请求头信息为："+ JSONObject.fromObject(headers));
        return headers;
    }
}
