package com.caad.wechat.utils.sdk;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestActionInvoker implements IActionInvoker {

    private static Log log = LogFactory.getLog(RestActionInvoker.class);

    private IAuthProvider authProvider;
    private String preFix;
    private String host;

    RestActionInvoker(IAuthProvider authProvider, String preFix, String host) {
        this.authProvider = authProvider;
        this.preFix = preFix;
        this.host = host;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(Class proxyType, Method method, Object[] args) throws Exception {
        String url = getUrl(method);
        HttpMethod httpMethod = getHttpMethod(method);
        Map<String, Object> header = authProvider.GetAuthHeader();
        Class<?> returnType = method.getReturnType();
        Parameter[] parameters = method.getParameters();
        String[] parameterName = Arrays.stream(parameters).map(item -> item.getAnnotation(RequestParam.class).value()).toArray(String[]::new);
        JSONObject json;
        switch (httpMethod) {
            case GET:
                //尝试按照当前的AppToken调用
                Map<String, Object> params = new HashMap<>();
                for (int i = 0; i < parameterName.length; i++) {
                    params.put(parameterName[i], args[i]);
                }
                json = HttpUtil.getAndReturnJSONObject(url, params, header);
                if (json.getInt("State") != 0) {
                    log.error("发送GET请求时发生错误：错误url=" + url + "----错误参数=" + JSONObject.fromObject(params)+"错误信息为="+json.getString("Message"));
                    throw new Exception(json.getString("Message"));
                }
                break;
            default:
                json = HttpUtil.postAndReturnJSONObject(url, JSONObject.fromObject(args[0]), header);
                if (json.getInt("State") != 0) {
                    log.error("发送POST请求时发生错误：错误url=" + url + "----错误参数=" + JSONObject.fromObject(args[0])+"错误信息为="+json.getString("Message"));
                    throw new Exception(json.getString("Message"));
                }
                break;
        }
        return DataConvert.convertApiMessage(json, returnType).getData();
    }


    /**
     * 获取url
     * 规则为
     * {api}/{area}/{controller/service}/{action}
     */
    private String getUrl(Method method) {
        String serviceName = method.getDeclaringClass().getSimpleName();
        if (serviceName.startsWith("I"))
            serviceName = serviceName.substring(1);
        if (serviceName.endsWith("AppService"))
            serviceName = serviceName.substring(0, serviceName.length() - 10);
        if (serviceName.endsWith("Service"))
            serviceName = serviceName.substring(0, serviceName.length() - 7);

        String[] areas = method.getDeclaringClass().getPackage().getName().split("\\.");

        String area = areas[areas.length - 1];

        return String.format("%s%s/%s/%s/%s", host, preFix, area, serviceName, method.getName()).toLowerCase();
    }

    private HttpMethod getHttpMethod(Method method) {

        Class<?>[] types = method.getParameterTypes();

        if (Arrays.stream(types).allMatch(item -> TypeUtil.isSampleType(item)))
            return HttpMethod.GET;
        return HttpMethod.POST;
    }
}
