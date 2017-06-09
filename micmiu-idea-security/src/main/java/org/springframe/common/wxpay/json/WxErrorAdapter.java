package org.springframe.common.wxpay.json;

import com.google.gson.*;
import org.springframe.common.wxpay.exception.WxError;

import java.lang.reflect.Type;

/**
 * @author: HeYixuan
 * @create: 2017-06-02 11:06
 */
public class WxErrorAdapter implements JsonDeserializer<WxError> {

    @Override
    public WxError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        WxError wxError = new WxError();
        JsonObject wxErrorJsonObject = json.getAsJsonObject();

        if (wxErrorJsonObject.get("errcode") != null && !wxErrorJsonObject.get("errcode").isJsonNull()) {
            wxError.setErrorCode(GsonHelper.getAsPrimitiveInt(wxErrorJsonObject.get("errcode")));
        }
        if (wxErrorJsonObject.get("errmsg") != null && !wxErrorJsonObject.get("errmsg").isJsonNull()) {
            wxError.setErrorMsg(GsonHelper.getAsString(wxErrorJsonObject.get("errmsg")));
        }
        wxError.setJson(json.toString());
        return wxError;
    }
}
