package cn.fves24.id.util;

import cn.fves24.id.util.qqlogin.QQClient;

import java.io.*;
import java.util.Objects;

public class CookieUtil {
    private static int tryTimes = 3;
    private static String fileName = "WeGameCookie.txt";
    public static String readCookie(){
        try {
            FileReader reader = new FileReader(fileName);
            char[] chars = new char[1024];
            int flag;
            StringBuilder builder = new StringBuilder();
            if ((flag=reader.read(chars))!=-1) {
                builder.append(new String(chars, 0, flag));
            }
            return builder.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            writeCookie();
            return readCookie();
        }
    }
    public static boolean writeCookie(){
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(Objects.requireNonNull(getCookie()));
            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    private static String getCookie() {
        QQClient qqClient = new QQClient("2558807914", "QIAO520...");
        try {
            String weGameCookie = qqClient.getWeGameCookie();
            tryTimes = 3;
            return weGameCookie;
        } catch (Exception e) {
            tryTimes--;
            if (tryTimes < 0) {
                return null;
            }
            return getCookie();
        }
    }
}
