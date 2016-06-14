package com.simpleweater.tools.analysis;

import com.orhanobut.logger.Logger;
import com.simpleweater.tools.dbmodel.Weater_now;
import com.simpleweater.tools.model.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Vector;

/**
 * Created by yszsyf on 16/6/12.
 */
public class WeaterNowAnalysis {
    public static Weater_now getnow(String result)  {


        Weater_now weater = new Weater_now();
        JSONTokener jsonParser = new JSONTokener(result);
        JSONObject  jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.nextValue();
            JSONArray results = jsonObject.getJSONArray("results");
            JSONObject now = results.getJSONObject(1);
            weater.setCode(now.get("code").toString());
            weater.setTemperature(now.get("temperature").toString());
            Logger.d(weater.getTemperature());

        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return weater;
        }

    }
}
