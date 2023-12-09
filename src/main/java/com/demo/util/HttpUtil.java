package com.demo.util;//package com.demo;

import cn.hutool.http.HttpRequest;

/**
 * @author yonglunyao
 */
public class HttpUtil {
    private static final int timeout = 10000;

    public static String postJson(String url, String reqStr) {
        String body = null;
        try {
            body = HttpRequest.post(url).body(reqStr).timeout(timeout).execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
