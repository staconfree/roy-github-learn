package com.roy.github.learn.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roy on 2018/12/19.
 */
public class HttpWebService {
    private static Logger log = LoggerFactory.getLogger(HttpWebService.class);

    //配置化
    static int socketTimeout = 30000;// 请求超时时间
    static int connectTimeout = 30000;// 传输超时时间

    private String syncToWebService(String xmlString) throws Exception {
        String requestUrl = "";// 请求地址
        log.info("requestUrl:"+requestUrl+",requestXml:"+xmlString);
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(requestUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);

        Map<String, String> map =new HashMap<String, String>();
        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", "http://www.opentravel.org/OTA/2003/05/IOtaIntegrationService/SubmitInsuranceBooking");
            StringEntity data = new StringEntity(xmlString, Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.print(retStr);
                log.info(" response:" + retStr);
                return retStr;
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            log.error(" error!",e);
            return "error";
        }
        return "";
    }

}
