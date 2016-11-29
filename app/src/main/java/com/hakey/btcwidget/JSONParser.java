package com.hakey.btcwidget;

/**
 * Created by user on 29.11.16.
 */
import org.json.*;

public class JSONParser {

    public static String getPrice(String s) throws JSONException {
        String price = "null";
        JSONObject obj = new JSONObject(s);
        JSONObject pairObj = obj.getJSONObject("btc_usd");
        price = pairObj.getString("last");

        return price;
    }

}
