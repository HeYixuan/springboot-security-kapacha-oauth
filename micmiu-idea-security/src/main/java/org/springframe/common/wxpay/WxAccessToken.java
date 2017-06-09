package org.springframe.common.wxpay;

import org.springframe.common.wxpay.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * @author: HeYixuan
 * @create: 2017-06-02 10:46
 */
public class WxAccessToken implements Serializable {

    private String accessToken;

    private int expiresIn = -1;

    public static WxAccessToken fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxAccessToken.class);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
