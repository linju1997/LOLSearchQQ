package cn.fves24.id.util.qqlogin;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

public class TGPCookie {
    public String getCookie(String qq, String skey) {
        String url = "https://middle.tgp.qq.com/clientapi/auth/login_by_qq";
        String body = "{\"mappid\":\"10001\",\"clienttype\":\"1000004\",\"mcode\":\"\",\"login_info\":{\"qq_info_type\":3,\"uin\":\"" + qq + "\",\"sig\":\"" + skey + "\"}}";
        try {
            StringEntity entity = new StringEntity(body);
            HttpResponse httpResponse = Request.Post(url).body(entity).execute().returnResponse();
            Header[] headers = httpResponse.getHeaders("Set-Cookie");
            Header tgpIdCookie = headers[0];
            Header tgpTicketCookie = headers[1];
            String endStr = "domain=qq.com; path=/";
            String tgpTicketValue = tgpTicketCookie.getValue();
            String tgpIdValue = tgpIdCookie.getValue();
            String tgpId = tgpIdValue.substring(0, tgpIdValue.indexOf(endStr));
            String tgpTicket = tgpTicketValue.substring(0, tgpTicketValue.indexOf(endStr));
            return tgpId+tgpTicket;
        } catch (IOException e) {
            System.out.println("错误信息:" + e.getMessage());
            return null;
        }
    }
}
