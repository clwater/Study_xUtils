package com.simpleweater.tools.analysis;

import android.util.Log;

import com.simpleweater.tools.model.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

/**
 * Created by yszsyf on 16/6/12.
 */
public class ProvinceAnalysis {
    public static Vector<Province> provinceanalysis(String result)  {

       // Log.d("=-=" , result);

        Vector<Province> province = new Vector<Province>();
        JSONTokener jsonParser = new JSONTokener(result);
        JSONObject  jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.nextValue();
            JSONArray province_json = jsonObject.getJSONArray("province");
            for (int i = 0 ; i < province_json.length() ; i++){
                Province p = new Province();
                JSONObject pr = province_json.getJSONObject(i);
                p.setName(pr.get("name").toString());
                p.setProvince_id(pr.getString("province_id").toString());
                province.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return province;
        }

    }
}
