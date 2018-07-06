package com.caad.wechat.service;

import java.util.Map;

public interface MsgProcessor {
    String process(Map<String, String> param);
}
