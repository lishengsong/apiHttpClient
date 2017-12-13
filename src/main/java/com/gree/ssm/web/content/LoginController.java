package com.gree.ssm.web.content;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gree.ssm.utils.httpclientutil.HttpClientUtil;
import com.gree.ssm.utils.httpclientutil.builder.HCB;
import com.gree.ssm.utils.httpclientutil.common.HttpConfig;

import com.gree.ssm.utils.httpclientutil.common.HttpHeader;
import com.gree.ssm.utils.httpclientutil.common.SSLs;
import com.gree.ssm.utils.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {


    /**
     * 登录
     */
     @ResponseBody
     @RequestMapping("/post")
     public JSONObject getLogin(HttpServletRequest request){
          Map<String,Object> map = new HashMap<String ,Object>();
          map.put("storeId",request.getParameter("storeId"));
          map.put("catalogId","10001");
          map.put("langId","-7");
          map.put("reLogonURL","LogonForm");
          map.put("fromOrderId","*");
          map.put("toOrderId",".");
          map.put("deleteIfEmpty","*");
          map.put("continue","1");
          map.put("createIfEmpty","0");
          map.put("updatePrices","1");
          map.put("URL","OrderItemMove?page=&URL=OrderCalculate?URL=AjaxLogonForm&calculationUsageId=-1&calculationUsageId=-2&calculationUsageId=-7&storeId=10151&catalogId=10001");
          map.put("logonId",request.getParameter("logonId"));
          map.put("logonPassword","abcd123456");
          map.put("calculationUsageId","-1");
          map.put("logonWay","consumer");
          map.put("logonType","consumer");
          System.out.println(map.toString());
          String url = "https://test.gree.com/webapp/wcs/stores/servlet/XLogon";
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
          String result2 ="";

          Header[] headers = HttpHeader.custom()
                  .userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                  .contentType("application/x-www-form-urlencoded")
                  .build();
          HttpConfig config = HttpConfig.custom().headers(headers).url(url).map(map).encoding(encoding).client(client);
          String jsessionid = "";
          String body = "";
          JSONObject jsonObject = null;
          try {
               HttpResponse response = HttpClientUtil.postResponse(config);	//post请求
               Header[] heads = response.getHeaders("Set-Cookie");
               String value = "";
               for (Header head : heads){
                    value = head.getValue();
                    if(value.startsWith("JSESSIONID")){
                        jsessionid=value.substring(value.indexOf('=')+1, value.indexOf(';'));

                    }

               }

               System.out.println(response);
               if (response.getEntity() != null) {
                    // 按指定编码转换结果实体为String类型
                    body = EntityUtils.toString(response.getEntity(), encoding);
                    jsonObject = JSON.parseObject(body);
                    jsonObject.put("JSSESSIONID",jsessionid);

               }else{//有可能是head请求
                    body =response.getStatusLine().toString();
               }

          } catch (HttpProcessException e) {
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          }
          return jsonObject;
     }


     /**
      * order
      */
     @ResponseBody
     @RequestMapping("/order")
     public String getOrder(Map<String,String> requestMap){
          Map<String,Object> map = new HashMap<String ,Object>();
          map.put("storeId","10151");
          map.put("catalogId","10001");
          map.put("langId","-7");
          map.put("pageSize","6");
          map.put("page","1");
          map.put("status","all");

          String url = "https://test.gree.com/webapp/wcs/stores/servlet/GLXMobileMyOrderListCmdn";
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
          String jssesionid = requestMap.get("JSESSIONID")!=null ? requestMap.get("JSESSIONID"):"";
          String result2 ="";

          Header[] headers = HttpHeader.custom()
                  .userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")
                  .contentType("application/x-www-form-urlencoded").cookie(jssesionid)
                  .build();
          HttpConfig config = HttpConfig.custom().headers(headers).url(url).map(map).encoding(encoding).client(client);
          String body = null;
          try {
               body = HttpClientUtil.post(config);
          } catch (HttpProcessException e) {
               e.printStackTrace();
          }

          return body;
     }



}
