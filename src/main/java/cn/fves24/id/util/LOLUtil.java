package cn.fves24.id.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LOLUtil {

    public static String get(String url) {
        try {
            String ret = Request.Get(url).execute().returnContent().asString();
            return handleRet(ret);
        } catch (IOException e) {
            return null;
        }
    }
    private static String handleRet(String str) {
        JSONObject jsonObject = JSONObject.parseObject(str);
        if (0 != jsonObject.getInteger("status")) {
            return null;
        }
        return jsonObject.getString("msg");
    }

    public static String encodeParam(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
