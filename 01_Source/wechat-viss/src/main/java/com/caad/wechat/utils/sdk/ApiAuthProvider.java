package com.caad.wechat.utils.sdk;

import java.util.HashMap;
import java.util.Map;

public class ApiAuthProvider implements IAuthProvider {
    @Override
    public Map<String, Object> GetAuthHeader() {
        return new HashMap<>();
    }
}
