package cn.fves24.id.util.qqlogin;


/**
 * 封装后的QQ客户端
 * @author Administrator
 */
public class QQClient {
    /**
     * 登陆业务
     */
    private LoginService loginService = new LoginService();

    public QQClient(String qqNumber, String password) {
        BaseConfig.qqNum = qqNumber;
        BaseConfig.qqPwd = password;
    }

    /**
     * desc: 登陆QQ
     * @throws Exception 异常
     */
    private String loginQQ() throws Exception {
        boolean checkQQ = loginService.checkQQ();
        if (checkQQ) {
            return loginService.loginQQ();
        }
        return null;
    }

    public String getWeGameCookie() throws Exception {
        String s = loginQQ();
        TGPCookie tgpCookie = new TGPCookie();
        String cookie = tgpCookie.getCookie(BaseConfig.qqNum, BaseConfig.skey);
        return "skey=" + BaseConfig.skey + ";puin=2558807914;" + cookie;
    }
}
