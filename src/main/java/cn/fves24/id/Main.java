package cn.fves24.id;

import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: fves
 * @Date: 2018-8-8 22:09
 **/
public class Main {
    public void faka917() throws IOException {
        String url = "http://917ka.com/merchant/order/index?paytype=&status=1&date_range=&keywords=&cid=0&type=0&page=7";
        String html = Request.Get(url)
                .setHeader("Cookie", "sd9d460c7=9eje52a3dgjdtta72q7djd1ec0; safedog-flow-item=; Hm_lvt_7ba84ad88fb4dda1c8bb088939fbf651=1533737053; Hm_lpvt_7ba84ad88fb4dda1c8bb088939fbf651=1533737055; freeze_money_tip=1")
                .setHeader("User-Agent", "User-Agent:Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Mobile Safari/537.36")
                .execute()
                .returnContent()
                .asString();
        Document parse = Jsoup.parse(html);

        Elements mt20 = parse.getElementsByClass("mt20");
        for (Element element : mt20) {
            Elements tbody = element.getElementsByTag("tbody");
            System.out.println(tbody.get(0).getElementsByTag("tr").get(5).text());
        }
    }

    public static void main(String[] args) throws IOException {


    }
}
