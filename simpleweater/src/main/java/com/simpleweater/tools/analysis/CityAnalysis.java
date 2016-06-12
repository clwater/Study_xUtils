package com.simpleweater.tools.analysis;

import com.simpleweater.tools.model.City;
import com.simpleweater.tools.model.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

/**
 * Created by yszsyf on 16/6/12.
 */
public class CityAnalysis {
    public static Vector<City> cityanalysis(String result)  {

       // Log.d("=-=" , result);

        Vector<City> city = new Vector<City>();
        JSONTokener jsonParser = new JSONTokener(result);
        JSONObject  jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.nextValue();
            JSONArray province_json = jsonObject.getJSONArray("city");
            for (int i = 0 ; i < province_json.length() ; i++){
                City c = new City();
                JSONObject pr = province_json.getJSONObject(i);
                c.setName(pr.get("name").toString());
                //c.setId(pr.getString("province_id").toString());
                city.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return city;
        }

    }
}
