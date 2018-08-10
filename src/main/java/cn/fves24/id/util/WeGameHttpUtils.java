package cn.fves24.id.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WeGameHttpUtils {

    public static String get(String url) {
        String cookie = CookieUtil.readCookie();
        try {
            String ret = Request.Get(url).setHeader("cookie", cookie).execute().returnContent().asString();
            return handleRet(ret, url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String handleRet(String str, String url) {
        int i1 = str.indexOf("try{jQuery(");
        if (i1 == -1) {
            return null;
        }
        int i2 = str.lastIndexOf(")}catch(e){}");
        String json = str.substring(i1 + 11, i2);
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer retCode = jsonObject.getInteger("retCode");
        if (retCode == 2001) {
            CookieUtil.writeCookie();
            return get(url);
        }
        return jsonObject.getString("data");
    }

    public static String encodeParam(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
