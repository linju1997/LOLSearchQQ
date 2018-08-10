package cn.fves24.id.util.qqlogin;

import java.util.Map;

/**
 * 一些配置参数
 * @author Administrator
 */
class BaseConfig {
    /**
     * QQ账号
     */
    static String qqNum;
    /**
     * QQ密码
     */
    static String qqPwd;
    /**
     * QQ状态:(0:不需要验证码,其他需要验证码)
     */
    static String vCode;
    /**
     * 登录QQ的验证码
     */
    static String verifycode;
    /**
     * QQ账号16进制字符
     */
    static String hexQQ;
    /**
     * QQ的验证码session
     */
    static String verifysession;
    /**
     * QQ的安全加密字符
     */
    static String loginP;
    /**
     * 登陆后的Cookie
     */
    static String skey;
}

