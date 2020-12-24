package com.example.second.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.second.conf.KrConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * WeChatUtil
 *
 * @author hf <liamxin@yeah.net>
 * @version 0.1
 * @since 2020/9/24 9:18 上午
 */

@Slf4j
@Component
public class WeChatUtil {

    /**
     * 获取登录凭证校验
     * <a>
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     * </a>
     */
    private final static String AUTH_CODE = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";


    /**
     * 获取小程序全局唯一后台接口调用凭据
     * <a>
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/access-token/auth.getAccessToken.html
     * </a>
     */
    private final static String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /**
     * 发送订阅消息
     * <a>
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
     * </a>
     */
    private final static String SEND = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s";

    @Autowired
    private KrConf krConf;
    /**
     * 获取openid
     *
     * @param code   微信js_code
     * @return 请求结果
     * @throws IOException error
     */
    public String getOpenId(String code) throws IOException {
        String appId = krConf.getAppId();
        String secret = krConf.getSecret();
        String url = String.format(AUTH_CODE, appId, secret, code);
        JSONObject data = JSONObject.parseObject(HttpUtil.doGet(url));
        if(data.containsKey("openid")){
            return data.getString("openid");
        }
        return null;
    }


    /**
     * 获取access_token
     *
     * @param appId  小程序唯一凭证
     * @param secret 小程序唯一凭证密钥
     * @return 请求结果
     * @throws IOException error
     */
    public String getAccessToken() throws IOException {
        String appId = krConf.getAppId();
        String secret = krConf.getSecret();
        String url = String.format(ACCESS_TOKEN, appId, secret);
        return HttpUtil.doGet(url);
    }


    /**
     * 发送订阅消息
     *
     * @param accessToken      接口调用凭证
     * @param toUser           接收者（用户）的 openid
     * @param templateId       所需下发的订阅模板id
     * @param page             点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     * @param data             模板内容
     * @param miniProgramState 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
     * @param lang             进入小程序查看”的语言类型
     * @return 请求结果
     * @throws IOException error
     */
    public static String send(String accessToken, String toUser, String templateId,
                              String page, Object data, String miniProgramState,
                              String lang) throws IOException {
        String              url    = String.format(SEND, accessToken);
        Map<String, Object> params = new HashMap<>();
        params.put("touser", toUser);
        params.put("template_id", templateId);
        params.put("page", page);
        params.put("miniprogram_state", miniProgramState);
        params.put("data", data);
        params.put("lang", lang);
        return HttpUtil.doPost(url, params);
    }
}
