package com.caad.wechat.utils.sdk;

import java.util.Map;

/**
 * 认证提供者
 */
public interface IAuthProvider {

    /**
     * 获取需要认证的Header
     */
    Map<String, Object> GetAuthHeader() throws Exception;
}
