package org.springframe.common.wxpay.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframe.common.wxpay.WxAccessToken;
import org.springframe.common.wxpay.exception.WxError;

/**
 * @author: HeYixuan
 * @create: 2017-06-02 11:01
 */
public class WxGsonBuilder {

    public static final GsonBuilder INSTANCE = new GsonBuilder();

    static {
        INSTANCE.disableHtmlEscaping();
        INSTANCE.registerTypeAdapter(WxAccessToken.class, new WxAccessTokenAdapter());
        INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
        //INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
        //INSTANCE.registerTypeAdapter(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());
    }

    public static Gson create() {
        return INSTANCE.create();
    }
}
