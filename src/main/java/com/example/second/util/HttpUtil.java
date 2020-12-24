package com.example.second.util;


import com.alibaba.fastjson.JSONObject;
import com.example.second.exception.ComException;
import com.example.second.format.LangImp;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.catalina.manager.host.Constants.CHARSET;

/**
 * HttpUtil
 *
 * @author hf <liamxin@yeah.net>
 * @version 0.1
 * @since 2020/9/23 4:13 下午
 */

public class HttpUtil {

    private static final String BODY_TYPE_JSON = "application/json";


    /**
     * 发送get请求
     *
     * @param url 请求路径
     * @return 请求结果内容
     * @throws IOException error
     */
    public static String doGet(String url) throws IOException {
        return doGet(url, null);
    }

    /**
     * 发送get请求
     *
     * @param url    请求路径
     * @param params 请求参数
     * @return 请求结果内容
     * @throws IOException error
     */
    public static String doGet(String url, Map<String, Object> params) throws IOException {
        CloseableHttpClient client   = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(parserUrl(url, params)));
        if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
            // 请求错误
            throw new ComException(new LangImp(400,"网络错误"));
        }
        return EntityUtils.toString(response.getEntity());
    }


    /**
     * 发送post请求
     *
     * @param url    请求url
     * @param params 参数信息
     * @return 返回数据
     * @throws IOException error
     */
    public static String doPost(String url, Object params) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost            post   = new HttpPost(url);
        post.setEntity(new StringEntity(parserJsonQuery(params), UTF_8));
        post.setHeader("Content-Type", BODY_TYPE_JSON + ";charset=" + CHARSET);
        CloseableHttpResponse response = client.execute(post);
        if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
            // 请求错误
            throw new ComException(new LangImp(400,"网络错误"));
        }
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 解析url与参数
     *
     * @param url    请求url
     * @param params 参数
     * @return 带参数的url
     */
    private static String parserUrl(String url, Map<String, Object> params) {
        String paramsStr = parserStrQuery(params);
        return StringUtils.isNotBlank(paramsStr) ?
                url.endsWith("?") ? url + paramsStr : url + "?" + paramsStr
                : url;
    }

    /**
     * 解析请求参数为json格式
     *
     * @param params 请求参数
     * @return json参数
     */
    private static String parserJsonQuery(Object params) {
        if (params instanceof String && String.valueOf(params) != null) {
            return (String) params;
        }
        return JSONObject.toJSONString(params);
    }

    /**
     * 解析请求参数为url格式
     *
     * @param params 请求参数
     * @return url参数
     */
    private static String parserStrQuery(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        if (ObjectUtils.isNotEmpty(params)) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


}
