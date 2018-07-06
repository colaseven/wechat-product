package com.caad.wechat.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgProcessorByPatterns {

    private String[] patterns;
    private MsgProcessor processor;

    public MsgProcessorByPatterns(String[] pattens, MsgProcessor processor) {
        this.patterns = pattens;
        this.processor = processor;
    }

    public boolean test(String message) {
        Pattern pattern;
        Matcher matcher;
        boolean fig;
        for (String patternStr : patterns) {
            pattern = Pattern.compile(patternStr);
            matcher = pattern.matcher(message);
            fig = matcher.matches();
            if (!fig) return false;
        }
        return true;
    }

    public String process(Map<String, String> param) {
        return processor.process(param);

    }

}
