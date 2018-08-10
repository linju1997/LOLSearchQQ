package cn.fves24.id.util.qqlogin;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;

/**
 * 工具类
 *
 * @author Administrator
 */
class QQUtil {
    /**
     * 解析验证登录时的返回信息，并封装到字符串数组里面
     *
     * @param headStr 方法头ptui_checkVC
     * @param str     需要解析的字符串       格式：ptui_checkVC('1','Sy9B6JOzRSCxoHQL9AJCwNLVxTRiS6Jk1GK3W8W-I63XrzpfBUjhMA**','\x00\x00\x00\x00\x45\xd6\xd5\x03','','0');
     * @return 返回解析后的字符串数组
     */
    static String[] analyticalQqReturn(String headStr, String str) {
        str = str.replace(headStr + "(", "");
        str = str.replace(")", "");
        str = str.replace(";", "");
        String[] strings = str.split(",");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("'", "");
        }
        return strings;
    }

    /**
     * java调用javascript代码，，获取加密后的密码
     *
     * @param inputStream   输入流
     * @param functionName 需要调用的方法名称
     * @param params       此方法需要传入的参数
     * @return 返回得到的结果
     * @throws Exception 异常
     */
    static Object exeJavascript(InputStream inputStream, String functionName, Object... params) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        /*执行指定脚本*/
        Reader reader = new InputStreamReader(inputStream);
        engine.eval(reader);
        if (engine instanceof Invocable) {
            Invocable invoke = (Invocable) engine;
            return invoke.invokeFunction(functionName, params);
        }
        return null;
    }

    /**
     * 将QQ号码的16进制，按一定格式转换，并按ISO-8895-1编码转换为成byte字节数组，然后再将byte数组转换为字符串返回
     *
     * @param hexStr QQ号码的16进制
     * @return 转换后的字符串
     * @throws UnsupportedEncodingException 不支持编码异常
     */
    static String hexchar2bin(String hexStr) throws UnsupportedEncodingException {

        String hexString = "0123456789ABCDEF";
        ByteArrayOutputStream baos = new ByteArrayOutputStream(hexStr.length() / 2);
        for (int i = 0; i < hexStr.length(); i = i + 2) {
            baos.write((hexString.indexOf(hexStr.charAt(i)) << 4 |
                    hexString.indexOf(hexStr.charAt(i + 1))));
        }
        return new String(baos.toByteArray(), "ISO-8859-1");
    }
}
