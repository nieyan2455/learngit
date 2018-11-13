package com.ny.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ny.model.AccessToken;

/**
 *
 *
 */
public class JsapiTicketUtil {
    private static Logger logger = LoggerFactory.getLogger(JsapiTicketUtil.class);
    private static String JSAPI_TICKET = null;
    private static String ACCESS_TOKEN = null;
    private static long TIMESTAMP = 0;

    public static String getJsapiTicket() throws Exception {
        if (JSAPI_TICKET == null || System.currentTimeMillis() > TIMESTAMP) {
            JSAPI_TICKET = getWXJsapiTicket();
        }
        return JSAPI_TICKET;
    }

    private static String getAccessToken() throws Exception {
        if (ACCESS_TOKEN == null || System.currentTimeMillis() > TIMESTAMP) {
            ACCESS_TOKEN = getWXAccessToken().getAccessToken();
            TIMESTAMP = System.currentTimeMillis() + 7000000;
        }
        return ACCESS_TOKEN;
    }

    /**
     * 获取access_token
     *
     * @return AccessToken
     */
    private static AccessToken getWXAccessToken() {
        NetWorkHelper netHelper = new NetWorkHelper();
        String url = String.format(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
                "wx69dcb4aae5871fe6", "1b0d2de2f4520d2a214ea6be8e39d045");
        String result = netHelper.getHttpsResponse(url, "");
        JSONObject json = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        // 判断接口调用是否成功
        String errorStr = "errcode";
        if (StringUtils.isEmpty(json.getString(errorStr))) {
            token.setAccessToken(json.getString("access_token"));
            token.setExpiresin(json.getInteger("expires_in"));
        } else {
            logger.error("WX access_token：" + result);
            token.setAccessToken(ACCESS_TOKEN);
        }
        return token;
    }

    /**
     * 获取access_token
     * 
     * @return String
     */
    private static String getWXJsapiTicket() throws Exception {
        NetWorkHelper netHelper = new NetWorkHelper();
        String url = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi",
                getAccessToken());
        String result = netHelper.getHttpsResponse(url, "");
        logger.error("WX jsapi_ticket：" + result);
        JSONObject json = JSON.parseObject(result);
        // 判断接口是否调用成功
        String errorStr = "errmsg";
        String okStr = "ok";
        if (!StringUtils.isEmpty(json.getString(errorStr)) && okStr.equals(json.getString(errorStr))) {
            return json.getString("ticket");
        } else {
            return JSAPI_TICKET;
        }
    }
}
