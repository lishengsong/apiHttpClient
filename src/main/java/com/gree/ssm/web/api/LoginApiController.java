package com.gree.ssm.web.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gree.ssm.bean.LoginParams;
import com.gree.ssm.bean.OrderListParams;
import com.gree.ssm.utils.ClassUtil;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午3:08 2017/12/13
 * @Description: 订单相关API
 */

@Controller
@RequestMapping("/api/login")
public class LoginApiController {


    /**
     * 登录
     */
     @ResponseBody
     @RequestMapping(value = "/index", method = RequestMethod.POST)
     public JSONObject getLogin(LoginParams loginParams){

          Map<String,Object> map = ClassUtil.setConditionMap(loginParams);
          String url = "https://test.gree.com/webapp/wcs/stores/servlet/XLogon";
          String encoding = "utf-8";
          HCB hcb = null;      //重试5次
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
                    jsonObject.put("JSESSIONID",jsessionid);

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






}
