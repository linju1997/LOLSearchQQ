package cn.fves24.id.util.qqlogin;


import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 登录业务实现类
 *
 * @author Administrator
 */
class LoginService {
    boolean checkQQ() throws Exception {
        String url = "http://check.ptlogin2.qq.com/check?pt_tea=2&uin=" + BaseConfig.qqNum + "&appid=549000929&ptlang=2052&regmaster=&pt_uistyle=9&r=0.7218746354337782&pt_jstoken=3818166328";
        String valiDateString = Request.Get(url).execute().returnContent().asString(StandardCharsets.UTF_8);
        String[] checkVCs = QQUtil.analyticalQqReturn("ptui_checkVC", valiDateString);
        if ("0".equals(checkVCs[0])) {
            BaseConfig.vCode = checkVCs[0];
            BaseConfig.verifycode = checkVCs[1];
            BaseConfig.hexQQ = checkVCs[2];
            BaseConfig.verifysession = checkVCs[3];
            return true;
        } else {
            System.out.println("需要手工验证");
            return false;
        }
    }

    String loginQQ() throws Exception {
        BaseConfig.hexQQ = QQUtil.hexchar2bin(BaseConfig.hexQQ.substring(2).replace("\\x", "").toUpperCase());
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("js/login.js");
        BaseConfig.loginP = (String) QQUtil.exeJavascript(resourceAsStream, "getQQEncryption", BaseConfig.qqPwd, BaseConfig.hexQQ, BaseConfig.verifycode, false);
        BaseConfig.loginP = BaseConfig.loginP.replaceAll("/", "-").replaceAll("\\+", "*").replaceAll("=", "_");
        String url = "https://ptlogin2.qq.com/login?pt_vcode_v1=" + BaseConfig.vCode + "&pt_verifysession_v1=" + BaseConfig.verifysession + "&verifycode=" + BaseConfig.verifycode + "&u=" + BaseConfig.qqNum + "&p=" + BaseConfig.loginP + "&pt_randsalt=2&ptlang=2052&low_login_enable=0&u1=https%3A%2F%2Fh5.qzone.qq.com%2Fmqzone%2Findex&from_ui=1&fp=loginerroralert&device=2&aid=549000929&daid=5&pt_ttype=1&pt_3rd_aid=0&ptredirect=1&h=1&g=1&pt_uistyle=9&regmaster=&";
        HttpResponse httpResponse = Request.Get(url).execute().returnResponse();
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        for (Header header : headers) {
            String cookieValue = header.getValue();
            int index = cookieValue.indexOf("=");
            String name = cookieValue.substring(0, index);
            String value = cookieValue.substring(index + 1, cookieValue.indexOf(";Path="));
            if ("skey".equals(name)) {
                BaseConfig.skey = value;
            }
        }
        return BaseConfig.skey;
    }
}
