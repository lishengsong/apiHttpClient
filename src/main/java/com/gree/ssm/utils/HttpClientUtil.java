package com.gree.ssm.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午9:25 2017/12/12
 * @Description:
 */




public class HttpClientUtil {
    /**
     * 模拟请求
     *
     * @param url       资源地址
     * @param map   参数列表
     * @param encoding  编码
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String send(String url, Map<String,String> map, String encoding) throws ParseException, IOException {
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        System.out.println("请求地址："+url);
        System.out.println("请求参数："+nvps.toString());

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        Header[]  headers = response.getAllHeaders();
        for(Header header : headers){
            System.out.println("name:"+header.getName()+"  value:"+header.getValue());
        }

        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

    public static void main(String args[]){
        HashMap<String,String> map = new HashMap<String ,String>();
        map.put("storeId","10151");
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
        map.put("logonId","18666109523");
        map.put("logonPassword","abcd123456");
        map.put("calculationUsageId","-1");
        map.put("logonWay","consumer");
        map.put("logonType","consumer");
        String url = "https://test.gree.com/webapp/wcs/stores/servlet/XLogon";

        String body = null;
        try {
            body = HttpClientUtil.send(url, map,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("交易响应结果：");
        System.out.println(body);

    }
}
