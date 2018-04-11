package com.lcx.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;

/**
 * Created by lcx on 2018/3/28.
 */
public class JsoupUtil {
    /**
     * 获取页面对象
     * @param uri
     * @return
     */
    private static Document getDocument(String uri) {
        Document doc = null;
        //FileWriter writer = null;
        String rui="index";
        try {
            doc = Jsoup.connect(uri).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36").timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 根据当前城市id 获取当前城市天气状况
     * @param citycode
     * @return
     */
    public static String gettoday(String citycode){
        String uri =  "http://www.weather.com.cn/weather1d/"+citycode+".shtml";
        Document doc = null;
        try {
            doc = Jsoup.connect(uri).userAgent("Chrome：Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/22.0.1229.79 Safari/535.12")
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Content-Type", "text/html;charset=UTF-8")
                    .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3").timeout(10000).get();
            Element weather =doc.selectFirst("input#hidden_title");
            Element element =doc.selectFirst("input#hidden_title");
            System.out.println(element.val());
            /* for(Element ele:elements){
                System.out.println( "123"+ele.getElementsByTag("a").val());
                ele.getElementsByTag("a").text();

            }*/


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据当前城市code获取当前城市空气质量
     * @param cityCode
     * @return
     */
    public static String getAir(String cityCode){
        // String uri =  "http://d1.weather.com.cn/aqi_all/99007.html?_="+System.currentTimeMillis();
        String uri =  "http://d1.weather.com.cn/dingzhi/"+cityCode+".html?_="+System.currentTimeMillis();

        Document doc = null;
        try {
            doc = Jsoup.connect(uri).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
                    .header("Accept", "*/*")
                    .header("Cookie", "vjuids=-16bb79005.1626a8eb0fc.0.66989ab5d6c0f; f_city=%E5%8C%97%E4%BA%AC%7C101010100%7C; returnUrl=http%3A%2F%2Fwww.weather.com.cn%2Fprofile%2Fcity.shtml; userInfo=%7B%22createTime%22%3A1522224924000%2C%22expiry%22%3A1523434525182%2C%22icon%22%3A%22%7B%5C%22imageName%5C%22%3A%5C%22e86906ff2ce84bba8b8466a907005eb2%5C%22%2C%5C%22imageSuffix%5C%22%3A%5C%22png%5C%22%2C%5C%22prefix%5C%22%3A%5C%22http%3A%2F%2Fmyspace01.weather.com.cn%5C%22%2C%5C%22result%5C%22%3A%5C%221%5C%22%7D%22%2C%22nickName%22%3A%22%D2%89%D2%89%D2%89Liszt%22%2C%22score%22%3A3%2C%22uId%22%3A%221522224924794715%22%2C%22userName%22%3A%22qquser_1522224924630648%22%7D; accessToken=OFACEQ4EBhZ0CAMBAk5ZUBJGWltRXxJDWVxcDFEQHhYMAFlPU0JTXUFARlpQcUdQW1lJUBpDDgBGGlYTOQIdBQgCA24WBAkwAkkpRANdRBldXxUAXRERTVoHIRNZAVNRREVBWF5TEERdAEJGNEtJOgIeBA0LRSAAAAAMCnxPUy9EHxwTKUxJH1AREQ4DGwt8Q1Q4AhwcEVBOR0YIH1MHCA8JEEJbEQMEBkgIG10FAB9aFgA5YV49QRkAAQZMFTJGGihKVHxWFUtJRE4eCgciQR4QRFxHoKm%2F4KHvIxsHDxpHb1ASAAQXF1EaUkJGVT0MRxpWWVxXVBJFXVVeFERMUlFURwJBSwYVCgA6FAMAYUhDEhoQARZSPl9REkZaVxRNWl1TVRBBXVROXQ%3D%3D; session_icon=e86906ff2ce84bba8b8466a907005eb2.png; Hm_lvt_080dabacb001ad3dc8b9b9049b36d43b=1522206161,1522225750,1522226498,1522287207; vjlast=1522206159.1522287210.13; Wa_lvt_1=1522206162,1522225750,1522226498,1522287210; Hm_lpvt_080dabacb001ad3dc8b9b9049b36d43b=1522287316; Wa_lpvt_1=1522287316")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Host", "d1.weather.com.cn")
                    .header("Connection", "keep-alive")
                    .header("Cache-Control", "max-age=0")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("Referer", "http://www.weather.com.cn/air/?city=101010100")
                    .header("Content-Type", "text/html;charset=UTF-8")
                    .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3").timeout(10000).get();
            String info = doc.body().text();
            int start = info.indexOf("{");
            info = info.substring(start,info.length());
            int secondStart = info.indexOf(";");
            info = info.substring(0,secondStart);
            System.out.println(info);
            JSONObject jb = JSON.parseObject(info);
            JSONObject jbw = JSON.parseObject(jb.get("weatherinfo").toString());

            System.out.println(jbw.get("cityname"));
            System.out.println(jbw.get("temp")+"/"+jbw.get("tempn"));
            System.out.println(jbw.get("weather"));
            System.out.println(jbw.get("wd"));
            System.out.println(jbw.get("ws"));

            Element weather =doc.selectFirst("input#hidden_title");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String  uri ="http://www.weather.com.cn/weather1d/101010100.shtml";
        String airurl =  "http://d1.weather.com.cn/aqi_all/99007.html?_="+System.currentTimeMillis();
        //空气质量 http://www.weather.com.cn/air/?city=101010100
        //gettoday("101010100");
        getAir("101010200");
    }
}
