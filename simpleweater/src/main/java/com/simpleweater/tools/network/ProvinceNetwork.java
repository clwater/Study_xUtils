package com.simpleweater.tools.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by yszsyf on 16/6/12.
 */
public class ProvinceNetwork extends StringRequest {
    public ProvinceNetwork( Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super("http://182.254.210.18/QueryCPC/QueryAll.php", listener, errorListener);
    }
}
