package com.simpleweater.tools.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by yszsyf on 16/6/12.
 */
public class WeatherNowNetwork extends StringRequest {
    public WeatherNowNetwork(String city , Response.Listener<String> listener, Response.ErrorListener errorListener) throws UnsupportedEncodingException {
        super("https://api.thinkpage.cn/v3/weather/now.json?key=pqmsn1xd41zne0oy&location="+URLEncoder.encode(city, "utf-8")+ "&language=zh-Hans&unit=c", listener, errorListener);
    }
}
