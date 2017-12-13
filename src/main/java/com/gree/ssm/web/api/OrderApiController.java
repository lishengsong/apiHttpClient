package com.gree.ssm.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gree.ssm.bean.LoginParams;
import com.gree.ssm.bean.OrderListParams;
import com.gree.ssm.utils.ClassUtil;
import com.gree.ssm.utils.httpclientutil.HttpClientUtil;
import com.gree.ssm.utils.httpclientutil.builder.HCB;
import com.gree.ssm.utils.httpclientutil.common.HttpConfig;
import com.gree.ssm.utils.httpclientutil.common.HttpCookies;
import com.gree.ssm.utils.httpclientutil.common.HttpHeader;
import com.gree.ssm.utils.httpclientutil.common.SSLs;
import com.gree.ssm.utils.httpclientutil.exception.HttpProcessException;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.cookie.Cookie;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午8:08 2017/12/13
 * @Description: 订单相关API
 */

@Controller
@RequestMapping("/api/order")
public class OrderApiController {


    /**
     * order
     */
    @ResponseBody
    @RequestMapping("/list")
    public JSONObject getOrderList(OrderListParams orderListParams){
        Map<String,Object> map = ClassUtil.setConditionMap(orderListParams);

        System.out.println(orderListParams.toString());
        String url = "https://test.gree.com/webapp/wcs/stores/servlet/GLXMobileMyOrderListCmd";
        String encoding = "utf-8";
        HCB hcb = null        //重试5次
                ;
        try {
            hcb = HCB.custom()
                    .timeout(1000) //超时
                    .pool(100, 10) //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
                    .sslpv(SSLs.SSLProtocolVersion.TLSv1_2) 	//设置ssl版本号，默认SSLv3，也可以调用sslpv("TLSv1.2")
                    .ssl()  	  	//https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
                    .retry(5);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
        HttpClient client = hcb.build();
        String jsesionid = orderListParams.getJsessionid()!=null ? orderListParams.getJsessionid():"";
        String result2 ="";
        System.out.println(jsesionid);
        Header[] headers = HttpHeader.custom()
                .userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                .contentType("application/x-www-form-urlencoded").cookie("JSESSIONID="+jsesionid+";")
                .build();
        HttpConfig config = HttpConfig.custom().headers(headers).url(url).map(map).encoding(encoding).client(client);

        String body = null;
        JSONObject jsonObject = null;
        try {
            body = HttpClientUtil.post(config);
            System.out.println(body);
            jsonObject = JSON.parseObject(body);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }


@Test
    public void getOrder( ){
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("storeId","10151");
        map.put("catalogId","10001");
        map.put("langId","-7");
        map.put("pageSize","6");
        map.put("page","1");
        map.put("status","all");

        String url = "https://test.gree.com/webapp/wcs/stores/servlet/GLXMobileMyOrderListCmd";
        String encoding = "utf-8";
        HCB hcb = null        //重试5次
                ;
        try {
            hcb = HCB.custom()
                    .timeout(1000) //超时
                    .pool(100, 10) //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
                    .sslpv(SSLs.SSLProtocolVersion.TLSv1_2) 	//设置ssl版本号，默认SSLv3，也可以调用sslpv("TLSv1.2")
                    .ssl()  	  	//https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
                    .retry(5);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
        //HttpClient client = hcb.build();
        String jssesionid = "0000As0wdcfcv1Gp5R6yX_p30x4:1921924cn";
        String result2 ="";

        Header[] headers = HttpHeader.custom()
                .userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                .contentType("application/x-www-form-urlencoded").cookie("JSESSIONID="+jssesionid+";")
                .build();

        HttpConfig config = HttpConfig.custom().headers(headers).url(url).map(map).encoding(encoding) ;

        String body = null;
        try {
            body = HttpClientUtil.post(config);
            System.out.println(body);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }

        //return body;
    }

}
